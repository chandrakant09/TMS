package com.lei.domain.user;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
@Entity
@Table(name = "TOKENDETAIL")
public class AuthenticationDomain {

	@Getter
	@Setter
	@Id
	@Column(name="TOKEN")
	private String token;
	
	@Getter
	@Setter
	@Column(name="LOGINID")
	private String loginId;
	
	@Getter
	@Setter
	@Column(name="IPADDRESS")
	private String ipAddress;
	
	@Getter
	@Setter
	@Column(name="ISSUEDATE")
	private Timestamp issueDate;
	
	@Getter
	@Setter
	@Column(name="EXPIRYDATE")
	private Timestamp expiryDate;
	
	@Getter
	@Setter
	@Column(name="EXPIRYDETAIL")
	private String expiryDetail;
	
	@Getter
	@Setter
	@Column(name="INVALIDATED")
	private String invalidated;
	
	@Getter
	@Setter
	@Column(name="INVALIDATETIME")
	private Timestamp invalidateTime;
	
	@Getter
	@Setter
	@Column(name="INVALIDATEDETAIL")
	private String invalidateDetail;
}
