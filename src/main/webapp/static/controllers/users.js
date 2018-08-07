angular.module("SpotLobby")
	.controller("usersCtrl", function($scope, $location, $window, $http, $rootScope){
		
			$scope.createNewUser = function(){
			$http({
				method: "POST",
				url: "users/createNewUser",
				data: $scope.users
			}).then(function successCallback(response){
				console.log(response);
				if(response.status === 201){
					window.alert("User Created");
					$location.path("/login");
				}
			}, function errorCallback(response){
				if(response.status === 400){
					console.log(response);
					window.alert("Invalid form input. Try Argain");
					$window.location.reload();
				}
			});
		}
	});