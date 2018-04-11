tnwApp.controller('walletDetailsCtrl', function($scope, $http,reportservice,$rootScope){ 
	reportservice.getuserswallet();
	$scope.Math = window.Math;
	var currentDate = new Date();
	var day = currentDate.getDate();
	var month = currentDate.getMonth() + 1;
	var year = currentDate.getFullYear();
	$scope.date2 = day+"-"+month+"-"+year;
	$scope.date1 =  "1-"+month+"-"+year;
	$scope.UserList="";
	$scope.$on("walletList", function(e, kvp){
			
		if(angular.equals(kvp.response.ResponseCode,'3001')){
			$scope.UserList=kvp.response.Data.UserList;
			$scope.walletList=kvp.response.Data.Wallets;
		}
	
	});
	
	$scope.getUserName = function(id) {
		for (obj in $scope.UserList) {
			if($scope.UserList[obj][0]==id){
				return ($scope.UserList[obj])[1] +" "+ ($scope.UserList[obj])[2];
			}
		}
	  return "";
    };
    
    $scope.getEmailId = function(id) {
		for (obj in $scope.UserList) {
			if($scope.UserList[obj][0]==id){
				return ($scope.UserList[obj])[3] ;
			}
		}
	  return "";
    };
    
    $scope.getEDisttID = function(id) {
		for (obj in $scope.UserList) {
			if($scope.UserList[obj][0]==id){
				return ($scope.UserList[obj])[4] ;
			}
		}
	  return "";
    };
    
    $scope.exportData = function () {
        var blob = new Blob([document.getElementById('exportable').innerHTML], {
            type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
        });
        saveAs(blob, "WalletReport.xls");
    };
    
   /* $scope.exportToExcel=function(tableId){ 
    	var exportHref=Excel.tableToExcel(tableId,'sheet.xls');
        $timeout(function(){$location.href=exportHref;},100);
    };*/
	
});

tnwApp.controller('userListCtrl', function($scope, $http,reportservice,$rootScope){ 
	
	$scope.user="";
	/*reportservice.getUsersList();*/
	reportservice.getTruckList();
	reportservice.getTruckListDetail();
	
	$scope.displayAttribute = ['Admin'];
	
	$scope.filterByAttribute = function(obj) {
	        return ($scope.displayAttribute.indexOf(obj.TypeDisplayName) == -1);
	};
	
	$scope.updateProfile = function (obj) {
		$scope.user=obj;
		$("#UpdateDIV").show();
	}
	

	$scope.hideupdateProfile = function() { 
		$("#UpdateDIV").hide();
	};
	
	$scope.$on("userList", function(e, kvp){
		   
		if(angular.equals(kvp.response.ResponseCode,'3001')){
		$scope.userList=kvp.response.Data.UserList;
		
	}
	
	});
	
	$scope.$on("truckList", function(e, kvp){
		   
		if(angular.equals(kvp.response.ResponseCode,'3001')){
		$scope.getTruckList=kvp.response.Data;
		console.log($scope.getTruckList);
	}
	
	});
	
	
	$scope.$on("truckListDetail", function(e, kvp){
		   
		if(angular.equals(kvp.response.ResponseCode,'3001')){
		$scope.truckListDetail=kvp.response.Data;
		console.log($scope.truckListDetail);
	}
	
	});
	
	
	$scope.exportData = function () {
        var blob = new Blob([document.getElementById('exportable').innerHTML], {
            type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
        });
        saveAs(blob, "UserList.xls");
    };
});

tnwApp.filter("walletdateFilter", function() {
	  return function(items, from, to) {
		    var df = parseDate(from);
	        var dt = parseDate(to);
	        var arrayToReturn = [];        
	        for (var i=0; i<items.length; i++){
	        	var tf = new Date(items[i].Updated);
	            if (tf > df && tf < dt)  {
	                arrayToReturn.push(items[i]);
	            }
	        }
	        
	        return arrayToReturn;
	  };
});


