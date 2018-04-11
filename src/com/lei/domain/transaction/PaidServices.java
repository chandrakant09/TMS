package com.lei.domain.transaction;

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
@Table (name="paidservices")
public class PaidServices {
	@Getter
	@Setter
	@Id
	@Column(name = "serviceid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String serviceId;
	
	@Getter
	@Setter
	@Column(name = "charges")
	private double charges;
	
	@Getter
	@Setter
	@Column(name = "servicename")
	private String serviceName;
	
	@Getter
	@Setter
	@Column(name = "createddate")
	private Date createdDate;
	
	@Getter
	@Setter
	@Column(name = "status")
	private String status;
	

}
