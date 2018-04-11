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
public class ConfirmNewPasswordException extends AbstractBaseException{

	private static final long serialVersionUID = 7174233440738361825L;
	public ConfirmNewPasswordException() {
		super();
	}
	public ConfirmNewPasswordException(String message) {
		super(message);
	}
	@ExceptionHandler(ConfirmNewPasswordException.class)
	public ResponseEntity<ResponseMessageDTO> exceptionHttpCall(HttpServletRequest req, Exception e){
		return getHTTPResponse(req, e);
	}
}
