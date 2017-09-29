
 
var serviceProjectModule = angular.module('serviceProjectModule');
serviceProjectModule.controller('serviceProjectController', function($scope,$rootScope,$location,serviceProjectService) {
	var serviceProjectCtrl = this;
	
	$scope.projectId = $rootScope.projectIdForService;
	
	serviceProjectCtrl.init = function() {
		console.log('Service Init Called: ' + $rootScope.projectIdForService);
		serviceProjectService.getService($scope.projectId, null,
				function(reponseData) {
					$scope.message = "Success";
		    		$scope.error = false;
		    		serviceProjectCtrl.services = reponseData;
				},
				function(reponseData) {
					$scope.message = "Failed to load data";
		    		$scope.error = true;   
				}
		);
	}
	
	serviceProjectCtrl.back = function() {
		if ($rootScope.globals.userSession.role == 'Admin') {
	    	$location.path('/admin/dashboard');
	    } else if ($rootScope.globals.userSession.role == 'Customer') {
	    	$location.path('/customer/dashboard');
	    } else if ($rootScope.globals.userSession.role == 'Creator'){
	    	$location.path('/creator/dashboard');
	    } else {
	    	$location.path('/dashboard');
	    }
	}
	
});



serviceProjectModule.factory('serviceProjectService', function($http,$timeout,APP_CONSTANT) {
	var serviceProjectService = {};
	
	serviceProjectService.getService = function (projectId, data, callback , callbackError) {
		 $http.get(
	 			APP_CONSTANT.REMOTE_HOST+'/user/service/getbyproject/'+projectId)
	 			.success(function (data, status, headers, config) { callback(data); })
				.error(function (data, status, headers, config) { if(status== 422){ callbackError(data); }
				});
	};
	
	return serviceProjectService;
});
