/**
 * This service JS contain the functionality of user login and changed password 
 */

tnwApp.service('dashboardservice', function ($resource,$location,$rootScope,$http) {
	
	
	
	this.getWallet= function(){
		   var projectsList = $resource(WSBASEURL+'transactionmaintenance/walletData');
		   projectsList.save({}, function(response,headers){
			   $rootScope.$broadcast("dashboardData", {response: response,flag:1});
			},function(err){
			     console.log(err);
			});
    };  
    
    this.walletRecharge= function(rechargeDTO){
    	 alert(rechargeDTO);
    	 var rechargeRequest = $resource(WSBASEURL+'transactionmaintenance/walletrecharge');
    	 rechargeRequest.save(rechargeDTO, function(response,headers){
		 //	 $rootScope.$broadcast("dashboardData", {response: response,flag:2});
		 },function(err){
		    console.log(err);
		});
    };  
    
    this.chequeRechage= function(cheque){
    	// alert(JSON.stringify(project));
    	 var projectsList = $resource(WSBASEURL+'transactionmaintenance/savechequeDetails');
		 projectsList.save(JSON.stringify(cheque), function(response,headers){
			 $rootScope.$broadcast("rechargeres", {response: response,flag:2});
		},function(err){
		    console.log(err);
		});
    };  
    
    this.cashRechage= function(cash){
    	// alert(JSON.stringify(project));
    	 var projectsList = $resource(WSBASEURL+'transactionmaintenance/savecashDetails');
		 projectsList.save(JSON.stringify(cash), function(response,headers){
			 $rootScope.$broadcast("rechargeres", {response: response,flag:2});
			    
		},function(err){
		    console.log(err);
		});
    };
    
    this.demandDraftRechage= function(demandDraft){
    	// alert(JSON.stringify(project));
    	 var projectsList = $resource(WSBASEURL+'transactionmaintenance/savedemandDraftDetails');
		 projectsList.save(JSON.stringify(demandDraft), function(response,headers){
			 $rootScope.$broadcast("rechargeres", {response: response,flag:2});
			    
		},function(err){
		    console.log(err);
		});
    };
    
    //Codes of dash board
    
    this.admindashboarddata= function(){
    	// alert(JSON.stringify(project));
    	 var projectsList = $resource(WSBASEURL+'dashboard/adminDashoardData');
		 projectsList.save({}, function(response,headers){
			 $rootScope.$broadcast("dashboardData", {response: response});
			    
		},function(err){
		    console.log(err);
		});
    };
    
//Codes of dash board
    
    this.userdashboarddata= function(){
    	// alert(JSON.stringify(project));
    	 var projectsList = $resource(WSBASEURL+'dashboard/userDashoardData');
		 projectsList.save({}, function(response,headers){
			 $rootScope.$broadcast("dashboardData", {response: response});
			    
		},function(err){
		    console.log(err);
		});
    };
    
//Codes of dash board
    
    this.getUserAutocompleteData= function(obj){
    	// alert(JSON.stringify(project));
    	 var projectsList = $resource(WSBASEURL+'dashboard/getUserAutocompleteData');
		 projectsList.save({}, function(response,headers){
				 $rootScope.$broadcast("rechargeres", {response: response.Data.UserList,flag:1});
		},function(err){
		    console.log(err);
		});
    };
    
   
    this.updateTransactionUpdate= function(transactionDetails){
    	var projectsList = $resource(WSBASEURL+'transactionmaintenance/trasactionStatusUpdate');
		projectsList.save(angular.toJson(transactionDetails), function(response,headers){
			 $rootScope.$broadcast("RechargeFilterList", {response: response,flag:2});
			    
		},function(err){
		    console.log(err);
		});
    }; 
   
 // Code for Super Admin
    
    this.getAutocomplete= function(obj){
    	// alert(JSON.stringify(project));
    	 var projectsList = $resource(WSBASEURL+'dashboard/getUserAutocompleteData');
		 projectsList.save({}, function(response,headers){
			// obj=response.Data.UserList;
			 $rootScope.$broadcast("responseStatus", {response: response.Data.UserList,flag:1});
		},function(err){
		    console.log(err);
		});
    };
     
    this.getUserInfo= function(userId){
	    var userLogin = $resource(WSBASEURL+'dashboard/getUserDetails');
		userLogin.save({UserId:userId}, function(response,headers){
				$rootScope.$broadcast("responseStatus", {userData: response, flag:2});
		},function(err){
		     console.log(err);
		});
    }; 
    
 
});
