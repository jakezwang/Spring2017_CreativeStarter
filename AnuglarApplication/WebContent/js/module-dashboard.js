/**
 * 
 */
var dashboardModule = angular.module('dashboardModule');
dashboardModule.controller('dashboardController', function($scope, $rootScope,$location,dashboardService) {
	var dashboardCtrl = this;
	dashboardCtrl.messageDash = "Welcome " + $rootScope.userSession.username;
	
	$rootScope.fields = {
		    money: ''
		}
	
	if ($rootScope.globals.userSession.role == 'Creator') {
		$('div#editprojects').show();
		$('div#allprojects').hide();
		$('div#editccs').hide();
		$('div#allccs').hide();
		$('div#editcat').hide();
	} else if ($rootScope.globals.userSession.role == 'Customer') {
		$('div#editprojects').hide();
		$('div#allprojects').show();
		$('div#editccs').show();
		$('div#allccs').hide();
		$('div#editcat').hide();
	} else {
		$('div#editprojects').show();
		$('div#allprojects').show();
		$('div#editccs').show();
		$('div#allccs').show();
		$('div#editcat').show();
	}
	
	dashboardCtrl.inputs = {
			projectName: "",
			projectDesc: "",
			amountGoal: "",
			dayStart:"",
			dayEnd:"",
			category:""
	}
	
	dashboardCtrl.inputsCC = {
			cardNumber: "",
			cardFullName: "",
			cardType: "",
			cardExpireDate:"",
			cardSecurity:""
	}
	
	dashboardCtrl.inputsCat = {
			categoryName: "",
			categoryShow: ""
	}
	
	dashboardCtrl.inputsService = {
			paymentAmount: "",
			paymentDate: "",
			paymentStatus: ""
	}
	
	dashboardCtrl.inputsKey = {
			projectId:"",
			ccId:""
	}
	
	//---------------------- Donate ------------------------------
	
	dashboardCtrl.donate = function (donateProjectId) {
		$rootScope.donateProjectId = donateProjectId;
		console.log($rootScope.donateProjectId);
		$location.path('/donate');
	};
	
	//-------------------------Service-----------------------------
	
	dashboardCtrl.viewService = function (projectIdForService) {
		$rootScope.projectIdForService = projectIdForService;
		console.log($rootScope.projectIdForService);
		$location.path('/service/viewbyproject');
	};
	
	dashboardCtrl.viewService2 = function (ccIdForService) {
		console.log("viewService 2's ccId is: " + ccIdForService);
		$rootScope.ccIdForService = ccIdForService;
		console.log($rootScope.ccIdForService);
		$location.path('/service/viewbycc');
	};
	
	dashboardCtrl.addService = function (projectId, ccId) {
		console.log(dashboardCtrl.inputsService);
		dashboardService.addService(projectId, ccId, dashboardCtrl.inputsService,
			function(reponseData) {
				$scope.message = "Success";
	    		$scope.error = false;
				dashboardCtrl.services = reponseData;
			},
			function(reponseData) {
				$scope.message = "Failed to load data";
	    		$scope.error = true;   
			}
		);
		if ($rootScope.globals.userSession.role == 'Admin') {
	    	$location.path('/admin/dashboard');
	    } else if ($rootScope.globals.userSession.role == 'Customer') {
	    	$location.path('/customer/dashboard');
	    } else if ($rootScope.globals.userSession.role == 'Creator'){
	    	$location.path('/creator/dashboard');
	    } else {
	    	$location.path('/dashboard');
	    }
	};
	
	//-------------------------Initialize----------------------
	dashboardCtrl.init = function(){
		dashboardService.listProject($rootScope.userSession.id,null,
			function(reponseData) {
				$scope.message = "Success";
	    		$scope.error = false;
				console.log(reponseData);
				dashboardCtrl.projects = reponseData;
			},
			function(reponseData) {
				$scope.message = "Failed to load data";
	    		$scope.error = true;   
			}
		);
	}
	
	dashboardCtrl.init1 = function(){
		dashboardService.listCC($rootScope.userSession.id,null,
			function(reponseData) {
				$scope.message = "Success";
	    		$scope.error = false;
				console.log(reponseData);
				dashboardCtrl.ccs = reponseData;
			},
			function(reponseData) {
				$scope.message = "Failed to load data";
	    		$scope.error = true;   
			}
		);
	}
	
	dashboardCtrl.init2 = function(){
		dashboardService.listAllProject(null,
			function(reponseData) {
				$scope.message = "Success";
	    		$scope.error = false;
				console.log(reponseData);
				dashboardCtrl.allprojects = reponseData;
			},
			function(reponseData) {
				$scope.message = "Failed to load data";
	    		$scope.error = true;   
			}
		);
	}
	
	dashboardCtrl.init3 = function(){
		dashboardService.listAllCC(null,
			function(reponseData) {
				$scope.message = "Success";
	    		$scope.error = false;
				console.log(reponseData);
				dashboardCtrl.allccs = reponseData;
			},
			function(reponseData) {
				$scope.message = "Failed to load data";
	    		$scope.error = true;   
			}
		);
	}
	
	//--------------------Category------------------------------
	
	dashboardCtrl.getCat = function () {
		dashboardService.getCat(null,
				function(reponseData) {
					$scope.message = "Success";
		    		$scope.error = false;
					dashboardCtrl.categories = reponseData;
				},
				function(reponseData) {
					$scope.message = "Failed to load data";
		    		$scope.error = true;   
				}
		);
	};
	
	dashboardCtrl.addCat = function (catName) {
		dashboardService.addCat(catName,null,
				function(reponseData) {
					$scope.message = "Success";
		    		$scope.error = false;
					dashboardCtrl.categories = reponseData;
				},
				function(reponseData) {
					$scope.message = "Failed to load data";
		    		$scope.error = true;   
				}
		);
		$location.path('/category');
	};
	
	dashboardCtrl.deleteCat = function (catName) {
		dashboardService.deleteCat(catName,null,
				function(reponseData) {
					$scope.message = "Success";
		    		$scope.error = false;
					dashboardCtrl.categories = reponseData;
				},
				function(reponseData) {
					$scope.message = "Failed to load data";
		    		$scope.error = true;   
				}
		);
		$location.path('/category');
	};
	
	//--------------------------Project --------------------------
	
	dashboardCtrl.viewProject = function (row) {
		console.log(row);
		$rootScope.project = row.project;
		console.log($rootScope.project);
		$location.path('/project/view');
	};
	
	
	dashboardCtrl.deleteProject = function (projectId) {
		dashboardService.deleteProject($rootScope.userSession.id,projectId,null,
			function(reponseData) {
				$scope.message = "Success";
	    		$scope.error = false;
				dashboardCtrl.projects = reponseData;
			},
			function(reponseData) {
				$scope.message = "Failed to load data";
	    		$scope.error = true;   
			}
		);
		if ($rootScope.globals.userSession.role == 'Admin') {
	    	$location.path('/admin/dashboard');
	    } else if ($rootScope.globals.userSession.role == 'Customer') {
	    	$location.path('/customer/dashboard');
	    } else if ($rootScope.globals.userSession.role == 'Creator'){
	    	$location.path('/creator/dashboard');
	    } else {
	    	$location.path('/dashboard');
	    }
	};
	
	dashboardCtrl.addProject = function () {
		console.log(dashboardCtrl.inputs);
		dashboardService.addProject($rootScope.userSession.id ,dashboardCtrl.inputs,
			function(reponseData) {
				$scope.message = "Success";
	    		$scope.error = false;
				dashboardCtrl.projects = reponseData;
			},
			function(reponseData) {
				$scope.message = "Failed to load data";
	    		$scope.error = true;   
			}
		);
		if ($rootScope.globals.userSession.role == 'Admin') {
	    	$location.path('/admin/dashboard');
	    } else if ($rootScope.globals.userSession.role == 'Customer') {
	    	$location.path('/customer/dashboard');
	    } else if ($rootScope.globals.userSession.role == 'Creator'){
	    	$location.path('/creator/dashboard');
	    } else {
	    	$location.path('/dashboard');
	    }
	};
	
	//--------------------------------Credit Card---------------------------------
	dashboardCtrl.deleteCC = function (ccId) {
		dashboardService.deleteCC($rootScope.userSession.id,ccId,null,
			function(reponseData) {
				$scope.message = "Success";
	    		$scope.error = false;
				dashboardCtrl.ccs = reponseData;
			},
			function(reponseData) {
				$scope.message = "Failed to load data";
	    		$scope.error = true;   
			}
		);
		if ($rootScope.globals.userSession.role == 'Admin') {
	    	$location.path('/admin/dashboard');
	    } else if ($rootScope.globals.userSession.role == 'Customer') {
	    	$location.path('/customer/creditcard');
	    } else if ($rootScope.globals.userSession.role == 'Creator'){
	    	$location.path('/creator/dashboard');
	    } else {
	    	$location.path('/dashboard');
	    }
	};
	
	dashboardCtrl.addCC = function () {
		dashboardService.addCC($rootScope.userSession.id ,dashboardCtrl.inputsCC,
			function(reponseData) {
				$scope.message = "Success";
	    		$scope.error = false;
				dashboardCtrl.ccs = reponseData;
			},
			function(reponseData) {
				$scope.message = "Failed to load data";
	    		$scope.error = true;   
			}
		);
		if ($rootScope.globals.userSession.role == 'Admin') {
	    	$location.path('/admin/dashboard');
	    } else if ($rootScope.globals.userSession.role == 'Customer') {
	    	$location.path('/customer/creditcard');
	    } else if ($rootScope.globals.userSession.role == 'Creator'){
	    	$location.path('/creator/dashboard');
	    } else {
	    	$location.path('/dashboard');
	    }
	};
	
	//--------------------------Multiple Pages---------------------------
	
	

});

