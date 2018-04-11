package com.lei.domain.transaction;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transactionupdate")
public class TransactionUpdate {
	@Getter
	@Setter
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Getter
	@Setter
	@Column(name="userid")
	private long userId;
	
	@Getter
	@Setter
	@Column(name="transactionid")
	private long transactionId;
	
	@Getter
	@Setter
	@Column(name="status")
	private String status;
	
	@Getter
	@Setter
	@Column(name="updateddate")
	private Date updatedDate;

}
