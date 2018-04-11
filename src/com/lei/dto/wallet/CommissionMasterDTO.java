package com.lei.dto.wallet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;


@XmlRootElement(name = "Commissiondivision")
@JsonRootName(value = "Commissiondivision")
@XmlAccessorType(XmlAccessType.NONE)
public class CommissionMasterDTO {
	@Getter
	@Setter
	@XmlElement(name = "id")
	@JsonProperty(value = "id")
    private long id;
	
	@Getter
	@Setter
	@XmlElement(name = "name")
	@JsonProperty(value = "name")
	private String name;
	
	@Getter
	@Setter
	@XmlElement(name = "address")
	@JsonProperty(value = "address")
	private String address;
	
	@Getter
	@Setter
	@XmlElement(name = "bankName")
	@JsonProperty(value = "bankName")
	private String bankName;
	
	@Getter
	@Setter
	@XmlElement(name = "ifsc")
	@JsonProperty(value = "ifsc")
	private String ifsc;
	
	@Getter
	@Setter
	@XmlElement(name = "accountNo")
	@JsonProperty(value = "accountNo")
	private long accountNo;
	
	@Getter
	@Setter
	@XmlElement(name = "accountType")
	@JsonProperty(value = "accountType")
	private String accountType;

}
