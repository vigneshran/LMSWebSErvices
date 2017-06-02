lmsApp.controller("branchController", function($scope, $http, $window,
		$location, branchService, $filter, Pagination, ngNotify) {
	if ($location.$$path === "/adminbranch") {
		branchService.getAllBranchesService().then(
				function(backendBranchesList) {
					$scope.branches = backendBranchesList;
					$scope.leftBranches = backendBranchesList;
					$scope.pagination = Pagination.getNew();
					$scope.pagination.numPages = Math
							.ceil($scope.branches.length
									/ $scope.pagination.perPage);
				});
	}

	$scope.success = function(message) {
		ngNotify.set(message, 'success');
	};

	$scope.showDeleteBranchModal = function(branchId) {
		branchService.getBranchByPKService(branchId).then(function(data) {
			$scope.branch = data;
			$scope.deleteBranchModal = true;
		});
	}

	$scope.showEditBranchModal = function(branchId) {
		branchService.getBranchByPKService(branchId).then(function(data) {
			$scope.branch = data;
			$scope.editBranchModal = true;
		});
	}

	$scope.showAddBranchModal = function(branchId) {
		branchService.getInitBranchService().then(function(data) {
			$scope.initBranch = data;
			$scope.addBranchModal = true;
		});
	}

	$scope.closeEditBranchModal = function() {
		$scope.editBranchModal = false;
	}

	$scope.closeDeleteBranchModal = function() {
		$scope.deleteBranchModal = false;
	}

	$scope.closeAddBranchModal = function() {
		$scope.addBranchModal = false;
	}

	$scope.updateBranch = function() {
		branchService.updateBranchService($scope.branch).success(
				function() {
					$scope.editBranchModal = false;
					var message = 'Branch named ' + $scope.branch.branchName
							+ ' is updated!';
					$scope.success(message);
					branchService.getAllBranchesService().then(
							function(backendBranchesList) {
								$scope.branches = backendBranchesList;
								$scope.leftBranches = backendBranchesList;
								$scope.pagination = Pagination.getNew();
								$scope.pagination.numPages = Math
										.ceil($scope.branches.length
												/ $scope.pagination.perPage);
							});
				});
	}

	$scope.deleteBranch = function() {
		branchService.deleteBranchService($scope.branch).success(
				function() {
					$scope.deleteBranchModal = false;
					var message = 'Branch named ' + $scope.branch.branchName
							+ ' is deleted!';
					$scope.success(message);
					branchService.getAllBranchesService().then(
							function(backendBranchesList) {
								$scope.branches = backendBranchesList;
								$scope.leftBranches = backendBranchesList;
								$scope.pagination = Pagination.getNew();
								$scope.pagination.numPages = Math
										.ceil($scope.branches.length
												/ $scope.pagination.perPage);
							});
				});
	}

	$scope.addBranch = function() {
		branchService.addBranchService($scope.initBranch).success(
				function() {
					$scope.addBranchModal = false;
					var message = 'Branch named '
							+ $scope.initBranch.branchName + ' is added!';
					$scope.success(message);
					branchService.getAllBranchesService().then(
							function(backendBranchesList) {
								$scope.branches = backendBranchesList;
								$scope.leftBranches = backendBranchesList;
								$scope.pagination = Pagination.getNew();
								$scope.pagination.numPages = Math
										.ceil($scope.branches.length
												/ $scope.pagination.perPage);
							});
				});
	}

	$scope.searchBranches = function() {
		branchService.searchBranchByName($scope.searchString).then(
				function(backendBranchesList) {
					$scope.leftBranches = backendBranchesList;
					$scope.pagination = Pagination.getNew();
					$scope.pagination.numPages = Math
							.ceil($scope.leftBranches.length
									/ $scope.pagination.perPage);
				});
	}

	ngNotify.config({
		html : true
	});

})