package com.lei.domain.transaction;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table (name="transactionDetail")
public class TransactionDetailDomain {
	
	private Date updated = new  Date();
	
	@Getter
	@Setter
	@Id
	@Column(name="TRANSACTION_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long transactionId;
	
	@Getter
	@Setter
	@Column(name="USER_ID")
	private long userId;
	
	@Getter
	@Setter
	@Column(name="AMOUNT")
	private double amount;
	
	@Getter
	@Setter
	@Column(name="REMARK")
	private String remark;
	
	
	
	@Getter
	@Setter
	@Column(name="TRANSACTION_TIME")
	private Date transactionTime;
	
	@PreUpdate
	public void setLastUpdate() {  
		this.transactionTime = updated; 
	}
	
	@Getter
	@Setter
	@Column(name="TRANSACTION_TYPE")
	private String transactionType;
	
	@Getter
	@Setter
	@Column(name="CURRENT_BALANCE")
	private double currentBalance;
	
	@Getter
	@Setter
	@Column(name="STATUS")
	private String status;
	
	

}
