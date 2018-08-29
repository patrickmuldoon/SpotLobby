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
		
		//check and see if username is already taken
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
		
		
		//if username is already taken load error message in dom
		$scope.addClassUsername = function(usernameStatus){
			if(usernameStatus == 'Username Unavailable'){
				return 'response not-exists';
			}else{
				return 'hide';
			}
		}
		
		
		var email = document.getElementById("email");
		
		//check if email is already in database
		$scope.checkEmailAvailibility = function(){
			if(email.value.length > 0){
				console.log(email.value);
				$http({
					method: "POST", url: "users/CheckEmail/" + email.value , data: angular.toJson(email.value)
				}).then(function successCallback(response){
					if(response.status === 200){
						$scope.emailStatus = "Email Unavailable";
					}
					if(response.status === 202){
						$scope.emailStatus = 'Email Available'
					}
				}, function errorCallback(response){
					
				});
			}
		}
		
		//if email is already taken, load error message in dom
		$scope.addClassEmail = function(emailStatus){
			if(emailStatus == 'Email Unavailable'){
				return 'response not-exists'
			}else{
				return 'hide';
			}
		}
		
		
	});