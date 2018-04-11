package com.lei.dao.common.orm;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.lei.dao.base.orm.HibernatePersistenceManager;
import com.lei.dao.common.ICommonDao;
import com.lei.domain.user.StatusDomain;

public class CommonDaoImpl implements ICommonDao {

	
	HibernatePersistenceManager hibernatePersistenceManager =null;

	public CommonDaoImpl() {
		hibernatePersistenceManager = new HibernatePersistenceManager();
	}

	
	
	@Override
	public StatusDomain getStatus(int statusID) {
		StatusDomain userDomain=null;
		
		try{
			userDomain = hibernatePersistenceManager.getFromSession(StatusDomain.class, statusID);
		}catch(Exception e){
			System.out.println("Not present in session Fetching from DB");
		}
		
		if(userDomain!=null){
			return userDomain;
		}
		hibernatePersistenceManager.beginTransaction();
		Criteria user = hibernatePersistenceManager.createCriteria(StatusDomain.class);
		user.add(Restrictions.eq("name", statusID));
		List<StatusDomain> result = user.list();
		if(result != null && result.size()>0){
			userDomain = result.get(0);
		}
		
		return userDomain;
	}

}
