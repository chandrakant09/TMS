package com.lei.utility.constants;

public enum PaidServices {
	Paidservice1("TXS0000001","Default Service");
	
	String id;
	String type;
	
	private PaidServices(String id,String type){
		this.id=id;
		this.type=type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

}
