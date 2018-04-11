package com.lei.dto.master;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@XmlRootElement(name = "ReportFilter")
@JsonRootName(value = "ReportFilter")
@XmlAccessorType(XmlAccessType.NONE)
public class ReportFilter {
	@Getter
	@Setter
	@XmlElement(name = "UserId")
	@JsonProperty(value = "UserId")
	private long userId;
	
	@Getter
	@Setter
	@XmlElement(name = "Status")
	@JsonProperty(value = "Status")
	private String status;
	
	@Getter
	@Setter
	@XmlElement(name = "District")
	@JsonProperty(value = "District")
	private String district;
	
	@Getter
	@Setter
	@XmlElement(name = "TransactionType")
	@JsonProperty(value = "TransactionType")
	private String transactionType;

	@Getter
	@Setter
	@XmlElement(name = "VleId")
	@JsonProperty(value = "VleId")
	private String vleId;
	
	@Getter
	@Setter
	@XmlElement(name = "G2cId")
	@JsonProperty(value = "G2cId")
	private String g2cId;
	
	@Getter
	@Setter
	@XmlElement(name = "StartDate")
	@JsonProperty(value = "StartDate")
	private String startDate;

	@Getter
	@Setter
	@XmlElement(name = "EndDate")
	@JsonProperty(value = "EndDate")
	private String endDate;
	

	@Getter
	@Setter
	@XmlElement(name = "Transstatus")
	@JsonProperty(value = "Transstatus")
	private String transstatus;

}
