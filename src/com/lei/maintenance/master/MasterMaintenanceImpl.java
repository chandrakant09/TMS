package com.lei.maintenance.master;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lei.dao.base.DaoManager;
import com.lei.dao.master.IMasterDAO;
import com.lei.dto.master.DistrictDTO;
import com.lei.exception.common.ObjectNotSupportedException;

@Service
public class MasterMaintenanceImpl implements IMasterMaintenance{

	@Override
	public List<DistrictDTO> getDistricList(String districtName) throws ObjectNotSupportedException {
		IMasterDAO dao = DaoManager.MASTERDAO.getDao(IMasterDAO.class);
		return dao.getDistricList(districtName);
	}
	

}
