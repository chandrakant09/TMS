package com.lei.dto.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
@JsonIgnoreProperties
@XmlRootElement(name = "GoogleResponse")
@JsonRootName(value = "GoogleResponse")
@XmlAccessorType(XmlAccessType.NONE)
public class GoogleCaptchaResponse {
	@Getter
	@Setter
	@XmlElement(name = "success")
	@JsonProperty(value = "success")
	private boolean success;
	
	@Getter
	@Setter
	@XmlElement(name = "error-codes")
	@JsonProperty(value = "error-codes")
	private String[] errorCodes;
}
