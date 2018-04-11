package com.lei.exception.file;

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
public class DataNotSupportedException extends AbstractBaseException {
	
	private static final long serialVersionUID = -6924986693453053425L;
	public DataNotSupportedException() {
		super();
	}
	
	public DataNotSupportedException(String message) {
		super(message);
	}
	@ExceptionHandler(DataNotSupportedException.class)
	public ResponseEntity<ResponseMessageDTO> exceptionHttpCall(HttpServletRequest req, Exception e){
		return getHTTPResponse(req, e);
	}
}
