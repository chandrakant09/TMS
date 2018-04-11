package com.lei.utility;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lei.dao.user.IAuthenticatorDao;
import com.lei.dao.user.orm.AuthenticatorDaoImpl;
import com.lei.domain.user.AuthenticationDomain;
import com.lei.exception.common.ProcessFailedException;
import com.lei.utility.constants.ApplicationConstants;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
public class Authenticator {
	private static final String HEADERTOKENKEY = PropertyUtility.getValueString(ApplicationConstants.CONFIGURATIONSBUNDLE.getValue(), ApplicationConstants.SESSIONTOKEN.getValue(),null);
	long sessionDuration = 600000;
	
	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	private String token = null;
	
	public Authenticator(HttpServletRequest request,HttpServletResponse response){
		this.request=request;
		this.response=response;
		token = getToken();
	}
	public boolean login(String loginID) throws ProcessFailedException{
		String newSessioToken = CommonUtils.getUniqueToken();
		String ipAddress = request.getRemoteAddr();
		Timestamp issueTime = CommonUtils.getCurrentTimestamp();
		Timestamp expiryDate = CommonUtils.getFutureTimestamp(sessionDuration); 
		AuthenticationDomain authenticationData = null;
		IAuthenticatorDao dao = new AuthenticatorDaoImpl();
		
		authenticationData = new AuthenticationDomain();
		authenticationData.setToken(newSessioToken);
		authenticationData.setLoginId(loginID);
		authenticationData.setIpAddress(ipAddress);
		authenticationData.setIssueDate(issueTime);
		authenticationData.setExpiryDate(expiryDate);
		authenticationData.setExpiryDetail("Not Expired");
		authenticationData.setInvalidated("N");
		dao.login(authenticationData);
		
		CommonUtils.setHeader(response, HEADERTOKENKEY, newSessioToken);
		
		return true;
	}
	
	public boolean authenticate(){
		IAuthenticatorDao dao = new AuthenticatorDaoImpl();
		Timestamp currentTime = CommonUtils.getCurrentTimestamp();
		
		if(dao.authenticate(token, currentTime)!=null)
			return true;
		else
			return false;
	}
	
	public String getLoginId(){
		IAuthenticatorDao dao = new AuthenticatorDaoImpl();
		Timestamp currentTime = CommonUtils.getCurrentTimestamp();
		AuthenticationDomain authData = null;
		
		if((authData=dao.authenticate(token, currentTime))!=null)
			return authData.getLoginId();
		else
			return null;
	}
	
	public boolean logout() throws ProcessFailedException{
		IAuthenticatorDao dao = new AuthenticatorDaoImpl();
		Timestamp currentTime = CommonUtils.getCurrentTimestamp();
		AuthenticationDomain authData = null;
		
		if((authData=dao.authenticate(token, currentTime))!=null){
			authData.setInvalidated("Y");
			authData.setInvalidateTime(currentTime);
			authData.setInvalidateDetail("User requested to logout");
			
			return dao.update(authData);
		}
		else{
			return false;
		}
		
	}
	
	private String getToken(){
		String token = request.getHeader(HEADERTOKENKEY);
		return token;
	}
}
