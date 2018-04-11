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
@Table(name="RECHARGE_WALLET")
public class RechargeWalletDomain {
	
	@Getter
	@Setter
	private Date updated = new  Date();

	@PreUpdate
	public void setLastUpdate() {  
		this.rechargeTime = updated; 
	}
	
	
	@Getter
	@Setter
	@Id
	@Column(name = "RECHARGE_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long rechargeId;
	
	@Getter
	@Setter
	@Column(name = "USER_ID")
	private long userId;
	
	@Getter
	@Setter
	@Column(name = "AMOUNT")
	private double amount;
	
	@Getter
	@Setter
	@Column(name = "LAST_BALANCE")
	private double lastBalance;
	
	@Getter
	@Setter
	@Column(name = "TRANSACTION_ID")
	private long transactionId;
	
	@Getter
	@Setter
	@Column(name = "STATUS")
	private String status;
	
	@Getter
	@Setter
	@Column(name = "RECHARGE_TIME")
	private Date rechargeTime;
	
	/*@Getter
	@Setter
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "RECHARGE_WALLET", cascade = CascadeType.ALL)
	private TransactionDetailDomain transactionDetailDomain;*/
	
	
	 

}
