package com.lei.dto.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@XmlRootElement(name = "Message")
@JsonRootName(value = "Message")
@XmlAccessorType(XmlAccessType.NONE)
public class MessageDTO {
	
	@Getter
	@Setter
	private long id;
	
	@Getter
	@Setter
	@XmlElement(name = "Message")
	@JsonProperty(value = "Message")
	private String message;

}
