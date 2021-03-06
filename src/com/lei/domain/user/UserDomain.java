package com.lei.domain.user;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lei.domain.roles.RolesDomain;
import com.lei.domain.wallet.IndividualWalletDomain;
import com.mysql.jdbc.Blob;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
@Entity
@Table(name = "user_info")
public class UserDomain {

	@Getter
	@Setter
	@Id
	@Column(name="USER_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	/*generator="SEQ_USER_ID")
	@SequenceGenerator(name="SEQ_USER_ID", sequenceName="SEQ_USER_ID")*/
	private long id;
	
	@Getter
	@Setter
	@Column(name="EMAIL_ID")
	private String email;
	
	@Getter
	@Setter
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Getter
	@Setter
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Getter
	@Setter
	@Column(name="COMPANY_NAME")
	private String companyName;
	
	@Getter
	@Setter
	@Column(name="JOB_TITLE")
	private String jobTitle;
	
	@Getter
	@Setter
	@Column(name="MOBILE_NO")
	private String mobile;

	@Getter
	@Setter
	@Column(name="DISTT")
	private String distt;
	
	@Getter
	@Setter
	@Column(name="BLOCK")
	private String block;
	
	@Getter
	@Setter
	@Column(name="VILLAGE")
	private String village;
	
	@Getter
	@Setter
	@Column(name="ADDRESS")
	private String address;
	
	@Getter
	@Setter
	@Column(name="EDISTTID")
	private String edisttId;
	
	@Getter
	@Setter
	@Column(name="AREA")
	private String area;
	
	@Getter
	@Setter
	@Column(name="POST")
	private String post;
	
	@Getter
	@Setter
	@Column(name="WARDNO")
	private String wardNo;
	
	@Getter
	@Setter
	@Column(name="STREET")
	private String street;
	
	@Getter
	@Setter
	@Column(name="TEHSIL")
	private String tehsil;
	
	@Getter
	@Setter
	@Column(name="NEWUSER")
	private String newUser;
	
	@Getter
	@Setter
	@Column(name="Date")
	private Date date;
	
	
	@Getter
	@Setter
	@Column(name = "OwnerType")
	private String ownertype;
	
	@Getter
	@Setter
	@Column(name = "PanNo")
	private String panno;
	
	@Getter
	@Setter
	@Column(name = "GSTN")
	private String gstn;

		
	@Getter
	@Setter
	@OneToOne(mappedBy="user")
    @Cascade(value=org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	private UserStatusDomain status;
	
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", 
             joinColumns = { @JoinColumn(name = "USER_ID") }, 
             inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	@Getter
	@Setter
	//private List<RolesDomain> userRoles = new ArrayList<RolesDomain>();
	private RolesDomain userRole ;

}
