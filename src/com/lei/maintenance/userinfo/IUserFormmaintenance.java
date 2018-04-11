package com.lei.maintenance.userinfo;

import com.lei.dto.userform.UserFormDTO;
import com.lei.exception.common.ObjectNotSupportedException;

public interface IUserFormmaintenance {

	boolean save(UserFormDTO userFormDTO) throws ObjectNotSupportedException;

}
