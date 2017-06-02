package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Loan;
import com.gcit.lms.entity.Publisher;

public class BookDAO extends BaseDAO implements ResultSetExtractor<List<Book>>{

	public void addBook(Book book) throws ClassNotFoundException, SQLException	{
		template.update("INSERT INTO tbl_book (title, pubId) VALUES (?, NULL)", new Object[] {book.getBookName()});
	}
	
	public Integer addBookWithId(Book book) throws ClassNotFoundException, SQLException {
		return template.update("INSERT INTO tbl_book (title) VALUES (?, NUL)", new Object[] {book.getBookName()});
	}
	
	public void updateBook(Book book) throws ClassNotFoundException, SQLException	{
		template.update("UPDATE tbl_book SET title = ? WHERE bookId = ?", new Object[] {book.getBookName(), book.getBookId()});
	}
	
	public void deleteBook(Book book) throws ClassNotFoundException, SQLException {
		template.update("DELETE FROM tbl_book WHERE bookId = ?", new Object[]{book.getBookId()});
	}
	
	public List<Book> readAllBooks() throws SQLException {
		return template.query("SELECT * FROM tbl_book", this);
	}
	
	public List<Book> readBookByName(String title) throws SQLException {
		return template.query("SELECT * FROM tbl_book WHERE title = ?", new Object[] {title}, this);
	}

	public void addPublisher(Publisher publisher, Book book) throws ClassNotFoundException, SQLException {
		template.update("UPDATE tbl_book SET pubId = ? WHERE bookId = ?", new Object[] {publisher.getPublisherId(), book.getBookId()});
	}
	
	public void addBookAuthors(Book book, Author author) throws ClassNotFoundException, SQLException {
		template.update("INSERT INTO tbl_book_authors VALUES (?,?)", new Object[] {book.getBookId(), author.getAuthorId()});
	}
	
	public void addBookGenres(Book book, Genre genre) throws ClassNotFoundException, SQLException {
		template.update("INSERT INTO tbl_book_genres VALUES (?,?)", new Object[] {genre.getGenreId(), book.getBookId()});
	}
	
	public List<Book> readBookById(Integer id) throws SQLException {
		return template.query("SELECT * FROM tbl_book WHERE bookId = ?", new Object[] {id}, this);
	}
	
	public Book readBookByLoan(Loan loan) throws SQLException {
		return template.query("SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_loans WHERE bookId = ?)", new Object[] {loan.getBookId()}, this).get(0);
	}
	
	public void updatePublisher(Publisher publisher, Book book) throws ClassNotFoundException, SQLException {
		template.update("UPDATE tbl_book SET pubId = ? WHERE bookId = ?", new Object[] {publisher.getPublisherId(), book.getBookId()});
	}
	
	public void deleteBookAuthors(Book book) throws ClassNotFoundException, SQLException {
		template.update("DELETE FROM tbl_book_authors WHERE bookId = ?", new Object[] {book.getBookId()});
	}
	
	public void deleteBookGenres(Book book) throws ClassNotFoundException, SQLException {
		template.update("DELETE FROM tbl_book_genres WHERE bookId = ?", new Object[] {book.getBookId()});
	}
	
	public List<Book> readAllBooksWithPageNo(Integer pageNo) throws SQLException {
		Integer start = (pageNo-1)*5;
		return template.query("SELECT * FROM tbl_book LIMIT ?, 5", new Object[] {start},this);
	}
	
	public List<Book> readAllBooksWithBranch(Integer branchId) throws SQLException {
		return template.query("SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_copies WHERE branchId = ? AND noOfCopies > 0)", new Object[] {branchId},this);
	}
	
	public List<Book> searchBookByName(String name, Integer pageNo) throws SQLException {
		name = "%"+name+"%";
		setPageNo(pageNo);
		return template.query("SELECT * FROM tbl_book WHERE title LIKE ?", new Object[] {name}, this);
	}
	
	public List<Book> searchBookByNameWithoutPage(String name) throws SQLException {
		name = "%"+name+"%";
		return template.query("SELECT * FROM tbl_book WHERE title LIKE ?", new Object[] {name}, this);
	}
	
	@Override
	public List extractData(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List<Book> books = new ArrayList<>();
		while(rs.next())	{
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setBookName(rs.getString("title"));
			books.add(b);
		}
		return books;
	}
}
