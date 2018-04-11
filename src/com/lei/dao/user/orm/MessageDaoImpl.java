package com.lei.dao.user.orm;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lei.dao.base.orm.HibernatePersistenceManager;
import com.lei.dao.user.IMessageDAO;
import com.lei.domain.user.MessageDomain;
import com.lei.dto.user.MessageDTO;
import com.lei.exception.common.ObjectNotSupportedException;
import com.lei.exception.common.ProcessFailedException;
import com.lei.utility.CommonUtils;

@Slf4j
@Repository("message")
public class MessageDaoImpl implements IMessageDAO {
	
	HibernatePersistenceManager hibernatePersistenceManager = null;
	public MessageDaoImpl() {
		hibernatePersistenceManager = new HibernatePersistenceManager();
	}
 	
	@Override
	public boolean saveMessage(MessageDTO messageDTO) throws ObjectNotSupportedException, ProcessFailedException {
		boolean result= false;
		MessageDomain message = CommonUtils.convertObject(messageDTO, MessageDomain.class);
		
		try{
			hibernatePersistenceManager.beginTransaction();
			hibernatePersistenceManager.save(message);
			result = true;
			hibernatePersistenceManager.commit();
		}catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
			hibernatePersistenceManager.rollback();
			throw new ProcessFailedException("Save Message Failed.");
		}
		
		return result;
	}
	
	@Override
	public List<MessageDTO> getActiveMessage() throws ObjectNotSupportedException {
		List<MessageDTO> messageDTOs=new ArrayList<MessageDTO>();
		hibernatePersistenceManager.beginTransaction();
		Criteria user = hibernatePersistenceManager.createCriteria(MessageDomain.class);
		user.add(Restrictions.eq("status", "Active"));
		List<MessageDomain> result = user.list();
		for(MessageDomain domain:result){
			messageDTOs.add(CommonUtils.convertObject(domain,MessageDTO.class));
		}
		return messageDTOs;
	}
	
}
