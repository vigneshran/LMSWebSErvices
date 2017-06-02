lmsApp.controller("overrideController", function($scope, $http, $window,
		$location, overrideService, $filter, Pagination, ngNotify) {
	if ($location.$$path === "/adminoverride") {
		overrideService.getAllLoansService().then(
				function(backendLoansList) {
					$scope.loans = backendLoansList;
					$scope.leftLoans = backendLoansList;
					$scope.pagination = Pagination.getNew();
					$scope.pagination.numPages = Math.ceil($scope.loans.length
							/ $scope.pagination.perPage);
				});
	}

	$scope.$watch('item', function(newValue, oldValue) {
		if (newValue === "YES") {
			$scope.overDueLoansOnly();
		} else {
			$scope.loansDue();
		}
	})

	$scope.loansDue = function() {
		overrideService.getAllLoansService().then(
				function(backendLoansList) {
					$scope.loans = backendLoansList;
					$scope.leftLoans = backendLoansList;
					$scope.pagination = Pagination.getNew();
					$scope.pagination.numPages = Math.ceil($scope.loans.length
							/ $scope.pagination.perPage);
				});
	}

	$scope.overDueLoansOnly = function() {
		overrideService.getOverDueLoansService().then(
				function(backEndLoansList) {
					$scope.leftLoans = backEndLoansList;
					$scope.pagination = Pagination.getNew();
					$scope.pagination.numPages = Math
							.ceil($scope.leftLoans.length
									/ $scope.pagination.perPage);
				});
	}

	$scope.pushDueDate = function(days) {
		overrideService.pushDueDateService($scope.loan, days).success(
				function() {
					$scope.confirmPushModal = false;
					var message = 'Duedate is pushed!';
					$scope.success(message);
					overrideService.getAllLoansService().then(
							function(backendLoansList) {
								$scope.loans = backendLoansList;
								$scope.leftLoans = backendLoansList;
								$scope.pagination = Pagination.getNew();
								$scope.pagination.numPages = Math
										.ceil($scope.loans.length
												/ $scope.pagination.perPage);
							});
				});
	}

	$scope.success = function(message) {
		ngNotify.set(message, 'success');
	};

	$scope.showConfirmPushModal = function(dateOut) {
		overrideService.getLoanByDateOut(dateOut).then(function(data) {
			$scope.loan = data;
			$scope.confirmPushModal = true;
		});
	}

	$scope.closeConfirmPushModal = function() {
		$scope.confirmPushModal = false;
	}

	$scope.searchLoans = function() {
		$scope.$watch('item', function(newValue, oldValue) {
			if (newValue === "YES") {
				overrideService.searchOverDueLoansByName($scope.searchString).then(
						function(backendLoansList) {
							$scope.leftLoans = backendLoansList;
							$scope.pagination = Pagination.getNew();
							$scope.pagination.numPages = Math
									.ceil($scope.leftLoans.length
											/ $scope.pagination.perPage);
						});
			} else {
				overrideService.searchLoansByName($scope.searchString).then(
						function(backendLoansList) {
							$scope.leftLoans = backendLoansList;
							$scope.pagination = Pagination.getNew();
							$scope.pagination.numPages = Math
									.ceil($scope.leftLoans.length
											/ $scope.pagination.perPage);
						});
			}
		})

	}

	ngNotify.config({
		html : true
	});

})