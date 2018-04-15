package com.lei.domain.wallet;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name="truckrechargewallet")
public class TruckRechargeWalletDomain {
	
	@Getter
	@Setter
	private Date updated = new  Date();
	/*@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")*/
	@PreUpdate
	public void setLastUpdate() {  
		this.rechargetime = updated; 
	}
	
	@Getter
	@Setter
	@Id
	@Column(name = "rechargeid")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long rechargeid;
	
	@Getter
	@Setter
	@Column(name = "truckid")
	private long truckid;
	
	@Getter
	@Setter
	@Column(name = "amount")
	private double amount;
	
	
	@Getter
	@Setter
	@Column(name = "status")
	private String status;
	
	@Getter
	@Setter
	@Column(name = "rechargetime")
	private Date rechargetime;
	
	@Getter
	@Setter
	@Column(name = "trucktransactionid")
	private long trucktransactionid;
	
	@Getter
	@Setter
	@Column(name = "lastbalence")
	private double lastbalence;
	
}
