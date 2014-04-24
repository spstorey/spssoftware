'use strict';

var app = angular.module('app', ['ngResource','ngRoute']);

app.config(['$routeProvider', function($routeProvider) {
	$routeProvider.
		when('/', {templateUrl: 'spalsh.html', controller: SplashCtrl}).
		otherwise({redirectTo: '/'});
}]);


var SplashCtrl = function ($scope, $http) {

	$http.get('/rest/splash').success(function(data) {
        $scope.splash = data;
    });
};