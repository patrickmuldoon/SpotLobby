angular.module("SpotLobby")
	.controller("profileCtrl", function($http, $scope, $location, $window){
		$scope.hasPhoto = false;
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
    					$scope.userProfile = response.data;
    					if($scope.userProfile.image != null && $scope.userProfile.image != undefined){
    						$scope.hasPhoto = true;
    					}
    					console.log(response.data);
    				}
    			}, function error(response){
    				if(response.status === 400){
    					window.alert("no profile information found");
    				}
    			});
    		}
    	}, function error(response){
    		if(response.status === 401){
    			window.alert("You must be logged in to view your profile");
    			$location.path("/login");
    		}
    	});
		
	$scope.updateUserPicture = function (){ 
		$http({
		method: "POST", url: "profile/UpdateUserProfileImage", data: $scope.users
		}).then(function successCallback(response){
			console.log(response)
			if(response.status === 201){
				window.alert("Picture has been added to database and updated");
				$window.location.reload();
			}
		}, function errorCallback(response){
			if(response.status === 400){
				console.log(response);
				window.alert("unsuccessful changing photo");
				$window.location.reload();
			}
		})
	}
});