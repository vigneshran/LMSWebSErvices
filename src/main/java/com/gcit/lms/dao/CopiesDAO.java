package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.entity.Copies;

public class CopiesDAO extends BaseDAO implements ResultSetExtractor<List<Copies>> {
	
	public void addCopies(Copies copies) throws ClassNotFoundException, SQLException	{
		template.update("INSERT INTO tbl_book_copies VALUES (?,?,?)", new Object[] {copies.getBookId(), copies.getBranchId(), copies.getNoOfCOpies()});
	}
	
	public void updateCopies(Copies copies) throws ClassNotFoundException, SQLException {
		template.update("UPDATE tbl_book_copies SET noOfCopies = ? WHERE branchId = ? AND bookId = ?", new Object[] {copies.getNoOfCOpies(), copies.getBranchId(), copies.getBookId()});
	}
	
	public void decrementCopies(Copies copies) throws ClassNotFoundException, SQLException {
		template.update("UPDATE tbl_book_copies SET noOfCopies = noOfCopies - 1 WHERE branchId = ? AND bookId = ?", new Object[] {copies.getBranchId(), copies.getBookId()});
	}
	
	public void incrementCopies(Copies copies) throws ClassNotFoundException, SQLException {
		template.update("UPDATE tbl_book_copies SET noOfCopies = noOfCopies + 1 WHERE branchId = ? AND bookId = ?", new Object[] {copies.getBranchId(), copies.getBookId()});
	}
	
	public void deleteCopies(Copies copies) throws ClassNotFoundException, SQLException {
		template.update("DELETE * FROM tbl_book_copies WHERE bookId = ? AND branchId = ?", new Object[] {copies.getBookId(), copies.getBranchId()});
	}
	
	public List<Copies> readCopiesByBranchId(Integer branchId) throws SQLException {
		return template.query("SELECT * FROM tbl_book_copies WHERE branchId = ?", new Object[] {branchId}, this);
	}
	
	public List<Copies> readCopyByBranchIdAndBookId(Integer branchId, Integer bookId) throws SQLException {
		return template.query("SELECT * FROM tbl_book_copies WHERE branchId = ? AND bookId = ?", new Object[] {branchId, bookId}, this);
	}
	
	public boolean doesBookExist(Integer branchId, Integer bookId) throws SQLException {
		List<Copies> copiesInBranch = template.query("SELECT * FROM tbl_book_copies WHERE branchId = ? AND bookId = ?", new Object[] {branchId, bookId}, this);
		if (copiesInBranch.isEmpty()) return false;
		return true;
	}

	@Override
	public List extractData(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List<Copies> copies = new ArrayList<>();
		while(rs.next())	{
			Copies copy = new Copies();
			copy.setBookId(rs.getInt("bookId"));
			copy.setBranchId(rs.getInt("branchId"));
			copy.setNoOfCOpies(rs.getInt("noOfCopies"));
			copies.add(copy);
		}
		return copies;
	}
	
}
