lmsApp.controller("publisherController", function($scope, $http, $window,
		$location, publisherService, $filter, Pagination, ngNotify) {
	if ($location.$$path === "/adminpublisher") {
		publisherService.getAllPublishersService().then(
				function(backendPublishersList) {
					$scope.publishers = backendPublishersList;
					$scope.leftPublishers = backendPublishersList;
					$scope.pagination = Pagination.getNew();
					$scope.pagination.numPages = Math
							.ceil($scope.publishers.length
									/ $scope.pagination.perPage);
				});
	}

	$scope.success = function(message) {
		ngNotify.set(message, 'success');
	};

	$scope.showDeletePublisherModal = function(publisherId) {
		publisherService.getPublisherByPKService(publisherId).then(function(data) {
			$scope.publisher = data;
			$scope.deletePublisherModal = true;
		});
	}

	$scope.showEditPublisherModal = function(publisherId) {
		publisherService.getPublisherByPKService(publisherId).then(function(data) {
			$scope.publisher = data;
			$scope.editPublisherModal = true;
		});
	}

	$scope.showAddPublisherModal = function() {
		publisherService.getInitPublisherService().then(function(data) {
			$scope.initPublisher = data;
			$scope.addPublisherModal = true;
		});
	}

	$scope.closeEditPublisherModal = function() {
		$scope.editPublisherModal = false;
	}

	$scope.closeDeletePublisherrModal = function() {
		$scope.deletePublisherModal = false;
	}

	$scope.closeAddPublisherModal = function() {
		$scope.addPublisherModal = false;
	}

	$scope.updatePublisher = function() {
		publisherService.updatePublisherService($scope.publisher).success(
				function() {
					$scope.editPublisherModal = false;
					var message = 'Publisher named ' + $scope.publisher.publisherName
							+ ' is updated!';
					$scope.success(message);
					publisherService.getAllPublishersService().then(
							function(backendPublishersList) {
								$scope.publishers = backendPublishersList;
								$scope.leftPublishers = backendPublishersList;
								$scope.pagination = Pagination.getNew();
								$scope.pagination.numPages = Math
										.ceil($scope.publishers.length
												/ $scope.pagination.perPage);
							});
				});
	}

	$scope.deletePublisher = function() {
		publisherService.deletePublisherService($scope.publisher).success(
				function() {
					$scope.deletePublisherModal = false;
					var message = 'Publisher named ' + $scope.publisher.publisherName
							+ ' is deleted!';
					$scope.success(message);
					publisherService.getAllPublishersService().then(
							function(backendPublishersList) {
								$scope.publishers = backendPublishersList;
								$scope.leftPublishers = backendPublishersList;
								$scope.pagination = Pagination.getNew();
								$scope.pagination.numPages = Math
										.ceil($scope.publishers.length
												/ $scope.pagination.perPage);
							});
				});
	}

	$scope.addPublisher = function() {
		publisherService.addPublisherService($scope.initPublisher).success(
				function() {
					$scope.addPublisherModal = false;
					var message = 'Publisher named '
							+ $scope.initPublisher.publisherName + ' is added!';
					$scope.success(message);
					publisherService.getAllPublishersService().then(
							function(backendPublishersList) {
								$scope.publishers = backendPublishersList;
								$scope.leftPublishers = backendPublishersList;
								$scope.pagination = Pagination.getNew();
								$scope.pagination.numPages = Math
										.ceil($scope.publishers.length
												/ $scope.pagination.perPage);
							});
				});
	}

	$scope.searchPublishers = function() {
		publisherService.searchPublisherByName($scope.searchString).then(
				function(backendPublishersList) {
					$scope.leftPublishers = backendPublishersList;
					$scope.pagination = Pagination.getNew();
					$scope.pagination.numPages = Math
							.ceil($scope.publishers.length
									/ $scope.pagination.perPage);
				});
	}

	ngNotify.config({
		html : true
	});

})