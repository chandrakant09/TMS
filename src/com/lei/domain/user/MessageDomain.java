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
@Table(name = "message")
public class MessageDomain {
	@Getter
	@Setter
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Getter
	@Setter
	@Column(name="MESSAGE")
	private String message;
	
	@Getter
	@Setter
	@Column(name="STATUS")
	private String status;
	
	@Getter
	@Setter
	@Column(name="REMARK")
	private String remark;

}
