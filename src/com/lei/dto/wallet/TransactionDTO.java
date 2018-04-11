package com.lei.dto.wallet;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;


@XmlRootElement(name = "Transaction")
@JsonRootName(value = "Transaction")
@XmlAccessorType(XmlAccessType.NONE)
public class TransactionDTO {
	
	@Getter
	@Setter
	@XmlElement(name = "TransactionId")
	@JsonProperty(value = "TransactionId")
	private long transactionId;
	
	@Getter
	@Setter
	@XmlElement(name = "UserId")
	@JsonProperty(value = "UserId")
	private long userId;
	
	@Getter
	@Setter
	@XmlElement(name = "Amount")
	@JsonProperty(value = "Amount")
	private double amount;
	
	@Getter
	@Setter
	@XmlElement(name = "Remark")
	@JsonProperty(value = "Remark")
	private String remark;
	
	@Getter
	@Setter
	@XmlElement(name = "TransactionTime")
	@JsonProperty(value = "TransactionTime")
	private Date transactionTime;
	
	@Getter
	@Setter
	@XmlElement(name = "TransactionType")
	@JsonProperty(value = "TransactionType")
	private String transactionType;
	
	@Getter
	@Setter
	@XmlElement(name = "CurrentBalance")
	@JsonProperty(value = "CurrentBalance")
	private double currentBalance;
	
	@Getter
	@Setter
	@XmlElement(name = "Status")
	@JsonProperty(value = "Status")
	private String status;
	
	@Getter
	@Setter
	@XmlElement(name = "Updated")
	@JsonProperty(value = "Updated")
	private Date updated;

}
