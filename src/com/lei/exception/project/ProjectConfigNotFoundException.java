package com.lei.exception.project;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lei.dto.common.ResponseMessageDTO;
import com.lei.exception.base.AbstractBaseException;
import com.lei.exception.common.ObjectNotSupportedException;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
@ControllerAdvice
public class ProjectConfigNotFoundException extends AbstractBaseException{

	private static final long serialVersionUID = -7453416172206935310L;
	public ProjectConfigNotFoundException() {
		super("No configuratrion found for the project(s)");
	}
	
	public ProjectConfigNotFoundException(String message) {
		super(message);
	}
	@ExceptionHandler(ProjectConfigNotFoundException.class)
	public ResponseEntity<ResponseMessageDTO> exceptionHttpCall(HttpServletRequest req, Exception e){
		return getHTTPResponse(req, e);
	}

}

