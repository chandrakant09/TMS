/*package com.lei.dao.userform.orm;

import lombok.extern.log4j.Log4j;

import org.springframework.stereotype.Repository;

import com.lei.dao.base.orm.HibernatePersistenceManager;
import com.lei.dao.userfrom.IUserFormDao;
import com.lei.domain.userform.UserFormDomain;
import com.lei.dto.userform.UserFormDTO;
import com.lei.exception.common.ObjectNotSupportedException;
import com.lei.exception.user.RegistrationFailedException;
import com.lei.utility.CommonUtils;

@Log4j
@Repository
public class UserFormDaoImpl implements IUserFormDao{
	HibernatePersistenceManager hibernatePersistenceManager =null;

	public UserFormDaoImpl() {
		hibernatePersistenceManager = new HibernatePersistenceManager();
	}
	
	@Override
	public boolean save(UserFormDTO userFormDTO) throws ObjectNotSupportedException {
		boolean result= false;
		UserFormDomain userform = CommonUtils.convertObject(userFormDTO, UserFormDomain.class);
		
		userform.setId(1);
		userform.setName("Chandra");
       try {
			hibernatePersistenceManager.beginTransaction();
			hibernatePersistenceManager.save(userform);
			
			result = true;
			hibernatePersistenceManager.commit();
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
			hibernatePersistenceManager.rollback();
			try {
				throw new RegistrationFailedException("UserForm Registration Failed.");
			} catch (RegistrationFailedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return result;

	}

}
*/