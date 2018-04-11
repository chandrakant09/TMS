package com.lei.dto.wallet;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.lei.serializers.XmlDateAdapter;
@XmlRootElement(name = "Recharge")
@JsonRootName(value = "Recharge")
@XmlAccessorType(XmlAccessType.NONE)
public class RechargeDTO {
	@Getter
	@Setter
	@XmlElement(name = "RechargeId")
	@JsonProperty(value = "RechargeId")
	private long rechargeId;
	
	@Getter
	@Setter
	@XmlElement(name = "UserId")
	@JsonProperty(value = "UserId")
	private long userId;
	
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
	private Timestamp rechargeTime;
	
	@Getter
	@Setter
	@XmlElement(name = "TransactionId")
	@JsonProperty(value = "TransactionId")
	private long transactionId;
	
	@Getter
	@Setter
	@XmlElement(name = "LastBalance")
	@JsonProperty(value = "LastBalance")
	private double lastBalance;
	
	@Getter
	@Setter
	@XmlElement(name = "Updated")
	@JsonProperty(value = "Updated")
	@XmlJavaTypeAdapter(XmlDateAdapter.class)
	/*@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")*/
	private Date updated;
	
	
	
}
