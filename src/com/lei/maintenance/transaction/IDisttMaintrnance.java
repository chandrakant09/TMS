package com.lei.maintenance.transaction;

import java.util.List;

import com.lei.dto.master.ReportFilter;
import com.lei.dto.wallet.EdisttDTO;
import com.lei.dto.wallet.PaidServiceDTO;
import com.lei.exception.common.ObjectNotSupportedException;
import com.lei.exception.common.ProcessFailedException;

public interface IDisttMaintrnance {
	EdisttDTO edistrictRequestProcess(EdisttDTO edisttDTO) throws ObjectNotSupportedException, ProcessFailedException;
	
	EdisttDTO edistrictRequestSaved(EdisttDTO edisttDTO) throws ObjectNotSupportedException, ProcessFailedException;

	PaidServiceDTO getServiceDTO(String serviceId)throws ObjectNotSupportedException, ProcessFailedException;

	EdisttDTO isExist(EdisttDTO edisttDTO) throws ObjectNotSupportedException,	ProcessFailedException;

	
	List<EdisttDTO> getEdisttList(ReportFilter filter)	throws ObjectNotSupportedException;

	List<EdisttDTO> getApplicationtList(ReportFilter filter) throws ObjectNotSupportedException;

	List<EdisttDTO> getApplicationtList() throws ObjectNotSupportedException;

	
}
