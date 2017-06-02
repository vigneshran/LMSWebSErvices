lmsApp.controller("basicBorrowerController", function($scope, $http, $window,
		$location, basicBorrowerService, $filter, Pagination, ngNotify,
		$routeParams) {

	$scope.init = function() {
		basicBorrowerService.getBorrowerByPKService($routeParams.bid).then(
				function(borrowerList) {
					$scope.borrower = borrowerList;
					$scope.loans = borrowerList.unreturnedLoans;
					$scope.others = borrowerList.loansNotOverDue;
					basicBorrowerService.getAllBranches().then(
							function(branchesList) {
								$scope.branches = branchesList;
							});
				});
	}

	$scope.allBooksDue = function() {
		basicBorrowerService.getBorrowerByPKService($scope.borrower.cardNo)
				.then(function(borrowerList) {
					$scope.loans = borrowerList.unreturnedLoans;
				});
	}

	$scope.allOverDueBooksDue = function() {
		basicBorrowerService.getBorrowerByPKService($scope.borrower.cardNo)
				.then(function(borrowerList) {
					$scope.loans = borrowerList.loansOverDue;
				});
	}

	$scope.saveCardNo = function() {
		var savedData = {};
		return {
			set : function(location, data) {
				savedData[location] = data;
			},
			get : function(location) {
				return savedData[location];
			},
			clear : function() {
				savedData = {};
			}
		};
	}

	$scope.success = function(message) {
		ngNotify.set(message, 'success');
	};

	

	$scope.showCheckOutBookModal = function() {
		basicBorrowerService.getInitLoanService($scope.borrower.cardNo).then(function(data) {
			$scope.loan = data;
			$scope.checkOutBookModal = true;
		});
	}

	$scope.closeCheckOutBookModal = function() {
		$scope.checkOutBookModal = false;
	}
	
	$scope.showConfirmReturnBookModal = function(l) {
		$scope.l = l;
		$scope.confirmReturnBookModal = true;
	}

	$scope.closeConfirmReturnBookModal = function() {
		$scope.confirmReturnBookModal = false;
	}
	
	

	$scope.checkOutBook = function() {
		basicBorrowerService.checkOutBookService($scope.loan).success(
				function() {
					$scope.checkOutBookModal = false;
					var message = 'The book titled ' + $scope.loan.book.bookName
							+ ' from ' + $scope.loan.branch.branchName
							+ ' is checked out!';
					$scope.success(message);
					basicBorrowerService.getBorrowerByPKService(
							$scope.borrower.cardNo).then(
							function(borrowerList) {
								$scope.borrower = borrowerList;
								$scope.loans = borrowerList.unreturnedLoans;
								basicBorrowerService.getAllBranches().then(
										function(branchesList) {
											$scope.branches = branchesList;
										});
							});
				});
	}
	
	$scope.returnBook = function() {
		basicBorrowerService.returnBookService($scope.l).success(
				function() {
					$scope.confirmReturnBookModal = false;
					var message = 'The book titled ' + $scope.l.book.bookName
							+ ' from ' + $scope.l.branch.branchName
							+ ' is returned';
					$scope.success(message);
					basicBorrowerService.getBorrowerByPKService(
							$scope.borrower.cardNo).then(
							function(borrowerList) {
								$scope.borrower = borrowerList;
								$scope.loans = borrowerList.unreturnedLoans;
								basicBorrowerService.getAllBranches().then(
										function(branchesList) {
											$scope.branches = branchesList;
										});
							});
				});
	}

	$scope.deleteBorrower = function() {
		borrowerService.deleteBorrowerService($scope.borrower).success(
				function() {
					$scope.deleteBorrowerModal = false;
					var message = 'Borrower named '
							+ $scope.borrower.borrowerName + ' is deleted!';
					$scope.success(message);
					borrowerService.getAllBorrowersService().then(
							function(backendBorrowersList) {
								$scope.borrowers = backendBorrowersList;
								$scope.leftBorrowers = backendBorrowersList;
								$scope.pagination = Pagination.getNew();
								$scope.pagination.numPages = Math
										.ceil($scope.borrowers.length
												/ $scope.pagination.perPage);
							});
				});
	}

	$scope.addBorrower = function() {
		borrowerService.addBorrowerService($scope.initBorrower).success(
				function() {
					$scope.addBorrowerModal = false;
					var message = 'Borrower named '
							+ $scope.initBorrower.borrowerName + ' is added!';
					$scope.success(message);
					borrowerService.getAllBorrowersService().then(
							function(backendBorrowersList) {
								$scope.borrowers = backendBorrowersList;
								$scope.leftBorrowers = backendBorrowersList;
								$scope.pagination = Pagination.getNew();
								$scope.pagination.numPages = Math
										.ceil($scope.borrowers.length
												/ $scope.pagination.perPage);
							});
				});
	}

	$scope.searchBorrowers = function() {
		borrowerService.searchBorrowerByName($scope.searchString).then(
				function(backendBorrowersList) {
					$scope.leftBorrowers = backendBorrowersList;
					$scope.pagination = Pagination.getNew();
					$scope.pagination.numPages = Math
							.ceil($scope.leftBorrowers.length
									/ $scope.pagination.perPage);
				});
	}

	ngNotify.config({
		html : true
	});

})