package com.lei.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lei.maintenance.user.IUserMaintenance;
import com.lei.maintenance.user.UserMaintenanceImpl;
import com.lei.utility.Authenticator;
import com.lei.utility.CommonUtils;
import com.lei.utility.constants.HttpStatusCodes;
/**
 * 
 * @author Shrikant Kushwaha
 *
 */
public class AuthenticationFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest reqq, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)reqq;
		HttpServletResponse response = (HttpServletResponse)resp;
		
		Authenticator authenticator = new Authenticator(request,response);
		if(authenticator.authenticate()){
			IUserMaintenance userMaintenance = new UserMaintenanceImpl();
			String status = userMaintenance.getStatus(authenticator.getLoginId());
		/*	if(status.equalsIgnoreCase(StatusConstants.FORGETPASSWORD.getStatus())||
					status.equalsIgnoreCase(StatusConstants.NEW.getStatus())){
				CommonUtils.prepareErrorResponse(request, response, HttpStatusCodes.UNAUTHENTICATEDREQUEST.getCode(), "User need to change password first. Current user status:"+status,true);
				return;
			}else if(status.equalsIgnoreCase(StatusConstants.REGISTERED.getStatus())){
				chain.doFilter(request, response);
			}*/
		}else{
			CommonUtils.prepareErrorResponse(request, response, HttpStatusCodes.UNAUTHENTICATEDREQUEST.getCode(), "You are unauthenticated user. Please login.",true);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
