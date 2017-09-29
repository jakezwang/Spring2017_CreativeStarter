/**
 * 
 */
var donateModule = angular.module('donateModule');
donateModule.controller('donateContoller', function($scope,$rootScope,$location,donateService) {
	var donateCtrl = this;
	$scope.message = "This is donate";
	donateCtrl.message="This is fun";
	
	donateCtrl.ccId = "";
	donateCtrl.inputs = {
			paymentAmount: "",
			paymentDate: new Date(), 
			paymentStatus: "Paid"
	}
	
	
	$scope.donateProjectId = $rootScope.donateProjectId;
	
	//---------------------Init ------------------------------
	
	donateCtrl.getCC = function () {
		donateService.getCC($rootScope.userSession.id, null,
				function(reponseData) {
					$scope.message = "Success";
		    		$scope.error = false;
		    		donateCtrl.ccs = reponseData;
				},
				function(reponseData) {
					$scope.message = "Failed to load data";
		    		$scope.error = true;   
				}
		);
	};
	
	//--------------------Pay or Cancel--------------------
	donateCtrl.pay = function() {
		$scope.donateCcId = donateCtrl.ccId;
		console.log("ccId in Pay function: " + donateCtrl.ccId);
		console.log("donateCtrl.inputs is : " + donateCtrl.inputs);
		donateService.addDonate($scope.donateProjectId, donateCtrl.ccId , donateCtrl.inputs,
				function(reponseData) {
					$location.path('/dashboard');
				},
				function(reponseData) {
					
				}
		);
		alert('Thank you for the donation!!!');
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
	
	donateCtrl.back = function() {
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


donateModule.factory('donateService', function($http,$timeout,APP_CONSTANT) {
	var donateService = {};
	
	donateService.addDonate = function (projectId, ccId, data, callback , callbackError) {
		console.log('ccId: ' + ccId);
		 $http.post(
	 			APP_CONSTANT.REMOTE_HOST+'/user/service/add/'+ projectId +'/'+ ccId, data)
	 			.success(function (data, status, headers, config) { callback(data);console.log(data); })
				.error(function (data, status, headers, config) { if(status== 422){ callbackError(data); }
				});
		 
	};
	
	donateService.getCC = function (id, data, callback,callbackError) {
		 $http.get(
	 			APP_CONSTANT.REMOTE_HOST+'/cc/'+id+'/get')
	 			.success(function (data, status, headers, config) { callback(data); })
				.error(function (data, status, headers, config) { // IF STATUS CODE NOT 200
						if(status== 422){ callbackError(data);  }
				});
	};
	
	return donateService;
});
