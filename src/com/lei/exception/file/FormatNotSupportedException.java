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
public class FormatNotSupportedException extends AbstractBaseException {
	
	private static final long serialVersionUID = 5444303615937906532L;
	public FormatNotSupportedException() {
		super();
	}
	
	public FormatNotSupportedException(String message) {
		super(message);
	}
	@ExceptionHandler(FormatNotSupportedException.class)
	public ResponseEntity<ResponseMessageDTO> exceptionHttpCall(HttpServletRequest req, Exception e){
		return getHTTPResponse(req, e);
	}
}
