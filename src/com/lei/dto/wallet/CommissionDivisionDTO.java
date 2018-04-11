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

@XmlRootElement(name = "Commissiondivision")
@JsonRootName(value = "Commissiondivision")
@XmlAccessorType(XmlAccessType.NONE)
public class CommissionDivisionDTO {
	@Getter
	@Setter
	@XmlElement(name = "id")
	@JsonProperty(value = "id")
	private long id;
	
	@Getter
	@Setter
	@XmlElement(name = "commisionPercentage")
	@JsonProperty(value = "commisionPercentage")
	private long commisionPercentage;
	
	@Getter
	@Setter
	@XmlElement(name = "status")
	@JsonProperty(value = "status")
	private String status;
	
	@Getter
	@Setter
	@XmlElement(name = "createDate")
	@JsonProperty(value = "createDate")
	private Date createDate;
	

}
