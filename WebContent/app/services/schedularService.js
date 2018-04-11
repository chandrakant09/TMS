ondemand.service('schedularService', function ($http, $resource, $rootScope) {
	var sMessage={};
	var self=this;
	
	
	this.getSchedulingDetail= function(projectId){
		   var scheduleInfo = $resource(WSBASEURL+'projectservice/getExecution?ID=:projectId',{"projectId":projectId});
		   scheduleInfo.get({}, function(response,headers){
			   	$rootScope.$broadcast("smessageupdated", {message: response});
			},function(err){
			     console.log(err);
			});
	}; 
	    
    // To schedule execution
    this.scheduleExecution= function(schedularObj, pId){
    	 var resp ='';
   	  	 var paramData = {
   		 
   		   "Id" : pId,
   		   "ExecutionDetails":{"OneTime": schedularObj.oneTimeExecution,
					   			"Periodically": schedularObj.selectedOption.id,
					     		  "OnDay": schedularObj.selectedOnDay.id}
         };
   	  
   	  	 
   	  	 
   	  	 
   	   	$http.post(WSBASEURL+'projectservice/execute', paramData)
           .success(function (response, status, headers) {
           	resp = response.ResponseCode;
           	
           	if(angular.equals(resp, '5053')){ $rootScope.$broadcast("smessageupdated" + pId, {message: resp});}
           	if(angular.equals(resp, '4006')){ $rootScope.$broadcast("smessageupdated" + pId, {message: resp});}
         	if(angular.equals(resp, '5081')){ $rootScope.$broadcast("smessageupdated" + pId, {message: resp});}
         	if(angular.equals(resp, '5056')){ $rootScope.$broadcast("smessageupdated" + pId, {message: resp});}
           	if(angular.equals(resp, '8001')){ $rootScope.$broadcast("smessageupdated" + pId, {message: 'success'});
           				self.checkFileStatusForScheduleExecutionForProject(pId);}       	           		
         }).error(function (response, status, header) {
           	resp = response.ResponseCode; 
           	$rootScope.$broadcast("smessageupdated"  + pId, {message: resp});
           });
   	
    };
    
       
    
    // to check schedule execution for a project/project ID
    this.checkFileStatusForScheduleExecutionForProject = function(pID){
    //	alert('in check status' + pID);
    	   	 var resp ='';
    	   	 var id= pID;
        	 var userResource = $resource(WSBASEURL+'projectservice/getExecutionStatus?ID=:ID', {ID: id});   // change the name
        	 userResource.get(id,function(response,headers){
    				 resp = response.ResponseCode.toString();
    				 if(angular.equals(resp, '5054')){ $rootScope.$broadcast("smessageupdated" + id, {message: resp}); }
    				 if(angular.equals(resp, '5055')){ $rootScope.$broadcast("smessageupdated" + id, {message: resp}); }
    				 if(angular.equals(resp, '4006')){ $rootScope.$broadcast("smessageupdated" + id, {message: resp}); }
    	    	   		if(angular.equals(resp, '8002')){ 
    	    	   			$rootScope.$broadcast("smessageupdated" + id, {message: resp});
    	       	   		}	
    	    	   		if(angular.equals(resp, '8003')){ $rootScope.$broadcast("smessageupdated" + id, {message: resp}); }
    	    	   		if(angular.equals(resp, '8004')){ $rootScope.$broadcast("smessageupdated" + id, {message: resp}); }
    	    	   		if(angular.equals(resp, '8001')){ $rootScope.$broadcast("smessageupdated" + id, {message: resp});}
    	    	   		if(angular.equals(resp, '8005')){ $rootScope.$broadcast("smessageupdated" + id, {message: resp});}
    	    	   		if(angular.equals(resp, '8006')){ $rootScope.$broadcast("smessageupdated" + id, {message: resp});}
    	    	   		if(angular.equals(resp, '8007')){ $rootScope.$broadcast("smessageupdated" + id, {message: resp});}
    	    	   		if(angular.equals(resp, '8008')){ $rootScope.$broadcast("smessageupdated" + id, {message: resp});}
    	    	   		if(angular.equals(resp, '5053')){ $rootScope.$broadcast("smessageupdated" + id, {message: resp}); }
    	    	   		
    			},
    			    function(err){
    			      // error callback
    			      console.log(err);
    			      $rootScope.$broadcast("smessageupdated" + id, {message: "error"});
    		 });
    	   	 
     };
    
    
   
    
});
