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
@Table(name="INDIVIDUAL_WALLET")
public class IndividualWalletDomain {
	
	@Getter
	@Setter
	private Date updated = new  Date();

			
	@Getter
	@Setter
	@Id
	@Column(name = "WALLET_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long walletId;
	
	

	@Getter
	@Setter
	@Column(name = "USER_ID")
	private long userId;

	
	
	@Getter
	@Setter
	@Column(name = "BALANCE")
	private double balance;

	
	
	@Getter
	@Setter
	@Column(name = "LAST_UPDATED")
	private Date lastupdated;

}
