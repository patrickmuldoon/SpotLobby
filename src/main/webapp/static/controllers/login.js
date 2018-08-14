angular.module("SpotLobby")
	.controller("loginCtrl", function($scope, $location, $window, $http, $rootScope){
		$rootScope.loggedIn = false;
//		$scope.authenticated = false;
//		$scope.isAdmin = false;
		$scope.loginUser = function() {
			$http({
				method : "POST",
				url : "login",
				data : $scope.users
			}).then(function(response) {
				if(response.status === 200){
					console.log(response);
					$rootScope.loggedInUser = response;
					$rootScope.loggedIn = true;
					$window.location.reload();
					$location.path("/home");
				}
				if(response.status === 204) {
					window.alert("invalid login credentials, try again!");
					$window.location.reload();
				}
			});
		}
	});