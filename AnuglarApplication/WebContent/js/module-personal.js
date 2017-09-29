/**
 * 
 */
var personalModule = angular.module('personalModule');
personalModule.controller('personalContoller', function($scope,$rootScope,$location,personalService) {
	$scope.message = "This is personal";
	var personalCtrl = this;
	
	
	
	personalCtrl.init = function(){
		personalService.listPersonal($rootScope.userSession.id , personalCtrl.profile,
			function(reponseData) {
				personalCtrl.ret = reponseData;
				personalCtrl.profile = {
						userName: personalCtrl.ret.userName,
						firstName:'',
						lastName:personalCtrl.ret.lastName,
						password:'pass',
						type: personalCtrl.ret.role,
						email: personalCtrl.ret.email
						
				}
			},
			function(reponseData) {}
		);
	}
	
	
	
	personalCtrl.updateFirstName = function () {
		personalService.updateFirstName($rootScope.userSession.id , personalCtrl.profile,
				function(reponseData) {personalCtrl.ret = reponseData;},
				function(reponseData) {}
			);
	}

});



personalModule.factory('personalService', function($http,$timeout,APP_CONSTANT) {
	var personalService = {};
	
	personalService.listPersonal = function (id,data, callback,callbackError) {
		 $http.get(
	 			APP_CONSTANT.REMOTE_HOST+'/user/get/'+ id )
	 			.success(function (data, status, headers, config) { callback(data); })
				.error(function (data, status, headers, config) {if(status== 422){ callbackError(data);}
		 });
	};
	
	personalService.updateFirstName = function (id,data, callback,callbackError) {
		 $http.post(
	 			APP_CONSTANT.REMOTE_HOST+'/user/update/'+ id, data )
	 			.success(function (data, status, headers, config) { callback(data); })
				.error(function (data, status, headers, config) {if(status== 422){ callbackError(data);}
		 });
	};
	
	return personalService;
});