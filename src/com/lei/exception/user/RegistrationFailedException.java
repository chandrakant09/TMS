/**
 * 
 */
package com.lei.exception.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lei.dto.common.ResponseMessageDTO;
import com.lei.exception.base.AbstractBaseException;

/**
 * @author santosh.panigrahi
 *
 */
@ControllerAdvice
public class RegistrationFailedException extends  AbstractBaseException {

	private static final long serialVersionUID = 1L;

	public RegistrationFailedException() {
		super();
	}

	public RegistrationFailedException(String message){
		super();
	}

	@Override
	@ExceptionHandler(RegistrationFailedException.class)
	public ResponseEntity<ResponseMessageDTO> exceptionHttpCall(HttpServletRequest req, Exception e) {
		return getHTTPResponse(req, e);
	}

}
