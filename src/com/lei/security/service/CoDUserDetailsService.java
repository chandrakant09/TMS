package com.lei.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lei.domain.roles.RolesDomain;
import com.lei.domain.user.UserDomain;
import com.lei.dto.user.UserDTO;
import com.lei.exception.common.ObjectNotSupportedException;
import com.lei.maintenance.user.IUserMaintenance;
import com.lei.utility.Loggable;

/**
 * Custom core interface which loads user-specific data. Used by Spring Security
 * Framework for User Authentication.
 * 
 * @author Vinay.Kumar1
 * 
 */
@Service("codUserDetailsService")
public class CoDUserDetailsService implements UserDetailsService {

	private static final String ROLE_ = "ROLE_";
	
	
	
	@Autowired
	private IUserMaintenance userService;

	
	/**
	 * IMplementged method that loads User details by sign-in ID
	 */
	@Transactional(readOnly = true)
	@Loggable
	public UserDetails loadUserByUsername(String ssoId) throws UsernameNotFoundException {
		//UserDomain user = userService.findBySignInId(ssoId);
		UserDTO user = null;
		
		String userSsoId="";
		String password="";
		try {
			user = userService.findBySignInId(ssoId);
			if (user == null) {
				throw new UsernameNotFoundException("Username not found");
			}
			userSsoId = user.getEmail();
			password = user.getStatus().getPassword();
		} catch (ObjectNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new org.springframework.security.core.userdetails.User(userSsoId, password, true, true, true, true, getGrantedAuthorities(user));
	}

	
	/**
	 * Method loads all granted Authorities (CUST, MGR, DATADMIN etc..) from database
	 * @param user
	 * @return
	 */
	private List<GrantedAuthority> getGrantedAuthorities(UserDTO user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		//authorities.add(new SimpleGrantedAuthority(ROLE_ + user.getUserRole().getType()));
		
		
		// default role added ( to be removed further)
		authorities.add(new SimpleGrantedAuthority(ROLE_ +"MGR"));
		return authorities;
	}

}
