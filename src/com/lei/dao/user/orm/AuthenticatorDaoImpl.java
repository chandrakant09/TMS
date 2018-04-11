package com.lei.dao.user.orm;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.lei.dao.base.orm.HibernatePersistenceManager;
import com.lei.dao.user.IAuthenticatorDao;
import com.lei.domain.user.AuthenticationDomain;
import com.lei.exception.common.ProcessFailedException;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
public class AuthenticatorDaoImpl implements IAuthenticatorDao{
	HibernatePersistenceManager hibernatePersistenceManager =null;

	public AuthenticatorDaoImpl() {
		hibernatePersistenceManager = new HibernatePersistenceManager();
	}
	@Override
	public boolean login(AuthenticationDomain authData) throws ProcessFailedException {
		boolean result= false;
		try {
			hibernatePersistenceManager.beginTransaction();
			hibernatePersistenceManager.save(authData);
			result = true;
			hibernatePersistenceManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			hibernatePersistenceManager.rollback();
			throw new ProcessFailedException("User Login Failed in DAO.");
		}
		return result;

	}
	
	@Override
	public AuthenticationDomain authenticate(String token,Timestamp currentTime) {
		AuthenticationDomain authDomain=null;
		hibernatePersistenceManager.beginTransaction();
		Criteria authData = hibernatePersistenceManager.createCriteria(AuthenticationDomain.class);
		authData.add(Restrictions.eq("token", token));
		authData.add(Restrictions.ge("expiryDate",currentTime));
		authData.add(Restrictions.eq("invalidated", "N"));
		List<AuthenticationDomain> result = authData.list();
		if(result != null && result.size()>0){
			authDomain = result.get(0);
		}
		return authDomain;
	}
	@Override
	public boolean update(AuthenticationDomain authData) throws ProcessFailedException {
		boolean result= false;
		try {
			hibernatePersistenceManager.beginTransaction();
			hibernatePersistenceManager.saveOrUpdate(authData);
			result = true;
			hibernatePersistenceManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			hibernatePersistenceManager.rollback();
			throw new ProcessFailedException("User Logout Failed.");
		}
		return result;
	}
	
}
