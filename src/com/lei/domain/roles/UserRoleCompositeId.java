package com.lei.domain.roles;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;


@Embeddable
public class UserRoleCompositeId {

	
	@Column(name="USER_ID")
	@Getter
	@Setter
    private String userId;

	
    @Column(name="ROLE_ID")
    @Getter
	@Setter
    private String roleId;
}
