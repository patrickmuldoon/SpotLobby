angular.module("SpotLobby")
	.controller("loginCtrl", function($scope, $location, $window, $http, $rootScope){
		$scope.authenticated = false;
		$scope.isAdmin = false;
	});