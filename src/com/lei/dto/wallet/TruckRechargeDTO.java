package com.lei.dto.wallet;

import java.sql.Timestamp;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lei.serializers.XmlDateAdapter;

public class TruckRechargeDTO {
	@Getter
	@Setter
	@XmlElement(name = "RechargeId")
	@JsonProperty(value = "RechargeId")
	private long rechargeid;
	
	@Getter
	@Setter
	@XmlElement(name = "TruckId")
	@JsonProperty(value = "TruckId")
	private long truckid;
	
	@Getter
	@Setter
	@XmlElement(name = "Amount")
	@JsonProperty(value = "Amount")
	private double amount;
	
	
	@Getter
	@Setter
	@XmlElement(name = "Status")
	@JsonProperty(value = "Status")
	private String status;
	
	@Getter
	@Setter
	@XmlElement(name = "RechargeTime")
	@JsonProperty(value = "RechargeTime")
	private Timestamp rechargetime;
	
	@Getter
	@Setter
	@XmlElement(name = "TransactionId")
	@JsonProperty(value = "TransactionId")
	private long trucktransactionid;
	
	@Getter
	@Setter
	@XmlElement(name = "LastBalance")
	@JsonProperty(value = "LastBalance")
	private double lastbalence;
	
	@Getter
	@Setter
	@XmlElement(name = "Updated")
	@JsonProperty(value = "Updated")
	@XmlJavaTypeAdapter(XmlDateAdapter.class)
	/*@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")*/
	private Date updated;

}
