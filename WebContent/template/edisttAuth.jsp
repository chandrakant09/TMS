<%
	String encodedString = request.getAttribute("encodedString")==null?"":(String)request.getAttribute("encodedString");
	String action1 = request.getAttribute("action1")==null?"":(String)request.getAttribute("action1");
	String vleId =request.getAttribute("vleId")==null?"":(String)request.getAttribute("vleId");
%>
<!-- Custom CSS -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/app/css/bootstrap.css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/app/css/style.css">
<html>


<body>




	<div class="userCotentWrapper padt40">
		<div class="authContentWrapper">
			<div class="authLogo">
				<img src="<%=request.getContextPath()%>/app/images/logo-active.png">
				<div>
				<h4>Please provide your the nature world password to go for next step.</h4>
				<div>Amount will deducted from your The Nature World wallet</div>
				</div>
			</div>
			<form action="<%=action1%>" method="post" class="form-horizontal form-label-left mrt20">
			<input type="hidden" name="encodedString" value="<%=encodedString%>" />
			
			
	 		 <div class="form-group">
               <label class="control-label col-md-2 col-sm-2 col-xs-12">VLE ID
               </label>
               <div class="col-md-10 col-sm-10 col-xs-12">
                 <input name="emailId" required class="form-control col-md-7 col-xs-12" value="<%=vleId%>" readonly/>
               </div>
             </div>
             
             <div class="form-group">
               <label class="control-label col-md-2 col-sm-2 col-xs-12">Password<span class="required">*</span>
               </label>
               <div class="col-md-10 col-sm-10 col-xs-12">
                 <input name="password" type="password"  required class="form-control col-md-7 col-xs-12"/>
               </div>
             </div>
             
            
				<div class="form-group">
                      <div class="col-md-10 col-sm-10 col-xs-12 col-md-offset-2">
                      	<input type="submit" class="btn btn-primary" value="Proceed the process" />
                      </div>
                </div>
                
                
                <div class="form-group">
                      <div class="col-md-10 col-sm-10 col-xs-12 col-md-offset-2">
                      
                      </div>
                </div>

			</form>
		</div>
	</div>

</body>
</html>