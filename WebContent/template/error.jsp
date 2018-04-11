<%@ page import="java.util.*"%>




<%
	String message = (String) request.getAttribute("message");
	
%>
<!-- Custom CSS -->
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/app/css/bootstrap.css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/app/css/style.css">
<html>


<body>




<div class="ab">
	<div class="userCotentWrapper padt40">
		<div class="authContentWrapper">
			<div class="authLogo">
				<img src="<%=request.getContextPath()%>/app/images/error.png">
				<div>
				<div><%=message %></div>
				</div>
			</div>
		
		</div>
	</div>
</div>
</body>
</html>