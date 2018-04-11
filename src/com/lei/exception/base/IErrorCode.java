package com.lei.exception.base;

import com.lei.exception.common.InvalidFileException;
import com.lei.exception.common.InvalidKeyException;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
public interface IErrorCode {
	String getErrorCode() throws InvalidKeyException,InvalidFileException;
}
