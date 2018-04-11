package com.lei.dto.wallet;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.lei.serializers.XmlDateAdapter;
@XmlRootElement(name = "Wallet")
@JsonRootName(value = "Wallet")
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso(Date.class)
public class WalletDTO {

	@Getter
	@Setter
	@XmlElement(name = "WalletId")
	@JsonProperty(value = "WalletId")
	private long walletId;
	
	@Getter
	@Setter
	@XmlElement(name = "UserId")
	@JsonProperty(value = "UserId")
	private long userId;
	

	@Getter
	@Setter
	@XmlElement(name = "Balance")
	@JsonProperty(value = "Balance")
	private double balance;

	@Getter
	@Setter
	@XmlElement(name = "Lastupdated")
	@JsonProperty(value = "Lastupdated")
	@XmlJavaTypeAdapter(XmlDateAdapter.class)
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss")
	private Date lastupdated;
	
	
	@Getter
	@Setter
	@XmlElement(name = "Updated")
	@JsonProperty(value = "Updated")
	@XmlJavaTypeAdapter(XmlDateAdapter.class)
	/*@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")*/
	private Date updated;
}
