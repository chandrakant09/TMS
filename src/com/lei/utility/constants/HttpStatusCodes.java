package com.lei.utility.constants;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
public enum HttpStatusCodes {
	SUCCESS("200"),
	AVAILABLE("3001"),
	REGISTERED("3002"),
	FORGETSUCCESS("3003"),
	LOGINSUCCESS("3004"),
	LOGOUTSUCCESS("3005"),
	UNAUTHENTICATEDREQUEST("3006"),
	UNAUTHERISEDREQUEST("3007"),
	CHANGEPASSWORDSUCCESS("3008"),
	
	PROJECTCREATIONSUCCESS("4001"),
	FILEPROCESSINGPENDING("4002"),
	FILEPROCESSINGINPROGRESS("4003"),
	FILEPROCESSINGCOMPLETED("4004"),
	
	EXECUTEANDSCHEDULESUCCESS("8001"),
	EXECUTIONPENDING ("8002"),
	EXECUTIONINPROGRESS("8003"),
	EXECUTIONINCOMPLETED("8004"),
	
	IDCOMPLETED("8005"),
	NORMALIZATIONCOMPLETED("8006"),
	MATCHINGCOMPLETED("8007"),
	REPORTINGCOMPLETED("8008"),
	
	
	REPORTSGETSUCCESS("9001"),
	PREVIEWREPORTSGETSUCCESS("9002"),
	SAVESUCCESSFULLY("1000")
	;


	private String code;
	private HttpStatusCodes(String code) {
		this.code=code;
	}
	public String getCode() {
		return code;
	}
}