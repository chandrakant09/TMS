<%@ page import="java.util.*" %>


<%!
public boolean empty(String s){
		if(s== null || s.trim().equals(""))
			return true;
		else
			return false;
	}
%>

<% 	
	Enumeration<String> paramNames = request.getParameterNames();
	Map<String,String> params= new HashMap<String,String>();
    while(paramNames.hasMoreElements()) 
	{
      		String paramName = (String)paramNames.nextElement();
      		String paramValue = "" +request.getParameter(paramName);
   	    params.put(paramName,paramValue);
	}
	

%>
<html>

<script>

function submitPayuForm() {
	  var payuFormfailure = document.forms.payuFormfailure;
	  payuFormfailure.submit();
}
</script>

<body onload="submitPayuForm();">


<div style="border: 2px solid #D3D3D3; margin-left:12%; margin-right:12%; margin-top:30px;" >
     <div style="border-bottom: 1px solid #D3D3D3; height: 30px; margin-left:10px; margin-right:10px; margin-top:5px; ">
      <strong>Transaction is in progress will redirect to the The Nature World web site </strong>
     </div>
   <div>
   <ul style="width: 65%; margin-top:30px;">
   <li>This is a secure payment gateway.</li>
   <li>When you submit the transaction the server will take about 1 to 5 second to process, but it may take longer at certain times.</li>
   <li>Please do not press "Submit" button once again or the "Back" or "Refresh" buttons</li>
   </ul>
   </div>
</div>

<div style="display:none">
<form action="../pym/failure" method="post" name="payuFormfailure">

       <table>
        <tr>
          <td><b>Mandatory Parameters</b></td>
        </tr>
        
        <tr>
          <td>Tax Id: </td>
          <td><input name="txnid" value="<%= (empty(params.get("txnid"))) ? "" : params.get("txnid") %>" /></td>
        </tr>
        <tr>
          <td>USER ID: </td>
          <td><input name="User ID" value="<%= (empty(params.get("udf1"))) ? "" : params.get("udf1") %>" /></td>
         </tr>
         <tr>
          <td>E Distric Id : </td>
          <td><input name="E Distt Id" value="<%= (empty(params.get("udf2"))) ? "" : params.get("udf2") %>" /></td>
        </tr>
        
        <tr>
          <td>Amount: </td>
          <td><input name="amount" value="<%= (empty(params.get("amount"))) ? "" : params.get("amount") %>" /></td>
         </tr>
      
   
        <tr>
        
            <td colspan="4"><input type="submit" value="Submit" /></td>
        
        </tr>
      </table>
    </form>

</div>
</body>
</html>