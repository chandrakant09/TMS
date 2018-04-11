package com.lei.security.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;


@Component("requestAwareAuthenticationFailureHandler")
public class RequestAwareAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{

	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		//super.onAuthenticationFailure(request, response, exception);
		
		 System.out.println("Login Failed...");
		 super.onAuthenticationFailure(request, response, exception);
		   /* response.getWriter().print(
		            "{'login': 'FAIL'}");
		    response.getWriter().flush();*/
	}
}
