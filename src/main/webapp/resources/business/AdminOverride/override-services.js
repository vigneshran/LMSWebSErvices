lmsApp.factory("overrideService", function($http, overrideConstants) {
	return {
		getAllLoansService : function() {
			var getAuthorData = {};
			return $http({
				url : overrideConstants.GET_ALL_LOANS_DUE
			}).success(function(data) {
				getAuthorData = data;
			}).then(function() {
				return getAuthorData;
			})
		},
		
		pushDueDateService: function(loan, days){
			return $http({
				url: overrideConstants.PUSH_DUE_DATE + days,
				method: "POST",
				data: loan
			})
		},
		
		searchLoansByName: function(searchString){
			var getLoansByPkData = {};
			return $http({
				url: overrideConstants.SEARCH_LOANS_URL+searchString
			}).success(function(data){
				getLoansByPkData = data;
			}).then(function(){
				return getLoansByPkData;
			})
		},
		
		searchOverDueLoansByName: function(searchString){
			var getLoansByPkData = {};
			return $http({
				url: overrideConstants.SEARCH_ALL_OVERDUE_LOANS+searchString
			}).success(function(data){
				getLoansByPkData = data;
			}).then(function(){
				return getLoansByPkData;
			})
		},
		
		getLoanByDateOut: function(dateOut){
			var getLoansByPkData = {};
			return $http({
				url: overrideConstants.GET_LOAN_BY_DATEOUT+dateOut
			}).success(function(data){
				getLoansByPkData = data;
			}).then(function(){
				return getLoansByPkData;
			})
		},
		
		getOverDueLoansService: function(){
			var getAllLoansData = {};
			return $http({
				url: overrideConstants.GET_ALL_OVERDUE_LOANS
			}).success(function(data){
				getAllLoansData = data;
			}).then(function(){
				return getAllLoansData;
			})
		}
	}
		
})