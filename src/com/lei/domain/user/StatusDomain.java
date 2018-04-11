package com.lei.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
@Entity
@Table(name = "STATUS")
public class StatusDomain {
	/*STATUS_ID   NOT NULL NUMBER         
	STATUS               NVARCHAR2(20)  
	DESCRIPTION          NVARCHAR2(100) */
	@Getter
	@Setter
	@Id
	@Column(name="STATUS_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Getter
	@Setter
	@Column(name="STATUS")
	private String status;
	
	@Getter
	@Setter
	@Column(name="DESCRIPTION")
	private String description;
	
}
