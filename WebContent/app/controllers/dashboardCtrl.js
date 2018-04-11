tnwApp.controller('dashBoardCtrl', function($scope, $http,dashboardservice,loginservice,$rootScope,$location){ 
	
	
	
   
	
	/** ******  left menu  *********************** **/
	$(function () {
	    $('#sidebar-menu li ul').slideUp();
	    $('#sidebar-menu li').removeClass('active');

	    $('#sidebar-menu li').on('click touchstart', function() {
	        var link = $('a', this).attr('href');

	        if(link) { 
	            window.location.href = link;
	        } else {
	            if ($(this).is('.active')) {
	                $(this).removeClass('active');
	                $('ul', this).slideUp();
	            } else {
	                $('#sidebar-menu li').removeClass('active');
	                $('#sidebar-menu li ul').slideUp();
	                
	                $(this).addClass('active');
	                $('ul', this).slideDown();
	            }
	        }
	    });

	    $('#menu_toggle').click(function () {
	        if ($('body').hasClass('nav-md')) {
	            $('body').removeClass('nav-md').addClass('nav-sm');
	            $('.left_col').removeClass('scroll-view').removeAttr('style');
	            $('.sidebar-footer').hide();

	            if ($('#sidebar-menu li').hasClass('active')) {
	                $('#sidebar-menu li.active').addClass('active-sm').removeClass('active');
	            }
	        } else {
	            $('body').removeClass('nav-sm').addClass('nav-md');
	            $('.sidebar-footer').show();

	            if ($('#sidebar-menu li').hasClass('active-sm')) {
	                $('#sidebar-menu li.active-sm').addClass('active').removeClass('active-sm');
	            }
	        }
	    });
	});

	/* Sidebar Menu active class */
	$(function () {
	    var url = window.location;
	    $('#sidebar-menu a[href="' + url + '"]').parent('li').addClass('current-page');
	    $('#sidebar-menu a').filter(function () {
	        return this.href == url;
	    }).parent('li').addClass('current-page').parent('ul').slideDown().parent().addClass('active');
	});

	/** ******  /left menu  *********************** **/
	
	
	
	
});

tnwApp.controller('WalletCtrl', function($scope, $http,dashboardservice,loginservice,$rootScope,$location, $interval){ 
	
	dashboardservice.getWallet();
	
	$scope.$on("dashboardData", function(e, kvp){
		if(angular.equals(kvp.response.ResponseCode,'5006')){
			 alert("Your Session expire");
			 $location.url('/home');
		}  
		if(kvp.flag ==1){
			$scope.walletAmount=kvp.response.Data.Balance;
		}else if(kvp.flag ==2){
			if(angular.equals(kvp.response.ResponseCode,'3001')){
				$scope.walletAmount=kvp.response.Data.Balance;	
			}
		}
	});
	
	 $scope.theTime = new Date().toLocaleTimeString();
	    $interval(function () {
	        $scope.theTime = new Date().toLocaleTimeString();
	    }, 1000);
});

tnwApp.controller('balanceCtrl', function($scope, dashboardservice,loginservice,$rootScope,$location){ 
	 
	loginservice.getUserInfo();
	
	$scope.walletRecharge = function() {
		 rechargeAmount=$scope.recharge;
		 alert(JSON.stringify($scope.recharge));
		 dashboardservice.walletRecharge(JSON.stringify($scope.recharge));
	};
	
	$scope.$on("responseStatus", function(e, kvp){
		if(angular.equals(kvp.userData.ResponseCode,'5006')){
			 alert("Your Session expire");
			 $location.url('/home');
		}else{
			if(angular.equals(kvp.userData.ResponseCode,'3001')){
				$scope.user=kvp.userData.Data;	
			}
		}
	});
	
	
	
});









tnwApp.controller('agentrechargeCtrl', function($scope,$location,dashboardservice,loginservice,$rootScope){ 
	
	$scope.paymentTypes=["Cash","Demand Draft","Cheque"];
	$scope.statuslist=["Rejected", "Pending For Approval", "Approved"];
	
	$scope.msg="";
	$scope.msgtype = "";
	$scope.msgimg = "";
	$scope.isSubmitted=false;
	
	 $scope.availableTags = [];
	 dashboardservice.getUserAutocompleteData($scope.availableTags);
	 
	 $scope.complete=function(){
		    $( "#tags" ).autocomplete({
		      source: $scope.availableTags
		    });
	 };
	 
	 
	 
	/*$scope.changeView = function() {
		$scope.
	};*/
	
	$scope.rechargeByCheque= function() {
		var x=document.getElementById("tags").value;
		var userId=$scope.user.UserId;
		if(x.indexOf("~") != -1){
			userId=(x.split("~"))[0];
		}
		$scope.cheque.UserId=userId;
		$scope.msg = "Recharge process in progress";  
		$scope.msgtype ="text-primary"; 
		$scope.msgimg ="app/images/ajax-loader.gif";
		$scope.isSubmitted=true;
		dashboardservice.chequeRechage($scope.cheque);
	};
	
	$scope.rechargeByCash= function() {
		var x=document.getElementById("tags").value;
		var userId=$scope.user.UserId;
		if(x.indexOf("~") != -1){
			userId=(x.split("~"))[0];
		}
		$scope.cash.UserId=userId;
		$scope.msg = "Recharge process in progress";  
		$scope.msgtype ="text-primary"; 
		$scope.msgimg ="app/images/ajax-loader.gif";
		$scope.isSubmitted=true;
		
		dashboardservice.cashRechage($scope.cash);
		
	};
	
	
	$scope.rechargeByDD = function() {
		var x=document.getElementById("tags").value;
		var userId=$scope.user.UserId;
		if(x.indexOf("~") != -1){
			userId=(x.split("~"))[0];
		}
		$scope.demanddraft.UserId=userId;
		$scope.msg = "Recharge process in progress";  
		$scope.msgtype ="text-primary"; 
		$scope.msgimg ="app/images/ajax-loader.gif";
		$scope.isSubmitted=true;
		dashboardservice.demandDraftRechage($scope.demanddraft);
		
	};
	
	$scope.$on("rechargeres", function(e, kvp){
		 if(angular.equals(kvp.flag,1)){
			 $scope.availableTags=kvp.response;
		 }
		 
		 if(angular.equals(kvp.flag,2)){
		    
		    if(angular.equals(kvp.response.ResponseCode,'1000')){
				$scope.msg=kvp.response.ResponseMessage;
				$scope.msgtype ="text-success"; 
				$scope.msgimg ="app/images/thubm.gif";
			}else{
				
			}
			$scope.isSubmitted=false;
		 }
			
	});
	
});









