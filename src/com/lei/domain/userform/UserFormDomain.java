package com.lei.domain.userform;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

public class UserFormDomain {
	@Getter
	@Setter
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	/*generator="SEQ_USER_ID")
	@SequenceGenerator(name="SEQ_USER_ID", sequenceName="SEQ_USER_ID")*/
	private long id;
	
	@Getter
	@Setter
	@Column(name="name")
	private String name;

}
