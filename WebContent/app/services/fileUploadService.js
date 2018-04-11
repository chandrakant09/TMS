ondemand.service('fileUploadService', ['$http', '$rootScope', '$resource',   function ($http,  $rootScope, $resource) {
	
	var fileID='';
	var self=this;
	
	// to upload a file 
	
    this.uploadFileToUrl = function(file, pId, ftype){
        var fd = new FormData();
        fd.append('myfile', file);
        var uploadUrl = WSBASEURL+"uploadFile/saveFile";
        
        $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
        .success(function(response, status){
        	var resp = response.ResponseCode.toString();
        	$rootScope.$broadcast("fmessageupdated" +  pId , {message: response});
        	if(angular.equals(resp, '200')){ 
        		fileID = response.Data.Id;
        		self.sendFileIdAndProjectId(fileID, pId, ftype);
        	}
        	 
        	
        	/*if(angular.equals(resp, '200')){ 
        		$rootScope.$broadcast("fmessageupdated" +  pId , {message: 'success'});
        		fileID = response.Data.Id;
        		self.sendFileIdAndProjectId(fileID, pId, ftype);
        	}
        	if(angular.equals(resp, '4050')){ 
        		$rootScope.$broadcast("fmessageupdated" +  pId, {message: resp});
        		}
        	
        	if(angular.equals(resp, '4006')){ 
        		$rootScope.$broadcast("fmessageupdated" +  pId, {message: resp});
        	}*/
        	
        })
        .error(function(response, status){
       	 	//var resp = response.ResponseCode;
       	    $rootScope.$broadcast("fmessageupdated" +  pId , {message: response});
       	 	//$rootScope.$broadcast("fmessageupdated" +  pId, {message:resp});
        });
    };
    
    
    
    // to check file upload status for a project/project ID
    this.checkFileUploadStatusForProject = function(pID){
    //	alert('in check status' + pID);
    	   	 var resp ='';
    	   	 var id= pID;
        	 var userResource = $resource(WSBASEURL+'projectservice/getFileUploadStatus?ID=:ID', {ID: id});
        	 userResource.get(id,function(response,headers){
    				 resp = response.ResponseCode.toString();
    				 
    				 
    				 if((angular.equals(resp, '4002')) || (angular.equals(resp, '4003'))){
    					 
    						if((angular.equals(resp, '4003'))){
    							$rootScope.$broadcast("fmessageupdated" + id, {message: response});
    						}				     					 
    					 self.checkFileUploadStatusForProject(pID);
    				 }
    				 
    				 $rootScope.$broadcast("fmessageupdated" + id, {message: response});
    				 
    				/* if(angular.equals(resp, '4004')){ 
 	    	   			$rootScope.$broadcast("fmessageupdated" + id, {message: response});
 	       	   		}	
 	    	   		if(angular.equals(resp, '4006')){ $rootScope.$broadcast("fmessageupdated" + id, {message: response}); }
 	    	   		if(angular.equals(resp, '4053')){ $rootScope.$broadcast("fmessageupdated" + id, {message: response}); }
 	    	   	    if(angular.equals(resp, '4054')){ $rootScope.$broadcast("fmessageupdated" + id, {message: response}); } 
    					    	   		*/
    	    	   		
    			},
    			    function(err){
    				
    			      // error callback
    			      console.log(err);
    			    //  $rootScope.$broadcast("fmessageupdated" + id, {message: "error"});
    			      
    		 });
    	   	 
     };
    
     
     
    // to send file id and project id
    this.sendFileIdAndProjectId = function(fileId, projectId, ftype){
    	 var resp ='';
    	  var paramData = {
    		  "UserFile": fileID,
    		  "Id": projectId,
    		  "Source" : ftype
          };
    	  
    	   	$http.post(WSBASEURL+'projectservice/saveFile', paramData)
            .success(function (response, status, headers) {
            	resp = response.ResponseCode;
            	
            	/*if(angular.equals(resp, '5052')){ $rootScope.$broadcast("fmessageupdated" +  projectId, {message: response});}
            	if(angular.equals(resp, '5053')){ $rootScope.$broadcast("fmessageupdated" +  projectId, {message: response});}
            	if(angular.equals(resp, '4051')){ $rootScope.$broadcast("fmessageupdated" +  projectId, {message: response});}*/
            	
            	if(angular.equals(resp, '4002')){ 
            		
            		self.checkFileUploadStatusForProject(projectId);
            	} 
            	$rootScope.$broadcast("fmessageupdated" +  projectId, {message: response});
          }) .error(function (response, status, header) {
            	resp = response.ResponseCode; 
            	$rootScope.$broadcast("fmessageupdated" +  projectId, {message: response});
            });
    	
    	
    	
    };
    
}]);