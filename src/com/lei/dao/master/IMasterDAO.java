package com.lei.dao.master;

import java.util.List;

import com.lei.dto.master.DistrictDTO;
import com.lei.exception.common.ObjectNotSupportedException;

public interface IMasterDAO {

	List<DistrictDTO> getDistricList(String districtName)
			throws ObjectNotSupportedException;

}