tnwApp.filter("dateFilter", function() {
	  return function(items, from, to) {
		    var df = parseDate(from);
	        var dt = parseDate(to);
	        var arrayToReturn = [];        
	        for (var i=0; i<items.length; i++){
	        	var tf = new Date(items[i].TransactionTime);
	            if (tf > df && tf < dt)  {
	                arrayToReturn.push(items[i]);
	            }
	        }
	        
	        return arrayToReturn;
	  };
});

function parseDate(input) {
	  var parts = input.split('-');
	  // Note: months are 0-based
	  return new Date(parts[2], parts[1]-1, parts[0]); 
}



tnwApp.controller('transactionHisCtrl', function($scope, $http,reportservice,dashboardservice,$rootScope){ 
	 $scope.reportFilter={"StartDate":"","EndDate":""}; 
	 $scope.Math = window.Math;
	 $scope.availableTags = [];
	 
	 $http.post(WSBASEURL+'dashboard/getUserAutocompleteData')
     .success(function (data, status, headers, config) {
    	 $scope.availableTags=data.Data.UserList;
     })
     .error(function (data, status, header, config) {
     });

	 
	 $scope.complete=function(){
		    $( "#tags" ).autocomplete({
		      source: $scope.availableTags
		    });
	 };
	 
	
	$scope.getTransactionList = function() {
	    var x=document.getElementById("tags").value;
		if(x=="" || x==null ){
			alert("Please enter the User Id");
			return ;
		}
	    var userId="";
		if(x.indexOf("~") != -1){
			userId=(x.split("~"))[0];
		}else{
			userId=x;
		}
		$scope.reportFilter.UserId=userId;
		$scope.reportFilter.StartDate=document.getElementById("startDate").value;
		$scope.reportFilter.EndDate=document.getElementById("endDate").value;
		//alert(JSON.stringify($scope.reportFilter));
		reportservice.getTransactionList($scope.reportFilter);
    };
    
	$scope.$on("transactionList", function(e, kvp){
		
		
		if(angular.equals(kvp.response.ResponseCode,'3001')){
			
			$scope.UserList=kvp.response.Data.UserList;	
			$scope.transactionList=kvp.response.Data.Transactions;
		}
	
	});

	
	$scope.getUserName = function(id) {
		for (obj in $scope.UserList) {
			if($scope.UserList[obj][0]==id){
				return ($scope.UserList[obj])[1] +" "+ ($scope.UserList[obj])[2];
			}
		}
	  return "";
    };
    
    $scope.getEmailId = function(id) {
		for (obj in $scope.UserList) {
			if($scope.UserList[obj][0]==id){
				return ($scope.UserList[obj])[3] ;
			}
		}
	  return "";
    };
    
    $scope.getEDisttID = function(id) {
		for (obj in $scope.UserList) {
			if($scope.UserList[obj][0]==id){
				return ($scope.UserList[obj])[4] ;
			}
		}
	  return "";
    };
    
    $scope.exportData = function () {
        var blob = new Blob([document.getElementById('exportable').innerHTML], {
            type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
        });
        saveAs(blob, "TransactionReport.xls");
    };
	
});

/*...................................*/

