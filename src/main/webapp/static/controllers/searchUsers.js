angular.module("SpotLobby")
	.controller("searchUsersCtrl", function($scope, $location, $window, $http, $rootScope){
			$http({
				method : "GET",
				url : "users/findAll"
			}).then(function(response){
				if(response.status === 200){
					console.log(response.data);
					$scope.users = response.data;
					$scope.users.follower = response.data.follower;
					$scope.users.following = response.data.following;
				}
			}, function error(response){
				if(response.status === 400){
					//do something here
				}
			});
	});