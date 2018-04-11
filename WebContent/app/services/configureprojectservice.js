/**
 * This service JS contain the functionality of user login and changed password 
 */

ondemand.service('configureprojectservice', function ($resource,$location,$rootScope) {
	
 	this.getConfigDetail= function(projectId){
 		   var projectsList = $resource(WSBASEURL+'projectservice/getProjectConfig?projectId=:projectId',{"projectId":projectId});
 		   projectsList.get({}, function(response,headers){
 			   	$rootScope.$broadcast("projectdata", {projectdata: response,flag : 1});
			},function(err){
			     console.log(err);
			});
    }; 
    
   
    
    this.saveConfigDetail= function(configData){
    	var saveProject = $resource(WSBASEURL+'projectservice/saveProjectConfig');
    		//alert("Befour Calling 1:" +angular.toJson(configData));
    		saveProject.save(angular.toJson(configData), function(response,headers){
    		//	alert("Response 1:"+JSON.stringify(response));
    			$rootScope.$broadcast("projectdata", {projectdata: response,flag : 2});
    	},function(err){
		     console.log(err);
		});
    }; 
    
    this.saveConfigResult= function(configData){
    	//alert("Befour Calling 2:" +angular.toJson(configData));
    	var saveConfigResult = $resource(WSBASEURL+'projectservice/saveProjectConfig');
    		saveConfigResult.save(angular.toJson(configData), function(response,headers){
    		//	alert("Response 2:"+JSON.stringify(response));
    			$rootScope.$broadcast("projectdata", {projectdata: response,flag : 3});
    	},function(err){
		     console.log(err);
		});
    }; 
    
});