dashboardModule.factory('dashboardService', function($http,$timeout,APP_CONSTANT) {
	var dashboardService = {};
	
	//----------------------Projects--------------------------------------
	
	dashboardService.listProject = function (id,data, callback,callbackError) {
		 $http.get(
	 			APP_CONSTANT.REMOTE_HOST+'/project/'+id+'/get')
	 			.success(function (data, status, headers, config) { callback(data); })
				.error(function (data, status, headers, config) { // IF STATUS CODE NOT 200
						if(status== 422){ callbackError(data);  }
				});
	};
	
	dashboardService.listAllProject = function (data, callback,callbackError) {
		 $http.get(
	 			APP_CONSTANT.REMOTE_HOST+'/project/getall')
	 			.success(function (data, status, headers, config) { callback(data); })
				.error(function (data, status, headers, config) { // IF STATUS CODE NOT 200
						if(status== 422){ callbackError(data);  }
				});
	};
	
	dashboardService.deleteProject = function (id, projectId, data , callback , callbackError) {
		 $http.get(
	 			APP_CONSTANT.REMOTE_HOST+'/project/'+id+'/'+projectId+'/delete')
	 			.success(function (data, status, headers, config) { callback(data); })
				.error(function (data, status, headers, config) { // IF STATUS CODE NOT 200
						if(status== 422){ callbackError(data);  }
				});
	};
	
	dashboardService.addProject = function (id, data , callback , callbackError) {
		 $http.post(
	 			APP_CONSTANT.REMOTE_HOST+'/project/'+ id + '/add' , data)
	 			.success(function (data, status, headers, config) { callback(data); })
				.error(function (data, status, headers, config) { // IF STATUS CODE NOT 200
						if(status== 422){ callbackError(data);  }
				});
	};
	
	//------------------------------------Credit Card---------------------------------------------------
	dashboardService.listAllCC = function (data, callback,callbackError) {
		 $http.get(
	 			APP_CONSTANT.REMOTE_HOST+'/cc/getall')
	 			.success(function (data, status, headers, config) { callback(data); })
				.error(function (data, status, headers, config) { // IF STATUS CODE NOT 200
						if(status== 422){ callbackError(data);  }
				});
	};
	
	dashboardService.listCC = function (id,data, callback,callbackError) {
		 $http.get(
	 			APP_CONSTANT.REMOTE_HOST+'/cc/'+id+'/get')
	 			.success(function (data, status, headers, config) { callback(data); })
				.error(function (data, status, headers, config) { // IF STATUS CODE NOT 200
						if(status== 422){ callbackError(data);  }
				});
	};
	
	dashboardService.deleteCC = function (id, ccId, data , callback , callbackError) {
		 $http.get(
	 			APP_CONSTANT.REMOTE_HOST+'/cc/'+id+'/'+ ccId+'/delete')
	 			.success(function (data, status, headers, config) { callback(data); })
				.error(function (data, status, headers, config) { // IF STATUS CODE NOT 200
						if(status== 422){ callbackError(data);  }
				});
	};
	
	dashboardService.addCC = function (id, data , callback , callbackError) {
		 $http.post(
	 			APP_CONSTANT.REMOTE_HOST+'/cc/'+ id + '/add' , data)
	 			.success(function (data, status, headers, config) { callback(data); })
				.error(function (data, status, headers, config) { // IF STATUS CODE NOT 200
						if(status== 422){ callbackError(data);  }
				});
	};
	//---------------------------------Category-------------------------------------
	
	dashboardService.getCat = function (data, callback,callbackError) {
		 $http.get(
	 			APP_CONSTANT.REMOTE_HOST+'/project/getcat')
	 			.success(function (data, status, headers, config) { callback(data); })
				.error(function (data, status, headers, config) { // IF STATUS CODE NOT 200
						if(status== 422){ callbackError(data);  }
				});
	};
	
	dashboardService.deleteCat = function (catName,data, callback,callbackError) {
		 $http.get(
	 			APP_CONSTANT.REMOTE_HOST+'/project/deletecat/'+ catName)
	 			.success(function (data, status, headers, config) { callback(data); })
				.error(function (data, status, headers, config) { // IF STATUS CODE NOT 200
						if(status== 422){ callbackError(data);  }
				});
	};
	dashboardService.addCat = function (catName, data, callback,callbackError) {
		 $http.get(
	 			APP_CONSTANT.REMOTE_HOST+'/project/addcat2/' + catName)
	 			.success(function (data, status, headers, config) { callback(data); })
				.error(function (data, status, headers, config) { // IF STATUS CODE NOT 200
						if(status== 422){ callbackError(data);  }
				});
	};
	//-----------------------------------------------------
	dashboardService.addService = function (projectId,ccId, data , callback , callbackError) {
		 $http.post(
	 			APP_CONSTANT.REMOTE_HOST+'/user/service/add/'+ projectId + '/' + ccId , data)
	 			.success(function (data, status, headers, config) { callback(data); })
				.error(function (data, status, headers, config) { // IF STATUS CODE NOT 200
						if(status== 422){ callbackError(data);  }
				});
	};
	
	return dashboardService;
});
