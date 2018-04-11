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
      		out.println(paramName+">"+ paramValue+"<br />");
		    params.put(paramName,paramValue);
	}
	

%>
<html>

<script>

function submitPayuForm() {
	  var payuFormsuccess = document.forms.payuFormsuccess;
	 // payuFormsuccess.submit();
}
</script>

<body onload="submitPayuForm();">


<form action="pym/failure" method="post" name="payuFormsuccess">

       <table>
        <tr>
          <td><b>Mandatory Parameters</b></td>
        </tr>
        <tr>
          <td>Amount: </td>
          <td><input name="amount" value="<%= (empty(params.get("amount"))) ? "" : params.get("amount") %>" /></td>
         </tr>
         <tr>
          <td>Amount: </td>
          <td><input name="txnid" value="<%= (empty(params.get("txnid"))) ? "" : params.get("txnid") %>" /></td>
        </tr>
      
   
        <tr>
        
            <td colspan="4"><input type="submit" value="Submit" /></td>
        
        </tr>
      </table>
    </form>


</body>
</html>