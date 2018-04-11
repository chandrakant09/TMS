package com.lei.exception.user;

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
public class PasswordFormatException extends  AbstractBaseException{

	private static final long serialVersionUID = 2272615326597964077L;

	public PasswordFormatException(){
		super();
	}
	
	public PasswordFormatException(String message){
		super();
	}
	@ExceptionHandler(PasswordFormatException.class)
	public ResponseEntity<ResponseMessageDTO> exceptionHttpCall(HttpServletRequest req, Exception e){
		return getHTTPResponse(req, e);
	}
}
