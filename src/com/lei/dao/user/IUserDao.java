package com.lei.dao.user;

import java.util.List;

import com.lei.domain.user.StatusDomain;
import com.lei.domain.user.UserDomain;
import com.lei.dto.master.ReportFilter;
import com.lei.dto.user.TruckDTO;
import com.lei.dto.user.UserDTO;
import com.lei.exception.common.ObjectNotSupportedException;
import com.lei.exception.common.ProcessFailedException;
import com.lei.exception.project.ProjectIdNotSupportedException;
import com.lei.exception.user.RegistrationFailedException;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
public interface IUserDao {
	public boolean login(UserDTO UserDomain);
	public boolean logout(UserDTO UserDomain) throws ObjectNotSupportedException;
	public boolean register(UserDTO UserDomain) throws RegistrationFailedException,ObjectNotSupportedException;
	public boolean updatePasswordAndStatus(UserDTO UserDomain) throws ProcessFailedException, ObjectNotSupportedException;
	public boolean exists(String emailId);
	public UserDTO getUser(String emailId) throws ObjectNotSupportedException;
	public int getStatusId(String status);
	public StatusDomain getStatus(String status);
	
	public UserDomain findById(long id);
	
	public UserDomain findBySignInId(String sso);
	
	
	public List<UserDTO> getUsersList(ReportFilter reportFilter) throws ObjectNotSupportedException ;
	List getUserDetails(long userId) throws ObjectNotSupportedException;
	UserDTO getUserDomainByDisttId(String eDisttId)	throws ObjectNotSupportedException;
	boolean existsEDisttId(String edisttId);
	
	
	
	
	boolean updateUserProfile(UserDTO userDTO) throws ProcessFailedException,
			ObjectNotSupportedException;
	List getAllUserList(ReportFilter filter) throws ObjectNotSupportedException;
	
	//Add Truck
	boolean addTruck(TruckDTO truckDTO) throws RegistrationFailedException,
			ObjectNotSupportedException;
	
	//Truck List
	List<TruckDTO> getTruckList() throws ObjectNotSupportedException;
	List<TruckDTO> getTruckDetails() throws ObjectNotSupportedException;
	long getTruckCount() throws ObjectNotSupportedException;
	
}
