package com.lei.exception.common;

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
public class EmailException  extends AbstractBaseException{

	private static final long serialVersionUID = 2569637955323014572L;
	
	public EmailException() {
		super();
	}
	
	public EmailException(String message) {
		super(message);
	}
	@ExceptionHandler(EmailException.class)
	public ResponseEntity<ResponseMessageDTO> exceptionHttpCall(HttpServletRequest req, Exception e){
		return getHTTPResponse(req, e);
	}
}
