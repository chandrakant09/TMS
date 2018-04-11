package com.lei.dto.user;

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
@XmlRootElement(name = "User")
@JsonRootName(value = "User")
@XmlAccessorType(XmlAccessType.NONE)
public class UserDTO {
	
	@Getter
	@Setter
	private long id;
	
	@Getter
	@Setter
	@XmlElement(name = "Email")
	@JsonProperty(value = "Email")
	private String email;
	
	@Getter
	@Setter
	@XmlElement(name = "FirstName")
	@JsonProperty(value = "FirstName")
	private String firstName;
	
	@Getter
	@Setter
	@XmlElement(name = "LastName")
	@JsonProperty(value = "LastName")
	private String lastName;
	
	@Getter
	@Setter
	@XmlElement(name = "CompanyName")
	@JsonProperty(value = "CompanyName")
	private String companyName;
	
	@Getter
	@Setter
	@XmlElement(name = "JobTitle")
	@JsonProperty(value = "JobTitle")
	private String jobTitle;
	
	@Getter
	@Setter
	@XmlElement(name = "Mobile")
	@JsonProperty(value = "Mobile")
	private String mobile;
	
	@Getter
	@Setter
	@XmlElement(name = "Distt")
	@JsonProperty(value = "Distt")
	private String distt;
	
	@Getter
	@Setter
	@XmlElement(name = "Block")
	@JsonProperty(value = "Block")
	private String block;
	
	@Getter
	@Setter
	@XmlElement(name = "Village")
	@JsonProperty(value = "Village")
	private String village;
	
	@Getter
	@Setter
	@XmlElement(name = "Address")
	@JsonProperty(value = "Address")
	private String address;
	
	@Getter
	@Setter
	@XmlElement(name = "EdisttId")
	@JsonProperty(value = "EdisttId")
	private String edisttId;
	
	@Getter
	@Setter
	@XmlElement(name = "Area")
	@JsonProperty(value = "Area")
	private String area;
	
	@Getter
	@Setter
	@XmlElement(name = "Post")
	@JsonProperty(value = "Post")
	private String post;
	
	@Getter
	@Setter
	@XmlElement(name = "WardNo")
	@JsonProperty(value = "WardNo")
	private String wardNo;
	
	@Getter
	@Setter
	@XmlElement(name = "Street")
	@JsonProperty(value = "Street")
	private String street;
	
	@Getter
	@Setter
	@XmlElement(name = "Tehsil")
	@JsonProperty(value = "Tehsil")
	private String tehsil;
	
	@Getter
	@Setter
	@XmlElement(name = "Status")
	@JsonProperty(value = "Status")
	private UserStatusDto status;
	
	
	
	@Getter
	@Setter
	@XmlElement(name = "UserRole")
	@JsonProperty(value = "UserRole")
	private RolesDTO userRole ;
	
	@Getter
	@Setter
	@XmlElement(name = "NewUser")
	@JsonProperty(value = "NewUser")
	private String newUser ;
	
	
	@Getter
	@Setter
	@XmlElement(name = "Date")
	@JsonProperty(value = "Date")
	private String date ;
	
	@Getter
	@Setter
	@XmlElement(name = "OwnerType")
	@JsonProperty(value = "OwnerType")
	private String ownertype;
	
	@Getter
	@Setter
	@XmlElement(name = "PanNo")
	@JsonProperty(value = "PanNo")
	private String panno;
	
	@Getter
	@Setter
	@XmlElement(name = "GSTN")
	@JsonProperty(value = "GSTN")
	private String gstn;
	
	
	
	
	
}
