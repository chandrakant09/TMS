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
@Table (name="edistt")
public class EdisttDomain {
	
	@Getter
	@Setter
	@Id
	@Column(name = "requestId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long requestId;
	
	@Getter
	@Setter
	@Column(name = "g2cId")
	private String g2cId;
	
	@Getter
	@Setter
	@Column(name = "vleid")
	private String vleId;
	
	@Getter
	@Setter
	@Column(name = "applicationNo")
	private String applicationNo;
	
	@Getter
	@Setter
	@Column(name = "secvicecode")
	private String servicecode;
	
	@Getter
	@Setter
	@Column(name = "uniqueid")
	private String uniqueID;
	
	@Getter
	@Setter
	@Column(name = "opt1")
	private String opt1;
	
	@Getter
	@Setter
	@Column(name = "opt2")
	private String opt2;
	
	@Getter
	@Setter
	@Column(name = "opt3")
	private String opt3;
	

	
	@Getter
	@Setter
	@Column(name = "trnid")
	private String trnid;
	
	@Getter
	@Setter
	@Column(name = "transstatus")
	private String transstatus;
	
	@Getter
	@Setter
	@Column(name = "res")
	private String res;
	
	@Getter
	@Setter
	@Column(name = "ropt1")
	private String ropt1;
	
	@Getter
	@Setter
	@Column(name = "ropt2")
	private String ropt2;
	
	@Getter
	@Setter
	@Column(name = "ropt3")
	private String ropt3;
	
	@Getter
	@Setter
	@Column(name = "status")
	private String status;
	
	@Getter
	@Setter
	@Column(name = "param1")
	private String param1;
	
	@Getter
	@Setter
	@Column(name = "param2")
	private String param2;
	
	@Getter
	@Setter
	@Column(name = "param3")
	private String param3;
	
	@Getter
	@Setter
	@Column(name = "remark")
	private String remark;
	
	

}
