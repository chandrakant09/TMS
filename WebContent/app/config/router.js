tnwApp.config(function($stateProvider, $urlRouterProvider,$httpProvider) {
	$httpProvider.defaults.withCredentials = true;
	$urlRouterProvider.otherwise('/login');

	$stateProvider.state('login', {
		url : "/login",
		templateUrl : "template/home.html"
	}).state('createuser', {
		url : "/createuser",
		templateUrl : "template/sadmin/register.html"
	}).state('dashboardUser', {
		url : "/dashboardUser",
		templateUrl : "template/dashboard.user.html"
	}).state('dashboardAdmin', {
		url : "/dashboardAdmin",
		templateUrl : "template/dashboard.admin.html"
	}).state('dashboardEdm', {
		url : "/dashboardEdm",
		templateUrl : "template/edm/dashboard.edm.html"
	}).state('dashboardSAdmin', {
		url : "/dashboardSAdmin",
		templateUrl : "template/dashboard.sadmin.html"
	}).state('dashboardCEG', {
		url : "/dashboardCEG",
		templateUrl : "template/ceg/dashboard.cegReport.body.html"
	}).state('dashboardAdmin.ahome', {
		url : "/ahome",
		views: {
			'admindashBoard@dashboardAdmin':{
				templateUrl : "template/dashboard.admin.body.html"
				}
			}
	}).state('dashboardSAdmin.sahome', {
		url : "/sahome",
		views: {
			'sadmindashBoard@dashboardSAdmin':{
				templateUrl : "template/dashboard.sadmin.body.html"
				}
			}
	}).state('dashboardAdmin.changepassword', {
		url : "/changepassword",
		views: {
			'admindashBoard@dashboardAdmin':{
				templateUrl : "template/changepassword.view.html"
				}
			}
	}).state('dashboardAdmin.usersList', {
		url : "/usersList",
		views: {
			'admindashBoard@dashboardAdmin':{
				templateUrl : "template/userslist.view.html"
				}
			}
	}).state('dashboardAdmin.trucklistdetail', {
		url : "/trucklistdetail",
		views: {
			'admindashBoard@dashboardAdmin':{
				templateUrl : "template/trucklistdetail.view.html"
				}
			}
	}).state('dashboardAdmin.editprofile', {
		url : "/editprofile",
		views: {
			'admindashBoard@dashboardAdmin':{
				templateUrl : "template/editprofile.view.html"
				}
			}
	}).state('dashboardAdmin.createuser', {
		url : "/createuser",
		views: {
			'admindashBoard@dashboardAdmin':{
				templateUrl : "template/register.html"
				}
			}
	}).state('dashboardAdmin.addtruck', {
		url : "/addtruck",
		views: {
			'admindashBoard@dashboardAdmin':{
				templateUrl : "template/addtruck.html"
				}
			}
	}).state('dashboardAdmin.trucklistdetail.buytruckbalance', {
		url : "/buytruckbalance",
		views: {
			'admindashBoard@dashboardAdmin':{
				templateUrl : "template/buytruckbalance.html"
				}
			}
	}).state('dashboardAdmin.resetuserpassword', {
		url : "/resetuserpassword",
		views: {
			'admindashBoard@dashboardAdmin':{
				templateUrl : "template/resetuserpassword.html"
				}
			}
	}).state('dashboardAdmin.walletdetails', {
		url : "/walletdetails",
		views: {
			'admindashBoard@dashboardAdmin':{
				templateUrl : "template/walletdetails.html"
				}
			}
	}).state('dashboardAdmin.agentrecharge', {
		url : "/agentrecharge",
		views: {
			'admindashBoard@dashboardAdmin':{
				templateUrl : "template/agentrecharge.html"
				}
			}
	}).state('dashboardAdmin.transationhistory', {
		url : "/transationhistory",
		views: {
			'admindashBoard@dashboardAdmin':{
				templateUrl : "template/transationhistory.html"
				}
			}
	}).state('dashboardAdmin.rechargehistory', {
		url : "/rechargehistory",
		views: {
			'admindashBoard@dashboardAdmin':{
				templateUrl : "template/rechargehistory.html"
				}
			}
	}).state('dashboardAdmin.chequedetails', {
		url : "/chequedetails",
		views: {
			'admindashBoard@dashboardAdmin':{
				templateUrl : "template/chequedetails.html"
				}
			}
	}).state('dashboardAdmin.cashDetails', {
		url : "/cashDetails",
		views: {
			'admindashBoard@dashboardAdmin':{
				templateUrl : "template/cashDetailsReport.html"
				}
			}
	}).state('dashboardSAdmin.pendingRecharge', {
		url : "/pendingRecharge",
		views: {
			'sadmindashBoard@dashboardSAdmin':{
				templateUrl : "template/pendingRecharge.html"
				}
			}
	}).state('dashboardAdmin.pendingRecharge', {
		url : "/pendingRecharge",
		views: {
			'admindashBoard@dashboardAdmin':{
				templateUrl : "template/pendingRecharge.html"
				}
			}
	}).state('dashboardAdmin.edisttReport', {
		url : "/edisttReport",
		views: {
			'admindashBoard@dashboardAdmin':{
				templateUrl : "template/edisttReport.html"
				}
			}
	}).state('dashboardSAdmin.edisttworkstatus', {
		url : "/edisttworkstatus",
		views: {
			'sadmindashBoard@dashboardSAdmin':{
				templateUrl : "template/edisttworkstatus.html"
				}
			}
	}).state('dashboardAdmin.edisttworkstatus', {
		url : "/edisttworkstatus",
		views: {
			'admindashBoard@dashboardAdmin':{
				templateUrl : "template/edisttworkstatus.html"
				}
			}
	}).state('logout', {
		url : "/logout",
		controller: 'logoutController',
		templateUrl : "home.html"
	}).state('dashboardUser.uhome', {
		url : "/uhome",
		views: {
			'userdashBoard@dashboardUser':{
				templateUrl : "template/dashboard.user.body.html"
				}
			}
	}).state('dashboardUser.application', {
		url : "/application",
		views: {
			'userdashBoard@dashboardUser':{
				templateUrl : "template/application/application.html"
				}
			}
	}).state('dashboardUser.changepassword', {
		url : "/changepassword",
		views: {
			'userdashBoard@dashboardUser':{
				templateUrl : "template/changepassword.view.html"
				}
			}
	}).state('dashboardUser.editprofile', {
		url : "/editprofile",
		views: {
			'userdashBoard@dashboardUser':{
				templateUrl : "template/editprofile.view.html"
				}
			}
	}).state('dashboardUser.buybalance', {
		url : "/buybalance",
		views: {
			'userdashBoard@dashboardUser':{
				templateUrl : "template/buybalance.html"
				}
			}
	}).state('dashboardUser.utransationhistory', {
		url : "/utransationhistory",
		views: {
			'userdashBoard@dashboardUser':{
				templateUrl : "template/utransationhistory.html"
				}
			}
	}).state('dashboardUser.urechargehistory', {
		url : "/urechargehistory",
		views: {
			'userdashBoard@dashboardUser':{
				templateUrl : "template/urechargehistory.html"
				}
			}
	}).state('dashboardUser.uchequedetails', {
		url : "/uchequedetails",
		views: {
			'userdashBoard@dashboardUser':{
				templateUrl : "template/uchequedetails.html"
				}
			}
	}).state('dashboardUser.ucddetails', {
		url : "/ucddetails",
		views: {
			'userdashBoard@dashboardUser':{
				templateUrl : "template/ucddetails.html"
				}
			}
	}).state('dashboardUser.udddetails', {
		url : "/udddetails",
		views: {
			'userdashBoard@dashboardUser':{
				templateUrl : "template/udddetails.html"
				}
			}
	}).state('dashboardUser.paymentreturnfailure', {
		url : "/paymentreturnfailure",
		views: {
			'userdashBoard@dashboardUser':{
				templateUrl : "template/paymentreturnfailure.html"
				}
			}
	}).state('dashboardUser.paymentreturnsuccess', {
		url : "/paymentreturnsuccess",
		views: {
			'userdashBoard@dashboardUser':{
				templateUrl : "template/paymentreturnsuccess.html"
				}
			}
	}).state('dashboardSAdmin.dtreport', {
		url : "/dtreport",
		views: {
			'sadmindashBoard@dashboardSAdmin':{
				templateUrl : "template/sadmin/dtreport.html"
				}
			}
	}).state('dashboardSAdmin.districtWork', {
		url : "/districtWork",
		views: {
			'sadmindashBoard@dashboardSAdmin':{
				templateUrl : "template/sadmin/districtWork.html"
				}
			}
	}).state('dashboardSAdmin.userdata', {
		url : "/userdata",
		views: {
			'sadmindashBoard@dashboardSAdmin':{
				templateUrl : "template/sadmin/userreport.html"
				}
			}
	}).state('dashboardSAdmin.trucklist', {
		url : "/trucklist",
		views: {
			'sadmindashBoard@dashboardSAdmin':{
				templateUrl : "template/sadmin/trucklist.view.html"
				}
			}
	}).state('dashboardEdm.edmhome', {
		url : "/edmhome",
		views: {
			'dashboardEdm@dashboardEdm':{
				templateUrl : "template/edm/dashboard.edm.body.html"
				}
			}
	}).state('dashboardEdm.edmreport', {
		url : "/edmreport",
		views: {
			'dashboardEdm@dashboardEdm':{
				templateUrl : "template/edm/edmreport.html"
				}
			}
	}).state('dashboardEdm.userarea', {
		url : "/userarea",
		views: {
			'dashboardEdm@dashboardEdm':{
				templateUrl : "template/edm/userarealist.html"
				}
			}
	}).state('dashboardCEG.ceghome', {
		url : "/ceghome",
		views: {
			'dashboardCEG@dashboardCEG':{
				templateUrl : "template/ceg/dashboard.cegReport.body.html"
				}
			}
	}).state('dashboardCEG.cegreport', {
		url : "/cegreport",
		views: {
			'dashboardCEG@dashboardCEG':{
				templateUrl : "template/ceg/cegreport.html"
				}
			}
	}).state('dashboardUser.centerlocation', {
		url : "/centerlocation",
		views: {
			'userdashBoard@dashboardUser':{
				templateUrl : "template/centerlocation/centerlocation.html"
				}
			}
	}).state('.registerform', {
		url : "/registerform",
		templateUrl : "template/application/registerform.html"
	});
	
	
	
})

 .run(function ($rootScope) {
        $rootScope.mySetting = 1;
    });