tnwApp.controller('utransactionHisCtrl', function($scope, $http,reportservice,$rootScope){ 
	$scope.reportFilter={"StartDate":"","EndDate":""}; 
	$scope.getuTransactionList = function() {
		$scope.reportFilter.StartDate=document.getElementById("startDate").value;
		$scope.reportFilter.EndDate=document.getElementById("endDate").value;
		reportservice.getTransactionList($scope.reportFilter);
    };
    
	$scope.$on("transactionList", function(e, kvp){
		if(angular.equals(kvp.response.ResponseCode,'3001')){
			
			$scope.UserList=kvp.response.Data.UserList;	
			$scope.transactionList=kvp.response.Data.Transactions;
		}
	
	});
	
	$scope.getUserName = function(id) {
		for (obj in $scope.UserList) {
			if($scope.UserList[obj][0]==id){
				return ($scope.UserList[obj])[1] +" "+ ($scope.UserList[obj])[2];
			}
		}
	  return "";
    };
    
    $scope.getEmailId = function(id) {
		for (obj in $scope.UserList) {
			if($scope.UserList[obj][0]==id){
				return ($scope.UserList[obj])[3] ;
			}
		}
	  return "";
    };
    
    $scope.getEDisttID = function(id) {
		for (obj in $scope.UserList) {
			if($scope.UserList[obj][0]==id){
				return ($scope.UserList[obj])[4] ;
			}
		}
	  return "";
    };
	
	
});

/*....................................*/

tnwApp.controller('rechargeHisCtrl', function($scope,reportservice,$rootScope){ 
	$scope.reportFilter={}; 
	$scope.getrechargeHisList=function(){
		$scope.reportFilter.StartDate=document.getElementById("startDate").value;
		$scope.reportFilter.EndDate=document.getElementById("endDate").value;
		//alert(JSON.stringify($scope.reportFilter));
		reportservice.getrechargeHisList(JSON.stringify($scope.reportFilter));
	};

	$scope.$on("rechargeHisList", function(e, kvp){
		console.info(JSON.stringify(kvp));
		if(angular.equals(kvp.response.ResponseCode,'3001')){
			
			$scope.rechargeList=kvp.response.Data.CommonList;
			
		}
	
	});
	  $scope.exportData = function () {
	        var blob = new Blob([document.getElementById('exportable').innerHTML], {
	            type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
	        });
	        saveAs(blob, "Rechargehistry.xls");
	    };
});


tnwApp.controller('chequeHisCtrl', function($scope, reportservice,$rootScope){ 
	reportservice.chequesdata();
	$scope.$on("chequeData", function(e, kvp){
		if(angular.equals(kvp.response.ResponseCode,'200')){
		$scope.myData =kvp.response.Data.Cheques;
		}
	});
	
	
	$scope.gridOptions = { 
	        data: 'myData',
		    columnDefs : [ {
						field : 'ifsc',
						displayName : 'IFSC Code'
					}, {
						field : 'ChequeNo',
						displayName : 'Cheque Number'
					}, {
						field : 'BankName',
						displayName : 'Bank Name'
					}, {
						field : 'Amount',
						displayName : 'Amount'
					}, {
						field : 'Feveroff',
						displayName : 'In Fever Of'
					}, {
						field : 'Status',
						displayName : 'Status'
					}, {
						field : 'ChequeDate',
						displayName : 'Cheque Date',
						cellFilter: "date:'dd-MM-yyyy HH:mm'" 
					}, {
						field : 'DateOfDepositeCheque',
						displayName : 'Deposite Date',
						cellFilter: "date:'dd-MM-yyyy HH:mm'" 
					} ],
			 showGroupPanel: true
	 };

});

tnwApp.controller('ddHisCtrl', function($scope, reportservice,$rootScope){ 
	reportservice.getDemandDraftData();
	$scope.$on("ddData", function(e, kvp){
		if(angular.equals(kvp.response.ResponseCode,'200')){
		$scope.myData =kvp.response.Data.DemandDraftDeposit;
		}
	});
	
	
	$scope.gridOptions = { 
	        data: 'myData',
		    /*columnDefs : [ {
						field : 'ifsc',
						displayName : 'IFSC Code'
					}, {
						field : 'ChequeNo',
						displayName : 'Cheque Number'
					}, {
						field : 'BankName',
						displayName : 'Bank Name'
					}, {
						field : 'Amount',
						displayName : 'Amount'
					}, {
						field : 'Feveroff',
						displayName : 'In Fever Of'
					}, {
						field : 'Status',
						displayName : 'Status'
					}, {
						field : 'ChequeDate',
						displayName : 'Cheque Date'
					}, {
						field : 'DateOfDepositeCheque',
						displayName : 'Deposite Date'
					} ], */
			 showGroupPanel: true
	 };

});


