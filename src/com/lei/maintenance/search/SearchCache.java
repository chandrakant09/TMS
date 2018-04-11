package com.lei.maintenance.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

import com.lei.dto.search.SearchFieldsMappingDTO;
import com.lei.utility.PropertyUtility;
import com.lei.utility.constants.ApplicationConstants;


/**
 * 
 * @author Shrikant Kushwaha
 *
 */
public class SearchCache {
	@Getter
	@Setter
	private static List<String> searchParameters = null;
	
	@Getter
	@Setter
	private static List<String> autoSearchParameters = null;
	
	
	static{
		searchParameters = new ArrayList<String>();
		autoSearchParameters = new ArrayList<String>();
		Set<Entry<Object, Object>> entryData = PropertyUtility.getEntrySet(ApplicationConstants.SEARCHPARAMETERBUNDLE.getValue());
		
		for(Entry<Object,Object> entry : entryData){
			String rawField = (String)entry.getValue();
			SearchFieldsMappingDTO field = new SearchFieldsMappingDTO((String)entry.getKey(),rawField);
			searchParameters.add((String)entry.getKey());
			if(field.isAutoComplete()){
				autoSearchParameters.add((String)entry.getKey());
			}
		}
	}
}
