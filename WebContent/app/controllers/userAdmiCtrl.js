tnwApp.controller("AdminDashboardCtrl",
		function($scope, $timeout, dashboardservice, $interval, $http) {

			dashboardservice.admindashboarddata();
			$scope.Math = window.Math;
			$scope.getUserName = function(id) {
				for (obj in $scope.UserList) {
					if($scope.UserList[obj][0]==id){
						return ($scope.UserList[obj])[1] +" "+ ($scope.UserList[obj])[2];
					}
				}
			  return "";
		    };
		    
		    $scope.theTime = new Date().toLocaleTimeString();
		    $interval(function () {
		        $scope.theTime = new Date().toLocaleTimeString();
		    }, 1000);
		    
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
			
			$scope.$on("dashboardData", function(e, kvp){
				
				if(angular.equals(kvp.response.ResponseCode,'200')){
					$scope.dashboardData=kvp.response;	
				}
		
		   });

			var amt = 66;

			$scope.countTo = amt;
			$scope.countFrom = 0;

			$timeout(function() {
				$scope.progressValue = amt;
			}, 200);
			
			//Truck Count
			$http.get(WSBASEURL+'usermaintenance/getTruckCount')
			.then(function(response) {
				$scope.truckCount=response.data.Data;
					});

		});

	


tnwApp.controller("UserDashboardCtrl",
		function($scope, $timeout, dashboardservice) {

			dashboardservice.userdashboarddata();
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
			
			$scope.$on("dashboardData", function(e, kvp){
				
				if(angular.equals(kvp.response.ResponseCode,'200')){
					$scope.dashboardData=kvp.response;	
				}
		
		   });

			var amt = 66;
			$scope.countTo = amt;
			$scope.countFrom = 0;

			$timeout(function() {
				$scope.progressValue = amt;
			}, 200);

			
			
		});

