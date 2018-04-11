package com.lei.domain.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "truck")
public class TruckDomain {
	
	@Getter
	@Setter
	@Id
	@Column(name="truckid")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long truckid;
	
	
	@Getter
	@Setter
	@Column(name="truckno")
	private String truckno;
	
	@Getter
	@Setter
	@Column(name="modelno")
	private String modelno;
	
	@Getter
	@Setter
	@Column(name="fuelavg")
	private long fuelavg;
	
	@Getter
	@Setter
	@Column(name="insuranceno")
	private String insuranceno;
	
	@Getter
	@Setter
	@Column(name="purchasedate")
	private Date purchasedate = new Date();
	
	
	@Getter
	@Setter
	@Column(name="drivername")
	private String drivername;
	
	@Getter
	@Setter
	@Column(name="drivermob")
	private String drivermob;
	
	@Getter
	@Setter
	@Column(name="driverid")
	private String driverid;
	
	@Getter
	@Setter
	@Column(name="driverylicenseno")
	private String driverylicenseno;
	
	@Getter
	@Setter
	@Column(name="maxtransportcapacity")
	private long maxtransportcapacity;
	
	
	
	/*
	@Getter
	@Setter
	@Column(name="sand")
	private String sand;
	
	@Getter
	@Setter
	@Column(name="bricks")
	private String bricks;
	
	@Getter
	@Setter
	@Column(name="slit")
	private String slit;
	
	@Getter
	@Setter
	@Column(name="other")
	private String other;
	
	
	
	*/
	
	@Getter
	@Setter
	@Column(name="creationdate")
	private Date creationdate = new Date();
	
	@Getter
	@Setter
	@Column(name="userid")
	private long userid;
	
	@Getter
	@Setter  
	@Column(name="trucktokenno")
	private long trucktokenno;
}
