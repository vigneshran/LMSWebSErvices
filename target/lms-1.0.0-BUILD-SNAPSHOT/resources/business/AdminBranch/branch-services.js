lmsApp.factory("branchService", function($http, branchConstants) {
	return {
		getAllBranchesService : function() {
			var getBranchData = {};
			return $http({
				url : branchConstants.GET_ALL_BRANCHES_URL
			}).success(function(data) {
				getBranchData = data;
			}).then(function() {
				return getBranchData;
			})
		},

		getBranchByPKService: function(branchId){
			var getBranchByPkData = {};
			return $http({
				url: branchConstants.GET_BRANCH_BY_PK_URL+branchId
			}).success(function(data){
				getBranchByPkData = data;
			}).then(function(){
				return getBranchByPkData;
			})
		}, 
		
		getInitBranchService: function(){
			var getBranchData = {};
			return $http({
				url: branchConstants.GET_BRANCH_INIT_URL
			}).success(function(data){
				getBranchData = data;
			}).then(function(){
				return getBranchData;
			})
		},
		
		addBranchService: function(branch){
			return $http({
				url: branchConstants.ADD_BRANCH_URL,
				method: "POST",
				data: branch
			})
		},
		
		updateBranchService: function(branch){
			return $http({
				url: branchConstants.UPDATE_BRANCH_URL,
				method: "POST",
				data: branch
			})
		},
		
		deleteBranchService: function(branch){
			return $http({
				url: branchConstants.DELETE_BRANCH_URL,
				method: "POST",
				data: branch
			})
		},
		
		searchBranchByName: function(searchString){
			var getBranchByPkData = {};
			return $http({
				url: branchConstants.SEARCH_BRANCHES_URL+searchString
			}).success(function(data){
				getBranchByPkData = data;
			}).then(function(){
				return getBranchByPkData;
			})
		}
	}
})