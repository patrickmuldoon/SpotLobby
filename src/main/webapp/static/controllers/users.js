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
					window.alert("Invalid form input. Try Again");
					$window.location.reload();
				}
			});
		}
			var password = document.getElementById("password");
			var confirm_password = document.getElementById("confirm_password");
			
			function validatePassword(){
				  if(password.value != confirm_password.value) {
				    confirm_password.setCustomValidity("Passwords Don't Match");
				  } else {
				    confirm_password.setCustomValidity('');
				  }
				}

				password.onchange = validatePassword;
				confirm_password.onkeyup = validatePassword;
				
		var username = document.getElementById("username");
		
		$scope.checkUsernameAvailibility = function(){
			$scope.uname = username.value;
			if($scope.uname.length > 0){
				console.log($scope.uname);
				$http({
					method: "POST", url: "users/CheckUsername", data: $scope.uname
				}).then(function successCallback(response){
					if(response.status === 200){
						$scope.usernameStatus = "Username Unavailable";
					}
					if(response.status === 202){
						$scope.usernameStatus = 'Username Available'
					}
				}, function errorCallback(response){
					
				});
			}
		}
		
		
		$scope.addClass = function(usernameStatus){
			if(usernameStatus == 'Username Unavailable'){
				return 'response not-exists';
			}else{
				return 'hide';
			}
		}
		
		
	});