package com.lei.filters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lei.dto.common.GoogleCaptchaResponse;
import com.lei.utility.CommonUtils;
import com.lei.utility.PropertyUtility;
import com.lei.utility.constants.ApplicationConstants;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
public class CaptchaValidationFilter implements Filter{

//	private static List<String> blockedIpAddress = new ArrayList<String>();
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponseCopier responseCopier = null;
		responseCopier = new HttpServletResponseCopier((HttpServletResponse) response);

		if(validateCaptcha(request)){
			chain.doFilter(request, responseCopier);
			responseCopier.flushBuffer();
//			byte[] copy = responseCopier.getCopy();
//            System.out.println("WS Response ---- >"+new String(copy, response.getCharacterEncoding()));
		}else{
			CommonUtils.prepareErrorResponse(request, response, "4001", "Invalid captcha Exception", true);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
	private boolean validateCaptcha(ServletRequest request){
		String requestedURI = null;
		boolean authenticateThisURI = false;
		boolean continueRequest = false;
		
		requestedURI = CommonUtils.getRequestedURI(request);
		authenticateThisURI = PropertyUtility.getValueBoolean(ApplicationConstants.CAPTCHAVALIDATIONBUNDLE.getValue(), requestedURI, false);
		
		if(authenticateThisURI){
			HttpServletRequest httpRequest = null;
			String captchaVariable = null;
			String captchaValue = null;
			String captchaPrivateKey = null;
//			String captchaPublicKey = null;

			httpRequest = (HttpServletRequest)request;
			captchaVariable = PropertyUtility.getValueString(ApplicationConstants.CAPTCHAVALIDATIONBUNDLE.getValue(), ApplicationConstants.CAPTCHAVARIABLE.getValue(),null);

			if(captchaVariable!=null){
				captchaValue = httpRequest.getHeader(captchaVariable);
				if(captchaValue!=null){
					captchaPrivateKey = PropertyUtility.getValueString(ApplicationConstants.CAPTCHAVALIDATIONBUNDLE.getValue(), ApplicationConstants.CAPTCHAPRIVATEKEY.getValue(),null);
//					captchaPublicKey = PropertyUtility.getValueString(ApplicationConstants.CAPTCHAVALIDATIONBUNDLE.getValue(), ApplicationConstants.CAPTCHAPUBLICKEY.getValue(),null);
					 if(validateCaptcha(captchaPrivateKey, captchaValue, httpRequest.getRemoteAddr())){
						 continueRequest = true;
					 }else{
						 continueRequest = false;
					 }
				}else{
					continueRequest = false;
				}
			}else{
				continueRequest = false;
			}

		}else{
			continueRequest=true;
		}
		return continueRequest;
	}
	private boolean validateCaptcha(String secret, String response, String remoteip)
	{
	    URLConnection connection = null;
	    InputStream is = null;
	    String charset = java.nio.charset.StandardCharsets.UTF_8.name();

	    String url = PropertyUtility.getValueString(ApplicationConstants.CAPTCHAVALIDATIONBUNDLE.getValue(), ApplicationConstants.CAPTCHAURL.getValue(),null);
	    try {            
	        String query = String.format("secret=%s&response=%s&remoteip=%s", 
	        URLEncoder.encode(secret, charset), 
	        URLEncoder.encode(response, charset),
	        URLEncoder.encode(remoteip, charset));

	        connection = new URL(url + "?" + query).openConnection();
	        is = connection.getInputStream();
	        
	        BufferedReader reader = new BufferedReader(
	        		new InputStreamReader(is));
	        String line=null;
	        String outputString = "";
	        while ((line = reader.readLine()) != null) {
	        	outputString += line;
	        }
	        GoogleCaptchaResponse  respDTO = CommonUtils.getObjectFromJSON(outputString, GoogleCaptchaResponse.class);
	        if(respDTO!=null && respDTO.isSuccess()){
	        	return true;
	        }else{
	        	return false;
	        }
	    } catch (IOException ex) {
	    	System.out.println("Exception Generated:"+ex);
	    	return true;
	    }
	    finally {
	        if (is != null) {
	            try {
	                is.close();
	            } catch (IOException e) {
	            	return true;
	            }

	        }
	    }
	}
}