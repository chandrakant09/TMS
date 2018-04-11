package com.lei.dto.userform;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@XmlRootElement(name = "UserForm")
@JsonRootName(value = "UserForm")
@XmlAccessorType(XmlAccessType.NONE)
public class UserFormDTO {
	
	@Getter
	@Setter
	private int id;
	
	@Getter
	@Setter
	@XmlElement(name = "Name")
	@JsonProperty(value = "Name")
	String name;
	
	

}
