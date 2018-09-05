angular.module("SpotLobby")
	.factory('userData', function(){
		
		var data = {
			UserName: '',
			FirstName: '',
			LastName: '',
			Followers: [],
			Following: [],
			Bio: '',
			Image: ''
		};
		
		return {
	        getFirstName: function () {
	            return data.FirstName;
	        },
	        setFirstName: function (firstName) {
	            data.FirstName = firstName;
	        },
	        getLastName: function(){
	        		return data.LastName;
	        },
	        setLastName: function (lastname){
	        		data.LastName = lastname;
	        },
	        getUserName: function(){
        			return data.Username;
	        },
	        setUserName: function (username){
	        		data.Username = username;
	        },
	        getFollowers: function(){
	        		return data.Followers;
	        },
	        setFollowers: function(followers){
	        		data.Followers = followers;
	        },
	        getFollowing: function(){
	        		return data.Following;
	        },
	        setFollowing: function(following){
	        		data.Following = following;
	        },
	        getBio: function() {
	        		return data.Bio;
	        },
	        setBio: function(bio) {
	        		data.Bio = bio;
	        },
	        getImage: function() {
	        		return data.Image;
	        },
	        setImage: function(image) {
	        		data.Image = image;
	        }
	    };
	});