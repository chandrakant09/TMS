package com.lei.dto.report;

import java.sql.Timestamp;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReportDTO {
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
	
	@Getter
	@Setter
	@XmlElement(name = "Email")
	@JsonProperty(value = "Email")
	private String email;
	
	@Getter
	@Setter
	@XmlElement(name = "FirstName")
	@JsonProperty(value = "FirstName")
	private String firstName;
	
	@Getter
	@Setter
	@XmlElement(name = "LastName")
	@JsonProperty(value = "LastName")
	private String lastName;
	
	@Getter
	@Setter
	@XmlElement(name = "CompanyName")
	@JsonProperty(value = "CompanyName")
	private String companyName;
	
	@Getter
	@Setter
	@XmlElement(name = "JobTitle")
	@JsonProperty(value = "JobTitle")
	private String jobTitle;
	
	@Getter
	@Setter
	@XmlElement(name = "Mobile")
	@JsonProperty(value = "Mobile")
	private String mobile;
	
	@Getter
	@Setter
	@XmlElement(name = "Distt")
	@JsonProperty(value = "Distt")
	private String distt;
	
	@Getter
	@Setter
	@XmlElement(name = "Block")
	@JsonProperty(value = "Block")
	private String block;
	
	@Getter
	@Setter
	@XmlElement(name = "Village")
	@JsonProperty(value = "Village")
	private String village;
	
	@Getter
	@Setter
	@XmlElement(name = "Address")
	@JsonProperty(value = "Address")
	private String address;
	
	@Getter
	@Setter
	@XmlElement(name = "EdisttId")
	@JsonProperty(value = "EdisttId")
	private String edisttId;
	
	@Getter
	@Setter
	@XmlElement(name = "RechargeId")
	@JsonProperty(value = "RechargeId")
	private long rechargeId;
	
	
	
	@Getter
	@Setter
	@XmlElement(name = "RechargeTime")
	@JsonProperty(value = "RechargeTime")
	private Timestamp rechargeTime;
	
	
	
	@Getter
	@Setter
	@XmlElement(name = "LastBalance")
	@JsonProperty(value = "LastBalance")
	private double lastBalance;
	
	@Getter
	@Setter
	@XmlElement(name = "ChequeId")
	@JsonProperty(value = "ChequeId")
	private long chequeId;
	
	@Getter
	@Setter
	@XmlElement(name = "ChequeNo")
	@JsonProperty(value = "ChequeNo")
	private long chequeNo;
	
	@Getter
	@Setter
	@XmlElement(name = "ChequeDate")
	@JsonProperty(value = "ChequeDate")
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
	private Date dateOfDepositeCheque;
	
	@Getter
	@Setter
	@XmlElement(name = "FileId")
	@JsonProperty(value = "FileId")
	private int fileId;
	
		
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

}
