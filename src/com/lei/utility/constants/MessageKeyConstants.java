package com.lei.utility.constants;


/**
 * 
 * @author Shrikant Kushwaha
 *
 */
public enum MessageKeyConstants {
	NEWREGISTRATIONBODY("register.email.password"),
	LOGINFORGETPASSWORDBODY("login.email.forgetpassword"),
	PROJECTREPORTSUCCESS("email.project.report"),
	USERCHANGEPASSWORD("email.user.changepassword"),
	RECHARGESUCCESS("recharge.success"),
	RECHARGEFailed("recharge.failed"),
	INSUFFICIENTBALANCE("insufficient.balance"),
	INCOMPLETETRANSACTION("incomplete.trans"),
	WALLETDEDUCTSUCCESS("wallet.deduct.success"),
	COLLECTIONMAILED("collection.informed")
	;
	
	private String key;
	private MessageKeyConstants(String key) {
		this.key=key;
	}
	public String getKey() {
		return key;
	}
}
