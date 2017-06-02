package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.dao.*;
import com.gcit.lms.entity.*;

@RestController
@RequestMapping(value = "/borrower")
public class AdminBorrower {
	@Autowired
	AuthorDAO adao;

	@Autowired
	CopiesDAO cdao;

	@Autowired
	BookDAO bdao;

	@Autowired
	BranchDAO branchDao;

	@Autowired
	BorrowerDAO borrowerDao;

	@Autowired
	LoanDAO ldao;

	@RequestMapping(value = "/getInitLoan/{cardNo}", method = RequestMethod.GET, produces = "application/json")
	public Loan getInitAuthor(@PathVariable Long cardNo) throws SQLException {

		Loan loan = new Loan();
		loan.setBorrower(this.returnBorrowerByCardNo(cardNo));
		return loan;
	}

	@RequestMapping(value = "/returnBorrowerByCardNo/{cardNo}", method = RequestMethod.GET, produces = "application/json")
	public Borrower returnBorrowerByCardNo(@PathVariable Long cardNo) throws SQLException {
		List<Borrower> borrowers = new ArrayList<>();
		try {
			borrowers = borrowerDao.returnBorrowerByPK(cardNo);
			return borrowers.get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/returnBorrowerByName/{name}", method = RequestMethod.GET, produces = "application/json")
	public Borrower returnBorrowerByName(@PathVariable String name) throws SQLException {
		List<Borrower> borrowers = new ArrayList<>();
		try {
			borrowers = borrowerDao.returnBorrowerByName(name);
			return borrowers.get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/returnBorrowerByCardNoBasedOnLoans/{cardNo}", method = RequestMethod.GET, produces = "application/json")
	public Borrower returnBorrowerByCardNoByLoan(@PathVariable Long cardNo) throws SQLException {
		List<Borrower> borrowers = new ArrayList<>();
		try {
			borrowers = borrowerDao.returnBorrowerByPK(cardNo);
			Borrower borrower = borrowers.get(0);
			List<Loan> loansDue = ldao.readLoansDueByBorrower(borrower);
			List<Loan> loansOverDue = new ArrayList<>();
			List<Loan> loansNotOverDue = new ArrayList<>();
			for (Loan l : loansDue) {
				Timestamp dueDate = getTimeStamp(l.getDueDate());
				Timestamp now = new Timestamp(System.currentTimeMillis());
				if (dueDate.before(now)) {
					loansOverDue.add(l);
					l.setOverDue(true);
				}
				l.setBook(bdao.readBookByLoan(l));
				l.setBranch(branchDao.readBranchByLoan(l));
			}
			borrower.setUnreturnedLoans(loansDue);
			System.out.println(loansOverDue.size());
			borrower.setLoansOverDue(loansOverDue);
			loansNotOverDue = ldao.readLoansNotDueByBorrower(borrower);
			for (Loan l : loansNotOverDue) {
				Timestamp dueDate = getTimeStamp(l.getDueDate());
				Timestamp now = new Timestamp(System.currentTimeMillis());
				if (dueDate.before(now)) {
					l.setOverDue(true);
				}
				l.setBook(bdao.readBookByLoan(l));
				l.setBranch(branchDao.readBranchByLoan(l));
			}
			borrower.setLoansNotOverDue(loansNotOverDue);
			return borrower;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private String parseTimeStamp(Timestamp later) {
		Date date = new Date();
		date.setTime(later.getTime());
		String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(date);
		return formattedDate;
	}

	private Timestamp getTimeStamp(String dateOut) {
		Timestamp timestamp = null;
		try {
			dateOut += ".0";
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
			Date parsedDate = dateFormat.parse(dateOut);
			timestamp = new java.sql.Timestamp(parsedDate.getTime());
		} catch (Exception e) {// this generic but you can control another types
								// of exception
			e.printStackTrace();
		}

		long retryDate = System.currentTimeMillis();

		int hours = 4;

		Timestamp original = new Timestamp(retryDate);
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timestamp.getTime());
		cal.add(Calendar.HOUR, hours);
		Timestamp later = new Timestamp(cal.getTime().getTime());
		return later;
	}

	@Transactional
	@RequestMapping(value = "/incrementCopies/", method = RequestMethod.POST, consumes = "application/json")
	public void incrementCopies(@RequestBody Copies copy) throws SQLException {
		try {
			cdao.incrementCopies(copy);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/decrementCopies/", method = RequestMethod.POST, consumes = "application/json")
	public void decrementCopies(@RequestBody Copies copy) throws SQLException {

		try {
			cdao.decrementCopies(copy);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/returnBookObjectByName/{title}", method = RequestMethod.GET, produces = "application/json")
	public List<Book> returnBookObjectByName(@PathVariable String title) throws SQLException {
		List<Book> bookByName = new ArrayList<>();
		try {
			bookByName = bdao.readBookByName(title);
			return bookByName;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@RequestMapping(value = "/updateLoan", method = RequestMethod.POST, consumes = "application/json")
	public void updateLoan(@RequestBody Loan loan) throws SQLException {
		try {
			Timestamp dateOutTs = getTimeStamp(loan.getDateOut());
			String dateOutStr = parseTimeStamp(dateOutTs);
			loan.setDateOut(dateOutStr);
			ldao.updateLoan(loan);
			Copies copy = new Copies();
			copy.setBookId(loan.getBookId());
			copy.setBranchId(loan.getBranchId());
			cdao.incrementCopies(copy);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/addLoan", method = RequestMethod.POST, consumes = "application/json")
	public void addLoan(@RequestBody Loan loan) throws SQLException {

		try {
			loan.setBookId(loan.getBook().getBookId());
			loan.setBranchId(loan.getBranch().getBranchId());
			loan.setCardNo(loan.getBorrower().getCardNo());
			ldao.addLoan(loan);
			Copies copy = new Copies();
			copy.setBookId(loan.getBookId());
			copy.setBranchId(loan.getBranchId());
			cdao.decrementCopies(copy);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/returnLoansDueByBorrower/{cardNo}", method = RequestMethod.GET, produces = "application/json")
	public List<Loan> returnLoansDueByBorrower(@PathVariable Long cardNo) throws SQLException {
		List<Loan> loans = new ArrayList<>();

		try {
			Borrower borrower = new Borrower();
			borrower.setCardNo(cardNo);
			loans = ldao.readLoansDueByBorrower(borrower);
			return loans;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/returnBooksByBranch/{branchId}", method = RequestMethod.GET, produces = "application/json")
	public List returnBooksByBranch(@PathVariable Integer branchId) throws SQLException {
		List<Book> books = new ArrayList<>();

		try {
			List<Branch> branch = branchDao.readBranchById(branchId);
			for (Branch b : branch) {
				b.setBooks(bdao.readAllBooksWithBranch(b.getBranchId()));
			}
			books = branch.get(0).getBooks();
			return books;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/doesNumberExist/{cardNo}", method = RequestMethod.GET, produces = "application/json")
	public boolean readAllBorrowers(@PathVariable Long cardNo) throws SQLException {
		try {
			boolean exists = false;
			List<Borrower> borrowers = borrowerDao.readAllBorrowers();
			outerloop: for (Borrower b : borrowers) {
				if ((long) b.getCardNo() == (long) cardNo) {
					exists = true;
					break outerloop;
				}
			}
			return exists;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@RequestMapping(value = "/readAllBranches", method = RequestMethod.GET, produces = "application/json")
	public List<Branch> readAllBranches() {
		try {
			List<Branch> branches = branchDao.readAllBranches();
			for (Branch b : branches) {
				b.setBooks(this.returnBooksByBranch(b.getBranchId()));
			}
			return branches;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
