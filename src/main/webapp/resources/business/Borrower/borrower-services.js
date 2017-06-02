lmsApp.factory("basicBorrowerService", function($http, basicBorrowerConstants) {
	return {

		accessCheckService : function(cardNo) {
			var booleanCheck = {};
			return $http({
				url : basicBorrowerConstants.ACCESS_CHECK_URL + cardNo
			}).success(function(data) {
				booleanCheck = data;
			}).then(function() {
				return booleanCheck;
			})
		},

		saveScopeService : function() {
			var cache = {};
			return {
				set : function(location, payload) {
					cache[location] = payload;
				},
				get : function(location) {
					return cache[location];
				},
				clear : function() {
					cache = {};
				}
			};
		},

		getBorrowerByPKService : function(cardNo) {
			var getBorrowerByPkData = {};
			return $http({
				url : basicBorrowerConstants.GET_BORROWER_BY_PK_URL + cardNo
			}).success(function(data) {
				getBorrowerByPkData = data;
			}).then(function() {
				return getBorrowerByPkData;
			})
		},
		
		getAllBranches : function() {
			var getBranchData = {};
			return $http({
				url : basicBorrowerConstants.GET_ALL_BRANCHES_URL
			}).success(function(data) {
				getBranchData = data;
			}).then(function() {
				return getBranchData;
			})
		},

		getInitLoanService : function(cardNo) {
			var getLoanData = {};
			return $http({
				url : basicBorrowerConstants.GET_INIT_LOAN_URL+cardNo
			}).success(function(data) {
				getLoanData = data;
			}).then(function() {
				return getLoanData;
			})
		},

		checkOutBookService : function(loan) {
			return $http({
				url : basicBorrowerConstants.CHECK_OUT_BOOK_URL,
				method : "POST",
				data : loan
			})
		},
		
		returnBookService : function(loan) {
			return $http({
				url : basicBorrowerConstants.RETURN_BOOK_URL,
				method : "POST",
				data : loan
			})
		},

		updateBorrowerService : function(borrower) {
			return $http({
				url : borrowerConstants.UPDATE_BORROWER_URL,
				method : "POST",
				data : borrower
			})
		},

		deleteBorrowerService : function(borrower) {
			return $http({
				url : borrowerConstants.DELETE_BORROWER_URL,
				method : "POST",
				data : borrower
			})
		},

		searchBorrowerByName : function(searchString) {
			var getBorrowerByPkData = {};
			return $http({
				url : borrowerConstants.SEARCH_BORROWERS_URL + searchString
			}).success(function(data) {
				getBorrowerByPkData = data;
			}).then(function() {
				return getBorrowerByPkData;
			})
		}
	}
})