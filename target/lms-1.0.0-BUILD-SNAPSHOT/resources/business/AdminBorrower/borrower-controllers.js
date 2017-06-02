lmsApp
		.controller(
				"borrowerController",
				function($scope, $http, $window, $location, borrowerService,
						$filter, Pagination, ngNotify) {
					if ($location.$$path === "/adminborrower") {
						borrowerService
								.getAllBorrowersService()
								.then(
										function(backendBorrowersList) {
											$scope.borrowers = backendBorrowersList;
											$scope.leftBorrowers = backendBorrowersList;
											$scope.pagination = Pagination
													.getNew();
											$scope.pagination.numPages = Math
													.ceil($scope.borrowers.length
															/ $scope.pagination.perPage);
										});
					}

					$scope.success = function(message) {
						ngNotify.set(message, 'success');
					};
					
					$scope.sticky = function(message){
				           ngNotify.set(message, {
				                sticky: true
				            });
				        };

					$scope.showDeleteBorrowerModal = function(cardNo) {
						borrowerService.getBorrowerByPKService(cardNo).then(
								function(data) {
									$scope.borrower = data;
									$scope.deleteBorrowerModal = true;
								});
					}

					$scope.showEditBorrowerModal = function(cardNo) {
						borrowerService.getBorrowerByPKService(cardNo).then(
								function(data) {
									$scope.borrower = data;
									$scope.editBorrowerModal = true;
								});
					}

					$scope.showAddBorrowerModal = function(cardNo) {
						borrowerService.getInitBorrowerService().then(
								function(data) {
									$scope.initBorrower = data;
									$scope.addBorrowerModal = true;
								});
					}

					$scope.closeEditBorrowerModal = function() {
						$scope.editBorrowerModal = false;
					}

					$scope.closeDeleteBorrowerModal = function() {
						$scope.deleteBorrowerModal = false;
					}

					$scope.closeAddBorrowerModal = function() {
						$scope.addBorrowerModal = false;
					}

					$scope.updateBorrower = function() {
						borrowerService
								.updateBorrowerService($scope.borrower)
								.success(
										function() {
											$scope.editBorrowerModal = false;
											var message = 'Borrower named '
													+ $scope.borrower.borrowerName
													+ ' is updated!';
											$scope.success(message);
											borrowerService
													.getAllBorrowersService()
													.then(
															function(
																	backendBorrowersList) {
																$scope.borrowers = backendBorrowersList;
																$scope.leftBorrowers = backendBorrowersList;
																$scope.pagination = Pagination
																		.getNew();
																$scope.pagination.numPages = Math
																		.ceil($scope.borrowers.length
																				/ $scope.pagination.perPage);
															});
										});
					}

					$scope.deleteBorrower = function() {
						borrowerService
								.deleteBorrowerService($scope.borrower)
								.success(
										function() {
											$scope.deleteBorrowerModal = false;
											var message = 'Borrower named '
													+ $scope.borrower.borrowerName
													+ ' is deleted!';
											$scope.success(message);
											borrowerService
													.getAllBorrowersService()
													.then(
															function(
																	backendBorrowersList) {
																$scope.borrowers = backendBorrowersList;
																$scope.leftBorrowers = backendBorrowersList;
																$scope.pagination = Pagination
																		.getNew();
																$scope.pagination.numPages = Math
																		.ceil($scope.borrowers.length
																				/ $scope.pagination.perPage);
															});
										});
					}

					$scope.addBorrower = function() {
						borrowerService
								.addBorrowerService($scope.initBorrower)
								.success(
										function() {
											borrowerService
													.getBorrowerByNameService(
															$scope.initBorrower.borrowerName)
													.then(
															function(data) {
																$scope.borrower = data;
																$scope.addBorrowerModal = false;
																var message = 'Borrower named '
																		+ $scope.initBorrower.borrowerName
																		+ ' is added! The borrower has a card number: ' + $scope.borrower.cardNo ;
																$scope
																		.sticky(message);
																borrowerService
																		.getAllBorrowersService()
																		.then(
																				function(
																						backendBorrowersList) {
																					$scope.borrowers = backendBorrowersList;
																					$scope.leftBorrowers = backendBorrowersList;
																					$scope.pagination = Pagination
																							.getNew();
																					$scope.pagination.numPages = Math
																							.ceil($scope.borrowers.length
																									/ $scope.pagination.perPage);
																				});
															});

										});
					}

					$scope.searchBorrowers = function() {
						borrowerService
								.searchBorrowerByName($scope.searchString)
								.then(
										function(backendBorrowersList) {
											$scope.leftBorrowers = backendBorrowersList;
											$scope.pagination = Pagination
													.getNew();
											$scope.pagination.numPages = Math
													.ceil($scope.leftBorrowers.length
															/ $scope.pagination.perPage);
										});
					}

					ngNotify.config({
						html : true
					});

				})