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
public class FileDoesNotExistException extends AbstractBaseException {
	
	private static final long serialVersionUID = 6256339666848292568L;
	public FileDoesNotExistException() {
		super();
	}
	
	public FileDoesNotExistException(String message) {
		super(message);
	}
	@ExceptionHandler(FileDoesNotExistException.class)
	public ResponseEntity<ResponseMessageDTO> exceptionHttpCall(HttpServletRequest req, Exception e){
		return getHTTPResponse(req, e);
	}
}
