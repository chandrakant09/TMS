tnwApp.controller('logoutController', function($scope, $rootScope, $location,  $resource,logoutservice) {
	$scope.logout=function(){
		logoutservice.logoutApplication();	
		if(logoutservice){
			$location.path('/#');
		};
	};
	
});

tnwApp.controller('footerController', function($scope) {
	var d = new Date();
    var n = d.getFullYear();
	$scope.curreentYear=n;
});

tnwApp.controller('headerController', function($scope) {
	
	$scope.contactDiv = function() { 
		$("#contactDiv").show();
		$("#loginDiv").hide();
		$("#registrationDiv").hide();
		$("#forgetPasswordDiv").hide();
	};
	
});

tnwApp.controller('contactUsBoxCtrl', function($scope) {
	$scope.closeBox = function() { 
		$("#loginDiv").show();
		$("#registrationDiv").hide();
		$("#forgetPasswordDiv").hide();
		$("#contactDiv").hide();
	};
	
});