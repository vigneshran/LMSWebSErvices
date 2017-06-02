package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.dao.*;
import com.gcit.lms.entity.*;

@RestController
@RequestMapping(value = "/librarian")
public class AdminLibrarian {
	@Autowired
	AuthorDAO adao;

	@Autowired
	CopiesDAO cdao;

	@Autowired
	BookDAO bdao;

	@Autowired
	BranchDAO branchDao;

	@RequestMapping(value = "/getInitBranch", method = RequestMethod.GET, produces = "application/json")
	public Branch getInitAuthor() throws SQLException {
		return this.readAllBranches().get(4);
	}

	@RequestMapping(value = "/returnBranchObjectByName/{branchName}", method = RequestMethod.GET, produces = "application/json")
	public List<Branch> returnBranchObjectByName(@PathVariable String branchName) {
		List<Branch> branchByName = new ArrayList<>();
		try {
			return branchByName = branchDao.readBranchByName(branchName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/readAllBranches", method = RequestMethod.GET, produces = "application/json")
	public List<Branch> readAllBranches() {
		try {
			return branchDao.readAllBranches();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/readAllBooks", method = RequestMethod.GET, produces = "application/json")
	public List<Book> readAllBooks() {
		try {
			return bdao.readAllBooks();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/readAllBooksByBranch/{branchId}", method = RequestMethod.GET, produces = "application/json")
	public List<Book> readAllBooksAndCopiesByBranch(@PathVariable Integer branchId) {
		try {
			List<Book> books = bdao.readAllBooks();
			for (Book b : books) {
				if (this.doesBookExist(b.getBookId(), branchId)) {
					b.setBookThere(true);
					b.setNoOfCopies(this.getCopies(b.getBookId(), branchId));
					b.setBranchId(branchId);
				} else {
					b.setBookThere(false);
					b.setNoOfCopies(0);
					b.setBranchId(branchId);
				}
			}
			return books;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/returnBookObjectByName/{title}", method = RequestMethod.GET, produces = "application/json")
	public List<Book> returnBookObjectByName(@PathVariable String title) {
		try {
			return bdao.readBookByName(title);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@RequestMapping(value = "/addCopies", method = RequestMethod.POST, consumes = "application/json")
	public void addCopies(@RequestBody Book book) {
		try {
			Copies copy = new Copies();
			copy.setBranchId(book.getBranchId());
			copy.setBookId(book.getBookId());
			copy.setNoOfCOpies(book.getNoOfCopies());
			cdao.addCopies(copy);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/updateCopies", method = RequestMethod.POST, consumes = "application/json")
	public void updateCopies(@RequestBody Book book) {

		try {
			Copies copy = new Copies();
			copy.setBranchId(book.getBranchId());
			copy.setBookId(book.getBookId());
			copy.setNoOfCOpies(book.getNoOfCopies());
			cdao.updateCopies(copy);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/doesBookExist/{bookId}/{branchId}", method = RequestMethod.GET, produces = "application/json")
	public boolean doesBookExist(@PathVariable Integer bookId, @PathVariable Integer branchId) {
		try {
			return cdao.doesBookExist(branchId, bookId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@RequestMapping(value = "/getCopies/{bookId}/{branchId}", method = RequestMethod.GET, produces = "application/json")
	public Integer getCopies(@PathVariable Integer bookId, @PathVariable Integer branchId) {
		List<Copies> copies = new ArrayList<>();

		try {
			if (!this.doesBookExist(bookId, branchId)) {
				return 0;
			}
			Copies copy = new Copies();
			copy.setBranchId(branchId);
			copies = cdao.readCopyByBranchIdAndBookId(branchId, bookId);
			return copies.get(0).getNoOfCOpies();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@RequestMapping(value = "/updateBranch", method = RequestMethod.POST, consumes = "application/json")
	public void updateBranch(@RequestBody Branch branch) {

		try {
			branchDao.updateBranch(branch);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getCopies/{branchId}", method = RequestMethod.GET, produces = "application/json")
	public List<Branch> returnBranchObjectById(@PathVariable Integer branchId) {
		List<Branch> branchByName = null;
		try {
			branchByName = branchDao.readBranchById(branchId);
			branchByName.get(0).setBooks(bdao.readAllBooksWithBranch(branchId));
			return branchByName;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/searchByBookNameOnly/{name}/{branchId}", method = RequestMethod.GET, produces = "application/json")
	public List<Book> searchByBookName(@PathVariable String name, @PathVariable Integer branchId) throws SQLException {
		try {
			List<Book> books = bdao.searchBookByNameWithoutPage(name);
			for (Book b : books) {
				if (this.doesBookExist(b.getBookId(), branchId)) {
					b.setBookThere(true);
					b.setNoOfCopies(this.getCopies(b.getBookId(), branchId));
					b.setBranchId(branchId);
				} else {
					b.setBookThere(false);
					b.setNoOfCopies(0);
					b.setBranchId(branchId);
				}
			}
			return books;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/searchByBookNameOnly/{branchId}", method = RequestMethod.GET, produces = "application/json")
	public List<Book> searchByBookName(@PathVariable Integer branchId) throws SQLException {
		return this.readAllBooksAndCopiesByBranch(branchId);
	}

}
