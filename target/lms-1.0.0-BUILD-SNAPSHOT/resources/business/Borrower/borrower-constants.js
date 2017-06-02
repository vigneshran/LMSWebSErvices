lmsApp
		.constant(
				"basicBorrowerConstants",
				{
					ACCESS_CHECK_URL : "http://localhost:8080/lms/borrower/doesNumberExist/",
					GET_BORROWER_BY_PK_URL : "http://localhost:8080/lms/borrower/returnBorrowerByCardNoBasedOnLoans/",
					GET_ALL_BRANCHES_URL : "http://localhost:8080/lms/borrower/readAllBranches",
					GET_INIT_LOAN_URL : "http://localhost:8080/lms/borrower/getInitLoan/",
					CHECK_OUT_BOOK_URL : "http://localhost:8080/lms/borrower/addLoan",
					RETURN_BOOK_URL : "http://localhost:8080/lms/borrower/updateLoan"
				})