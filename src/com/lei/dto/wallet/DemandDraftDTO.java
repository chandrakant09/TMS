package com.lei.dto.wallet;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DemandDraftDTO {
	
	@Getter
	@Setter
	@XmlElement(name = "DraftId")
	@JsonProperty(value = "DraftId")
	private long draftId;
	
	@Getter
	@Setter
	@XmlElement(name = "UserId")
	@JsonProperty(value = "UserId")
	private long userId;
	
	@Getter
	@Setter
	@XmlElement(name = "TransactionId")
	@JsonProperty(value = "TransactionId")
	private long transactionId;
	
	@Getter
	@Setter
	@XmlElement(name = "RechargeBy")
	@JsonProperty(value = "RechargeBy")
	private long rechargeBy;
	
	@Getter
	@Setter
	@XmlElement(name = "Amount")
	@JsonProperty(value = "Amount")
	private double amount;
	
	@Getter
	@Setter
	@XmlElement(name = "Comment")
	@JsonProperty(value = "Comment")
	private String comment;
	
	@Getter
	@Setter
	@XmlElement(name = "DateOfDepositeDD")
	@JsonProperty(value = "DateOfDepositeDD")
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss")
	private Date dateOfDepositeDD;

	@Getter
	@Setter
	@XmlElement(name = "Feveroff")
	@JsonProperty(value = "Feveroff")
	private String feveroff;
	
	@Getter
	@Setter
	@XmlElement(name = "Ddnumber")
	@JsonProperty(value = "Ddnumber")
	private long ddNumber;

	
	@Getter
	@Setter
	@XmlElement(name = "DateCreated")
	@JsonProperty(value = "DateCreated")
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss")
	private Date dateCreated;
	
	@Getter
	@Setter
	@XmlElement(name = "Status")
	@JsonProperty(value = "Status")
	private String status;


	
}
