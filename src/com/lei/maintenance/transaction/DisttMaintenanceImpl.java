package com.lei.maintenance.transaction;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.lei.dao.base.DaoManager;
import com.lei.dao.transaction.IDisttDao;
import com.lei.dao.transaction.ITransactionDao;
import com.lei.dao.user.IUserDao;
import com.lei.dto.master.ReportFilter;
import com.lei.dto.user.UserDTO;
import com.lei.dto.wallet.EdisttDTO;
import com.lei.dto.wallet.PaidServiceDTO;
import com.lei.exception.common.ObjectNotSupportedException;
import com.lei.exception.common.ProcessFailedException;
import com.lei.utility.constants.PaidServices;

@Slf4j
@Service
public class DisttMaintenanceImpl<edisttDTO> implements IDisttMaintrnance{

	@Override
	public EdisttDTO edistrictRequestProcess(EdisttDTO edisttDTO) throws ObjectNotSupportedException, ProcessFailedException {
		IDisttDao dao = DaoManager.DISTTDAO.getDao(IDisttDao.class);
		ITransactionDao transactionDao = DaoManager.TRABSACTIONDAO.getDao(ITransactionDao.class);
		IUserDao userDao = DaoManager.USER.getDao(IUserDao.class);
		
		UserDTO userDTO=userDao.getUserDomainByDisttId(edisttDTO.getG2cId());
		PaidServiceDTO paidServiceDTO=transactionDao.getDeductionAmount(PaidServices.Paidservice1.getId());
		
		long transactionId=transactionDao.amountDeductionProcess(userDTO.getId(), paidServiceDTO.getCharges());
		
		edisttDTO.setTrnid(transactionId+"");
		edisttDTO.setRemark("Trasaction Success with Trasaction Id"+transactionId);
		edisttDTO=dao.saveEdittDetail(edisttDTO);
		return edisttDTO;
	
	}
	
	@Override
	public EdisttDTO edistrictRequestSaved(EdisttDTO edisttDTO) throws ObjectNotSupportedException, ProcessFailedException {
		IDisttDao dao = DaoManager.DISTTDAO.getDao(IDisttDao.class);
		edisttDTO=dao.saveEdittDetail(edisttDTO);
		return edisttDTO;
	
	}
	
	@Override
	public PaidServiceDTO getServiceDTO(String serviceId) throws ObjectNotSupportedException, ProcessFailedException {
		ITransactionDao transactionDao = DaoManager.TRABSACTIONDAO.getDao(ITransactionDao.class);
		PaidServiceDTO paidServiceDTO=transactionDao.getDeductionAmount(serviceId);
		return paidServiceDTO;
	}
	
	@Override
	public EdisttDTO isExist(EdisttDTO edisttDTO) throws ObjectNotSupportedException, ProcessFailedException {
		ITransactionDao transactionDao = DaoManager.TRABSACTIONDAO.getDao(ITransactionDao.class);
		EdisttDTO flag=transactionDao.isExist(edisttDTO);
		return flag;
	}
	
	
	@Override
	public List<EdisttDTO> getEdisttList(ReportFilter filter) throws ObjectNotSupportedException {
		IDisttDao dao = DaoManager.DISTTDAO.getDao(IDisttDao.class);
		List<EdisttDTO> edisttList = dao.getEdisttList(filter);
		return edisttList;
	}

	@Override
	public List<EdisttDTO> getApplicationtList(ReportFilter reportFilter)
			throws ObjectNotSupportedException {
		IDisttDao dao = DaoManager.DISTTDAO.getDao(IDisttDao.class);
		List<EdisttDTO> applicationtList = dao.getApplicationtList(reportFilter);
		return applicationtList;
	}

	@Override
	public List<EdisttDTO> getApplicationtList()
			throws ObjectNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}
	



}


