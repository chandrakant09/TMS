<%@ page import="java.util.*" %>
<%@ page import="java.security.*" %>

<%!
public boolean empty(String s)
	{
		if(s== null || s.trim().equals(""))
			return true;
		else
			return false;
	}
%>
<%!
	public String hashCal(String type,String str){
		byte[] hashseq=str.getBytes();
		StringBuffer hexString = new StringBuffer();
		try{
		MessageDigest algorithm = MessageDigest.getInstance(type);
		algorithm.reset();
		algorithm.update(hashseq);
		byte messageDigest[] = algorithm.digest();
            
		

		for (int i=0;i<messageDigest.length;i++) {
			String hex=Integer.toHexString(0xFF & messageDigest[i]);
			if(hex.length()==1) hexString.append("0");
			hexString.append(hex);
		}
			
		}catch(NoSuchAlgorithmException nsae){ }
		
		return hexString.toString();


	}
%>
<% 	
	String merchant_key="IosCeBuu";
	String salt="1lrZOGfTw0";
	String action1 ="";
	String base_url="https://secure.payu.in";
	int error=0;
	String hashString="";
	
 	String hashSequence = "key|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5|udf6|udf7|udf8|udf9|udf10";
 	String attributes = "key|txnid|amount|productinfo|firstname|email|surl|phone|curl|furl|service_provider|udf1|udf2";
	Enumeration<String> paramNames = request.getAttributeNames();
	Map<String,String> params= new HashMap<String,String>();
    while(paramNames.hasMoreElements()) 
	{
      		String paramName = (String)paramNames.nextElement();
      		String paramValue = "" +request.getAttribute(paramName);
      		if(attributes.indexOf(paramName) !=-1){
		    	params.put(paramName,paramValue);
		    	//out.println(paramName+">"+ paramValue+"<br />");
      		}
	}
	String txnid ="";
	String udf2="";
	if(empty(params.get("txnid"))){
		Random rand = new Random();
		String rndm = Integer.toString(rand.nextInt())+(System.currentTimeMillis() / 1000L);
		txnid=hashCal("SHA-256",rndm).substring(0,20);
	}
	else
		txnid=params.get("txnid");
    //    udf2 = txnid;
	//String txn="abcd";
	String hash="";
	if(empty(params.get("hash")) && params.size()>0)
	{
		if( empty(params.get("key"))
			|| empty(params.get("txnid"))
			|| empty(params.get("amount"))
			|| empty(params.get("firstname"))
			|| empty(params.get("email"))
			|| empty(params.get("phone"))
			|| empty(params.get("productinfo"))
			|| empty(params.get("surl"))
			|| empty(params.get("furl"))
			|| empty(params.get("service_provider"))
	){
			
			error=1;
		}else{
			String[] hashVarSeq=hashSequence.split("\\|");
			
			for(String part : hashVarSeq)
			{
				hashString= (empty(params.get(part)))?hashString.concat(""):hashString.concat(params.get(part));
				hashString=hashString.concat("|");
			}
			hashString=hashString.concat(salt);
			//out.println("HashString:"+hashString);
			hash=hashCal("SHA-512",hashString);
			//.println("Hash:"+hash);
			action1=base_url.concat("/_payment");
			//out.println("Action"+action1);
		}
	}
	else if(!empty(params.get("hash")))
	{
		hash=params.get("hash");
		action1=base_url.concat("/_payment");
	}
		

%>
<html>

<script>
var hash='<%= hash %>';
function submitPayuForm() {
	
	if (hash == '')
		return;
	  	
      var payuForm = document.forms.payuForm;
      payuForm.submit();
    }
</script>

<body onload="submitPayuForm();">

<div> </div>

