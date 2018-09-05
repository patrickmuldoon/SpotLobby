angular.module("SpotLobby")
	.controller("searchBarCtrl", function($scope, $location, $window, $http, $rootScope, $route, userData){
		var searchItem = document.getElementById("searchBar");
		$rootScope.usersInDB = [];
		$scope.searchForUsers = function(event){
			if(searchItem.value.length > 0){
				$http({
					method: "GET", url: "/users/searchForUsers/" + searchItem.value, data: angular.toJson(searchItem.value)
				}).then(function successCallback(response){
					if(response.status === 200){
						$rootScope.usersInDB = response.data;
						console.log($scope.usersInDB);
					}
					if(response.status === 202){
						
					}
				}, function errorCallback(response){
					
				});
			}
			else{
				$rootScope.usersInDB = [];
			}
		}
		
	
	
		$scope.dropdownClass = function(){
			if($rootScope.usersInDB.length > 0){
				return 'show';
			}else
				return "dropdown-content";
		}
		
		$scope.loadUserProfile = function(username){
			$http({
				method: "GET", url: "/users/findByUsername/" + username, data: angular.toJson(username)
			}).then(function successCallback(response){
				if(response.status === 200){
					userData.setFirstName(response.data.firstName);
					userData.setLastName(response.data.lastName)
					userData.setUserName(response.data.username);
					userData.setFollowers(response.data.followers);
					userData.setFollowing(response.data.followingUsers);
					userData.setBio(response.data.userProfile.bio);
					userData.setImage(response.data.userProfile.image);
					if($location.path() === "/searchedUsersProfile"){
						$route.reload();
					}
					$location.path("/searchedUsersProfile");
				}
			}, function errorCallback(response){
				if(response.status === 400){
					window.alert("Error finding User Information");
				}
			});
		}
		
	});