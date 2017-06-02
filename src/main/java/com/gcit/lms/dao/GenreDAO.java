package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Genre;

public class GenreDAO extends BaseDAO implements ResultSetExtractor<List<Genre>>{
	
	public void addGenre(Genre genre) throws ClassNotFoundException, SQLException	{
		template.update("INSERT INTO tbl_genre (genreName) VALUES (?)", new Object[] {genre.getGenreName()});
	}
	
	public void updateGenre(Genre genre) throws ClassNotFoundException, SQLException	{
		template.update("UPDATE tbl_genre SET genreName = ? WHERE genreId = ?", new Object[] {genre.getGenreName(), genre.getGenreId()});
	}
	
	public void deleteGenre(Genre genre) throws ClassNotFoundException, SQLException {
		template.update("DELETE * FROM tbl_genre WHERE genreId = ?", new Object[]{genre.getGenreId()});
	}
	
	public List<Genre> readAllGenres() throws SQLException {
		return template.query("SELECT * FROM tbl_genre", this);
	}
	
	public List<Genre> returnGenreObjectByName(String name) throws SQLException {
		return template.query("SELECT * FROM tbl_genre WHERE genre_name = ?", new Object[] {name}, this);
	}
	
	public List<Genre> returnGenreObjectByBookId(Book book) throws SQLException {
		return template.query("SELECT * FROM tbl_genre WHERE genre_id IN (SELECT genre_id FROM tbl_book_genres WHERE bookId = ?)", new Object[] {book.getBookId()}, this);
	}
	
	@Override
	public List extractData(ResultSet rs) throws SQLException {
		List<Genre> genres = new ArrayList<>();
		while (rs.next())	{
			Genre g = new Genre();
			g.setGenreId(rs.getInt("genre_id"));
			g.setGenreName(rs.getString("genre_name"));
			genres.add(g);
		}
		
 		return genres;
	}
}
