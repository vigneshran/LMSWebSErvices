package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;

public class AuthorDAO extends BaseDAO implements ResultSetExtractor<List<Author>>{

	public void addAuthor(Author author) throws ClassNotFoundException, SQLException	{
		template.update("INSERT INTO tbl_author (authorName) VALUES (?)", new Object[] {author.getAuthorName()});
	}
	
	public Integer addAuthorWithId(Author author) throws ClassNotFoundException, SQLException {
		return template.update("INSERT INTO tbl_author (authorName) VALUES (?)", new Object[] {author.getAuthorName()});
	}
	
	public void updateAuthor(Author author) throws ClassNotFoundException, SQLException	{
		template.update("UPDATE tbl_author SET authorName = ? WHERE authorId = ?", new Object[] {author.getAuthorName(), author.getAuthorId()});
	}
	
	public void deleteAuthor(Author author) throws ClassNotFoundException, SQLException {
		template.update("DELETE FROM tbl_author WHERE authorId = ?", new Object[]{author.getAuthorId()});
	}
	
	public List<Author> readAllAuthors() throws SQLException {
		return template.query("SELECT * FROM tbl_author", this);
	}
	
	public List<Author> readAllAuthorsByBookId(Book book) throws SQLException {
		return template.query("SELECT * FROM tbl_author WHERE authorId IN (SELECT authorId FROM tbl_book_authors WHERE bookId = ?)", new Object[] {book.getBookId()}, this);
	}
	
	public List<Author> returnAuthorObjectByName(String name) throws SQLException {
		return template.query("SELECT * FROM tbl_author WHERE authorName = ?", new Object[] {name}, this);
	}
	
	public List<Author> returnAuthorObjectById(Integer id) throws SQLException {
		return template.query("SELECT * FROM tbl_author WHERE authorId = ?", new Object[] {id}, this);
	}
	
	public List<Author> readAllAuthorsWithPageNo(Integer pageNo) throws SQLException {
		setPageNo(pageNo);
		return template.query("SELECT * FROM tbl_author", this);
	}
	
	public List<Author> searchAuthorByName(String name, Integer pageNo) throws SQLException {
		name = "%"+name+"%";
		setPageNo(pageNo);
		return template.query("SELECT * FROM tbl_author WHERE authorName LIKE ?", new Object[] {name}, this);
	}
	
	public List<Author> searchAuthorByNameWithoutPage(String name) throws SQLException {
		name = "%"+name+"%";
		return template.query("SELECT * FROM tbl_author WHERE authorName LIKE ?", new Object[] {name}, this);
	}
	
	
	
	@Override
	public List<Author> extractData(ResultSet rs) throws SQLException {
		List<Author> authorList = new ArrayList<>();
		while(rs.next())	{
			Author a = new Author();
			a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));
			authorList.add(a);
		}
		return authorList;
	}	
	
}
