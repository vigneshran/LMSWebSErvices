package com.gcit.lms.entity;

import java.io.Serializable;
import java.util.List;

public class Borrower implements Serializable {

	private static final long serialVersionUID = 2907419198267529299L;
	
	Long cardNo;
	String borrowerName;
	String borrowerAddress;
	String borrowerPhone;
	
	List<Book> booksBorrowed;
	List<Book> booksDue;
	
	List<Loan> loans;
	List<Loan> unreturnedLoans;
	List<Loan> loansOverDue;
	List<Loan> allDueBooks;
	
	List<Loan> loansNotOverDue;
	
	
	/**
	 * @return the loansNotOverDue
	 */
	public List<Loan> getLoansNotOverDue() {
		return loansNotOverDue;
	}
	/**
	 * @param loansNotOverDue the loansNotOverDue to set
	 */
	public void setLoansNotOverDue(List<Loan> loansNotOverDue) {
		this.loansNotOverDue = loansNotOverDue;
	}
	/**
	 * @return the loansOverDue
	 */
	public List<Loan> getLoansOverDue() {
		return loansOverDue;
	}
	/**
	 * @param loansOverDue the loansOverDue to set
	 */
	public void setLoansOverDue(List<Loan> loansOverDue) {
		this.loansOverDue = loansOverDue;
	}
	/**
	 * @return the allDueBooks
	 */
	public List<Loan> getAllDueBooks() {
		return allDueBooks;
	}
	/**
	 * @param allDueBooks the allDueBooks to set
	 */
	public void setAllDueBooks(List<Loan> allDueBooks) {
		this.allDueBooks = allDueBooks;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((borrowerAddress == null) ? 0 : borrowerAddress.hashCode());
		result = prime * result + ((borrowerName == null) ? 0 : borrowerName.hashCode());
		result = prime * result + ((borrowerPhone == null) ? 0 : borrowerPhone.hashCode());
		result = prime * result + ((cardNo == null) ? 0 : cardNo.hashCode());
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
		Borrower other = (Borrower) obj;
		if (borrowerAddress == null) {
			if (other.borrowerAddress != null)
				return false;
		} else if (!borrowerAddress.equals(other.borrowerAddress))
			return false;
		if (borrowerName == null) {
			if (other.borrowerName != null)
				return false;
		} else if (!borrowerName.equals(other.borrowerName))
			return false;
		if (borrowerPhone == null) {
			if (other.borrowerPhone != null)
				return false;
		} else if (!borrowerPhone.equals(other.borrowerPhone))
			return false;
		if (cardNo == null) {
			if (other.cardNo != null)
				return false;
		} else if (!cardNo.equals(other.cardNo))
			return false;
		return true;
	}
	
	
	/**
	 * @return the cardNo
	 */
	public Long getCardNo() {
		return cardNo;
	}
	/**
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(Long cardNo) {
		this.cardNo = cardNo;
	}
	/**
	 * @return the borrowerName
	 */
	public String getBorrowerName() {
		return borrowerName;
	}
	/**
	 * @param borrowerName the borrowerName to set
	 */
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	/**
	 * @return the borrowerAddress
	 */
	public String getBorrowerAddress() {
		return borrowerAddress;
	}
	/**
	 * @param borrowerAddress the borrowerAddress to set
	 */
	public void setBorrowerAddress(String borrowerAddress) {
		this.borrowerAddress = borrowerAddress;
	}
	/**
	 * @return the borrowerPhone
	 */
	public String getBorrowerPhone() {
		return borrowerPhone;
	}
	/**
	 * @param borrowerPhone the borrowerPhone to set
	 */
	public void setBorrowerPhone(String borrowerPhone) {
		this.borrowerPhone = borrowerPhone;
	}
	/**
	 * @return the booksBorrowed
	 */
	public List<Book> getBooksBorrowed() {
		return booksBorrowed;
	}
	/**
	 * @param booksBorrowed the booksBorrowed to set
	 */
	public void setBooksBorrowed(List<Book> booksBorrowed) {
		this.booksBorrowed = booksBorrowed;
	}
	/**
	 * @return the booksDue
	 */
	public List<Book> getBooksDue() {
		return booksDue;
	}
	/**
	 * @param booksDue the booksDue to set
	 */
	public void setBooksDue(List<Book> booksDue) {
		this.booksDue = booksDue;
	}
	
	
	/**
	 * @return the unreturnedLoans
	 */
	public List<Loan> getUnreturnedLoans() {
		return unreturnedLoans;
	}
	/**
	 * @param unreturnedLoans the unreturnedLoans to set
	 */
	public void setUnreturnedLoans(List<Loan> unreturnedLoans) {
		this.unreturnedLoans = unreturnedLoans;
	}
	/**
	 * @return the loans
	 */
	public List<Loan> getLoans() {
		return loans;
	}
	/**
	 * @param loans the loans to set
	 */
	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}
	
	

	
}
