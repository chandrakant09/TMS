package com.lei.exception.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lei.dto.common.ResponseMessageDTO;
import com.lei.utility.PropertyUtility;
import com.lei.utility.constants.ApplicationConstants;

/**
 * Sub class of this class need to be annotated with @ControllerAdvice so that framework can map the handler for the Exception class. 
 * @author Shrikant Kushwaha
 *
 */
public abstract class AbstractBaseException extends Exception implements IErrorCode{
	
	private static final long serialVersionUID = -4393780960680486163L;
	
	public AbstractBaseException() {
		super();
	}
	
	public AbstractBaseException(String message) {
		super(message);
	}
	
	@Override
	public final String getErrorCode() {
		return PropertyUtility.getValueString(ApplicationConstants.EXCEPTIONBUNDLE.getValue(), this.getClass().getName(),"00");
	}
	
	public final ResponseEntity<ResponseMessageDTO> getHTTPResponse(HttpServletRequest req, Exception e){
		ResponseMessageDTO message = new ResponseMessageDTO();
		message.setError(true);
		message.setResponseCode(getErrorCode());
		message.setResponseMessage(e.getMessage());
		return new ResponseEntity<ResponseMessageDTO>(message, HttpStatus.OK);
	}
	/**
	 * Implementation of this method need to annotated with @ExceptionHandler(XYZ.class) which says its an handler of its exception class.  
	 * @param req
	 * @param e
	 * @return
	 */
	public abstract ResponseEntity<ResponseMessageDTO> exceptionHttpCall(HttpServletRequest req, Exception e);
}
