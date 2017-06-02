lmsApp
		.controller(
				"librarianController",
				function($scope, $http, $window, $location, librarianService,
						$filter, Pagination, ngNotify) {
					if ($location.$$path === "/librarian") {
						librarianService
								.getAllBranchesService()
								.then(
										function(backendBranchesList) {
											$scope.branches = backendBranchesList;
											librarianService
													.getInitBranchService()
													.then(
															function(
																	backendBranch) {
																$scope.initBranch = backendBranch;
																librarianService
																		.readAllBooksService(
																				$scope.initBranch.branchId)
																		.then(
																				function(
																						backendBooksList) {
																					$scope.books = backendBooksList;
																					$scope.leftBooks = backendBooksList;
																					$scope.pagination = Pagination
																							.getNew();
																					$scope.pagination.numPages = Math
																							.ceil($scope.books.length
																									/ $scope.pagination.perPage);
																				});

															});
										});
					}

					$scope.getCopies = function(bookId, branchId) {
						librarianService.getCopiesService(bookId, branchId)
								.then(function(data) {
									return data;
								});
					}

					$scope.getCopies1 = function(bookId, branchId) {
						librarianService.getCopiesService(bookId, branchId)
								.then(function(data) {
									return 1;
								});
					}
					
					$scope.val = '19324';

					$scope.refresh = function() {
						librarianService
								.readAllBooksService($scope.initBranch.branchId)
								.then(
										function(backendBooksList) {
											$scope.books = backendBooksList;
											$scope.leftBooks = backendBooksList;
											$scope.pagination = Pagination
													.getNew();
											$scope.pagination.numPages = Math
													.ceil($scope.books.length
															/ $scope.pagination.perPage);
										});
					}

					$scope.addCopies = function(book) {
						librarianService
								.addCopiesService(book)
								.success(
										function() {
											var message = book.noOfCopies
													+ ' copies of '
													+ book.bookName
													+ ' now exist(s) '
													+ $scope.initBranch.branchName
													+ '!';
											$scope.success(message);
											librarianService
													.readAllBooksService(
															$scope.initBranch.branchId)
													.then(
															function(
																	backendBooksList) {
																$scope.books = backendBooksList;
																$scope.leftBooks = backendBooksList;
																$scope.pagination = Pagination
																		.getNew();
																$scope.pagination.numPages = Math
																		.ceil($scope.books.length
																				/ $scope.pagination.perPage);
															});
										});
					}

					$scope.updateCopies = function(book) {
						librarianService
								.updateCopiesService(book)
								.success(
										function() {
											var message = book.noOfCopies
													+ ' copies of '
													+ book.bookName
													+ ' now exist(s) in '
													+ $scope.initBranch.branchName
													+ '!';
											$scope.success(message);
											librarianService
													.readAllBooksService(
															$scope.initBranch.branchId)
													.then(
															function(
																	backendBooksList) {
																$scope.books = backendBooksList;
																$scope.leftBooks = backendBooksList;
																$scope.pagination = Pagination
																		.getNew();
																$scope.pagination.numPages = Math
																		.ceil($scope.books.length
																				/ $scope.pagination.perPage);
															});
										});
					}

					$scope.updateBranch = function() {
						librarianService
								.updateBranchService($scope.initBranch)
								.success(
										function() {
											$scope.editBranchModal = false;
											var message = 'Branch named '
													+ $scope.initBranch.branchName
													+ ' is updated!';
											$scope.success(message);
											
										});
					}

					$scope.showEditBranchModal = function() {
						$scope.editBranchModal = true;
					}

					$scope.closeEditBranchModal = function() {
						$scope.editBranchModal = false;
					}

					$scope.success = function(message) {
						ngNotify.set(message, 'success');
					};

					ngNotify.config({
						html : true
					});

					$scope.searchBooks = function() {
						librarianService
								.searchBookByName($scope.searchString, $scope.initBranch.branchId)
								.then(
										function(backendBooksList) {
											$scope.books = backendBooksList;
											$scope.pagination = Pagination
													.getNew();
											$scope.pagination.numPages = Math
													.ceil($scope.books.length
															/ $scope.pagination.perPage);
										});
					}

				})