package com.lei.dao.transaction.orm;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lei.dao.base.orm.HibernatePersistenceManager;
import com.lei.dao.transaction.IDisttDao;
import com.lei.domain.transaction.EdisttDomain;
import com.lei.dto.master.ReportFilter;
import com.lei.dto.wallet.EdisttDTO;
import com.lei.exception.common.ObjectNotSupportedException;
import com.lei.exception.common.ProcessFailedException;
import com.lei.utility.CommonUtils;

@Slf4j
@Repository("disttDao")
public class DisttDaoImpl implements IDisttDao {

	HibernatePersistenceManager  hibernatePersistenceManager=null;
	
	
	public DisttDaoImpl() {
		hibernatePersistenceManager = new HibernatePersistenceManager();
	}
	
	@Override
	public EdisttDTO saveEdittDetail(EdisttDTO edisttDTO)	throws ObjectNotSupportedException, ProcessFailedException {
		log.info("Going to save EdittDetail "+edisttDTO.getApplicationNo() +edisttDTO.getVleId());
		EdisttDomain edisttDomain=CommonUtils.convertObject(edisttDTO,EdisttDomain.class);
		try {
			hibernatePersistenceManager.beginTransaction();
			edisttDomain=(EdisttDomain)hibernatePersistenceManager.save(edisttDomain);
			hibernatePersistenceManager.commit();
			log.info("EdittDetail saved "+edisttDTO.getApplicationNo() +edisttDTO.getVleId());
		} catch (Exception e) {
			log.info(e.getMessage());
			hibernatePersistenceManager.rollback();
			throw new ProcessFailedException("Unable to save the EdisttDomain ");
		}
		edisttDTO=CommonUtils.convertObject(edisttDomain,EdisttDTO.class);
		
		return edisttDTO;
	}

	@Override
	public EdisttDTO updateEdittDetail(EdisttDTO edisttDTO) 	throws ObjectNotSupportedException, ProcessFailedException {
		EdisttDomain edisttDomain=CommonUtils.convertObject(edisttDTO,EdisttDomain.class);
		try {
			hibernatePersistenceManager.beginTransaction();
			edisttDomain=(EdisttDomain)hibernatePersistenceManager.save(edisttDomain);
			hibernatePersistenceManager.commit();
		} catch (Exception e) {
			log.info(e.getMessage());
			hibernatePersistenceManager.rollback();
			throw new ProcessFailedException("Unable to update the EdisttDomain wallet");
		}
		edisttDTO=CommonUtils.convertObject(edisttDomain,EdisttDTO.class);
		return edisttDTO;
	}

	@Override
	public List<EdisttDTO> getEdittDetails(String edisttId)	throws ObjectNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	// Get All District List
	public List<EdisttDTO> getEdisttList(ReportFilter reportFilter) throws ObjectNotSupportedException {
		List<EdisttDTO> edisttList = new ArrayList<EdisttDTO>();
		hibernatePersistenceManager.beginTransaction();
		Criteria edistt = hibernatePersistenceManager.createCriteria(EdisttDomain.class);
		edistt = getFilteredData(edistt, reportFilter);
		List<EdisttDomain> result = edistt.list();
		for(EdisttDomain edisttDomain:result){
			EdisttDTO edisttDTO = CommonUtils.convertObject(edisttDomain, EdisttDTO.class);
			edisttList.add(edisttDTO);
		}
		
		return edisttList;
	}
	
	
	@Override
	// Get All Application List
	public List<EdisttDTO> getApplicationtList(ReportFilter reportFilter) throws ObjectNotSupportedException {
		List<EdisttDTO> applicationtList = new ArrayList<EdisttDTO>();
		hibernatePersistenceManager.beginTransaction();
		Criteria edistt = hibernatePersistenceManager.createCriteria(EdisttDomain.class);
		edistt.add(Restrictions.eq("transstatus","Y"));
		edistt.add(Restrictions.eq("g2cId",reportFilter.getG2cId()));
	//	edistt = getFilteredData(edistt, reportFilter);
		List<EdisttDomain> result = edistt.list();
		for(EdisttDomain edisttDomain:result){
			EdisttDTO edisttDTO = CommonUtils.convertObject(edisttDomain, EdisttDTO.class);
			applicationtList.add(edisttDTO);
		}
		return applicationtList;
	}
	
	
	
	
	private Criteria getFilteredData(Criteria data,ReportFilter reportFilter){
		if(reportFilter.getVleId() != null ){
			data.add(Restrictions.eq("vleId", reportFilter.getVleId()));
		}
		if(reportFilter.getG2cId() != null ){
			data.add(Restrictions.eq("g2cId", reportFilter.getG2cId()));
		}
		if( reportFilter.getStatus() != null ){
			data.add(Restrictions.eq("status", reportFilter.getStatus()));
		}
		return data;
	}

}
