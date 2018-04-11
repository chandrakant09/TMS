package com.lei.maintenance.master;

import java.util.List;

import com.lei.dto.master.DistrictDTO;
import com.lei.exception.common.ObjectNotSupportedException;

public interface IMasterMaintenance {
	
	List<DistrictDTO> getDistricList(String districtName)
			throws ObjectNotSupportedException;

}
