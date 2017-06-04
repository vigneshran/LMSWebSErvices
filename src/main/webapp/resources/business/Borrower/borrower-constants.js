lmsApp
		.constant(
				"basicBorrowerConstants",
				{
					ACCESS_CHECK_URL : "http://13.59.248.203:8080/lms-1.0.0-BUILD-SNAPSHOT/borrower/doesNumberExist/",
					GET_BORROWER_BY_PK_URL : "http://13.59.248.203:8080/lms-1.0.0-BUILD-SNAPSHOT/borrower/returnBorrowerByCardNoBasedOnLoans/",
					GET_ALL_BRANCHES_URL : "http://13.59.248.203:8080/lms-1.0.0-BUILD-SNAPSHOT/borrower/readAllBranches",
					GET_INIT_LOAN_URL : "http://13.59.248.203:8080/lms-1.0.0-BUILD-SNAPSHOT/borrower/getInitLoan/",
					CHECK_OUT_BOOK_URL : "http://13.59.248.203:8080/lms-1.0.0-BUILD-SNAPSHOT/borrower/addLoan",
					RETURN_BOOK_URL : "http://13.59.248.203:8080/lms-1.0.0-BUILD-SNAPSHOT/borrower/updateLoan"
				})