tnwApp.controller('cdHisCtrl', function($scope, reportservice,$rootScope){ 
	reportservice.getCashdeposit();
	$scope.$on("cdData", function(e, kvp){
		if(angular.equals(kvp.response.ResponseCode,'200')){
		$scope.myData =kvp.response.Data.CashDeposit;
		}
	});
	
	
	$scope.gridOptions = { 
	        data: 'myData',
		    columnDefs : [ {
						
						field : 'Amount',
						displayName : 'Amount'
					}, {
						field : 'DateOfDepositeCash',
						displayName : 'Date Of Deposit'
					}, {
						field : 'Remark',
						displayName : 'Remark'
					}, {
						field : 'RechargeBy',
						displayName : 'Recharge By (JRS-ID)'
					} ], 
			 showGroupPanel: true
	 };

});

tnwApp.controller('pendingRechargeCtrl', function($scope, reportservice,dashboardservice,$rootScope){
	var reportFilter = { Status:"PROGRESS"};
	$scope.recharge="";
	reportservice.getPendingRecharge(reportFilter);
	$scope.isSubmit=false;
	$scope.updateTrasactionStatus = function(obj) {
		
		$("#UpdateDIV").show();
		$scope.recharge=obj;
		$scope.isSubmit=false;
    };
    

	$scope.hideTrasactionStatus = function() { 
		$("#UpdateDIV").hide();
	};
    
    $scope.updateTrasaction = function(status) {
		var trasaction={TransactionId:$scope.recharge.TransactionId, Status:status, Id:$scope.recharge.UserId};
		$scope.msg="";
		$scope.msgtype = "";
		$scope.msgimg = "";
		if(confirm("This Action will directly process to the user wallet/trasaction. Once confirmed can proceed the process")){
			$scope.msg = "Request is forward for wallet processing ";  
			$scope.msgtype ="text-primary"; 
			$scope.msgimg ="app/images/ajax-loader.gif";
			$scope.isSubmit=true;
			dashboardservice.updateTransactionUpdate(trasaction);
    	}
    };
    
    $scope.$on("RechargeFilterList", function(e, kvp){
    	if(angular.equals(kvp.flag,1)){
			if(angular.equals(kvp.response.ResponseCode,'3001')){
				$scope.UserList=kvp.response.Data.UserList;	
				$scope.rechargeList=kvp.response.Data.Recharges;
			}
    	}else if(angular.equals(kvp.flag,2)){
    		if(angular.equals(kvp.response.ResponseCode,'1000')){
    			$scope.msg =  kvp.response.ResponseMessage;  
				$scope.msgtype ="text-success";
				$scope.msgimg ="app/images/thubm.gif";
	 			$scope.rechargeList.splice( $scope.rechargeList.indexOf($scope.recharge),1);
				
			}else{
				 $scope.msg =  kvp.response.ResponseMessage;  
				 $scope.msgtype ="text-danger"; 
				 $scope.msgimg ="";
			}
    	}
	
	});
	
	$scope.getUserName = function(id) {
		for (obj in $scope.UserList) {
			if($scope.UserList[obj][0]==id){
				return ($scope.UserList[obj])[1] +" "+ ($scope.UserList[obj])[2];
			}
		}
	  return "";
    };
    
    $scope.getEmailId = function(id) {
		for (obj in $scope.UserList) {
			if($scope.UserList[obj][0]==id){
				return ($scope.UserList[obj])[3] ;
			}
		}
	  return "";
    };
    
    $scope.getEDisttID = function(id) {
		for (obj in $scope.UserList) {
			if($scope.UserList[obj][0]==id){
				return ($scope.UserList[obj])[4] ;
			}
		}
	  return "";
    };
	
});








