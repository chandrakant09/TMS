package com.lei.exception.scheduler;

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
public class SchedulingFailedException extends AbstractBaseException{
	private static final long serialVersionUID = -7178669041257042406L;
	public SchedulingFailedException() {
		super();
	}
	
	public SchedulingFailedException(String message) {
		super(message);
	}
	@ExceptionHandler(SchedulingFailedException.class)
	public ResponseEntity<ResponseMessageDTO> exceptionHttpCall(HttpServletRequest req, Exception e){
		return getHTTPResponse(req, e);
	}
}
