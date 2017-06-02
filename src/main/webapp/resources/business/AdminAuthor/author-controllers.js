lmsApp.controller("authorController", function($scope, $http, $window,
		$location, authorService, $filter, Pagination, ngNotify) {
	if ($location.$$path === "/adminauthor") {
		authorService.getAllAuthorsService().then(
				function(backendAuthorsList) {
					$scope.authors = backendAuthorsList;
					$scope.leftAuthors = backendAuthorsList;
					$scope.pagination = Pagination.getNew($scope.perPage);
					$scope.pagination.numPages = Math
							.ceil($scope.authors.length
									/ $scope.pagination.perPage);
				});
	}
	
	$scope.init = function() {
		authorService.getAllAuthorsService().then(
				function(backendAuthorsList) {
					$scope.authors = backendAuthorsList;
					$scope.leftAuthors = backendAuthorsList;
					$scope.pagination = Pagination.getNew($scope.perPage);
					$scope.pagination.numPages = Math
							.ceil($scope.authors.length
									/ $scope.pagination.perPage);
				});
	}

	$scope.success = function(message) {
		ngNotify.set(message, 'success');
	};

	$scope.showDeleteAuthorModal = function(authorId) {
		authorService.getAuthorByPKService(authorId).then(function(data) {
			$scope.author = data;
			$scope.deleteAuthorModal = true;
		});
	}

	$scope.showEditAuthorModal = function(authorId) {
		authorService.getAuthorByPKService(authorId).then(function(data) {
			$scope.author = data;
			$scope.editAuthorModal = true;
		});
	}

	$scope.showAddAuthorModal = function(authorId) {
		authorService.getInitAuthorService().then(function(data) {
			$scope.initAuthor = data;
			$scope.addAuthorModal = true;
		});
	}

	$scope.closeEditAuthorModal = function() {
		$scope.editAuthorModal = false;
	}

	$scope.closeDeleteAuthorModal = function() {
		$scope.deleteAuthorModal = false;
	}

	$scope.closeAddAuthorModal = function() {
		$scope.addAuthorModal = false;
	}

	$scope.updateAuthor = function() {
		authorService.updateAuthorService($scope.author).success(
				function() {
					$scope.editAuthorModal = false;
					var message = 'Author named ' + $scope.author.authorName
							+ ' is updated!';
					$scope.success(message);
					authorService.getAllAuthorsService().then(
							function(backendAuthorsList) {
								$scope.authors = backendAuthorsList;
								$scope.leftAuthors = backendAuthorsList;
								$scope.pagination = Pagination.getNew();
								$scope.pagination.numPages = Math
										.ceil($scope.authors.length
												/ $scope.pagination.perPage);
							});
				});
	}

	$scope.deleteAuthor = function() {
		authorService.deleteAuthorService($scope.author).success(
				function() {
					$scope.deleteAuthorModal = false;
					var message = 'Author named ' + $scope.author.authorName
							+ ' is deleted!';
					$scope.success(message);
					authorService.getAllAuthorsService().then(
							function(backendAuthorsList) {
								$scope.authors = backendAuthorsList;
								$scope.leftAuthors = backendAuthorsList;
								$scope.pagination = Pagination.getNew();
								$scope.pagination.numPages = Math
										.ceil($scope.authors.length
												/ $scope.pagination.perPage);
							});
				});
	}

	$scope.addAuthor = function() {
		authorService.addAuthorService($scope.initAuthor).success(
				function() {
					$scope.addAuthorModal = false;
					var message = 'Author named '
							+ $scope.initAuthor.authorName + ' is added!';
					$scope.success(message);
					authorService.getAllAuthorsService().then(
							function(backendAuthorsList) {
								$scope.authors = backendAuthorsList;
								$scope.leftAuthors = backendAuthorsList;
								$scope.pagination = Pagination.getNew();
								$scope.pagination.numPages = Math
										.ceil($scope.authors.length
												/ $scope.pagination.perPage);
							});
				});
	}

	$scope.searchAuthors = function() {
		authorService.searchAuthorByName($scope.searchString).then(
				function(backendAuthorsList) {
					$scope.leftAuthors = backendAuthorsList;
					$scope.pagination = Pagination.getNew();
					$scope.pagination.numPages = Math
							.ceil($scope.leftAuthors.length
									/ $scope.pagination.perPage);
				});
	}

	ngNotify.config({
		html : true
	});

})