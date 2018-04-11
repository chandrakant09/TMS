package com.lei.utility.constants;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
public enum StatusConstants {
	ACTIVE(1,"ACTIVE"),
	PENDING(2,"PENDING"),
	ACTIVATIONPENDING(3,"ACTIVATIONPENDING"),
	SUSPENDED(4,"SUSPENDED"),
	DELETED(5,"DELETED"),
	INACTIVE(6,"INACTIVE"),
	NEW(7,"NEW"),
	FORGETPASSWORD(8,"FORGERPASSWORD"), 
	
	FAILED(9,"FAILED"), 
	PROCESSING(10,"PROCESSING"), 
	SCHEDULED(11,"SCHEDULED"),
	COMPLETED(12,"COMPLETED"),
	
	IDCOMPLETE(13,"IDCOMPLETE"),
	NORMALIZATIONCOMPLETED(14,"NORMALIZATIONCOMPLETED"),
	MATCHINGCOMPLETE(15,"MATCHINGCOMPLETE"),
	REPORTINGCOMPLETED(16,"REPORTINGCOMPLETED"),
	
	
	EXECUTIONREPORTCOMPLETE(17, "EXECUTIONREPORTCOMPLETE")
	;
	
	private Integer id;
	private String status;
	private StatusConstants(Integer statusId,String status) {
		this.status=status;
		this.id = statusId;
	}
	public String getStatus() {
		return status;
	}
	public Integer getID(){
		return id;
	}
}
