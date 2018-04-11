
//controllers

tnwApp.controller ('rghtMenuCtrl', function ($scope, $location, loginservice,$rootScope) {
	
	//$scope.statuses = ["welcome","Logout1"];
	 $scope.statuses = [ {
				"name" : "",
				"link" : ""
			}, {
				"name" : "Change Password",
				"link" : ".changepassword"
			}, {
				"name" : "Edit Profile",
				"link" : ".editprofile"
			} ];

  $scope.selected = $scope.statuses[0];  
  
  loginservice.getUserDetail();

  $scope.change = function(status) {
	  $scope.selected = status;     
   };
   
   
   $scope.$on("messageAppendRightMenu", function(e, kvp){
	   
	   var obj ={"name": "" + kvp.userstatus.FirstName +" "+ kvp.userstatus.LastName, "link" : ".ahome"};      
       $scope.statuses.splice(0, 1);
       $scope.statuses.unshift(obj);
       $scope.selected = $scope.statuses[0];  
       $scope.userName = $scope.statuses[0];
  });
   
   $scope.toggled = function (open) {
       $timeout(function () {
           $scope.ctrl.isOpen = true;
       });
   };
   

   
});







//directive

tnwApp.directive('dropdown', function() {

  
  return {
      restrict: 'A',
      link: function(scope, elem, attrs) {

        // var dd = new DropDown(elem);
        angular.element(elem).on('click', function(event) {
        	//angular.element(this).temp('active');
          angular.element(this).toggleClass('active');
			 return false;
			 
		
      });
       

    }
  };
  angular.element(document).click(function() {
	    // all dropdowns
	    angular.element('.wrapper-dropdown-3').removeClass('active');
	  });  
	
}); 