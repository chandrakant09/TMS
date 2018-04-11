package com.lei.exception.project;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lei.dto.common.ResponseMessageDTO;
import com.lei.exception.base.AbstractBaseException;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
@ControllerAdvice
public class ProjectExecutionFailedException extends AbstractBaseException{

	private static final long serialVersionUID = -1080887080626136960L;
	public ProjectExecutionFailedException() {
		super("No configuratrion found for the project(s)");
	}
	
	public ProjectExecutionFailedException(String message) {
		super(message);
	}
	@ExceptionHandler(ProjectExecutionFailedException.class)
	public ResponseEntity<ResponseMessageDTO> exceptionHttpCall(HttpServletRequest req, Exception e){
		return getHTTPResponse(req, e);
	}

}

