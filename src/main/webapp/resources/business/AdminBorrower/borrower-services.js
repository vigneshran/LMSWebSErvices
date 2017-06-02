lmsApp.factory("borrowerService", function($http, borrowerConstants) {
	return {
		getAllBorrowersService : function() {
			var getBorrowerData = {};
			return $http({
				url : borrowerConstants.GET_ALL_BORROWERS_URL
			}).success(function(data) {
				getBorrowerData = data;
			}).then(function() {
				return getBorrowerData;
			})
		},

		getBorrowerByPKService: function(cardNo){
			var getBorrowerByPkData = {};
			return $http({
				url: borrowerConstants.GET_BORROWER_BY_PK_URL+cardNo
			}).success(function(data){
				getBorrowerByPkData = data;
			}).then(function(){
				return getBorrowerByPkData;
				
			})
		}, 
		
		getBorrowerByNameService: function(name){
			var getBorrowerByPkData = {};
			return $http({
				url: borrowerConstants.GET_BORROWER_BY_NAME_URL+name
			}).success(function(data){
				getBorrowerByPkData = data;
			}).then(function(){
				return getBorrowerByPkData;
			})
		}, 
		
		getInitBorrowerService: function(){
			var getBorrowerData = {};
			return $http({
				url: borrowerConstants.GET_BORROWER_INIT_URL
			}).success(function(data){
				getBorrowerData = data;
			}).then(function(){
				return getBorrowerData;
			})
		},
		
		addBorrowerService: function(borrower){
			return $http({
				url: borrowerConstants.ADD_BORROWER_URL,
				method: "POST",
				data: borrower
			})
		},
		
		updateBorrowerService: function(borrower){
			return $http({
				url: borrowerConstants.UPDATE_BORROWER_URL,
				method: "POST",
				data: borrower
			})
		},
		
		deleteBorrowerService: function(borrower){
			return $http({
				url: borrowerConstants.DELETE_BORROWER_URL,
				method: "POST",
				data: borrower
			})
		},
		
		searchBorrowerByName: function(searchString){
			var getBorrowerByPkData = {};
			return $http({
				url: borrowerConstants.SEARCH_BORROWERS_URL+searchString
			}).success(function(data){
				getBorrowerByPkData = data;
			}).then(function(){
				return getBorrowerByPkData;
			})
		}
	}
})