lmsApp
		.constant(
				"overrideConstants",
				{
					GET_ALL_LOANS_DUE : "http://localhost:8080/lms/admin/readAllLoans",
					PUSH_DUE_DATE : "http://localhost:8080/lms/admin/overrideDueDate/",
					SEARCH_LOANS_URL : "http://localhost:8080/lms/admin/searchLoanByBorrowerNameOnly/",
					GET_LOAN_BY_DATEOUT : "http://localhost:8080/lms/admin/getLoanByDateOut/",
					GET_ALL_OVERDUE_LOANS : "http://localhost:8080/lms/admin/getOverDueLoans",
					SEARCH_ALL_OVERDUE_LOANS : "http://localhost:8080/lms/admin/searchOverDueLoanByBorrowerNameOnly/"
				})