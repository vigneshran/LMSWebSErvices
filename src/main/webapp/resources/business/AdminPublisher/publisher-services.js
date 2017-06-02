lmsApp.factory("publisherService", function($http, publisherConstants) {
	return {
		getAllPublishersService : function() {
			var getPublisherData = {};
			return $http({
				url : publisherConstants.GET_ALL_PUBLISHERS_URL
			}).success(function(data) {
				getPublisherData = data;
			}).then(function() {
				return getPublisherData;
			})
		},

		getPublisherByPKService: function(publisherId){
			var getPublisherByPkData = {};
			return $http({
				url: publisherConstants.GET_PUBLISHER_BY_PK_URL+publisherId
			}).success(function(data){
				getPublisherByPkData = data;
			}).then(function(){
				return getPublisherByPkData;
				
			})
		}, 
		
		getInitPublisherService: function(){
			var getPublisherData = {};
			return $http({
				url: publisherConstants.GET_PUBLISHER_INIT_URL
			}).success(function(data){
				getPublisherData = data;
			}).then(function(){
				return getPublisherData;
			})
		},
		
		addPublisherService: function(publisher){
			return $http({
				url: publisherConstants.ADD_PUBLISHER_URL,
				method: "POST",
				data: publisher
			})
		},
		
		updatePublisherService: function(publisher){
			return $http({
				url: publisherConstants.UPDATE_PUBLISHER_URL,
				method: "POST",
				data: publisher
			})
		},
		
		deletePublisherService: function(publisher){
			return $http({
				url: publisherConstants.DELETE_PUBLISHER_URL,
				method: "POST",
				data: publisher
			})
		},
		
		searchPublisherByName: function(searchString){
			var getPublisherData = {};
			return $http({
				url: publisherConstants.SEARCH_PUBLISHER_URL+searchString
			}).success(function(data){
				getPublisherData = data;
			}).then(function(){
				return getPublisherData;
			})
		}
	}
})