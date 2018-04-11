package com.lei.dto.user;


import java.util.Date;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@XmlRootElement(name = "UserStatus")
@JsonRootName(value = "UserStatus")
@XmlAccessorType(XmlAccessType.NONE)
public class UserStatusDto {

	@Getter
	@Setter
	@XmlElement(name = "NewPassword")
	@JsonProperty(value = "NewPassword")
	private String newPassword;
	
	@Getter
	@Setter
	@XmlElement(name = "ConfirmNewPassword")
	@JsonProperty(value = "ConfirmNewPassword")
	private String confirmNewPassword;
	
	
	@Getter
	@Setter
	@XmlElement(name = "Password")
	@JsonProperty(value = "Password")
	private String password;
	
	@Getter
	@Setter
	@Column(name="PASSWORD_EXPIRY")
	private Date passwordExpiry;
	
	@Getter
	@Setter
	@Column(name="LAST_LOGIN")
	private Date lastLogin;
	
	
	@Getter
	@Setter
	@XmlElement(name = "StatusId")
	@JsonProperty(value = "StatusId")
	private int statusId;
	
}
