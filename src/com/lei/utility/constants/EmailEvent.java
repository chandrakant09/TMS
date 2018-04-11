package com.lei.utility.constants;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
public enum EmailEvent {
	USERREGISTERED("USERREGISTERED"),
	USERFORGETPASSWORD("USERFORGETPASSWORD"),
	USERNOTIFICATION("USERNOTIFICATION"),
	PROJECTREPORT("PROJECTREPORT"),
	USERCHANGEPASSWORD("USERCHANGEPASSWORD"),
	RECHARGESUCCESS("RECHARGESUCCESS"),
	RECHARGEFAILED("RECHARGEFAILED"),
	INSUFFICIENTBALANCE("INSUFFICIENTBALANCE"),
	INCOMPLETETRANSACTION("INCOMPLETETRANSACTION"),
	COLLECTIONMAILED("COLLECTIONMAILED"),
	EDISTRICTSUCCESS("EDISTRICTSUCCESS");
	
	private String event;
	
	private EmailEvent(String event) {
		this.event=event;
	}
	
	public String getEvent() {
		return event;
	}
}
