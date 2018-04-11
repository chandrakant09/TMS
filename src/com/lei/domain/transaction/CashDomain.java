package com.lei.domain.transaction;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name="CashDetail")
public class CashDomain {

	@Getter
	@Setter
	@Id
	@Column(name = "CASH_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long cashId;
	
	@Getter
	@Setter
	@Column(name = "USER_ID")
	private long userId;
	
	@Getter
	@Setter
	@Column(name = "RECHARGE_BY")
	private long rechargeBy;
	
	@Getter
	@Setter
	@Column(name = "AMOUNT")
	private double amount;
	
	@Getter
	@Setter
	@Column(name = "STATUS")
	private String status;
	
	@Getter
	@Setter
	@Column(name = "DATE_OF_DEPOSITE_CASH")
	private Date dateOfDepositeCash;
	
	@Getter
	@Setter
	@Column(name = "REMARK")
	private String remark;
	
	@Getter
	@Setter
	@Column(name="transactionid")
	private long transactionId;
}
