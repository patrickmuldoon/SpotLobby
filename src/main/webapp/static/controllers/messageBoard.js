angular.module("SpotLobby")
	.controller("messageBoardCtrl", function($scope, $location, $window, $http, $rootScope){
		$http({
			method: "GET", url: "posts/findAllWithUsers"
		}).then(function(response){
			if(response.status === 200){
				$scope.posts = response.data;
				console.log(response.data);
				console.log(response.data.messageOwner);
			}else
				window.alert("Posts not loaded");
		});
	});