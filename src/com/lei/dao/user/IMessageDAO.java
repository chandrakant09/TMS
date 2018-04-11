package com.lei.dao.user;

import java.util.List;

import com.lei.dto.user.MessageDTO;
import com.lei.exception.common.ObjectNotSupportedException;
import com.lei.exception.common.ProcessFailedException;

public interface IMessageDAO {

	boolean saveMessage(MessageDTO messageDTO)	throws ObjectNotSupportedException, ProcessFailedException;

	List<MessageDTO> getActiveMessage() throws ObjectNotSupportedException;

}
