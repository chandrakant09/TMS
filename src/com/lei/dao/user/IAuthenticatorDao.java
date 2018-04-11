package com.lei.dao.user;

import java.sql.Timestamp;

import com.lei.domain.user.AuthenticationDomain;
import com.lei.exception.common.ProcessFailedException;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
public interface IAuthenticatorDao {
	public boolean login(AuthenticationDomain authData) throws ProcessFailedException;
	public AuthenticationDomain authenticate(String token,Timestamp currentTime);
	public boolean update(AuthenticationDomain authData) throws ProcessFailedException ;
}
