lmsApp.controller("bookController", function($scope, $http, $window, $location,
		bookService, $filter, Pagination, ngNotify) {
	if ($location.$$path === "/admin") {
		bookService.getAllBooksService().then(
				function(backendBooksList) {
					$scope.books = backendBooksList;
					$scope.leftBooks = backendBooksList;
					$scope.pagination = Pagination.getNew();
					$scope.pagination.numPages = Math
							.ceil($scope.books.length
									/ $scope.pagination.perPage);
				});
		bookService.getAllAuthorsService().then(function(backendAuthorsList) {
			$scope.authors = backendAuthorsList;
		});
		bookService.getAllPublishersService().then(
				function(backendPublishersList) {
					$scope.publishers = backendPublishersList;
				});
		bookService.getAllGenresService().then(function(backendGenresList) {
			$scope.genres = backendGenresList;
		});
	}

	$scope.success = function(message) {
		ngNotify.set(message, 'success');
	};

	$scope.showDeleteBookModal = function(bookId) {
		bookService.getBookByPKService(bookId).then(function(data) {
			$scope.book = data;
			$scope.deleteBookModal = true;
		});
	}

	$scope.showEditBookModal = function(bookId) {
		bookService.getBookByPKService(bookId).then(function(data) {
			$scope.book = data;
			$scope.editBookModal = true;
		});
	}

	$scope.showAddBookModal = function(bookId) {
		bookService.getInitBookService().then(function(data) {
			$scope.initBook = data;
			$scope.addBookModal = true;
		});
	}

	$scope.closeEditBookModal = function() {
		$scope.editBookModal = false;
	}

	$scope.closeDeleteBookModal = function() {
		$scope.deleteBookModal = false;
	}

	$scope.closeAddBookModal = function() {
		$scope.addBookModal = false;
	}

	$scope.updateBook = function() {
		bookService.updateBookService($scope.book).success(
				function() {
					$scope.editBookModal = false;
					var message = 'Book named ' + $scope.book.bookName
							+ ' is updated!';
					$scope.success(message);
					bookService.getAllBooksService().then(
							function(backendBooksList) {
								$scope.books = backendBooksList;
								$scope.leftBooks = backendBooksList;
								$scope.pagination = Pagination.getNew();
								$scope.pagination.numPages = Math
										.ceil($scope.books.length
												/ $scope.pagination.perPage);
							});
				});
	}

	$scope.deleteBook = function() {
		bookService.deleteBookService($scope.book).success(
				function() {
					$scope.deleteBookModal = false;
					var message = 'Book named ' + $scope.book.bookName
							+ ' is deleted!';
					$scope.success(message);
					bookService.getAllBooksService().then(
							function(backendBooksList) {
								$scope.books = backendBooksList;
								$scope.leftBooks = backendBooksList;
								$scope.pagination = Pagination.getNew();
								$scope.pagination.numPages = Math
										.ceil($scope.books.length
												/ $scope.pagination.perPage);
							});
				});
	}

	$scope.addBook = function() {
		bookService.addBookService($scope.initBook).success(
				function() {
					$scope.addBookModal = false;
					var message = 'Book named ' + $scope.initBook.bookName
							+ ' is added!';
					$scope.success(message);
					bookService.getAllBooksService().then(
							function(backendBooksList) {
								$scope.books = backendBooksList;
								$scope.leftBooks = backendBooksList;
								$scope.pagination = Pagination.getNew();
								$scope.pagination.numPages = Math
										.ceil($scope.books.length
												/ $scope.pagination.perPage);
							});
				});
	}

	$scope.searchBooks = function() {
		bookService.searchBookByName($scope.searchString).then(
				function(backendBooksList) {
					$scope.leftBooks = backendBooksList;
					$scope.pagination = Pagination.getNew();
					$scope.pagination.numPages = Math.ceil($scope.leftBooks.length
							/ $scope.pagination.perPage);
				});
	}

	ngNotify.config({
		html : true
	});

})