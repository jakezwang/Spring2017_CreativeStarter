/**
 * 
 */
angular.module('app')
.constant('AUTH_EVENTS', {
	loginSuccess: 'auth-login-success',
	logoutSuccess: 'auth-logout-success',
	loginFailed:'auth-login-falied',
	notAuthenticated : 'auth-not-authenticated',
	notAuthorized : 'auth-not-authorized',
	sessionTimeout: 'auth-session-timeout',
	
	registerSuccess: 'auth-register-success',
	registerFailed: 'auth-register-failed',
	
	projectAddFailed: 'project-add-failed',

})
.constant('APP_CONSTANT',{
		DEMO:false,
		
		REMOTE_HOST:'http://localhost:8180/AnguarRESTApplication/rest'
});