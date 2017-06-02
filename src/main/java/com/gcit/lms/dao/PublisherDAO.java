package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Publisher;

public class PublisherDAO extends BaseDAO implements ResultSetExtractor<List<Publisher>>{

	public void addPublisher(Publisher publisher) throws ClassNotFoundException, SQLException	{
		template.update("INSERT INTO tbl_publisher (publisherName, publisherAddress, publisherPhone) VALUES (?,?,?)", new Object[] {publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone()});
	}
	
	public void updatePublisher(Publisher publisher) throws ClassNotFoundException, SQLException	{
		template.update("UPDATE tbl_publisher SET publisherName = ?, publisherAddress = ?, publisherPhone = ? WHERE publisherId = ?", new Object[] {publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone(), publisher.getPublisherId()});
	}
	
	public void deletePublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
		template.update("DELETE FROM tbl_publisher WHERE publisherId = ?", new Object[]{publisher.getPublisherId()});
	}

	public List<Publisher> readAllPublishers() throws SQLException {
		return template.query("SELECT * FROM tbl_publisher", this);
	}
	
	public List<Publisher> returnPublisherObjectByName(String name) throws SQLException {
		return template.query("SELECT * FROM tbl_publisher WHERE publisherName = ?", new Object[] {name}, this);
	}
	
	public List<Publisher> returnPublisherObjectById(Integer id) throws SQLException {
		return template.query("SELECT * FROM tbl_publisher WHERE publisherId = ?", new Object[] {id}, this);
	}
	
	public Publisher returnPublisherObjectByBookId(Book book) throws SQLException {
		List<Publisher> publishers = template.query("SELECT * FROM tbl_publisher WHERE publisherId IN (SELECT pubId FROM tbl_book WHERE bookId = ?)", new Object[] {book.getBookId()}, this);
		if (!publishers.isEmpty()) {
			return publishers.get(0);
		}
		return null;
	}
	
	public List<Publisher> searchPublisherByNameWithoutPage(String name) throws SQLException {
		name = "%"+name+"%";
		return template.query("SELECT * FROM tbl_publisher WHERE publisherName LIKE ?", new Object[] {name}, this);
	}

	@Override
	public List extractData(ResultSet rs) throws SQLException {
		List<Publisher> publishers = new ArrayList<>();
		while (rs.next())	{
			Publisher p = new Publisher();
			p.setPublisherId(rs.getInt("publisherId"));
			p.setPublisherName(rs.getString("publisherName"));
			p.setPublisherAddress(rs.getString("publisherAddress"));
			p.setPublisherPhone(rs.getString("publisherPhone"));
			publishers.add(p);
		}
		
 		return publishers;
	}
	
}
