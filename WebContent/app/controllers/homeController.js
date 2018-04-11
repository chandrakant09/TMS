tnwApp.controller('homeController', function($scope,loginservice,$rootScope, $resource, $location) { 
	
	$scope.loginBoxDisplay = function() {
		
		$("#loginDiv").show();
		$("#forgetPasswordDiv").hide();
	};
	
	$scope.forgetPasswordDisplay = function() {
		
		$("#loginDiv").hide();
		$("#forgetPasswordDiv").show();
		
	};
	
	$scope.registrationDisplay = function() {
		$("#loginDiv").hide();
		$("#registrationDiv").show();
	};
	
	$scope.closeBox = function() { 
		$("#loginDiv").show();
		$("#registrationDiv").hide();
		$("#forgetPasswordDiv").hide();
	};
	
	$scope.loginBoxHide = function() { 
		$("#loginDiv").hide();
		$("#forgetPasswordDiv").hide();
	};
	
	$scope.msg="";
	$scope.msgtype = "";
	$scope.msgimg = "";
	
	$scope.login = {
			authenticateUser: function(loginform) {
				if(loginform.$valid){
					$scope.msg = "Login Credentials verificaton progress";  
					$scope.msgtype ="text-primary"; 
					$scope.msgimg ="app/images/ajax-loader.gif";
					loginservice.authenticate($scope.user);
				}	
	        }
	}
	
	
	
	$scope.user=null;
	$scope.loginmessage="";
	
	$scope.changePassword = function() {
		alert("change password");
		loginservice.changePassword($scope.user);
	}
	
	$scope.$on("messageAppend", function(e, kvp){
       
		
		 if(angular.equals(kvp.flag,'loginResponse')){ 
			 //Code for login Response
	         if(angular.equals(kvp.loginstatus.ResponseCode,'3004')){
	        	 if(angular.equals(kvp.loginstatus.Data.RoleId,10001)){
	        		 $location.url('/dashboardAdmin/ahome');
	        	 }else if(angular.equals(kvp.loginstatus.Data.RoleId,10002)){
	        		 $location.url('/dashboardUser/uhome'); 
	        	 }else if(angular.equals(kvp.loginstatus.Data.RoleId,10003)){
	        		 $location.url('/dashboardEdm/edmhome'); 
	        	 }else if(angular.equals(kvp.loginstatus.Data.RoleId,10004)){
	        		 $location.url('/dashboardSAdmin/sahome');
	        	 }else if(angular.equals(kvp.loginstatus.Data.RoleId,10005)){
	        		 $location.url('/dashboardCEG/ceghome');
	        	 }
	         }else{
				 $scope.msg =  kvp.loginstatus.ResponseMessage;  
				 $scope.msgtype ="text-danger"; 
				 $scope.msgimg ="";
				 $scope.loginmessage = kvp.loginstatus.ResponseMessage;
			 }
         
		 }else if(angular.equals(kvp.flag,'changePasswordResponse')){ 
			 //Code for Change password Response
			 
			 
			 var resp = kvp.changepasswordstatus.ResponseCode;
			 var respMsg='';
			 if(angular.equals(resp, '5006')){
				 alert("Your Session expire");
				 $location.url('/home');
		 	 }	
			 
			 var respType = '';
		 		var respimg = '';
		 		if(angular.equals(resp, '3008')){
		 			respMsg = "Password has been successfully changed"; 
		 			respType ="text-success";
		 			respimg = "app/images/thubm.gif";
		 		}else if(angular.equals(resp, '5001')){
		 			respMsg = "New password and confirm password does not match."; 
		 			respType ="text-danger";
		 		}else if(angular.equals(resp, '5001')){
		 			respMsg = "Old Password dose not matched."; 
		 			respType ="text-danger";
		 		}else{
		 			respMsg = kvp.changepasswordstatus.ResponseMessage; 
		 			respType ="text-danger";
		 		}
		 		$scope.changePasswordMsg = respMsg;
		 		$scope.changePasswordType = respType;
		 		$scope.changePasswordimg = respimg;
		 }
	});
	
	
	

});


