/**
 * This service JS contain the functionality of user login and changed password 
 */

tnwApp.service('loginservice', function ($resource,$location,$rootScope, $q, $location) {
	
 
	
	this.authenticate= function(user){
		    var userLogin = $resource(WSBASEURL+'usermaintenance/login');
			userLogin.save(JSON.stringify(user), function(response,headers){
					$rootScope.$broadcast("messageAppend", {loginstatus: response,flag: "loginResponse"});
			},function(err){
			     console.log(err);
			});
    }; 
    
    this.getUserInfo= function(){
	    var userLogin = $resource(WSBASEURL+'usermaintenance/getUserDetail');
		userLogin.save({}, function(response,headers){
				$rootScope.$broadcast("responseStatus", {userData: response, flag:1});
		},function(err){
		     console.log(err);
		});
    }; 
    
    
    
    this.getUserDetail= function(){
	    var userLogin = $resource(WSBASEURL+'usermaintenance/getUserDetail');
		userLogin.save({}, function(response,headers){
				//alert(JSON.stringify(response.Data));
				if(angular.equals(response.ResponseCode,'5006')){
					alert("Your Session has been expired !! Please login again");
					$location.url('/home');
				} 
				$rootScope.$broadcast("messageAppendRightMenu", {userstatus: response.Data});
		},function(err){
		     console.log(err);
		});
    }; 
    
    this.changePassword= function(user){
    	var message;
    	
		var userLogin = $resource(WSBASEURL+'usermaintenance/changePassword');
		userLogin.save(JSON.stringify(user), function(response,headers){
			$rootScope.$broadcast("messageAppend", {changepasswordstatus: response,flag: "changePasswordResponse"});
		},function(err){
		      console.log(err);
		});
    }; 
    
    
    this.forgetpassword= function(user){
    	var message;
    	var userLogin = $resource(WSBASEURL+'usermaintenance/forgetPassword');
		userLogin.save(JSON.stringify(user), function(response,headers){
			$rootScope.$broadcast("forgetPassword", {forgetpasswordstatus: response,flag: "forgetPasswordResponse"});
		},function(err){
		      console.log(err);
		});
    }; 
    
    
   
 
});
