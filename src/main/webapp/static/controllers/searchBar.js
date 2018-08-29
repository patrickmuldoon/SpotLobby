angular.module("SpotLobby")
	.controller("searchBarCtrl", function($scope, $location, $window, $http, $rootScope){
		var searchItem = document.getElementById("searchBar");
		$rootScope.usersInDB = [];
		$scope.searchForUsers = function(){
			if(searchItem.value.length > 0){
				$http({
					method: "GET", url: "/users/searchForUsers/" + searchItem.value, data: angular.toJson(searchItem.value)
				}).then(function successCallback(response){
					if(response.status === 200){
						$rootScope.usersInDB = response.data;
						console.log($scope.usersInDB);
						//document.getElementById("myDropdown").classList.toggle("show");
					}
					if(response.status === 202){
						
					}
				}, function errorCallback(response){
					
				});
			}
			else{
				$rootScope.usersInDB = [];
				//document.getElementById("myDropdown").classList.toggle("hide");
			}
		}
	
		$rootScope.dropdownClass = function(){
			console.log($rootScope.usersInDB.length);
			if($rootScope.usersInDB.length > 0){
				return 'show';
			}else
				return "dropdown-content";
		}
		
	});