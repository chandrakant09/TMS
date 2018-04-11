package com.lei.dto.user;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@XmlRootElement(name = "Truck")
@JsonRootName(value = "Truck")
@XmlAccessorType(XmlAccessType.NONE)
public class TruckDTO {
	
	@Getter
	@Setter
	private long truckid;
	
	@Getter
	@Setter  
	@XmlElement(name = "truckno")
	@JsonProperty(value = "truckno")
	private String truckno;
	
	@Getter
	@Setter
	@XmlElement(name = "modelno")
	@JsonProperty(value = "modelno")
	private String modelno;
	
	@Getter
	@Setter
	@XmlElement(name = "fuelavg")
	@JsonProperty(value = "fuelavg")
	private Long fuelavg;
	
	@Getter
	@Setter
	@XmlElement(name = "insuranceno")
	@JsonProperty(value = "insuranceno")
	private String insuranceno;
	
	@Getter
	@Setter
	@XmlElement(name = "purchasedate")
	@JsonProperty(value = "purchasedate")
	private Date purchasedate = new Date();
	
	
	@Getter
	@Setter
	@XmlElement(name = "drivername")
	@JsonProperty(value = "drivername")
	private String drivername;
	
	@Getter
	@Setter
	@XmlElement(name = "drivermob")
	@JsonProperty(value = "drivermob")
	private String drivermob;
	
	@Getter
	@Setter
	@XmlElement(name = "driverid")
	@JsonProperty(value = "driverid")
	private String driverid;
	
	@Getter
	@Setter
	@XmlElement(name = "driverylicenseno")
	@JsonProperty(value = "driverylicenseno")
	private String driverylicenseno;
	
	@Getter
	@Setter
	@XmlElement(name = "maxtransportcapacity")
	@JsonProperty(value = "maxtransportcapacity")
	private long maxtransportcapacity;
	
	
	
	/*
	@Getter
	@Setter
	@XmlElement(name = "sand")
	@JsonProperty(value = "sand")
	private String sand;
	
	@Getter
	@Setter
	@XmlElement(name = "bricks")
	@JsonProperty(value = "bricks")
	private String bricks;
	
	@Getter
	@Setter
	@XmlElement(name = "slit")
	@JsonProperty(value = "slit")
	private String slit;
	
	@Getter
	@Setter
	@XmlElement(name = "other")
	@JsonProperty(value = "other")
	private String other;
	
	
	
	*/
	
	@Getter
	@Setter
	@XmlElement(name = "creationdate")
	@JsonProperty(value = "creationdate")
	private Date creationdate;
	
	@Getter
	@Setter
	@XmlElement(name = "userid")
	@JsonProperty(value = "userid")
	private long userid;
	

	@Getter
	@Setter  
	@XmlElement(name = "trucktokenno")
	@JsonProperty(value = "trucktokenno")
	private long trucktokenno;
	
	
	
}
