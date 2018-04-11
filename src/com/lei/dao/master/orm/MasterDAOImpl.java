package com.lei.dao.master.orm;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lei.dao.base.orm.HibernatePersistenceManager;
import com.lei.dao.master.IMasterDAO;
import com.lei.domain.master.District;
import com.lei.dto.master.DistrictDTO;
import com.lei.exception.common.ObjectNotSupportedException;
import com.lei.utility.CommonUtils;

@Repository("masterdao")
public class MasterDAOImpl implements IMasterDAO {
	HibernatePersistenceManager hibernatePersistenceManager;
	
	public MasterDAOImpl() {
		hibernatePersistenceManager=new HibernatePersistenceManager();
	}
	

	

	@Override
	public List<DistrictDTO> getDistricList(String districtName) throws ObjectNotSupportedException {
		List<DistrictDTO> districtDTOs=new ArrayList<DistrictDTO>();
		hibernatePersistenceManager.beginTransaction();
		Criteria distt = hibernatePersistenceManager.createCriteria(District.class);
		if(districtName != null){
			distt.add(Restrictions.eq("disttName", districtName));
		}
		
		List<District> ds=distt.list();
		for(District d:ds){
			districtDTOs.add(CommonUtils.convertObject(d,DistrictDTO.class));
		}
		return districtDTOs;
	}

}
