package com.lei.dto.search;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
public class SearchFieldsMappingDTO {
	@Getter
	@Setter
	private String uiField;
	@Getter
	@Setter
	private boolean autoComplete;
	@Getter
	@Setter
	private String dbField;
	@Getter
	@Setter
	private String dbTable;
	@Getter
	@Setter
	private String additionalCondition;

	public SearchFieldsMappingDTO() {
	}
	public SearchFieldsMappingDTO(String uiField,boolean autoComplete,String dbField,String dbTable,String additionalCondition) {

	}

	public SearchFieldsMappingDTO(String uiField,String inFields) {
		String[] fields = inFields.split(",");
		this.uiField = uiField;
		autoComplete= Boolean.parseBoolean(fields[0]);
		dbField = fields[1];
		dbTable = fields[2];
		additionalCondition = fields[3];
	}

	public SearchFieldsMappingDTO(String uiField,String autoComplete,String dbField,String dbTable,String additionalCondition) {
		this.uiField = uiField;
		this.autoComplete= Boolean.parseBoolean(autoComplete);
		this.dbField = dbField;
		this.dbTable = dbTable;
		this.additionalCondition = additionalCondition;
	}
}
