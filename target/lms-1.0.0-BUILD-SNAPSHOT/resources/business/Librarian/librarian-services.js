lmsApp.factory("librarianService", function($http, librarianConstants) {
	return {
		getAllBranchesService : function() {
			var getBranchData = {};
			return $http({
				url : librarianConstants.GET_ALL_BRANCHES_URL
			}).success(function(data) {
				getAuthorData = data;
			}).then(function() {
				return getAuthorData;
			})
		},
		
		getInitBranchService: function(){
			var getBranchData = {};
			return $http({
				url: librarianConstants.GET_INIT_BRANCH
			}).success(function(data){
				getBranchData = data;
			}).then(function(){
				return getBranchData;
			})
		},
		
		readAllBooksService: function(branchId){
			var getBooksData = {};
			return $http({
				url: librarianConstants.GET_ALL_BOOKS_URL+branchId
			}).success(function(data){
				getBooksData = data;
			}).then(function(){
				return getBooksData;
			})
		},
		
		getCopiesService: function(bookId, branchId){
			var getCopiesData = {};
			return $http({
				url: librarianConstants.GET_COPIES_URL+bookId+ '/' + branchId
			}).success(function(data){
				getCopiesData = data;
			}).then(function(){
				return getCopiesData;
			})
		},
		
		addCopiesService: function(book){
			return $http({
				url: librarianConstants.ADD_COPIES_URL,
				method: "POST",
				data: book
			})
		},
		
		updateCopiesService: function(book){
			return $http({
				url: librarianConstants.UPDATE_COPIES_URL,
				method: "POST",
				data: book
			})
		},
		
		updateBranchService: function(branch){
			return $http({
				url: librarianConstants.UPDATE_BRANCH_URL,
				method: "POST",
				data: branch
			})
		},
		
		searchBookByName: function(searchString, branchId){
			var getBookByPkData = {};
			return $http({
				url: librarianConstants.SEARCH_BOOKS_URL+searchString +'/'+ branchId
			}).success(function(data){
				getBookByPkData = data;
			}).then(function(){
				return getBookByPkData;
			})
		}
	}
})