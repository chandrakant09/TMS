package com.lei.security.configuration;



/**
 * @author chandu
 *
 */
public enum UserRolesType {
	/*USER("USER"),
	DBA("DBA"),
	ADMIN("ADMIN");
	*/
	
	CUSTOMER("CUST"),
	MANAGER("MGR"),
	SYSADMIN("SYSADM"),
	DATAADMIN("DTADM"),
	DATAANALYST("DTANY"),
	QAANALYST("QAANY");
	
	String userProfileType;
	
	private UserRolesType(String userProfileType){
		this.userProfileType = userProfileType;
	}
	
	public String getUserProfileType(){
		return userProfileType;
	}
	
}
