/**
 * This service JS contain the functionality of user login and changed password 
 */

tnwApp.service('logoutservice', function ($resource,$location) {
	
 
	
	this.logoutApplication= function(){
		   	var userLogout = $resource(WSBASEURL+'usermaintenance/logout ');
		    userLogout.get({}, function(response,headers){
		     		if(angular.equals(response.ResponseCode)){
						alert("User session is already expired");
					//	$location.url('home');
					}
					
					if(angular.equals(response.ResponseCode,'3005')){
					//	$location.url('home');
					}
			},function(err){
			     console.log(err);
			});
    };  
    
    
 
});