tnwApp.controller('edisttCtrl', function($scope,$http, reportservice,$rootScope){
     $scope.availableTags = [];
   
     $http.post(WSBASEURL+'dashboard/getUserAutocompleteData')
     .success(function (data, status, headers, config) {
    	 $scope.availableTags=data.Data.UserList;
     })
     .error(function (data, status, header, config) {
     });

     $scope.complete=function(){
		    $( "#tags" ).autocomplete({
		      source: $scope.availableTags
		    });
	 };

	 $scope.getEDistricReport = function() {
		
		 var x=document.getElementById("tags").value;
			if(x=="" || x==null ){
				alert("Please enter the User Id");
				return ;
			}
		    var edisttId="";
			if(x.indexOf("~") != -1){
				edisttId=(x.split("~"))[4];
			}else{
				edisttId=x;
			}
			$scope.reportFilter.G2cId=edisttId;
		 reportservice.getEdisttList($scope.reportFilter);
	 };
	
	$scope.$on("edisttList", function(e, kvp){
		if(angular.equals(kvp.response.ResponseCode,'3001')){
			
			$scope.edisttList=kvp.response.Data.EdisttList;
		}
	
	});
	$scope.exportData = function () {
        var blob = new Blob([document.getElementById('exportable').innerHTML], {
            type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
        });
        saveAs(blob, "EdisttReport.xls");
    };
	
	
});



tnwApp.controller('workStatusCtrl', function($scope, reportservice,$rootScope){
    
	$scope.reportFilter={};
	reportservice.getEdistWorkStatus($scope.reportFilter);

	
	$scope.getEdistWorkStatus = function() {
		reportservice.getEdistWorkStatus($scope.reportFilter);
    };
    
    $scope.gridOptions= { data: 'workstatus',
                          columnDefs: [{field: '0', displayName: 'User Id'},
                                       {field: '1', displayName: 'VLE Id'},
                                       {field: '2', displayName: 'Name',cellTemplate: '<div>{{row.getProperty(col.field)}} {{row.getProperty(3)}}</div>'},
                                       {field: '4', displayName: 'Block'},
                                       {field: '5', displayName: 'Transaction (Debit)'},
                                       {field: '5', displayName: 'No of Application',cellTemplate: '<div>{{row.getProperty(col.field)/20}}</div>'}]
            
    
    };
	
	$scope.$on("workstatus", function(e, kvp){
		if(angular.equals(kvp.response.ResponseCode,'3001')){
			$scope.workstatus=kvp.response.Data.UserList;
			
		}
	
	});
	
	$scope.exportData = function () {
        var blob = new Blob([document.getElementById('exportable').innerHTML], {
            type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
        });
        saveAs(blob, "WorkStatus.xls");
    };
	
});

//Cash details List

tnwApp.controller('cashDetailCtrl', function($scope, reportservice,$rootScope){
	$scope.reportFilter={};
	/*reportservice.getCashDetailstList();*/
	$scope.getCashDetailstList = function() {
		$scope.reportFilter.StartDate=document.getElementById("startDate").value;
		$scope.reportFilter.EndDate=document.getElementById("endDate").value;
		reportservice.getCashDetailstList($scope.reportFilter);
		
    };
	
	$scope.UserList="";
	$scope.$on("cashdetailList", function(e, kvp){
		if(angular.equals(kvp.response.ResponseCode,'3001')){
			$scope.UserList=kvp.response.Data.UserList;
			$scope.cashdetailList=kvp.response.Data.CashdetailList;
		}
	
	});
			
		$scope.getUserName = function(id) {
		for (obj in $scope.UserList) {
			if($scope.UserList[obj][0]==id){
				return ($scope.UserList[obj])[1] +" "+ ($scope.UserList[obj])[2];
			}
		}
		return "";
		};
		
		
		
		$scope.getEDisttID = function(id) {
		for (obj in $scope.UserList) {
			if($scope.UserList[obj][0]==id){
				return ($scope.UserList[obj])[4] ;
			}
		}
		return "";
		};
	
		 $scope.exportData = function () {
		        var blob = new Blob([document.getElementById('exportable').innerHTML], {
		            type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
		        });
		        saveAs(blob, "CashRecharge.xls");
		    };
	
	
});

