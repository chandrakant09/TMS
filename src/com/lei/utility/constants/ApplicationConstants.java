package com.lei.utility.constants;

import java.io.File;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
public enum ApplicationConstants {
	CONFIGURATIONSBUNDLE(File.separator + "configuration.properties"),
	EXCEPTIONBUNDLE(File.separator + "exception.properties"),
	EMAILBUNDLE(File.separator + "emailTemplate.properties"),
	CAPTCHAVALIDATIONBUNDLE(File.separator + "captchavalidation.properties"),
	SEARCHPARAMETERBUNDLE(File.separator + "searchFieldsMapping.properties"),
	DAOABSTRACTIONBUNDLE(File.separator + "daoabstraction.properties"),
	WORKFLOWBUNDLE(File.separator + "workflow.properties"),
	CAPTCHAVARIABLE("captchaformvariable"),
	CAPTCHAPUBLICKEY("captchapublickey"),
	CAPTCHAPRIVATEKEY("captchaprivatekey"),
	CAPTCHAURL("captchavalidationURL"),
	SESSIONTOKEN("SESSIONTOKEN"),
	ENCRIPTIONENABLED("encriptionenabled"),
	ENCRIPTIONALGO("encriptionalgorithm"),
	ENCRIPTKEYLENGTH("encriptionkeylength"),
	SERVERKEY("securekey"),
	FILESTORAGEPATH("FILESTORAGEPATH"),
	
	SERIALIZED_DATE_FORMAT("dd-MMM-yyyy hh:mm:ss");
	
	private String value;
	
	private ApplicationConstants(String value) {
		this.value=value;
	}
	
	public String getValue() {
		return value;
	}
}
