package com.lei.dto.wallet;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@XmlRootElement(name = "Cheque")
@JsonRootName(value = "Cheque")
@XmlAccessorType(XmlAccessType.NONE)
public class ChequeDTO {
	@Getter
	@Setter
	@XmlElement(name = "ChequeId")
	@JsonProperty(value = "ChequeId")
	private long chequeId;
	
	@Getter
	@Setter
	@XmlElement(name = "TransactionId")
	@JsonProperty(value = "TransactionId")
	private long transactionId;
	
	@Getter
	@Setter
	@XmlElement(name = "ChequeNo")
	@JsonProperty(value = "ChequeNo")
	private long chequeNo;
	
	@Getter
	@Setter
	@XmlElement(name = "ChequeDate")
	@JsonProperty(value = "ChequeDate")
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss")
	private Date chequeDate;
	
	@Getter
	@Setter
	@XmlElement(name = "BankName")
	@JsonProperty(value = "BankName")
	private String bankName;
	
	@Getter
	@Setter
	@XmlElement(name = "ifsc")
	@JsonProperty(value = "ifsc")
	private String ifsc;
	
	@Getter
	@Setter
	@XmlElement(name = "Location")
	@JsonProperty(value = "Location")
	private String location;
	
	@Getter
	@Setter
	@XmlElement(name = "DateOfDepositeCheque")
	@JsonProperty(value = "DateOfDepositeCheque")
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss")
	private Date dateOfDepositeCheque;
	
	@Getter
	@Setter
	@XmlElement(name = "FileId")
	@JsonProperty(value = "FileId")
	private int fileId;
	
	@Getter
	@Setter
	@XmlElement(name = "Amount")
	@JsonProperty(value = "Amount")
	private double amount;
	
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
	@XmlElement(name = "Feveroff")
	@JsonProperty(value = "Feveroff")
	private String feveroff;
	
	@Getter
	@Setter
	@XmlElement(name = "Status")
	@JsonProperty(value = "Status")
	private String status;
	
}
