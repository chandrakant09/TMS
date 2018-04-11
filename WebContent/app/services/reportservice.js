 /**
 * This service JS contain the functionality of user login and changed password 
 */

tnwApp.service('reportservice', function ($resource,$location,$rootScope, $q) {
	
 

this.getuserswallet= function(obj){
	    var userLogin = $resource(WSBASEURL+'transactionmaintenance/userswalletData');
		userLogin.save({}, function(response,headers){
			$rootScope.$broadcast("walletList", {response: response});
			
		},function(err){
		     console.log(err);
		});
    }; 
    
    
    
this.getUsersList= function(){
    	// alert(JSON.stringify(project));
    	 var projectsList = $resource(WSBASEURL+'usermaintenance/getUsersList');
		 projectsList.save({}, function(response,headers){
			 $rootScope.$broadcast("userList", {response: response});
			    
		},function(err){
		    console.log(err);
		});
 }; 
 
 this.getTruckList= function(){
 	// alert(JSON.stringify(project));
 	 var projectsList = $resource(WSBASEURL+'usermaintenance/getTruckList');
		 projectsList.save({}, function(response,headers){
			 $rootScope.$broadcast("truckList", {response: response});
			    
		},function(err){
		    console.log(err);
		});
};


this.getTruckListDetail= function(){
	// alert(JSON.stringify(project));
	 var projectsList = $resource(WSBASEURL+'usermaintenance/getTrucklistdetail');
	 projectsList.save({}, function(response,headers){
		 $rootScope.$broadcast("truckListDetail", {response: response});
		    
	},function(err){
	    console.log(err);
	});
}; 

 
 this.getTransactionList= function(filter){
 	// alert(JSON.stringify(project));
 	 var projectsList = $resource(WSBASEURL+'transactionmaintenance/getTransactionList');
		 projectsList.save(filter, function(response,headers){
			 $rootScope.$broadcast("transactionList", {response: response});
			    
		},function(err){
		    console.log(err);
		});
}; 

this.getrechargeHisList= function(filter){
 	// alert(JSON.stringify(project));
 	 var projectsList = $resource(WSBASEURL+'transactionmaintenance/getRechargeList');
		 projectsList.save(filter, function(response,headers){
			 $rootScope.$broadcast("rechargeHisList", {response: response});
			    
		},function(err){
		    console.log(err);
		});
};  

/*this.getchequeHisList= function(){
 	// alert(JSON.stringify(project));
 	 var projectsList = $resource(WSBASEURL+'transactionmaintenance/getchequesList');
		 projectsList.save({}, function(response,headers){
			 $rootScope.$broadcast("hequeHisList", {response: response});
			    
		},function(err){
		    console.log(err);
		});
}; 
*/
this.chequesdata= function(){
	
	 var projectsList = $resource(WSBASEURL+'dashboard/chequeData');
	 projectsList.save({}, function(response,headers){
		 $rootScope.$broadcast("chequeData", {response: response});
		    
	},function(err){
	    console.log(err);
	});
};

this.getDemandDraftData= function(){
	
	 var projectsList = $resource(WSBASEURL+'dashboard/demandDraftData');
	 projectsList.save({}, function(response,headers){
		 $rootScope.$broadcast("ddData", {response: response});
		    
	},function(err){
	    console.log(err);
	});
};

this.getCashdeposit= function(){
	
	 var projectsList = $resource(WSBASEURL+'dashboard/cashData');
	 projectsList.save({}, function(response,headers){
		 $rootScope.$broadcast("cdData", {response: response});
		    
	},function(err){
	    console.log(err);
	});
};

this.getPendingRecharge= function(obj){
	 var rechargeFilterList = $resource(WSBASEURL+'dashboard/getPendingRechargeList');
	 rechargeFilterList.save(obj, function(response,headers){
		 $rootScope.$broadcast("RechargeFilterList", {response: response,flag:1});
		    
	},function(err){
	    console.log(err);
	});
};







this.getEdisttList= function(obj){
	// alert(JSON.stringify(project));
	 var projectsList = $resource(WSBASEURL+'dashboard/getEdisttList');
	 projectsList.save(JSON.stringify(obj), function(response,headers){
		 $rootScope.$broadcast("edisttList", {response: response});
		    
	},function(err){
	    console.log(err);
	});
};





this.getCashDetailstList= function(filter){
	// alert(JSON.stringify(project));
	 var projectsList = $resource(WSBASEURL+'dashboard/getCashDetailstList');
	 projectsList.save(filter, function(response,headers){
		 $rootScope.$broadcast("cashdetailList", {response: response});
		    
	},function(err){
	    console.log(err);
	});
};

this.getEdistWorkStatus= function(filter){
	// alert(JSON.stringify(project));
	 var projectsList = $resource(WSBASEURL+'dashboard/getEdistWorkStatus');
	 projectsList.save(filter, function(response,headers){
			 $rootScope.$broadcast("workstatus", {response: response,flag:1});
		 	    
	},function(err){
	    console.log(err);
	});
};

    
});    