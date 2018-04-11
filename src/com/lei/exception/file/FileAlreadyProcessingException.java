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
public class FileAlreadyProcessingException  extends AbstractBaseException {
	
	private static final long serialVersionUID = -6924986693453053425L;
	public FileAlreadyProcessingException() {
		super();
	}
	
	public FileAlreadyProcessingException(String message) {
		super(message);
	}
	@ExceptionHandler(FileAlreadyProcessingException.class)
	public ResponseEntity<ResponseMessageDTO> exceptionHttpCall(HttpServletRequest req, Exception e){
		return getHTTPResponse(req, e);
	}
}