angular.module("SpotLobby")
	.controller("searchedUsersProfileCtrl", function($scope, $location, $window, $http, $rootScope, Upload, userData) {
		
		$scope.hasPhoto = false;
		
		$scope.init = function(){
			$scope.user = userData;
			$scope.user;
			$scope.user.FirstName = '';
			$scope.user.LastName = '';
			$scope.user.UserName = '';
			$scope.user.Followers = [];
			$scope.user.Following = [];
			$scope.user.bio = '';
			$scope.user.image = undefined;
			$scope.user.FirstName = userData.getFirstName();
			$scope.user.LastName = userData.getLastName();
			$scope.user.UserName = userData.getUserName();
			$scope.user.Followers = userData.getFollowers();
			$scope.user.Following = userData.getFollowing();
			$scope.user.bio = userData.getBio();
			$scope.user.image = userData.getImage();
			if($scope.user.image != undefined && $scope.user.image != null && $scope.user.image != ''){
				$scope.hasPhoto = true;
			}
		}
		
		$scope.init();
	});