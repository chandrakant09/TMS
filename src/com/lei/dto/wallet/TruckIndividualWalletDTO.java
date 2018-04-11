package com.lei.dto.wallet;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lei.serializers.XmlDateAdapter;

public class TruckIndividualWalletDTO {
	@Getter
	@Setter
	@XmlElement(name = "WalletId")
	@JsonProperty(value = "WalletId")
	private long walletid;
	
	@Getter
	@Setter
	@XmlElement(name = "truckid")
	@JsonProperty(value = "truckid")
	private long truckid;
	

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
