package com.lei.dao.user;

import com.lei.dto.user.UserEmailInfoDTO;
import com.lei.exception.common.ObjectNotSupportedException;
import com.lei.exception.common.ProcessFailedException;


public interface IUserEmailInfoDAO {

	boolean saveUserEmailInfo(UserEmailInfoDTO userEmailInfoDTO) throws ObjectNotSupportedException, ProcessFailedException ;

}