tnwApp.controller('forgetPasswordCtrl', function($scope,loginservice,$rootScope, $resource, $location) { 
	$scope.password = {
			forgetPassword: function(forgetPasswordForm) {
				if(forgetPasswordForm.$valid){
					$scope.forgetPasswordMsg = "Process started for email verification/reset password/send mail";  
					$scope.forgetPasswordType ="text-primary"; 
					$scope.forgetPasswordimg ="app/images/ajax-loader.gif";
					loginservice.forgetpassword($scope.user);
					
				}	
	        }
	}
	
	$scope.closeBox = function() { 
		$("#loginDiv").show();
		$("#forgetPasswordDiv").hide();
	};
	
	
	$scope.$on("forgetPassword", function(e, kvp){
	       
		     var resp = kvp.forgetpasswordstatus.ResponseCode;
			 var respMsg='';
		 		var respType = '';
		 		var respimg = '';
		 		if(angular.equals(resp, '3003')){
		 			respMsg = "Password has been successfully send to registered Email id"; 
		 			respType ="text-success";
		 			respimg = "app/images/thubm.gif";
		 		}else if(angular.equals(resp, '5001')){
		 			respMsg = "New password and confirm password does not match."; 
		 			respType ="text-danger";
		 		}else if(angular.equals(resp, '5002')){
		 			respMsg = "Email is not registered"; 
		 			respType ="text-danger";
		 		}else{
		 			respMsg = kvp.forgetpasswordstatus.ResponseMessage; 
		 			respType ="text-danger";
		 		}
		 		$scope.forgetPasswordMsg = respMsg;
		 		$scope.forgetPasswordType = respType;
		 		$scope.forgetPasswordimg = respimg;

	});
});


tnwApp.controller('DatepickerCtrl', function ($scope) {
	  
	$scope.today = function() {
	    $scope.dt = new Date();
	  };
	  $scope.today();

	  $scope.clear = function() {
	    $scope.dt = null;
	  };
	
	  $scope.options = {
			    customClass: getDayClass,
			    minDate: new Date(),
			    showWeeks: true
			  };

		  

		  // Disable weekend selection
		  function disabled(data) {
			    var date = data.date,
			      mode = data.mode;
			    return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
			  }

		 /* $scope.toggleMin = function() {
		    $scope.inlineOptions.minDate = $scope.inlineOptions.minDate ? null : new Date();
		    $scope.dateOptions.minDate = $scope.inlineOptions.minDate;
		  };

		  $scope.toggleMin();
	*/
		  $scope.open1 = function() {
		    $scope.popup1.opened = true;
		  };

		  $scope.open2 = function() {
		    $scope.popup2.opened = true;
		  };

		

		  $scope.formats = ['yyyy-MM-dd', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate','yyyy-MM-dd'];
		  $scope.format = $scope.formats[0];
		  $scope.altInputFormats = ['M!/d!/yyyy'];

		  $scope.popup1 = {
		    opened: false
		  };

		  $scope.popup2 = {
		    opened: false
		  };

		  var tomorrow = new Date();
		  //tomorrow.setDate(tomorrow.getDate() + 1);
		  var afterTomorrow = new Date();
		 // afterTomorrow.setDate(tomorrow.getDate() + 1);
		  $scope.events = [
		    {
		      date: tomorrow,
		      status: 'full'
		    },
		    {
		      date: afterTomorrow,
		      status: 'partially'
		    }
		  ];

		  function getDayClass(data) {
		    var date = data.date,
		      mode = data.mode;
		    if (mode === 'day') {
		      var dayToCheck = new Date(date).setHours(0,0,0,0);

		      for (var i = 0; i < $scope.events.length; i++) {
		        var currentDay = new Date($scope.events[i].date).setHours(0,0,0,0);

		        if (dayToCheck === currentDay) {
		          return $scope.events[i].status;
		        }
		      }
		    }

		    return '';
		  }
		});



