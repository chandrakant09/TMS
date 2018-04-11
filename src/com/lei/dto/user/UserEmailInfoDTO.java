package com.lei.dto.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;


@XmlRootElement(name = "UserEmailInfo")
@JsonRootName(value = "UserEmailInfo")
@XmlAccessorType(XmlAccessType.NONE)
public class UserEmailInfoDTO {

	@Getter
	@Setter
	@XmlElement(name = "Id")
	@JsonProperty(value = "Id")
	private long id;
	
	@Getter
	@Setter
	@XmlElement(name = "Email")
	@JsonProperty(value = "Email")
	private String emailId;
	
	@Getter
	@Setter
	@XmlElement(name = "Subject")
	@JsonProperty(value = "Subject")
	private String subject;
	
	@Getter
	@Setter
	@XmlElement(name = "UserMessage")
	@JsonProperty(value = "UserMessage")
	private String usermessage;
	}
