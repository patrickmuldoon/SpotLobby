angular.module("SpotLobby")
	.controller("profileCtrl", function($http, $scope){
		$http({
    		method: "GET", url: "isLoggedIn"
    	}).then(function successCallback(response){
    		if(response.status == 200){
    			$scope.users = response.data;
    			$http({
    				method: "GET", url: "users/findById/" + $scope.users.userID, data: angular.toJson($scope.users.userID)
    			}).then(function(response){
    			console.log(response.data);
    			$scope.users = response.data;
    		});
    	}
    	}, function error(response){
    		if(response.status == 401){
    			window.alert("You must be logged in to view your profile");
    			$location.path("/login");
    		}
    	});
	});