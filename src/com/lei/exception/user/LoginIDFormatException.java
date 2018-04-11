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
public class LoginIDFormatException extends AbstractBaseException{

	private static final long serialVersionUID = -6088396182029122247L;
	
	public LoginIDFormatException(String message) {
		super(message);
	}
	
	public LoginIDFormatException() {
		super();
	}
	@ExceptionHandler(LoginIDFormatException.class)
	public ResponseEntity<ResponseMessageDTO> exceptionHttpCall(HttpServletRequest req, Exception e){
		return getHTTPResponse(req, e);
	}
}
