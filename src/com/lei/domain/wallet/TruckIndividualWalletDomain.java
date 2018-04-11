package com.lei.domain.wallet;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import lombok.Getter;
import lombok.Setter;

import com.lei.serializers.XmlDateAdapter;

@Entity
@Table(name = "truck_individual_wallet")
public class TruckIndividualWalletDomain {
	@Getter
	@Setter
	@Id
	@Column(name="walletid")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long walletid;
	
	@Getter
	@Setter
	@Column(name = "updated")
	@XmlJavaTypeAdapter(XmlDateAdapter.class)
	/*@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")*/
	private Date updated = new  Date();
	
	@Getter
	@Setter
	@Column(name = "balence")
	private double balance;

	@Getter
	@Setter
	@Column(name = "lastupdated")
	@XmlJavaTypeAdapter(XmlDateAdapter.class)
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss")
	private Date lastupdated;
	
	
	@Getter
	@Setter
	@Column(name = "truckid")
	private long truckid;
	

}
