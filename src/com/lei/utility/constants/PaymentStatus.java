package com.lei.utility.constants;

public enum PaymentStatus {
	Rejected("Rejected"),
	Pending_For_Approval("Pending For Approval"),
	Approved("Approved");
	

	private String status;
	private PaymentStatus(String status) {
		this.status=status;
	
	}
	public String getStatus() {
		return status;
	}
	

}
