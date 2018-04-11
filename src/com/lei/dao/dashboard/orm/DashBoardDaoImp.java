package com.lei.dao.dashboard.orm;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

import com.lei.dao.base.orm.HibernatePersistenceManager;
import com.lei.dao.dashboard.IDashBoard;
import com.lei.dto.master.ReportFilter;
import com.lei.dto.user.UserDTO;

public class DashBoardDaoImp implements IDashBoard {
	
HibernatePersistenceManager  hibernatePersistenceManager=null;
	
	
	public DashBoardDaoImp() {
		hibernatePersistenceManager = new HibernatePersistenceManager();
	}
	
	
	
	
	public List<UserDTO> getDistrictSummaryReport(ReportFilter reportFilter){
	hibernatePersistenceManager.beginTransaction();
	StringBuffer queryStr=new StringBuffer("select user.area ,user.distt  ,user.newUser, count(*) count from TransactionDetailDomain trans ,UserDomain user  where user.id=trans.userId and trans.transactionType='Debit' and trans.status='SUCCESS' group by user.area,use.edisttId,user.newUser");
	List<UserDTO> reports=null;
	Query query = hibernatePersistenceManager.createQuery(queryStr.toString());
	List result = query.list();
	UserDTO userDTO=null;
	Iterator it = result.iterator();
	while(it.hasNext()){
		Object[] obj=(Object[]) it.next();
		userDTO=new UserDTO();
		userDTO.setArea((String)obj[0]);
		userDTO.setDistt((String)obj[1]);
		userDTO.setNewUser((String)obj[2]);
		userDTO.setId((Long)obj[3]);
		reports.add(userDTO);
	}
	return reports;
	}
	
	
	

}
