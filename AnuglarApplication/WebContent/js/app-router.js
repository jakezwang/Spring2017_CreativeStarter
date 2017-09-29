/**
 * 
 */
var configModule = angular.module('app'); // Please dont not use [], dependency 


configModule.config(function($routeProvider) {	
//    $urlRouterProvider.otherwise('/login');
	$routeProvider
    // route for the home page
	.when('/', {
		 templateUrl : 'partial/home.html',
	     controller  : 'homeController'
	})
	.when('/login', {
		 templateUrl : 'partial/login.html',
	     controller  : 'authController',
	     controllerAs: 'authCtrl'
	})
	.when('/registration', {
		 templateUrl : 'partial/registration.html',
	     controller  : 'registrationController',
	     controllerAs: 'regCtrl'
	})
	.when('/logout', {
		template :"",
		controller  : 'logoutController',
		redirectTo: '/' 
	})
	.when('/mymission', {
		template :"My Mission is to learn angular"
	})
	
	
    .when('/dashboard', {
        templateUrl : 'partial/dashboard.html',
        controller  : 'dashboardController',
        controllerAs: 'dashboardCtrl'
    })
    .when('/project/view', {
        templateUrl : 'partial/project.html',
        controller  : 'projectController'
    })
    .when('/service/viewbyproject', {
        templateUrl : 'partial/service/project.html',
        controller  : 'serviceProjectController',
        controllerAs: 'serviceProjectCtrl'
    })
    .when('/service/viewbycc', {
        templateUrl : 'partial/service/cc.html',
        controller  : 'serviceCCController',
        controllerAs: 'serviceCCCtrl'
    })
    .when('/skill', {
        templateUrl : 'partial/skill.html',
        controller  : 'skillContoller',
        controllerAs: 'skillCtrl'
    })
    .when('/personal', {
        templateUrl : 'partial/personal.html',
        controller  : 'personalContoller',
        controllerAs: 'personalCtrl'
    })
    .when('/donate', {
        templateUrl : 'partial/donate.html',
        controller  : 'donateContoller',
        controllerAs: 'donateCtrl'
    })
    
    
    .when('/category', {
        templateUrl : 'partial/admin/category.html',
        controller  : 'dashboardController',
        controllerAs: 'dashboardCtrl'
    })
    
    
    .when('/admin/dashboard', {
        templateUrl : 'partial/admin/dashboard.html',
        controller  : 'dashboardController',
        controllerAs: 'dashboardCtrl'
    })
    .when('/customer/dashboard', {
        templateUrl : 'partial/customer/dashboard.html',
        controller  : 'dashboardController',
        controllerAs: 'dashboardCtrl'
    })
    .when('/customer/creditcard', {
        templateUrl : 'partial/customer/creditcard.html',
        controller  : 'dashboardController',
        controllerAs: 'dashboardCtrl'
    })
    .when('/creator/dashboard', {
        templateUrl : 'partial/creator/dashboard.html',
        controller  : 'dashboardController',
        controllerAs: 'dashboardCtrl'
    })
    
    .otherwise({ redirectTo: '/' });
});


configModule.run(function ($rootScope, $location, $cookieStore,$window, $http,AUTH_EVENTS) {
	    	//Management 
	    	$rootScope.$on('$locationChangeStart', function (event, next, current) {
	            // redirect to login page if not logged in
	    		console.log(' Requested path '+$location.path());
	            if ( 
	            		!(
	            				$location.path() == '/' || 
	            				$location.path() == '/registration'|| 
	            				$location.path() == '/login' ||
	            				$location.path() == '/mymission'
	            		 ) && 
	            		 !$rootScope.globals.userSession) {
	            	console.log('Invalid failed');
	                $location.path('/');
	            }
	        });
	    	
			$rootScope.$on(AUTH_EVENTS.loginFailed, function(event, next){
		    		console.log('Login failed');
		        	 //$scope.message = "Login failed";
		    });
		
			$rootScope.$on(AUTH_EVENTS.logoutSuccess, function(event, next){
				console.log('Logout Success');
				$window.localStorage.removeItem("globals");
				$rootScope.userSession=null;
				$rootScope.$emit("CallParentMethod", {});
				
			});
			
			$rootScope.$on(AUTH_EVENTS.notAuthorized, function(event, next){
				console.log('notAuthorized');
				$window.localStorage.removeItem("globals");
				$rootScope.userSession=null;
				$rootScope.$emit("CallParentMethod", {});
				
			});
	    
		    $rootScope.$on(AUTH_EVENTS.loginSuccess, function(event, next){
				//$scope.message = "Login Success";
				console.log('Login success');
			    $window.localStorage.setItem("globals", angular.toJson($rootScope.globals));
			    //$rootScope.userSession=angular.toJson($rootScope.globals.userSession)
			    $rootScope.userSession = JSON.parse($window.localStorage.getItem("globals")).userSession;
			  
			    $rootScope.$emit("CallParentMethod", {});
//------------------------------------------------Login 1st seeing page--------------------------
			    if ($rootScope.globals.userSession.role == 'Admin') {
			    	$location.path('/admin/dashboard');
			    } else if ($rootScope.globals.userSession.role == 'Customer') {
			    	$location.path('/customer/dashboard');
			    } else if ($rootScope.globals.userSession.role == 'Creator'){
			    	$location.path('/creator/dashboard');
			    } else {
			    	$location.path('/dashboard');
			    }
		    
		    });
	    
		    // keep user logged in after page refresh
		    $rootScope.globals = $cookieStore.get('globals') || {};
		    if ($rootScope.globals.userSession) {
			    $window.localStorage.setItem("globals", angular.toJson($rootScope.globals));
			    $rootScope.userSession = JSON.parse($window.localStorage.getItem("globals")).userSession;
		    }

	});