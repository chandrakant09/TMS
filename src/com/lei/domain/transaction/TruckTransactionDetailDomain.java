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
@Table (name="trucktransactiondetail")
public class TruckTransactionDetailDomain {
private Date updated = new  Date();
	
	@Getter
	@Setter
	@Id
	@Column(name="trucktransactionid")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long trucktransactionid;
	
	@Getter
	@Setter
	@Column(name="truckid")
	private long truckid;
	
	@Getter
	@Setter
	@Column(name="amount")
	private double amount;
	
	@Getter
	@Setter
	@Column(name="remark")
	private String remark;
	
	
	
	@Getter
	@Setter
	@Column(name="transactiontime")
	private Date transactionTime;
	
	@PreUpdate
	public void setLastUpdate() {  
		this.transactionTime = updated; 
	}
	
	@Getter
	@Setter
	@Column(name="transactiontype")
	private String transactionType;
	
	@Getter
	@Setter
	@Column(name="currentbalence")
	private double currentbalence;
	
	@Getter
	@Setter
	@Column(name="status")
	private String status;

}
