lmsApp.factory("bookService", function($http, bookConstants) {
	return {
		getAllBooksService : function() {
			var getBookData = {};
			return $http({
				url : bookConstants.GET_ALL_BOOKS_URL
			}).success(function(data) {
				getBookData = data;
			}).then(function() {
				return getBookData;
			})
		},
		
		getAllAuthorsService : function() {
			var getAuthorsData = {};
			return $http({
				url : bookConstants.GET_ALL_AUTHORS_URL
			}).success(function(data) {
				getAuthorsData = data;
			}).then(function() {
				return getAuthorsData;
			})
		},
		
		getAllPublishersService : function() {
			var getPublishersData = {};
			return $http({
				url : bookConstants.GET_ALL_PUBLISHERS_URL
			}).success(function(data) {
				getPublishersData = data;
			}).then(function() {
				return getPublishersData;
			})
		},
		
		getAllGenresService : function() {
			var getGenresData = {};
			return $http({
				url : bookConstants.GET_ALL_GENRES_URL
			}).success(function(data) {
				getGenresData = data;
			}).then(function() {
				return getGenresData;
			})
		},

		getBookByPKService: function(bookId){
			var getBookByPkData = {};
			return $http({
				url: bookConstants.GET_BOOK_BY_PK_URL+bookId
			}).success(function(data){
				getBookByPkData = data;
			}).then(function(){
				return getBookByPkData;
			})
		}, 
		
		getInitBookService: function(){
			var getBookData = {};
			return $http({
				url: bookConstants.GET_BOOK_INIT_URL
			}).success(function(data){
				getBookData = data;
			}).then(function(){
				return getBookData;
			})
		},
		
		addBookService: function(book){
			return $http({
				url: bookConstants.ADD_BOOK_URL,
				method: "POST",
				data: book
			})
		},
		
		updateBookService: function(book){
			return $http({
				url: bookConstants.UPDATE_BOOK_URL,
				method: "POST",
				data: book
			})
		},
		
		deleteBookService: function(book){
			return $http({
				url: bookConstants.DELETE_BOOK_URL,
				method: "POST",
				data: book
			})
		},
		
		searchBookByName: function(searchString){
			var getBookByPkData = {};
			return $http({
				url: bookConstants.SEARCH_BOOK_URL+searchString
			}).success(function(data){
				getBookByPkData = data;
			}).then(function(){
				return getBookByPkData;
			})
		}
	}
})