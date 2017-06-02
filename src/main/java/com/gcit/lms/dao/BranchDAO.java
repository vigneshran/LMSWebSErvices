package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Copies;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Loan;

public class BranchDAO extends BaseDAO implements ResultSetExtractor<List<Branch>> {
	
	public List<Branch> readAllBranches() throws SQLException {
		return template.query("SELECT * FROM tbl_library_branch", this);
	}
	
	public void addBranch(Branch branch) throws ClassNotFoundException, SQLException	{
		template.update("INSERT INTO tbl_library_branch (branchName, branchAddress) VALUES (?,?)", new Object[] {branch.getBranchName(), branch.getBranchAddress()});
	}
	
	public Integer addBranchWithId(Branch branch) throws ClassNotFoundException, SQLException {
		return template.update("INSERT INTO tbl_library_branch (BranchName, branchAddress) VALUES (?,?)", new Object[] {branch.getBranchName(), branch.getBranchAddress()});
	}
	
	public void updateBranch(Branch branch) throws ClassNotFoundException, SQLException	{
		template.update("UPDATE tbl_library_branch SET branchName = ?, branchAddress = ? WHERE BranchId = ?", new Object[] {branch.getBranchName(), branch.getBranchAddress(), branch.getBranchId()});
	}
	
	public void deleteBranch(Branch branch) throws ClassNotFoundException, SQLException {
		template.update("DELETE FROM tbl_library_branch WHERE branchId = ?", new Object[]{branch.getBranchId()});
	}
	
	public void addBooksToBranch(Integer bookId, Branch branch, Integer no) throws ClassNotFoundException, SQLException	{
		template.update("INSERT INTO tbl_book_copies VALUES (?,?,?)", new Object[]{bookId, branch.getBranchId(), no});
	}
	
	public void updateCopies(Integer bookId, Branch branch, Integer no) throws ClassNotFoundException, SQLException	{
		template.update("UPDATE tbl_book_copies SET noOfCopies = ? WHERE branchId = ? AND bookId = ?", new Object[] {no, branch.getBranchId(), bookId});
	}
	
	public List<Branch> readBranchByName (String branchName) throws SQLException	{
		return template.query("SELECT * FROM tbl_library_branch WHERE branchName = ?", new Object[] {branchName}, this);
	}
	
	public List<Branch> readBranchById (Integer branchId) throws SQLException	{
		return template.query("SELECT * FROM tbl_library_branch WHERE branchId = ?", new Object[] {branchId}, this);
	}
	
	public List<Branch> searchBranchByNameWithoutPage(String name) throws SQLException {
		name = "%"+name+"%";
		return template.query("SELECT * FROM tbl_library_branch WHERE branchName LIKE ?", new Object[] {name}, this);
	}
	
	public Branch readBranchByLoan(Loan loan) throws SQLException {
		return template.query("SELECT * FROM tbl_library_branch WHERE branchId IN (SELECT branchId FROM tbl_book_loans WHERE branchId = ?)", new Object[] {loan.getBranchId()}, this).get(0);
	}
	
	@Override
	public List extractData(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List<Branch> branches = new ArrayList<>();
		while (rs.next())	{
			Branch b = new Branch();
			b.setBranchId(rs.getInt("branchId"));
			b.setBranchAddress(rs.getString("branchAddress"));
			b.setBranchName(rs.getString("branchName"));
			branches.add(b);
		}
		
 		return branches;
	}	
}
