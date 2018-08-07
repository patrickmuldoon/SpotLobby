var app = angular.module("SpotLobby", ["ngRoute"]);

angular.module("SpotLobby")
	.config(function($locationProvider, $routeProvider) {
		
		$locationProvider.hashPrefix("");
		$routeProvider
		.when("/home", {
			templateUrl: "pages/home.html",
			controller: "homeCtrl"
		})
		.when("/user", {
			templateUrl: "pages/users.html",
			controller: "usersCtrl"
		})
		.when("/signup", {
			templateUrl: "pages/signup.html",
			controller: "usersCtrl"
		})
		.when("/login", {
			templateUrl: "pages/login.html",
			controller: "loginCtrl"
		})
		.when("/profile", {
			templateUrl: "pages/profile.html", 
			controller: "profileCtrl"
		}).otherwise({
			templateUrl: "pages/home.html",
		});
	});