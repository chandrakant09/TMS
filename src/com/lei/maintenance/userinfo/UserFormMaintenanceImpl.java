/*package com.lei.maintenance.userinfo;

import com.lei.dao.base.DaoManager;
import com.lei.dao.userfrom.IUserFormDao;
import com.lei.dto.userform.UserFormDTO;
import com.lei.exception.common.ObjectNotSupportedException;


public class UserFormMaintenanceImpl implements IUserFormmaintenance{
	
	@Override
	public boolean save(UserFormDTO userFormDTO) throws ObjectNotSupportedException{
		IUserFormDao dao = DaoManager.USERFORMDAO.getDao(IUserFormDao.class);
		boolean saved = false;
		saved = dao.save(userFormDTO);
	return saved;	
	}

}
*/