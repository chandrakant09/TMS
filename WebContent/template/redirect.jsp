
<%
	String action = (String) request.getAttribute("action");
	String str = (String) request.getAttribute("xwcparm_DSPqStR");
	String message = request.getAttribute("message")==null?"": (String)request.getAttribute("message");
%>
<!-- Custom CSS -->
<html>

<script>

function submitPayuForm() {
	  var encryptForm = document.forms.encryptForm;
	  encryptForm.submit();
  }
</script>


<body  onload="submitPayuForm();">
	<div style="border: 2px solid #D3D3D3; margin-left:12%; margin-right:12%; margin-top:30px;" >
	     <div style="border-bottom: 1px solid #D3D3D3; height: 30px; margin-left:10px; margin-right:10px; margin-top:5px; ">
	      <strong>Wallet payment is in progress don't refresh the page </strong>
	      <strong>Status: <%=message %> </strong>
	     </div>
	</div>
    
	<div style="display:none">
	<form action="<%=action %>" method="post" name="encryptForm">
		<input type="hidden" name="xwcparm_DSPqStR" value="<%= str %>" />
    </form>	 
    </div>
</body>
</html>