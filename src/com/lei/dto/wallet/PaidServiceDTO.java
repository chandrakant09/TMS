package com.lei.dto.wallet;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class PaidServiceDTO {
	@Getter
	@Setter
	private String serviceId;
	
	@Getter
	@Setter
	private double charges;
	
	@Getter
	@Setter
	private String serviceName;
	
	@Getter
	@Setter
	private Date createdDate;
	
	@Getter
	@Setter
	private String status;
}
