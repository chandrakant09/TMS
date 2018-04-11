package com.lei.dao.base;

import com.lei.dao.common.ICommonDao;
import com.lei.dao.master.IMasterDAO;
import com.lei.dao.transaction.IDisttDao;
import com.lei.dao.transaction.ITransactionDao;
import com.lei.dao.user.IMessageDAO;
import com.lei.dao.user.IUserDao;
import com.lei.utility.PropertyUtility;
import com.lei.utility.constants.ApplicationConstants;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
public enum DaoManager {
	USER("userdao",IUserDao.class),
	USERAUTHDAO("authdao",IUserDao.class),
	PROJECTDAO("projectdao",IUserDao.class),
	COMMONDAO("commondao",ICommonDao.class),
	TRABSACTIONDAO("transactiondao",ITransactionDao.class),
	DISTTDAO("disttDao",IDisttDao.class),
	MASTERDAO("masterdao",IMasterDAO.class),
	MESSAGEDAO("messagedao",IMessageDAO.class)
	;
	
	
	private final static String prefix = "maindao";
	private Class<?> type = null;
	private String classPath = null;
	
	DaoManager(String daoKey,Class type){
		this.type = type;
		
		String prefix =PropertyUtility.getValueString(ApplicationConstants.DAOABSTRACTIONBUNDLE.getValue(),DaoManager.prefix,null);
		String fullDaoKey = prefix+"."+daoKey;
		classPath = PropertyUtility.getValueString(ApplicationConstants.DAOABSTRACTIONBUNDLE.getValue(),fullDaoKey,null);
	}
	
	public <T> T getDao(Class<T> type){
		Object object = null;
		
		try {
			object = Class.forName(classPath).newInstance();
			if(type.isInstance(object)){
				return type.cast(object);
			}else{
				return null;
			}
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			return null;
		}
	}
	public Object getDao(){
		return getDao(type);
	}
}
