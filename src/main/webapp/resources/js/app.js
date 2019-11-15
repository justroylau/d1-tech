

var App = angular.module('techApp', ['ui.router','tm.pagination','ngSanitize','ngCookies']);

App.config(['$stateProvider', '$urlRouterProvider',function ($stateProvider, $urlRouterProvider) {
	$urlRouterProvider.otherwise('/home');
	$stateProvider
		.state('home',{
			url:'/home',
			templateUrl: 'view/home.html',
		    controller: HomeController
		});
}]);