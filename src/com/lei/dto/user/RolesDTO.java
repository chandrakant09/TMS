package com.lei.dto.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Getter;
import lombok.Setter;

@XmlRootElement(name = "Roles")
@JsonRootName(value = "Roles")
@XmlAccessorType(XmlAccessType.NONE)
public class RolesDTO {
	
	
	@Getter
	@Setter
	@XmlElement(name = "RoleId")
	@JsonProperty(value = "RoleId")
	private int roleId;
	
	@Getter
	@Setter
	@XmlElement(name = "Type")
	@JsonProperty(value = "Type")
	private String type ;
}
