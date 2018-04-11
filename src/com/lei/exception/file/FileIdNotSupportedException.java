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
public class FileIdNotSupportedException extends AbstractBaseException {
	
	private static final long serialVersionUID = -7400066755177600911L;
	public FileIdNotSupportedException() {
		super();
	}
	
	public FileIdNotSupportedException(String message) {
		super(message);
	}
	@ExceptionHandler(FileIdNotSupportedException.class)
	public ResponseEntity<ResponseMessageDTO> exceptionHttpCall(HttpServletRequest req, Exception e){
		return getHTTPResponse(req, e);
	}
}
