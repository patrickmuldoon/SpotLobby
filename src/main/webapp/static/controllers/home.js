angular.module("SpotLobby")
	.controller("homeCtrl", function($scope, $location, $window, $http, $rootScope){
		$scope.authenticated = false;
		$scope.isAdmin = false;
	});