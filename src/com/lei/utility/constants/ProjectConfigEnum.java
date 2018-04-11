package com.lei.utility.constants;

import lombok.Getter;

public enum ProjectConfigEnum {

		INCLUDEINRESULT("INCLUDEINRESULT" , "Include following results in output file"),
		SUPPORTSERVICES("SUPPORTSERVICES","Support services"),
		PREMIUMSERVICES("PREMIUMSERVICES", "Premium services");
	 
		
		private ProjectConfigEnum(String projectType,String projectTypeText ) {
			this.projectType = projectType;
			this.projectTypeText = projectTypeText;
		//	projectTypeAndAtrributeMap.put(ProjectConfigEnum.INCLUDEINRESULT, ProjectConfigEnum.ConfigProperties);
		}
		
	

		@Getter
	    private final String projectType;

		@Getter
	    private final String projectTypeText;
	    
	    
	    public enum ConfigProperties{
	         IN_PERFECT_MATCH_ONLY("IN_PERFECT_MATCH_ONLY", "Perfect-Match only", "MATG"),
	         IN_NO_MATCH("IN_NO_MATCH", "No-Match", "MATE"),
	         IN_POTEN_MATCH("IN_POTEN_MATCH", "Potential-Match", "MATP"),
	         IN_EXCEPTION_REPORT("IN_EXCEPTION_REPORT", "Exception Report", "INEXP"),
	         
	         
	         SS_ADDTOWORKFLOW("SS_ADDTOWORKFLOW", "Add potential, no match(es) and exception(s) to Credit-Dimensions workflow", "SSW"),
	         SS_RESOLVEANDMAIL("SS_RESOLVEANDMAIL", "I will resolve myself and send the output to my email Id", "SSR"),
	         
	         
	         PS_NAEMANDTRACK("PS_NAEMANDTRACK", "Monitor names and track changes", "PSN"),
	         PS_NOTIFYONCHANGE("PS_NOTIFYONCHANGE", "Notify when changes happen", "PSC"),
	         PS_SENDREPORT("PS_SENDREPORT", "Send consolidated report weekly", "PSR");
	         
	         
	         @Getter
	         private final String attribute;
	         
	         @Getter
	         private final String attributeText;
	         
	         @Getter
	         private final String attributeDbValue;
	         
	         
	         private ConfigProperties(String attribute, String attributeText, String attributeDbValue) {
	        	 this.attribute = attribute;
	        	 this.attributeText =  attributeText;
	        	 this.attributeDbValue = attributeDbValue;
				// TODO Auto-generated constructor stub
			}
	    }
}
