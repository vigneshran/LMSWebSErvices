lmsApp
		.constant(
				"librarianConstants",
				{
					GET_ALL_BRANCHES_URL : "http://localhost:8080/lms/librarian/readAllBranches",
					GET_BRANCH_BY_PK_URL : "http://localhost:8080/lms/librarian/returnBranchObjectById/",
					GET_INIT_BRANCH : "http://localhost:8080/lms/librarian/getInitBranch",
					GET_ALL_BOOKS_URL : "http://localhost:8080/lms/librarian/readAllBooksByBranch/",
					GET_COPIES_URL : "http://localhost:8080/lms/librarian/getCopies/",
					DOES_BOOK_EXIST_URL : "http://localhost:8080/lms/librarian/doesBookExist/",
					ADD_COPIES_URL : "http://localhost:8080/lms/librarian/addCopies",
					UPDATE_COPIES_URL : "http://localhost:8080/lms/librarian/updateCopies",
					UPDATE_BRANCH_URL : "http://localhost:8080/lms/librarian/updateBranch",
					SEARCH_BOOKS_URL : "http://localhost:8080/lms/librarian/searchByBookNameOnly/"
				})