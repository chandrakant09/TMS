package com.lei.utility.constants;

public enum TransactionTypeEnum {
	CREDIT(1,"Credit"),
	DEBIT(2,"Debit");
	
	private int id ;
	private String value;
	private TransactionTypeEnum(int id,String value) {
		this.id=id;
		this.value=value;
	}
	public int getId() {
		return id;
	}
	
	public String getValue() {
		return value;
	}
	
	
	
	
}
