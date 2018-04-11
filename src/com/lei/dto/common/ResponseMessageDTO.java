package com.lei.dto.common;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.lei.dto.search.SearchDTO;
import com.lei.dto.user.UserDTO;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
@XmlRootElement(name = "Response")
@JsonRootName(value = "Response")
@JsonInclude(Include.NON_NULL)
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({UserDTO.class,SearchDTO.class,ArrayList.class})
public class ResponseMessageDTO {
	
	@Getter
	@Setter
	@XmlElement(name = "ResponseCode")
	@JsonProperty(value = "ResponseCode")
	private String responseCode;
	
	@Getter
	@Setter
	@XmlElement(name = "IsError")
	@JsonProperty(value = "IsError")
	private boolean isError;
	
	@Getter
	@Setter
	@XmlElement(name = "ResponseMessage")
	@JsonProperty(value = "ResponseMessage")
	private String responseMessage;
	
	@Getter
	@Setter
	@XmlElement(name = "ErrorDiscription")
	@JsonProperty(value = "ErrorDiscription")
	private String errorDiscription;
	
	@Getter
	@Setter
	@XmlElement(name = "Data")
	@JsonProperty(value = "Data")
	private Object data;
}
