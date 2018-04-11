package com.lei.dto.wallet;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CashDTO {
	
	@Getter
	@Setter
	@XmlElement(name = "CashId")
	@JsonProperty(value = "CashId")
	private long cashId;
	
	@Getter
	@Setter
	@XmlElement(name = "UserId")
	@JsonProperty(value = "UserId")
	private long userId;
	
	@Getter
	@Setter
	@XmlElement(name = "RechargeBy")
	@JsonProperty(value = "RechargeBy")
	private long rechargeBy;
	
	@Getter
	@Setter
	@XmlElement(name = "TransactionId")
	@JsonProperty(value = "TransactionId")
	private long transactionId;
	
	@Getter
	@Setter
	@XmlElement(name = "Amount")
	@JsonProperty(value = "Amount")
	private double amount;
	
	@Getter
	@Setter
	@XmlElement(name = "Status")
	@JsonProperty(value = "Status")
	private String status;
	
	@Getter
	@Setter
	@XmlElement(name = "DateOfDepositeCash")
	@JsonProperty(value = "DateOfDepositeCash")
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss")
	private Date dateOfDepositeCash;
	
	
	@Getter
	@Setter
	@XmlElement(name = "Remark")
	@JsonProperty(value = "Remark")
	private String remark;
	
	

}
