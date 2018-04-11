package com.lei.dto.user;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@XmlRootElement(name = "UserReport")
@JsonRootName(value = "UserReport")
@XmlAccessorType(XmlAccessType.NONE)
public class UserReport {
	@Getter
	@Setter
	@XmlElement(name = "UserList")
	@JsonProperty(value = "UserList")
	private List<UserDTO> userList;
	
}
