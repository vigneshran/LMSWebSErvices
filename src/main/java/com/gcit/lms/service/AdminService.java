package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.dao.CopiesDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.LoanDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Loan;
import com.gcit.lms.entity.Publisher;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/admin")
public class AdminService {

	@Autowired
	AuthorDAO adao;

	@Autowired
	CopiesDAO cdao;

	@Autowired
	BookDAO bdao;

	@Autowired
	BranchDAO branchDao;

	@Autowired
	PublisherDAO pdao;

	@Autowired
	GenreDAO gdao;

	@Autowired
	LoanDAO ldao;

	@Autowired
	BorrowerDAO borrowerDao;

	@RequestMapping(value = "/getInitAuthor", method = RequestMethod.GET, produces = "application/json")
	public Author getInitAuthor() throws SQLException {
		return new Author();
	}

	@RequestMapping(value = "/getInitBook", method = RequestMethod.GET, produces = "application/json")
	public Book getInitBook() throws SQLException {
		return new Book();
	}

	@RequestMapping(value = "/getInitBranch", method = RequestMethod.GET, produces = "application/json")
	public Branch getInitBranch() throws SQLException {
		return new Branch();
	}
	
	@RequestMapping(value = "/getInitPublisher", method = RequestMethod.GET, produces = "application/json")
	public Publisher getInitPublisher() throws SQLException {
		return new Publisher();
	}
	
	@RequestMapping(value = "/getInitBorrower", method = RequestMethod.GET, produces = "application/json")
	public Borrower getInitBorrower() throws SQLException {
		return new Borrower();
	}