//-----------------------Super Admin Controllers


tnwApp.controller('userInfoCtrl', function($scope, dashboardservice,$rootScope){
	
	 $scope.availableTags = [];
	 dashboardservice.getAutocomplete($scope.availableTags);
	 $scope.complete=function(){
		    $( "#tags" ).autocomplete({
		      source: $scope.availableTags
		    });
	 };
	 $scope.tabs = [
	        	    { title:'Dynamic Title 1', content:'Dynamic content 1' },
	        	    { title:'Dynamic Title 2', content:'Dynamic content 2', disabled: true }
	        	  ];

	        	  $scope.alertMe = function() {
	        	    setTimeout(function() {
	        	      $window.alert('You\'ve selected the alert tab!');
	        	    });
	        	  };

	        	  $scope.model = {
	        	    name: 'Tabs'
	        	  };
	 
	 $scope.fetchUser= function() {
			var x=document.getElementById("tags").value;
			var userId=$scope.user.UserId;
			if(x.indexOf("~") != -1){
				userId=(x.split("~"))[0];
			}
			dashboardservice.getUserInfo(userId);
	};
	$scope.$on("responseStatus", function(e, kvp){
		if(angular.equals(kvp.flag,1)){
			 $scope.availableTags=kvp.response;
		}
		if(angular.equals(kvp.flag,2)){
			$scope.report=kvp.userData.Data;	
		}
		});
	
});


tnwApp.controller("edmReport", function($scope, $http){
		$scope.getData=function(){
			$scope.reportFilter = {};
			$scope.reportFilter.StartDate=document.getElementById("startDate").value;
			$scope.reportFilter.EndDate=document.getElementById("endDate").value;
		//	alert(JSON.stringify($scope.reportFilter));
			var req = {
					 method: 'POST',
					 url: WSBASEURL+"transactionmaintenance/getDisttAmount",
					 headers: {
					   'Content-Type': 'application/json'
					 },
					 data:$scope.reportFilter
			 };
			$http(req)
			  .then(function(response) {
				  $scope.eReport = response.data.Data.DisttAmount;
				  $scope.wReport = response.data.Data.WalletAmount;
				  });
			
			 
		};
		
		$scope.exportData = function () {
	        var blob = new Blob([document.getElementById('exportable').innerHTML], {
	            type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
	        });
	        saveAs(blob, "Weekly Report.xls");
	    };
	    
	    	    
	   
});


tnwApp.controller("userareaCtrl", function($scope, $http){
	    $http.get(WSBASEURL+"transactionmaintenance/getAreaList")
				  .then(function(response) {
					$scope.arealist=response.data.Data.UserStatics;
					 $scope.today = new Date();
					});
	    
	$scope.exportData = function () {
        var blob = new Blob([document.getElementById('exportable').innerHTML], {
            type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
        });
        saveAs(blob, "userworkDetail.xls");
    };
	
	 
});



tnwApp.controller('applicationCtrl', function($scope, $http){
	// alert("getApplicationtList");
	  $scope.reportFilter = {};
			var req = {
					 method: 'POST',
					 url: WSBASEURL+"dashboard/getApplicationtList",
					 headers: {
					   'Content-Type': 'application/json'
					 },
					 data:$scope.reportFilter
			 };
			$http(req)
			  .then(function(response) {
				  $scope.applicationList = response.data.Data.applicationList;
				  });
		
		
		$scope.exportData = function () {
	        var blob = new Blob([document.getElementById('exportable').innerHTML], {
	            type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
	        });
	        saveAs(blob, "Application List.xls");
	    };


   	
});

