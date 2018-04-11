package com.lei.domain.user;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.lei.domain.roles.RolesDomain;
import com.lei.domain.roles.UserRoleCompositeId;

@Entity
@Table(name="USER_ROLES")
public class UserRolesDomain {
	
	@EmbeddedId
	@Getter
	@Setter
	private UserRoleCompositeId userAndRole;
	
	@Getter
	@Setter
	@Column(name = "DATECREATED")
	private Date dateCreated;
	
	@Column(name = "USER_ID_CREATED")
	@Getter
	@Setter
	private Long userIdCreated;

	@Getter
	@Setter
	@OneToOne
    @PrimaryKeyJoinColumn 
    private UserDomain user;
	
	
	@Getter
	@Setter
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "userRole", 
             joinColumns = { @JoinColumn(name = "USER_ID") }, 
             inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	private RolesDomain roleDomain;
}
