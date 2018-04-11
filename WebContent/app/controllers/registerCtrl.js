tnwApp.controller('registerBoxCtrl', function($scope, registrationService,$filter)	{ // for registrationBox
	 
	$scope.closeBox = function() { 
		$("#loginDiv").show();
		$("#registrationDiv").hide();
		$("#forgetPasswordDiv").hide();
	};
	
	$scope.disttList=[];
	registrationService.disttList();
		
	$scope.msg="";
	$scope.msgtype = "";
	$scope.msgimg = "";
	// to register a  user
	$scope.registerUser = function(valid) {
		if (valid) {
			 $scope.msg = "User Creation process in progress";
			 $scope.msgtype = "text-primary"; 
			 $scope.msgimg = "app/images/ajax-loader.gif";
			 registrationService.saveUser($scope.user);
		}

	};
	
	//addTruck
	$scope.addTruck = function(valid) {
		if (valid) {
			 $scope.msg = "Add Truck process in progress";
			 $scope.msgtype = "text-primary"; 
			 $scope.msgimg = "app/images/ajax-loader.gif";
			 registrationService.addTruck($scope.truck);
		}

	};
	
	
	
	$scope.updateUser = function(valid) {
		if (valid) {
			 $scope.msg = "User update process in progress";
			 $scope.msgtype = "text-primary"; 
			 $scope.msgimg = "app/images/ajax-loader.gif";
			 registrationService.updateUserProfile($scope.user);
		}

	};
	
	$scope.getBlocks = function(selectedDistt) {
	    var filteredBlock = $filter('filter')($scope.disttList, selectedDistt);
	    return filteredBlock[0].Blocks;
	  };
	
	
	$scope.$on("messageupdated", function(e, kvp) {
		if (angular.equals(kvp.flag, 2)) {
			if(angular.equals(kvp.response.ResponseCode, '3002')){
				$scope.msg = "User Created Successfully ";
				$scope.msgtype = "text-success";
				 $scope.msgimg="";
			}else{
				$scope.msg = kvp.response.ResponseMessage;
				$scope.msgtype = "text-danger";
				$scope.msgimg = "";
			}
		}
		
		if (angular.equals(kvp.flag, 4)) {
			if(angular.equals(kvp.response.ResponseCode, '3002')){
				$scope.msg = "User updated Successfully";
				$scope.msgtype = "text-success";
				$scope.msgimg="";
			}else{
				$scope.msg = kvp.response.ResponseMessage;
				$scope.msgtype = "text-danger";
				$scope.msgimg = "";
			}
		}
		
		if (angular.equals(kvp.flag, 5)) {
			if(angular.equals(kvp.response.ResponseCode, '3002')){
				$('#demo-form2').trigger("reset");
				$scope.msg = "Truck added Successfully ";
				$scope.msgtype = "text-success";
				$scope.msgimg="";
				
			}else{
				$scope.msg = kvp.response.ResponseMessage;
				$scope.msgtype = "text-danger";
				$scope.msgimg = "";
			}
		}
		
		if (angular.equals(kvp.flag, 3)) {
			$scope.disttList=kvp.response.Data;
		}
		
		
	});
	
	
});

tnwApp.controller('editProfileCtrl', function($scope, registrationService,loginservice,$filter)	{ // for registrationBox
	
	$scope.msg="";
	$scope.msgtype = "";
	$scope.msgimg = "";
	// to register a  user
	loginservice.getUserInfo();
	$scope.$on("responseStatus", function(e, kvp) {
		$scope.user=kvp.userData.Data;
	
	});
	
	

});
