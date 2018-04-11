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
@Table(name="DEMANDDRAFT_DEPOSIT")
public class DemandDraftDomain {

	@Getter
	@Setter
	@Id
	@Column(name = "DRAFT_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long draftId;
	
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
	@Column(name = "COMMENT")
	private String comment;
	
	@Getter
	@Setter
	@Column(name = "DATE_OF_DEPOSITE_DD")
	private Date dateOfDepositeDD;
	
	@Getter
	@Setter
	@Column(name = "FEVER_OFF")
	private String feveroff;
	
	@Getter
	@Setter
	@Column(name = "DD_NUMBER")
	private long ddNumber;

	
	@Getter
	@Setter
	@Column(name = "DATE_CREATED")
	private Date dateCreated;
	
	@Getter
	@Setter
	@Column(name="transactionid")
	private long transactionId;
	
	@Getter
	@Setter
	@Column(name="status")
	private String status;

}
