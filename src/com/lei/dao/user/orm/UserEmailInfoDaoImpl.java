package com.lei.dao.user.orm;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import com.lei.dao.base.orm.HibernatePersistenceManager;
import com.lei.dao.user.IUserEmailInfoDAO;
import com.lei.domain.user.UserEmailInfoDomain;
import com.lei.dto.user.UserEmailInfoDTO;
import com.lei.exception.common.ObjectNotSupportedException;
import com.lei.exception.common.ProcessFailedException;
import com.lei.utility.CommonUtils;

@Slf4j
@Repository("userEmailInfoDao")
public class UserEmailInfoDaoImpl implements IUserEmailInfoDAO{
	HibernatePersistenceManager hibernatePersistenceManager =null;
	public UserEmailInfoDaoImpl() {
		hibernatePersistenceManager = new HibernatePersistenceManager();
	}
	
	
	
	@Override
	public boolean saveUserEmailInfo(UserEmailInfoDTO userEmailInfoDTO) throws ObjectNotSupportedException, ProcessFailedException {
		boolean result= false;
	UserEmailInfoDomain	useremailinfo = CommonUtils.convertObject(userEmailInfoDTO, UserEmailInfoDomain.class);
		try{
			hibernatePersistenceManager.beginTransaction();
			hibernatePersistenceManager.save(useremailinfo);
			result = true;
			hibernatePersistenceManager.commit();
		}catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
			hibernatePersistenceManager.rollback();
			throw new ProcessFailedException("Save in User Email Info Failed.");
		}
		
		return result;
	}
	
	
	

}
