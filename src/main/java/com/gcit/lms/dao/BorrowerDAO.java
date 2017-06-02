package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Loan;

public class BorrowerDAO extends BaseDAO implements ResultSetExtractor <List<Borrower>>{
	
	public void addBorrower(Borrower borrower) throws ClassNotFoundException, SQLException	{
		template.update("INSERT INTO tbl_borrower (name, address, phone) VALUES (?, ?, ?)", new Object[] {borrower.getBorrowerName(), borrower.getBorrowerAddress(), borrower.getBorrowerPhone()});
	}
	
	public Integer addBorrowerWithId(Borrower borrower) throws ClassNotFoundException, SQLException {
		return template.update("INSERT INTO tbl_borrower (name, address, phone) VALUES (?, ?, ?)", new Object[] {borrower.getBorrowerName(),borrower.getBorrowerAddress(), borrower.getBorrowerPhone()});
	}
	
	public void updateBorrower(Borrower borrower) throws ClassNotFoundException, SQLException	{
		template.update("UPDATE tbl_borrower SET name = ?, address =?, phone = ? WHERE cardNo = ?", new Object[] {borrower.getBorrowerName(),borrower.getBorrowerAddress(), borrower.getBorrowerPhone(), borrower.getCardNo()});
	}
	
	public void deleteBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		template.update("DELETE FROM tbl_borrower WHERE cardNo = ?", new Object[]{borrower.getCardNo()});
	}
	
	public List<Borrower> returnBorrowerByPK(Long cardNo) throws SQLException {
		return template.query("SELECT * FROM tbl_borrower WHERE cardNo = ?", new Object[]{cardNo}, this);
	}
	
	public List<Borrower> returnBorrowerByName(String name) throws SQLException {
		return template.query("SELECT * FROM tbl_borrower WHERE name = ?", new Object[]{name}, this);
	}
	
	public List<Borrower> readAllBorrowers() throws SQLException {
		return template.query("SELECT * FROM tbl_borrower", this);
	}
	
	public List<Borrower> readBorrowerByCardNo(Long cardNo) throws SQLException {
		return template.query("SELECT * FROM tbl_borrower WHERE cardNo = ?", new Object[] {cardNo}, this);
	}
	
	public Borrower readBorrowerByLoan(Loan loan) throws SQLException {
		return template.query("SELECT * FROM tbl_borrower WHERE cardNo IN (SELECT cardNo FROM tbl_book_loans WHERE cardNo = ?)", new Object[] {loan.getCardNo()}, this).get(0);
	}
	
	public List<Borrower> searchBorrwerByNameWithoutPage(String name) throws SQLException {
		name = "%"+name+"%";
		return template.query("SELECT * FROM tbl_borrower WHERE name LIKE ?", new Object[] {name}, this);
	}

	@Override
	public List extractData(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List<Borrower> borrowers = new ArrayList<>();
		
		while (rs.next())	{
			Borrower b = new Borrower();
			b.setCardNo(rs.getLong("cardNo"));
			b.setBorrowerName(rs.getString("name"));
			b.setBorrowerAddress(rs.getString("address"));
			b.setBorrowerPhone(rs.getString("phone"));
			borrowers.add(b);
		}
		return borrowers;
	}
}