	@RequestMapping(value = "/readAllBooks", method = RequestMethod.GET, produces = "application/json")
	public List<Book> readAllBooks() throws SQLException {
		try {

			List<Book> books = bdao.readAllBooks();
			String[] authors = null;
			for (Book b : books) {
				b.setAuthors(adao.readAllAuthorsByBookId(b));
				b.setPublisher(pdao.returnPublisherObjectByBookId(b));
				b.setGenres(gdao.returnGenreObjectByBookId(b));
			}
			return books;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/readAllAuthors", method = RequestMethod.GET, produces = "application/json")
	public List<Author> readAllAuthors() throws SQLException {
		try {
			return adao.readAllAuthors();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/readAllAuthorsWithPageNo/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public List<Author> readAllAuthorsWithPageNo(@PathVariable Integer pageNo) throws SQLException {
		try {
			return adao.readAllAuthorsWithPageNo(pageNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/readAllPublishers", method = RequestMethod.GET, produces = "application/json")
	public List<Publisher> readAllPublishers() throws SQLException {
		try {
			return pdao.readAllPublishers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/readAllGenres", method = RequestMethod.GET, produces = "application/json")
	public List<Genre> readAllGenres() throws SQLException {
		try {
			return gdao.readAllGenres();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/returnAuthorObjectByName/{name}", method = RequestMethod.GET, produces = "application/json")
	public List<Author> returnAuthorObjectByName(@PathVariable String name) throws SQLException {
		try {
			return adao.returnAuthorObjectByName(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/returnBookObjectByName/{name}", method = RequestMethod.GET, produces = "application/json")
	public List<Book> returnBookObjectByNAme(@PathVariable String name) throws SQLException {
		try {
			return bdao.readBookByName(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/returnGenreObjectByName/{name}", method = RequestMethod.GET, produces = "application/json")
	public List<Genre> returnGenreObjectByName(@PathVariable String name) throws SQLException {
		try {
			return gdao.returnGenreObjectByName(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/returnPublisherObjectByName/{name}", method = RequestMethod.GET, produces = "application/json")
	public List<Publisher> returnPublisherObjectByName(@PathVariable String name) throws SQLException {
		try {
			return pdao.returnPublisherObjectByName(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@RequestMapping(value = "/addBook", method = RequestMethod.POST, consumes = "application/json")
	public void addBook(@RequestBody Book book) throws SQLException {
		try {
			bdao.addBook(book);
			Book book1 = this.returnBookObjectByNAme(book.getBookName()).get(0);
			book.setBookId(book1.getBookId());

			if (book.getAuthors() != null) {
				for (Author a : book.getAuthors()) {
					a = this.returnAuthorObjectByName(a.getAuthorName()).get(0);
					bdao.addBookAuthors(book, a);
				}
			}
			if (book.getPublisher() != null) {
				Publisher p = book.getPublisher();
				Publisher P = this.returnPublisherObjectByName(p.getPublisherName()).get(0);
				bdao.addPublisher(p, book);

			}
			if (book.getGenres() != null) {
				for (Genre g : book.getGenres()) {
					g = this.returnGenreObjectByName(g.getGenreName()).get(0);
					bdao.addBookGenres(book, g);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/returnBookObjectById/{bookId}", method = RequestMethod.GET, produces = "application/json")
	public Book returnBookObjectById(@PathVariable Integer bookId) throws SQLException {
		try {
			Book book = bdao.readBookById(bookId).get(0);
			book.setAuthors(adao.readAllAuthorsByBookId(book));
			book.setPublisher(pdao.returnPublisherObjectByBookId(book));
			book.setGenres(gdao.returnGenreObjectByBookId(book));
			return book;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@RequestMapping(value = "/updateBook", method = RequestMethod.POST, consumes = "application/json")
	public void updateBook(@RequestBody Book book) throws SQLException {
		try {
			bdao.updateBook(book);
			if (book.getAuthors() != null) {
				bdao.deleteBookAuthors(book);
				for (Author a : book.getAuthors()) {
					a = this.returnAuthorObjectByName(a.getAuthorName()).get(0);
					bdao.addBookAuthors(book, a);
				}
			}
			if (book.getPublisher() != null) {
				Publisher p = book.getPublisher();
				p = this.returnPublisherObjectByName(p.getPublisherName()).get(0);
				bdao.updatePublisher(p, book);
			}
			if (book.getGenres() != null) {
				bdao.deleteBookGenres(book);
				for (Genre g : book.getGenres()) {
					g = this.returnGenreObjectByName(g.getGenreName()).get(0);
					bdao.addBookGenres(book, g);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/deleteBook", method = RequestMethod.POST, consumes = "application/json")
	public void deleteBook(@RequestBody Book book) throws SQLException {
		try {
			bdao.deleteBook(book);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/addAuthor", method = RequestMethod.POST, consumes = "application/json")
	public void addAuthor(@RequestBody Author author) throws SQLException {
		try {
			adao.addAuthor(author);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/returnAuthorObjectById/{id}", method = RequestMethod.GET, produces = "application/json")
	public Author returnAuthorObjectById(@PathVariable Integer id) throws SQLException {
		try {
			return adao.returnAuthorObjectById(id).get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@RequestMapping(value = "/updateAuthor", method = RequestMethod.POST, consumes = "application/json")
	public void updateAuthor(@RequestBody Author author) throws SQLException {
		try {
			adao.updateAuthor(author);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/deleteAuthor", method = RequestMethod.POST, consumes = "application/json")
	public void deleteAuthor(@RequestBody Author author) throws SQLException {
		try {
			adao.deleteAuthor(author);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/readAllBranches", method = RequestMethod.GET, produces = "application/json")
	public List<Branch> readAllBranches() throws SQLException {
		try {
			return branchDao.readAllBranches();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@RequestMapping(value = "/addBranch", method = RequestMethod.POST, consumes = "application/json")
	public void addBranch(@RequestBody Branch branch) throws SQLException {
		try {
			branchDao.addBranch(branch);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/updateBranch", method = RequestMethod.POST, consumes = "application/json")
	public void updateBranch(@RequestBody Branch branch) throws SQLException {
		try {
			branchDao.updateBranch(branch);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/deleteBranch", method = RequestMethod.POST, consumes = "application/json")
	public void deleteBranch(@RequestBody Branch branch) throws SQLException {
		try {
			branchDao.deleteBranch(branch);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/returnBranchObjectById/{id}", method = RequestMethod.GET, produces = "application/json")
	public Branch returnBranchObjectById(@PathVariable Integer id) throws SQLException {
		try {
			return branchDao.readBranchById(id).get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@RequestMapping(value = "/addPublisher", method = RequestMethod.POST, consumes = "application/json")
	public void addPublisher(@RequestBody Publisher publisher) throws SQLException {
		try {
			pdao.addPublisher(publisher);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/updatePublisher", method = RequestMethod.POST, consumes = "application/json")
	public void updatePublisher(@RequestBody Publisher publisher) throws SQLException {
		try {
			pdao.updatePublisher(publisher);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/deletePublisher", method = RequestMethod.POST, consumes = "application/json")
	public void deletePublisher(@RequestBody Publisher publisher) throws SQLException {
		try {
			pdao.deletePublisher(publisher);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/returnPublisherObjectById/{id}", method = RequestMethod.GET, produces = "application/json")
	public Publisher returnPublisherObjectById(@PathVariable Integer id) throws SQLException {
		try {
			return pdao.returnPublisherObjectById(id).get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/returnBorrowerByPK/{cardNo}", method = RequestMethod.GET, produces = "application/json")
	public Borrower returnBorrowerByPK(@PathVariable Long cardNo) throws SQLException {
		try {
			return borrowerDao.returnBorrowerByPK(cardNo).get(0);
		} catch (SQLException e) {
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
	
	@Transactional
	@RequestMapping(value = "/addBorrower", method = RequestMethod.POST, consumes = "application/json")
	public void addBorrower(@RequestBody Borrower borrower) throws SQLException {
		try {
			borrowerDao.addBorrower(borrower);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/updateBorrower", method = RequestMethod.POST, consumes = "application/json")
	public void updateBorrower(@RequestBody Borrower borrower) throws SQLException {
		try {
			borrowerDao.updateBorrower(borrower);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/deleteBorrower", method = RequestMethod.POST, consumes = "application/json")
	public void deleteBorrower(@RequestBody Borrower borrower) throws SQLException {
		try {
			borrowerDao.deleteBorrower(borrower);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/readAllBorrowers", method = RequestMethod.GET, produces = "application/json")
	public List<Borrower> readAllBorrowers() throws SQLException {
		try {
			return borrowerDao.readAllBorrowers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/readAllLoans", method = RequestMethod.GET, produces = "application/json")
	public List<Loan> readAllLoans() throws SQLException {
		List<Loan> loans = new ArrayList<>();

		try {
			loans = ldao.readAllLoansDue();
			for (Loan l : loans) {
				l.setBook(bdao.readBookByLoan(l));
				l.setBranch(branchDao.readBranchByLoan(l));
				l.setBorrower(borrowerDao.readBorrowerByLoan(l));

				Timestamp dueDate = getTimeStamp(l.getDueDate());
				Timestamp now = new Timestamp(System.currentTimeMillis());
				System.out.println("dueDate" + dueDate + "\n");
				System.out.println("now" + now + "\n");
				if (dueDate.before(now)) {
					l.setOverDue(true);
				}
			}
			System.out.println(loans.get(0).isOverDue());
			return loans;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/readOverDueAllLoans", method = RequestMethod.GET, produces = "application/json")
	public List<Loan> readOverDueAllLoans() throws SQLException {
		List<Loan> loans = new ArrayList<>();
		List<Loan> overDueLoans = new ArrayList<>();

		try {
			loans = ldao.readAllLoansDue();
			for (Loan l : loans) {
				l.setBook(bdao.readBookByLoan(l));
				l.setBranch(branchDao.readBranchByLoan(l));
				l.setBorrower(borrowerDao.readBorrowerByLoan(l));

				Timestamp dueDate = getTimeStamp(l.getDueDate());
				Timestamp now = new Timestamp(System.currentTimeMillis());
				System.out.println("dueDate" + dueDate + "\n");
				System.out.println("now" + now + "\n");
				if (dueDate.before(now)) {
					l.setOverDue(true);
					overDueLoans.add(l);
				}
			}
			System.out.println(loans.get(0).isOverDue());
			return overDueLoans;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/getOverDueLoans", method = RequestMethod.GET, produces = "application/json")
	public List<Loan> readAllOverDueLoans() throws SQLException {
		List<Loan> loans = new ArrayList<>();
		List<Loan> loansOverDue = new ArrayList<>();

		try {
			loans = ldao.readAllLoansDue();
			for (Loan l : loans) {
				l.setBook(bdao.readBookByLoan(l));
				l.setBranch(branchDao.readBranchByLoan(l));
				l.setBorrower(borrowerDao.readBorrowerByLoan(l));

				Timestamp dueDate = getTimeStamp(l.getDueDate());
				Timestamp now = new Timestamp(System.currentTimeMillis());
				if (dueDate.before(now)) {
					l.setOverDue(true);
					loansOverDue.add(l);
				}
			}
			return loansOverDue;
		} catch (SQLException e) {
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
	@RequestMapping(value = "/overrideDueDate/{days}", method = RequestMethod.POST, consumes = "application/json")
	public void overrideDueDate(@RequestBody Loan loan, @PathVariable Integer days) throws SQLException {
		try {
			Loan l = loan;
			String dateOut = l.getDateOut();
			Timestamp dateOutTS = getTimeStamp(dateOut);
			String dateOutStr = parseTimeStamp(dateOutTS);
			l.setDateOut(dateOutStr);
			ldao.updatDueDate(l, days);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	@RequestMapping(value = "/getLoanByDateOut/{dateOut}", method = RequestMethod.GET, produces = "application/json")
	public Loan getLoanByDateOut( @PathVariable String dateOut) throws SQLException, ParseException {
		try {
			Timestamp dateOutTS = getTimeStamp(dateOut);
			String dateOutString = parseTimeStamp(dateOutTS);
			System.out.println(dateOutString);
			Loan l = ldao.returnLoanByDateOut(dateOutString);
			l.setBook(bdao.readBookByLoan(l));
			l.setBranch(branchDao.readBranchByLoan(l));
			l.setBorrower(borrowerDao.readBorrowerByLoan(l));

			Timestamp dueDate = getTimeStamp(l.getDueDate());
			Timestamp now = new Timestamp(System.currentTimeMillis());
			System.out.println("dueDate" + dueDate + "\n");
			System.out.println("now" + now + "\n");
			if (dueDate.before(now)) {
				l.setOverDue(true);
			}
			return l;
		} catch ( SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/searchByAuthorName/{name}/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public List<Author> searchByAuthorName(@PathVariable String name, @PathVariable Integer pageNo)
			throws SQLException {
		try {
			return adao.searchAuthorByName(name, pageNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/searchByAuthorNameOnly/{name}", method = RequestMethod.GET, produces = "application/json")
	public List<Author> searchByAuthorName(@PathVariable String name) throws SQLException {
		if (name.isEmpty()) {
			try {
				return adao.readAllAuthors();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			return adao.searchAuthorByNameWithoutPage(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/searchByAuthorNameOnly/", method = RequestMethod.GET, produces = "application/json")
	public List<Author> searchByAuthorName() throws SQLException {
		try {
			return adao.readAllAuthors();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/readAllBooksWithPageNo/{pageNo}/", method = RequestMethod.GET, produces = "application/json")
	public List<Book> readAllBooksWithPageNo(@PathVariable Integer pageNo) throws SQLException {
		try {
			return bdao.readAllBooksWithPageNo(pageNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/searchByBookName/{name}/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public List<Book> searchByBookName(@PathVariable String name, @PathVariable Integer pageNo) throws SQLException {
		try {
			return bdao.searchBookByName(name, pageNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/searchByBookNameOnly/{name}", method = RequestMethod.GET, produces = "application/json")
	public List<Book> searchByBookName(@PathVariable String name) throws SQLException {
		try {
			return bdao.searchBookByNameWithoutPage(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/searchByBookNameOnly/", method = RequestMethod.GET, produces = "application/json")
	public List<Book> searchByBookName() throws SQLException {
		try {
			return bdao.readAllBooks();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/searchByBranchNameOnly/{name}", method = RequestMethod.GET, produces = "application/json")
	public List<Branch> searchByBranchName(@PathVariable String name) throws SQLException {
		try {
			return branchDao.searchBranchByNameWithoutPage(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/searchByBranchNameOnly/", method = RequestMethod.GET, produces = "application/json")
	public List<Branch> searchByBranchName() throws SQLException {
		try {
			return branchDao.readAllBranches();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/searchLoanByBorrowerNameOnly/{name}", method = RequestMethod.GET, produces = "application/json")
	public List<Loan> searchLoanByBorrowerNameOnly(@PathVariable String name) throws SQLException {
		try {
			List<Loan> loans = ldao.searchLoanByBorrowerName(name);
			for (Loan l : loans) {
				l.setBook(bdao.readBookByLoan(l));
				l.setBranch(branchDao.readBranchByLoan(l));
				l.setBorrower(borrowerDao.readBorrowerByLoan(l));

				Timestamp dueDate = getTimeStamp(l.getDueDate());
				Timestamp now = new Timestamp(System.currentTimeMillis());
				System.out.println("dueDate" + dueDate + "\n");
				System.out.println("now" + now + "\n");
				if (dueDate.before(now)) {
					l.setOverDue(true);
				}
			}
			return loans;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/searchLoanByBorrowerNameOnly/", method = RequestMethod.GET, produces = "application/json")
	public List<Loan> searchLoanByBorrowerNameOnly() throws SQLException {
		try {
			return this.readAllLoans();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/searchOverDueLoanByBorrowerNameOnly/{name}", method = RequestMethod.GET, produces = "application/json")
	public List<Loan> searchOverDueLoanByBorrowerNameOnly(@PathVariable String name) throws SQLException {
		try {
			List<Loan> loans = ldao.searchLoanByBorrowerName(name);
			List<Loan> overDueLoans = new ArrayList<>();
			for (Loan l : loans) {
				l.setBook(bdao.readBookByLoan(l));
				l.setBranch(branchDao.readBranchByLoan(l));
				l.setBorrower(borrowerDao.readBorrowerByLoan(l));

				Timestamp dueDate = getTimeStamp(l.getDueDate());
				Timestamp now = new Timestamp(System.currentTimeMillis());
				System.out.println("dueDate" + dueDate + "\n");
				System.out.println("now" + now + "\n");
				if (dueDate.before(now)) {
					l.setOverDue(true);
					overDueLoans.add(l);
				}
			}
			return overDueLoans;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/searchOverDueLoanByBorrowerNameOnly/", method = RequestMethod.GET, produces = "application/json")
	public List<Loan> searchOverDueLoanByBorrowerNameOnly() throws SQLException {
		try {
			return this.readAllOverDueLoans();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/searchByPublisherNameOnly/{name}", method = RequestMethod.GET, produces = "application/json")
	public List<Publisher> searchByPublisherNameOnly(@PathVariable String name) throws SQLException {
		try {
			return pdao.searchPublisherByNameWithoutPage(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/searchByPublisherNameOnly/", method = RequestMethod.GET, produces = "application/json")
	public List<Publisher> searchByPublisherNameOnly() throws SQLException {
		try {
			return pdao.readAllPublishers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/searchByBorrowerNameOnly/{name}", method = RequestMethod.GET, produces = "application/json")
	public List<Borrower> searchByBorrowerNameOnly(@PathVariable String name) throws SQLException {
		try {
			return borrowerDao.searchBorrwerByNameWithoutPage(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/searchByBorrowerNameOnly/", method = RequestMethod.GET, produces = "application/json")
	public List<Borrower> searchByBorrowerNameOnly() throws SQLException {
		try {
			return borrowerDao.readAllBorrowers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
