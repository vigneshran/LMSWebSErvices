package com.gcit.lms.entity;
import java.io.Serializable;
import java.util.List;

public class Book implements Serializable {

	private static final long serialVersionUID = 5919473657203370698L;
	
	private Integer bookId;
	private String bookName;
	private List<Author> authors;
	private Publisher publisher;
	private List<Genre> genres;
	
	private Integer bookInBranch;
	private boolean isBookThere;
	private Integer noOfCopies;
	private Integer branchId;
	
	/**
	 * @return the branchId
	 */
	public Integer getBranchId() {
		return branchId;
	}


	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}


	/**
	 * @return the isBookThere
	 */
	public boolean isBookThere() {
		return isBookThere;
	}


	/**
	 * @param isBookThere the isBookThere to set
	 */
	public void setBookThere(boolean isBookThere) {
		this.isBookThere = isBookThere;
	}


	/**
	 * @return the noOfCopies
	 */
	public Integer getNoOfCopies() {
		return noOfCopies;
	}


	/**
	 * @param noOfCopies the noOfCopies to set
	 */
	public void setNoOfCopies(Integer noOfCopies) {
		this.noOfCopies = noOfCopies;
	}


	/**
	 * @return the bookInBranch
	 */
	public Integer getBookInBranch() {
		return bookInBranch;
	}


	/**
	 * @param bookInBranch the bookInBranch to set
	 */
	public void setBookInBranch(Integer bookInBranch) {
		this.bookInBranch = bookInBranch;
	}


	/**
	 * @return the bookId
	 */
	public Integer getBookId() {
		return bookId;
	}


	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}


	/**
	 * @return the bookName
	 */
	public String getBookName() {
		return bookName;
	}


	/**
	 * @param bookName the bookName to set
	 */
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}


	/**
	 * @return the authors
	 */
	public List<Author> getAuthors() {
		return authors;
	}


	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}


	/**
	 * @return the publisher
	 */
	public Publisher getPublisher() {
		return publisher;
	}


	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}


	/**
	 * @return the genres
	 */
	public List<Genre> getGenres() {
		return genres;
	}


	/**
	 * @param genres the genres to set
	 */
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		if (bookName == null) {
			if (other.bookName != null)
				return false;
		} else if (!bookName.equals(other.bookName))
			return false;
		return true;
	}
	
}
