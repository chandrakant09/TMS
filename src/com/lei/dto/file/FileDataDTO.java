package com.lei.dto.file;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
@XmlRootElement(name = "FileData")
@JsonRootName(value = "FileData")
@XmlAccessorType(XmlAccessType.NONE)
public class FileDataDTO {
	@Getter
	@Setter
	@XmlElement(name = "Id")
	@JsonProperty(value = "Id")
	private String id;
	
	@Getter
	@Setter
	@XmlElement(name = "LegalName")
	@JsonProperty(value = "LegalName")
	private String legalName;
	
	@Getter
	@Setter
	@XmlElement(name = "Country")
	@JsonProperty(value = "Country")
	private String country;
	
	@Getter
	@Setter
	@XmlElement(name = "ISIN")
	@JsonProperty(value = "ISIN")
	private String isin;
	
	@Getter
	@Setter
	@XmlElement(name = "SEDOL")
	@JsonProperty(value = "SEDOL")
	private String sedol;
	
	@Getter
	@Setter
	@XmlElement(name = "CUSIP")
	@JsonProperty(value = "CUSIP")
	private String cusip;

}
