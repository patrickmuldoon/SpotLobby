angular.module("SpotLobby")
	.controller("profileCtrl", function($http, $scope, $location){
		$http({
    		method: "GET", url: "isLoggedIn"
    	}).then(function successCallback(response){
    		if(response.status === 200){
    			$scope.users = response.data;
    			console.log(response.data);
    			$http({
    				method: "GET", url: "profile/findProfileByUserId/" + $scope.users.userID, data: angular.toJson($scope.users.userID)
    			}).then(function(response){
    				if(response.status === 200){
    					$scope.userProfile = response.data
    					console.log(response.data);
    				}
    			}, function error(response){
    				if(response.status === 400){
    					window.alert("no profile information found");
    				}
    			});
    		}
    	}, function error(response){
    		if(response.status == 401){
    			window.alert("You must be logged in to view your profile");
    			$location.path("/login");
    		}
    	});
	});