package com.lei.service.search;


import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lei.dto.common.KeyValueDto;
import com.lei.dto.common.ResponseMessageDTO;
import com.lei.dto.search.SearchDTO;
import com.lei.maintenance.search.SearchCache;
import com.lei.utility.constants.HttpStatusCodes;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
@RestController
@RequestMapping("/sec/search")
public class SearchService {
	@RequestMapping(value = "/searchParameters",
			method = RequestMethod.GET,
			headers="Accept=application/xml, application/json")
	public ResponseMessageDTO emailExists() {
			ResponseMessageDTO response = new ResponseMessageDTO();
			response.setResponseCode(HttpStatusCodes.AVAILABLE.getCode());
			response.setResponseMessage("Parameters for search and autosearch fields. Configured by current user.");
			
			SearchDTO sDto = new SearchDTO();
			
			int i=1;
			
			List<KeyValueDto> data = new ArrayList<KeyValueDto>();
			data.add(new KeyValueDto(""+i++,""+i++));
			data.add(new KeyValueDto(""+i++,""+i++));
			data.add(new KeyValueDto(""+i++,""+i++));
			data.add(new KeyValueDto(""+i++,""+i++));
			data.add(new KeyValueDto(""+i++,""+i++));
			data.add(new KeyValueDto(""+i++,""+i++));
			sDto.setSearchParameters(SearchCache.getSearchParameters());
			
			data = new ArrayList<KeyValueDto>();
			data.add(new KeyValueDto(""+i++,""+i++));
			data.add(new KeyValueDto(""+i++,""+i++));
			data.add(new KeyValueDto(""+i++,""+i++));
			data.add(new KeyValueDto(""+i++,""+i++));
			data.add(new KeyValueDto(""+i++,""+i++));
			data.add(new KeyValueDto(""+i++,""+i++));
			sDto.setAutoSearchEnabled(SearchCache.getAutoSearchParameters());
			
			
			response.setData(sDto);
			
			return response;
	}
}
