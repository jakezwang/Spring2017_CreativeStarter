
 
var serviceCCModule = angular.module('serviceCCModule');
serviceCCModule.controller('serviceCCController', function($scope,$rootScope,$location,serviceCCService) {
	var serviceCCCtrl = this;
	
	$scope.ccId = $rootScope.ccIdForService;
	
	console.log('$rootScope.ccIdForService ' + $rootScope.ccIdForService);
	console.log('$scope.ccId ' + $scope.ccId);
	serviceCCCtrl.init = function() {
		serviceCCService.getService($scope.ccId, null,
				function(reponseData) {
					$scope.message = "Success";
		    		$scope.error = false;
		    		serviceCCCtrl.services = reponseData;
		    		console.log('-----Data Success!!-----');
		    		console.log(serviceCCCtrl.services);
				},
				function(reponseData) {
					$scope.message = "Failed to load data";
		    		$scope.error = true;   
				}
		);
	}
	
	serviceCCCtrl.back = function() {
		if ($rootScope.globals.userSession.role == 'Admin') {
	    	$location.path('/admin/dashboard');
	    } else if ($rootScope.globals.userSession.role == 'Customer') {
	    	$location.path('/customer/creditcard');
	    } else if ($rootScope.globals.userSession.role == 'Creator'){
	    	$location.path('/creator/dashboard');
	    } else {
	    	$location.path('/dashboard');
	    }
	}
	
});



serviceCCModule.factory('serviceCCService', function($http,$timeout,APP_CONSTANT) {
	var serviceCCService = {};
	
	serviceCCService.getService = function (ccId, data, callback , callbackError) {
		console.log('ccId: ' + ccId);
		 $http.get(
	 			APP_CONSTANT.REMOTE_HOST+'/user/service/getbycc/'+ccId)
	 			.success(function (data, status, headers, config) { callback(data);console.log(data); })
				.error(function (data, status, headers, config) { if(status== 422){ callbackError(data); }
				});
		 
	};
	
	return serviceCCService;
});
