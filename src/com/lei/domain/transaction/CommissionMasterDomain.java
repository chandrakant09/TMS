package com.lei.domain.transaction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "commissionmaster")
public class CommissionMasterDomain {
	@Getter
	@Setter
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private long id;
	
	@Getter
	@Setter
	@Column(name="name")
	private String name;
	
	@Getter
	@Setter
	@Column(name="address")
	private String address;
	
	@Getter
	@Setter
	@Column(name="bankname")
	private String bankName;
	
	@Getter
	@Setter
	@Column(name="ifsc")
	private String ifsc;
	
	@Getter
	@Setter
	@Column(name="accountno")
	private long accountNo;
	
	@Getter
	@Setter
	@Column(name="accounttype")
	private String accountType;
	
	

}
