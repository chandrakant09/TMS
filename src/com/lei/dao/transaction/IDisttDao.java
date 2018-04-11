package com.lei.dao.transaction;

import java.util.List;

import com.lei.dto.master.ReportFilter;
import com.lei.dto.wallet.EdisttDTO;
import com.lei.exception.common.ObjectNotSupportedException;
import com.lei.exception.common.ProcessFailedException;

public interface IDisttDao {
	
	public EdisttDTO saveEdittDetail(EdisttDTO edisttDTO) throws ObjectNotSupportedException, ProcessFailedException ;
	
	public EdisttDTO updateEdittDetail(EdisttDTO edisttDTO) throws  ObjectNotSupportedException, ProcessFailedException;
	
	public List<EdisttDTO> getEdittDetails(String edisttId) throws  ObjectNotSupportedException;

	
	List<EdisttDTO> getEdisttList(ReportFilter reportFilter) throws ObjectNotSupportedException;

	List<EdisttDTO> getApplicationtList(ReportFilter reportFilter) throws ObjectNotSupportedException;
	

}
