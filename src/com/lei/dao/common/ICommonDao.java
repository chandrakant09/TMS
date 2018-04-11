package com.lei.dao.common;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.lei.domain.user.StatusDomain;

public interface ICommonDao {

	
	public StatusDomain getStatus(int statusID);
}