<div style="border: 2px solid #D3D3D3; margin-left:12%; margin-right:12%; margin-top:30px;" >
     <div style="border-bottom: 1px solid #D3D3D3; height: 30px; margin-left:10px; margin-right:10px; margin-top:5px; ">
      <strong>Wallet payment is in progress don't refresh the page </strong>
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
<form action="<%= action1 %>" method="post" name="payuForm">
<input type="hidden" name="key" value="<%= merchant_key %>" />
      <input type="hidden" name="hash" value="<%= hash %>"/>
      <input type="hidden" name="txnid" value="<%= txnid %>" />
     
      <input type="hidden" name="service_provider" value="payu_paisa" />
      <table>
        <tr>
          <td><b>Mandatory Parameters</b></td>
        </tr>
        <tr>
          <td>Amount: </td>
          <td><input name="amount" value="<%= (empty(params.get("amount"))) ? "" : params.get("amount") %>" /></td>
          <td>First Name: </td>
          <td><input name="firstname" id="firstname" value="<%= (empty(params.get("firstname"))) ? "" : params.get("firstname") %>" /></td>
        </tr>
        <tr>
          <td>Email: </td>
          <td><input name="email" id="email" value="<%= (empty(params.get("email"))) ? "" : params.get("email") %>" /></td>
          <td>Phone: </td>
          <td><input name="phone" value="<%= (empty(params.get("phone"))) ? "" : params.get("phone") %>" /></td>
        </tr>
        <tr>
          <td>Product Info: </td>
          <td colspan="3"><input name="productinfo" value="<%= (empty(params.get("productinfo"))) ? "" : params.get("productinfo") %>" size="64" /></td>
        </tr>
        <tr>
          <td>Success URI: </td>
          <td colspan="3"><input name="surl" value="<%= (empty(params.get("surl"))) ? "" : params.get("surl") %>" size="64" /></td>
        </tr>
        <tr>
          <td>Failure URI: </td>
          <td colspan="3"><input name="furl" value="<%= (empty(params.get("furl"))) ? "" : params.get("furl") %>" size="64" /></td>
        </tr>
        <tr>
          <td><b>Optional Parameters</b></td>
        </tr>
        <tr>
          <td>Last Name: </td>
          <td><input name="lastname" id="lastname" value="<%= (empty(params.get("lastname"))) ? "" : params.get("lastname") %>" /></td>
          <td>Cancel URI: </td>
          <td><input name="curl" value="" /></td>
        </tr>
        <tr>
          <td>Address1: </td>
          <td><input name="address1" value="<%= (empty(params.get("address1"))) ? "" : params.get("address1") %>" /></td>
          <td>Address2: </td>
          <td><input name="address2" value="<%= (empty(params.get("address2"))) ? "" : params.get("address2") %>" /></td>
        </tr>
        <tr>
          <td>City: </td>
          <td><input name="city" value="<%= (empty(params.get("city"))) ? "" : params.get("city") %>" /></td>
          <td>State: </td>
          <td><input name="state" value="<%= (empty(params.get("state"))) ? "" : params.get("state") %>" /></td>
        </tr>
        <tr>
          <td>Country: </td>
          <td><input name="country" value="<%= (empty(params.get("country"))) ? "" : params.get("country") %>" /></td>
          <td>Zipcode: </td>
          <td><input name="zipcode" value="<%= (empty(params.get("zipcode"))) ? "" : params.get("zipcode") %>" /></td>
        </tr>
        <tr>
          <td>UDF1: </td>
          <td><input name="udf1" value="<%= (empty(params.get("udf1"))) ? "" : params.get("udf1") %>" /></td>
        </tr>  
        <tr>
          <td>UDF2: </td>
          <td><input name="udf2" value="<%= (empty(params.get("udf2"))) ? "" : params.get("udf2") %>" /></td>
         </tr>  
          
        <tr>
          <td>UDF3: </td>
          <td><input name="udf3" value="<%= (empty(params.get("udf3"))) ? "" : params.get("udf3") %>" /></td>
          <td>UDF4: </td>
          <td><input name="udf4" value="<%= (empty(params.get("udf4"))) ? "" : params.get("udf4") %>" /></td>
        </tr>
        <tr>
          <td>UDF5: </td>
          <td><input name="udf5" value="<%= (empty(params.get("udf5"))) ? "" : params.get("udf5") %>" /></td>
          <td>PG: </td>
          <td><input name="pg" value="<%= (empty(params.get("pg"))) ? "" : params.get("pg") %>" /></td>
        </tr>
        <tr>
       
          <% if(empty(hash)){ %>
            <td colspan="4"><input type="submit" value="Submit" /></td>
          <% } %>  
        </tr>
      </table>
    </form>
</div>

</body>
</html>