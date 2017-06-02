lmsApp.controller("authController", function($scope, $http, $window,
		$location, basicBorrowerService, $filter, Pagination, ngNotify,
		$routeParams) {

	$scope.checkNumber = function() {
		basicBorrowerService.accessCheckService($scope.cardNo).then(
				function(data) {
					if (data == true) {
						$window.location.href = "#/borrowerPage/" + $scope.cardNo;

					} else {
						var message = "The card number " + $scope.cardNo
								+ " does not exist!"
						$scope.success(message);
					}
				});
	}
	
	ngNotify.config({
		html : true
	});
	
	$scope.success = function(message) {
		ngNotify.set(message, 'danger');
	};
	
	
})