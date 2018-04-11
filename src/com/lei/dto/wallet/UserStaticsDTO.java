package com.lei.dto.wallet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@XmlRootElement(name = "UserStatics")
@JsonRootName(value = "UserStatics")
@XmlAccessorType(XmlAccessType.NONE)

public class UserStaticsDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	@XmlElement(name = "NewRural")
	@JsonProperty(value ="NewRural")
	private int newRural;
	
	@Getter
	@Setter
	@XmlElement(name = "NewUrban")
	@JsonProperty(value = "NewUrban")
	private int newUrban;
	
	@Getter
	@Setter
	@XmlElement(name = "OldRural")
	@JsonProperty(value ="OldRural")
	private int oldRural;
	
	@Getter
	@Setter
	@XmlElement(name = "OldUrban")
	@JsonProperty(value = "OldUrban")
	private int oldUrban;
	
	@Getter
	@Setter
	@XmlElement(name = "Distt")
	@JsonProperty(value = "Distt")
	private String distt;
	
	@Getter
	@Setter
	@XmlElement(name = "ActiveUser")
	@JsonProperty(value = "ActiveUser")
	private long activeUser;
	
	
}
