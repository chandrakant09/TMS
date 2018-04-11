tnwApp.service('registrationService', function ($resource, $http, $rootScope) {
	
	//To save a new user   	
	this.saveUser= function(user){
			 var newUser = $resource(WSBASEURL+'usermaintenance/register');
			 newUser.save(angular.toJson(user), function(response,headers){
				 	$rootScope.$broadcast("messageupdated", {response: response,flag:2});
			 },
			 function(err){
			      	console.log(err);
			 });
			
    };  
    
    
  //Add Truck 	
	this.addTruck= function(truck){
			 var newUser = $resource(WSBASEURL+'usermaintenance/addTruck');
			 newUser.save(angular.toJson(truck), function(response,headers){
				 	$rootScope.$broadcast("messageupdated", {response: response,flag:5});
			 },
			 function(err){
			      	console.log(err);
			 });
			
    }; 
    
    
    
    //To Update a  user Profile
	this.updateUserProfile= function(user){
			 var updateUser = $resource(WSBASEURL+'usermaintenance/updateUserProfile');
			 updateUser.save(angular.toJson(user), function(response,headers){
				 	$rootScope.$broadcast("messageupdated", {response: response,flag:4});
			 },
			 function(err){
				 	console.log(err);
			 });
			
    };
    
    
   
    //To save a new user   	
	this.disttList= function(){
			 var newUser = $resource(WSBASEURL+'master/getDistt');
			 newUser.get({}, function(response,headers){
				$rootScope.$broadcast("messageupdated", {response: response,flag:3});
			 },
			 function(err){
			      console.log(err);
			 });
    };  
   
    
});
