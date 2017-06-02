package com.gcit.lms.entity;

import java.io.Serializable;
import java.util.HashMap;

public class Copies implements Serializable {

	private static final long serialVersionUID = 5799809741079074620L;
	
	Integer branchId;
	Integer bookId;
	Integer noOfCOpies;

	
	HashMap<Integer, HashMap<Integer, Integer>> copiesOfBooks;

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
	 * @return the noOfCOpies
	 */
	public Integer getNoOfCOpies() {
		return noOfCOpies;
	}

	/**
	 * @param noOfCOpies the noOfCOpies to set
	 */
	public void setNoOfCOpies(Integer noOfCOpies) {
		this.noOfCOpies = noOfCOpies;
	}

	/**
	 * @return the copiesOfBooks
	 */
	public HashMap<Integer, HashMap<Integer, Integer>> getCopiesOfBooks() {
		return copiesOfBooks;
	}

	/**
	 * @param copiesOfBooks the copiesOfBooks to set
	 */
	public void setCopiesOfBooks(HashMap<Integer, HashMap<Integer, Integer>> copiesOfBooks) {
		this.copiesOfBooks = copiesOfBooks;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		result = prime * result + ((branchId == null) ? 0 : branchId.hashCode());
		result = prime * result + ((noOfCOpies == null) ? 0 : noOfCOpies.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Copies other = (Copies) obj;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		if (branchId == null) {
			if (other.branchId != null)
				return false;
		} else if (!branchId.equals(other.branchId))
			return false;
		if (noOfCOpies == null) {
			if (other.noOfCOpies != null)
				return false;
		} else if (!noOfCOpies.equals(other.noOfCOpies))
			return false;
		return true;
	}
	

}
