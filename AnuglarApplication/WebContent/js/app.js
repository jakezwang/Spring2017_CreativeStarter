
/**
 * 
 */
'use strict';
// Step 1: declare modules
 angular.module("authModule",[]);
 angular.module("dashboardModule",[]);
 angular.module("personalModule",[]);
 angular.module("skillModule",[]);
 angular.module("projectModule",[]);
 angular.module("homeModule",[]);
 angular.module("registrationModule",[]);
 angular.module("serviceProjectModule",[]);
 angular.module("serviceCCModule",[]);
 angular.module("donateModule",[]);
 angular.module("personalModule",[]);



 



 angular.module('appCoreModule', [
	 'ngRoute',
     'ngCookies'
 ]);
//Step 2: Register App
var app = angular.module("app", 
		[
		 'appCoreModule',
		 'homeModule',
		 'authModule',
		 'personalModule',
		 'skillModule',
		 'projectModule',
		 
		 'registrationModule',
		 'serviceProjectModule',
		 'serviceCCModule',
		 'dashboardModule',
		 'donateModule',
		 'personalModule'
		 ]);