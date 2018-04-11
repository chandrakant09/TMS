package com.lei.dao.user.orm;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lei.dao.base.orm.HibernatePersistenceManager;
import com.lei.dao.user.IUserDao;
import com.lei.domain.roles.RolesDomain;
import com.lei.domain.user.StatusDomain;
import com.lei.domain.user.TruckDomain;
import com.lei.domain.user.UserDomain;
import com.lei.domain.wallet.IndividualWalletDomain;
import com.lei.domain.wallet.TruckIndividualWalletDomain;
import com.lei.dto.master.ReportFilter;
import com.lei.dto.user.TruckDTO;
import com.lei.dto.user.UserDTO;
import com.lei.dto.wallet.WalletDTO;
import com.lei.exception.common.ObjectNotSupportedException;
import com.lei.exception.common.ProcessFailedException;
import com.lei.exception.user.RegistrationFailedException;
import com.lei.utility.CommonUtils;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
@Slf4j
@Repository("userDao")
public class UserDaoImpl implements IUserDao{

	HibernatePersistenceManager hibernatePersistenceManager =null;

	public UserDaoImpl() {
		hibernatePersistenceManager = new HibernatePersistenceManager();
	}


	@Override
	public boolean exists(String emailId) {

		hibernatePersistenceManager.beginTransaction();
		Criteria user = hibernatePersistenceManager.createCriteria(UserDomain.class);
		user.add(Restrictions.eq("email", emailId));
		List<UserDomain> result = user.list();
		if(result != null && result.size()>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean existsEDisttId(String edisttId) {

		hibernatePersistenceManager.beginTransaction();
		Criteria user = hibernatePersistenceManager.createCriteria(UserDomain.class);
		user.add(Restrictions.eq("edisttId", edisttId));
		List<UserDomain> result = user.list();
		if(result != null && result.size()>0){
			return true;
		}
		return false;
	}
	

	@Override
	public boolean updatePasswordAndStatus(UserDTO userDTO) throws ProcessFailedException, ObjectNotSupportedException {
		UserDomain userDomain = getUserDomain(userDTO.getEmail());
		userDomain.getStatus().setStatusId(userDTO.getStatus().getStatusId());
		userDomain.getStatus().setPassword(userDTO.getStatus().getPassword());
		return updateUserDomain(userDomain);

	}
	
	public Long getUserID(String emailId)  {
		UserDomain userDomain=getUserDomain(emailId);
		
		if(userDomain!=null){
			return userDomain.getId();
		}else{
			return -1L;
		}
	}
	
	@Override
	public UserDTO getUser(String emailId) throws ObjectNotSupportedException {
		UserDomain userDomain=getUserDomain(emailId);
		
		if(userDomain!=null){
			return CommonUtils.convertObject(userDomain, UserDTO.class);
		}else{
			return null;
		}
	}

	@Override
	public boolean login(UserDTO UserDTO) {
		return false;

	}

	@Override
	public boolean logout(UserDTO userDTO) throws ObjectNotSupportedException {
		
		CommonUtils.convertObject(userDTO, UserDomain.class);
		return false;

	}

	@Override
	public boolean register(UserDTO userDto) throws RegistrationFailedException, ObjectNotSupportedException {
		boolean result= false;
		UserDomain user = CommonUtils.convertObject(userDto, UserDomain.class);
        IndividualWalletDomain individualWalletDomain=new IndividualWalletDomain();
        
        
		user.getStatus().setUser(user);
		
		try {
			hibernatePersistenceManager.beginTransaction();
			Date date=new Date();
			RolesDomain userRole=getUserRole(10001);
			user.setUserRole(userRole);
			user.setDate(date);
			hibernatePersistenceManager.save(user);
			
			individualWalletDomain.setUserId(user.getId());
			hibernatePersistenceManager.save(individualWalletDomain);
			
			result = true;
			hibernatePersistenceManager.commit();
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
			hibernatePersistenceManager.rollback();
			throw new RegistrationFailedException("User Registration Failed.");
		}
		return result;

	}
	
	
	
	@Override
	public int getStatusId(String status) {
		StatusDomain userDomain=getStatus(status);
		if(userDomain!=null){
			return userDomain.getId();
		}
		return -1;
	}


	@Override
	public StatusDomain getStatus(String status) {
		StatusDomain userDomain=null;
		hibernatePersistenceManager.beginTransaction();
		Criteria user = hibernatePersistenceManager.createCriteria(StatusDomain.class);
		user.add(Restrictions.eq("name", status));
		List<StatusDomain> result = user.list();
		if(result != null && result.size()>0){
			userDomain = result.get(0);
		}
		return userDomain;
	}
	
	

		
	

	public UserDomain findById(long id) {
		hibernatePersistenceManager.beginTransaction();
		UserDomain user=(UserDomain)hibernatePersistenceManager.getPersistentObject(UserDomain.class, id);
		return user;
		
	}

	public UserDomain findBySignInId(String signInId) {
		hibernatePersistenceManager.beginTransaction();
		Long id = getUserID(signInId.trim());
		UserDomain userDomain  = (UserDomain) hibernatePersistenceManager.getPersistentObject(UserDomain.class, id);
		return userDomain;
		
		
		
	}
	
	public UserDomain getUserDomain(String emailId) {
		UserDomain userDomain=null;
		hibernatePersistenceManager.beginTransaction();
		Criteria user = hibernatePersistenceManager.createCriteria(UserDomain.class);
		user.add(Restrictions.eq("email", emailId));
		List<UserDomain> result = user.list();
		if(result != null && result.size()>0){
			userDomain = result.get(0);
		}
		return userDomain;
	}
	
	@Override
	public UserDTO getUserDomainByDisttId(String eDisttId) throws ObjectNotSupportedException {
		UserDomain userDomain=null;
		hibernatePersistenceManager.beginTransaction();
		Criteria user = hibernatePersistenceManager.createCriteria(UserDomain.class);
		user.add(Restrictions.eq("edisttId", eDisttId));
		List<UserDomain> result = user.list();
		if(result != null && result.size()>0){
			userDomain = result.get(0);
		}
		if(userDomain==null){
			return null;
		}
		return CommonUtils.convertObject(userDomain, UserDTO.class);
	}
	
	@Override
	public List<UserDTO> getUsersList(ReportFilter reportFilter) throws ObjectNotSupportedException {
		List<UserDTO> userList=new ArrayList<UserDTO>();
		hibernatePersistenceManager.beginTransaction();
		Criteria user = hibernatePersistenceManager.createCriteria(UserDomain.class);
		if(reportFilter.getDistrict()!=null){
			user.add(Restrictions.eq("distt", reportFilter.getDistrict()));
		}
		
		List<UserDomain> result = user.list();
		for(UserDomain userDomain:result){
			UserDTO userDto = CommonUtils.convertObject(userDomain, UserDTO.class);
			userList.add(userDto);
		}
		return userList;
	}
	
	
	
	
	public List<RolesDomain> getUserRoles(){
		hibernatePersistenceManager.beginTransaction();
		Criteria user = hibernatePersistenceManager.createCriteria(RolesDomain.class);
		List<RolesDomain> result = user.list();
		return result;
	}
	
	public RolesDomain getUserRole(long roleId) {
		hibernatePersistenceManager.beginTransaction();
		RolesDomain rolesDomain = hibernatePersistenceManager
				.getPersistentObject(RolesDomain.class, roleId);
		return rolesDomain;
	}
	
	public boolean updateUserDomain(UserDomain userDomain) throws ProcessFailedException{
		boolean result = false;
		try {
			hibernatePersistenceManager.beginTransaction();
			//userDomain.setStatus(getStatus(userDomain.getStatus().getId()));
			hibernatePersistenceManager.saveOrUpdate(userDomain);
			result = true;
			hibernatePersistenceManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			hibernatePersistenceManager.rollback();
			throw new ProcessFailedException("Forget Password Failed.");
		}
		return result;
	}
	
	@Override
	public List getAllUserList(ReportFilter filter) throws ObjectNotSupportedException {
		hibernatePersistenceManager.beginTransaction();
		String queryStr = "SELECT user.id,user.firstName,user.lastName,user.email,user.edisttId,user.area,user.newUser,user.distt from UserDomain as user where user.distt like '%"+((filter.getDistrict()==null || filter.getDistrict().indexOf(",") !=-1) ?"":filter.getDistrict())+"%'";
		Query query = hibernatePersistenceManager.createQuery(queryStr);
		List result = query.list();
		return result;
	}
	
	
	
	@Override
	public List getUserDetails(long userId) throws ObjectNotSupportedException {
		hibernatePersistenceManager.beginTransaction();
		Query query = hibernatePersistenceManager.createQuery("SELECT user.id,user.firstName,user.lastName,user.email,user.edisttId from UserDomain as user where user.id="+userId);
		List result = query.list();
		return result;
	}
	
	
	
	@Override
	public boolean updateUserProfile(UserDTO userDTO) throws ProcessFailedException, ObjectNotSupportedException {
		
		UserDomain userDomain = getUserDomain(userDTO.getEmail());
		boolean result = false;
		try {
			hibernatePersistenceManager.beginTransaction();
			
			userDomain.setDistt(userDTO.getDistt());
			userDomain.setBlock(userDTO.getBlock());	
			userDomain.setFirstName(userDTO.getFirstName());
			userDomain.setLastName(userDTO.getLastName());
			userDomain.setMobile(userDTO.getMobile());
			userDomain.setArea(userDTO.getArea());
			userDomain.setVillage(userDTO.getVillage());
		    userDomain.setPost(userDTO.getPost());
		    userDomain.setWardNo(userDTO.getWardNo());
		    userDomain.setStreet(userDTO.getStreet());
		    userDomain.setTehsil(userDTO.getTehsil());
			userDomain.setAddress(userDTO.getAddress());
			userDomain.setEdisttId(userDTO.getEdisttId());
			userDomain.setNewUser(userDTO.getNewUser());
			//userDomain.setUserImage(userDTO.getUserImage());
				
			hibernatePersistenceManager.saveOrUpdate(userDomain);
			result = true;
			hibernatePersistenceManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			
			log.info(e.getMessage());
			
			hibernatePersistenceManager.rollback();
			throw new ProcessFailedException("Update User Profile failed");
		}
		return result;
	}
	
	//add Truck
		@Override
		public boolean addTruck(TruckDTO truckDTO) throws RegistrationFailedException, ObjectNotSupportedException {
			boolean result= false;
			TruckDomain truck = CommonUtils.convertObject(truckDTO, TruckDomain.class);
			TruckIndividualWalletDomain truckIndividualWalletDomain = new TruckIndividualWalletDomain();
			
		
	       try {
				hibernatePersistenceManager.beginTransaction();
				truck.setCreationdate(new Date());
				truck.setTrucktokenno(CommonUtils.generateTruckTokenNo());
				
				hibernatePersistenceManager.save(truck);
			
				truckIndividualWalletDomain.setUpdated(new Date());
				truckIndividualWalletDomain.setTruckid(truck.getTruckid());
				hibernatePersistenceManager.save(truckIndividualWalletDomain);
				
				result = true;
				hibernatePersistenceManager.commit();
			} catch (Exception e) {
				log.info(e.getMessage());
				e.printStackTrace();
				hibernatePersistenceManager.rollback();
				throw new RegistrationFailedException("Truck Registration Failed.");
			}
			return result;

		}
		
		//Get Truck List
		
		@Override
		public List<TruckDTO> getTruckList() throws ObjectNotSupportedException {
			List<TruckDTO> truckList = new ArrayList<>();
			hibernatePersistenceManager.beginTransaction();
			Criteria trucks = hibernatePersistenceManager.createCriteria(TruckDomain.class);
			List<TruckDomain> result = trucks.list();
			for(TruckDomain truckDomain : result){
				TruckDTO truckDTO = CommonUtils.convertObject(truckDomain, TruckDTO.class);
				truckList.add(truckDTO);
			}
			return truckList;
		}
		
		//Get Truck List Detail by owner
		@Override
		public List<TruckDTO> getTruckDetails() throws ObjectNotSupportedException {
			List<TruckDTO> truckdetails = new ArrayList<TruckDTO>();
			hibernatePersistenceManager.beginTransaction();
			String queryStr = "SELECT truck.truckid, truck.truckno, truck.modelno, truck.fuelavg, truck.insuranceno, truck.purchasedate, truck.drivername, truck.drivermob, truck.driverid, truck.driverylicenseno, truck.maxtransportcapacity, truck.creationdate, truck.userid, truck.trucktokenno from TruckDomain as truck, UserDomain as user where truck.userid=user.id";
			Query query = hibernatePersistenceManager.createQuery(queryStr);
			
			List result = query.list();
			TruckDTO truckDTO = null;
			
			Iterator it = result.iterator();
			while(it.hasNext()){
				Object[] obj = (Object[]) it.next();
				truckDTO = new TruckDTO();
				
				truckDTO.setTruckid((Long)obj[0]);
				truckDTO.setTruckno((String)obj[1]);
				truckDTO.setModelno((String)obj[2]);
				truckDTO.setFuelavg((Long)obj[3]);
				truckDTO.setInsuranceno((String)obj[4]);
				truckDTO.setPurchasedate((Date)obj[5]);
				truckDTO.setDrivername((String)obj[6]);
				truckDTO.setDrivermob((String)obj[7]);
				truckDTO.setDriverid((String)obj[8]);
				truckDTO.setDriverylicenseno((String)obj[9]);
				truckDTO.setMaxtransportcapacity((Long)obj[10]);
				truckDTO.setCreationdate((Date)obj[11]);
				truckDTO.setUserid((Long)obj[12]);
				truckDTO.setTrucktokenno((Long)obj[13]);
				truckdetails.add(truckDTO);
				
			}
			
			return truckdetails;
		}
		
		//Truck Count
		@Override
		public long getTruckCount() throws ObjectNotSupportedException{
			hibernatePersistenceManager.beginTransaction();
			String queryStr = "SELECT count(*) FROM TruckDomain truck";
			Query query = hibernatePersistenceManager.createQuery(queryStr);
			long count = (long) query.list().get(0);
			//long count = ((Long)hibernatePersistenceManager.createQuery("select count(*) from TruckDomain").uniqueResult()).intValue();
			return count;
			
		}
	
	
}
