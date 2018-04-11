package com.lei.domain.roles;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.lei.domain.user.UserDomain;
import com.lei.security.configuration.UserRolesType;

@Entity
@Table(name = "ROLES")
public class RolesDomain {
	/*
	 * @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private int id;
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLE_ID")
	@Getter
	@Setter
	private long roleId;

	@Column(name = "TYPE")
	@Getter
	@Setter
	private String type = UserRolesType.CUSTOMER.getUserProfileType();

	/*@Getter
	@Setter
	@OneToOne
    @PrimaryKeyJoinColumn 
    private UserRolesDomain userRole;*/
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", 
             joinColumns = { @JoinColumn(name = "ROLE_ID") }, 
             inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
	@Getter
	@Setter
	private List<UserDomain> users = new ArrayList<UserDomain>();
	//private RolesDomain userRole ;

	
}
