package com.lei.utility.constants;

public enum TransactionStatus {
	Success(1,"SUCCESS"),
	Progress(2,"PROGRESS"),
	Stuck(3,"STUCK"),
	Failed(3,"FAILED");
	
	private Integer id;
	private String status;
	private TransactionStatus(Integer statusId,String status) {
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
