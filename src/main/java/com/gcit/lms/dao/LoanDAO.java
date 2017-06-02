package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Loan;

public class LoanDAO extends BaseDAO implements ResultSetExtractor<List<Loan>>{
	
	public void addLoan(Loan loan) throws ClassNotFoundException, SQLException {
		template.update("INSERT INTO tbl_book_loans VALUES (?,?,?,NOW(), CURDATE() + INTERVAL 7 DAY, NULL)", new Object[]{loan.getBookId(), loan.getBranchId(), loan.getCardNo()});
	}
	
	public void updateLoan(Loan loan) throws ClassNotFoundException, SQLException	{
		template.update("UPDATE tbl_book_loans SET dateIn = NOW() WHERE bookId = ? AND cardNo = ? AND dateOut = ?", new Object[]{loan.getBookId(), loan.getCardNo(), loan.getDateOut()});
	}
	
	public void updatDueDate(Loan loan, Integer days) throws ClassNotFoundException, SQLException {
		template.update("UPDATE tbl_book_loans SET dueDate = dueDate + INTERVAL ? DAY WHERE bookId = ? AND cardNo = ? AND branchId = ? AND dateOut = ?", new Object[]{days, loan.getBookId(), loan.getCardNo(), loan.getBranchId(), loan.getDateOut()});
	}
	
	public List<Loan> readLoansByBorrower(Borrower borrower) throws SQLException {
		return template.query("SELECT * FROM tbl_book_loans WHERE cardNo = ?", new Object[] {borrower.getCardNo()},this);
	}
	
	public List<Loan> readLoansDueByBorrower(Borrower borrower) throws SQLException {
		return template.query("SELECT * FROM tbl_book_loans WHERE cardNo = ? AND dateIn IS NULL", new Object[] {borrower.getCardNo()}, this);
	}
	
	public List<Loan> readLoansNotDueByBorrower(Borrower borrower) throws SQLException {
		return template.query("SELECT * FROM tbl_book_loans WHERE cardNo = ? AND dateIn IS NOT NULL", new Object[] {borrower.getCardNo()}, this);
	}
	
	public List<Loan> readAllLoansDue() throws SQLException {
		return template.query("SELECT * FROM tbl_book_loans WHERE dateIn IS NULL", this);
	}
	
	public Loan returnLoanByDateOut(String dateOut) throws SQLException {
		return template.query("SELECT * FROM tbl_book_loans WHERE dateOut = ?", new Object[]{dateOut}, this).get(0);
	}
	
	public List<Loan> searchLoanByBorrowerName(String name) throws SQLException {
		name = "%"+name+"%";
		return template.query("SELECT * FROM tbl_book_loans WHERE cardNo IN (SELECT cardNo FROM tbl_borrower WHERE name LIKE ?) AND dateIn IS NULL", new Object[] {name}, this);
	}
	
	@Override
	public List extractData(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List<Loan> loans = new ArrayList<>();
		
		while(rs.next())	{
			Loan loan = new Loan();
			loan.setBookId(rs.getInt("bookId"));
			loan.setBranchId(rs.getInt("branchId"));
			loan.setCardNo(rs.getLong("cardNo"));
			loan.setDateOut(rs.getString("dateOut"));
			loan.setDueDate(rs.getString("dueDate"));
			loan.setDateIn(rs.getString("dateIn"));
			loan.setDateOut1(rs.getTimestamp("dateOut"));
			loans.add(loan);
		}
		return loans;
	}

}
