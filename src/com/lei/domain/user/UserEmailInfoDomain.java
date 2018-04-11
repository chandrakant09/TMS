package com.lei.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "user_email_info")
public class UserEmailInfoDomain {
	
	@Getter
	@Setter
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Getter
	@Setter
	@Column(name="EMAIL_ID")
	private String emailId;
	
	@Getter
	@Setter
	@Column(name="SUBJECT")
	private String subject;
	
	
	@Getter
	@Setter
	@Column(name="USERMESSAGE")
	private String usermessage;

}
