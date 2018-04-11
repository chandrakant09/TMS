package com.lei.maintenance.user;

import java.util.List;

import com.lei.dto.master.ReportFilter;
import com.lei.dto.user.TruckDTO;
import com.lei.dto.user.UserDTO;
import com.lei.exception.common.EmailException;
import com.lei.exception.common.ObjectNotSupportedException;
import com.lei.exception.common.ProcessFailedException;
import com.lei.exception.user.ConfirmNewPasswordException;
import com.lei.exception.user.EDistrickRegistrationException;
import com.lei.exception.user.InvalidUserException;
import com.lei.exception.user.LoginIDFormatException;
import com.lei.exception.user.LoginIDRegisteredException;
import com.lei.exception.user.OldPasswordException;
import com.lei.exception.user.PasswordFormatException;
import com.lei.exception.user.RegistrationFailedException;
import com.lei.exception.user.SessionDoesNotExistException;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
public interface IUserMaintenance {
//	public boolean login(UserDTO userDto) throws InvalidUserException, LoginIDFormatException, ObjectNotSupportedException,ActivationPendingException;
	public boolean logout(UserDTO userDto) throws SessionDoesNotExistException, ObjectNotSupportedException;
	public boolean register(UserDTO userDto) throws LoginIDFormatException, LoginIDRegisteredException, RegistrationFailedException, ObjectNotSupportedException,EmailException, EDistrickRegistrationException;
	public boolean forgetPassword(UserDTO userDto) throws InvalidUserException, LoginIDFormatException, ObjectNotSupportedException,ProcessFailedException,EmailException;
	public boolean changePassword(UserDTO userDto) throws ConfirmNewPasswordException, OldPasswordException, PasswordFormatException, ObjectNotSupportedException, ProcessFailedException, EmailException;
	public boolean exists(String emailId) throws LoginIDFormatException;
	public UserDTO getUser(String emailId) throws LoginIDFormatException, ObjectNotSupportedException;
	public UserDTO getUserByEdistt(String edisttId) throws ObjectNotSupportedException;
	public String getStatus(String emailId);
	public List<UserDTO> getUsersList(ReportFilter reportFilter) throws ObjectNotSupportedException;
	public boolean updateUserProfile(UserDTO userDTO) throws ProcessFailedException, ObjectNotSupportedException;
	public UserDTO findById(long id) throws ObjectNotSupportedException;
	public UserDTO findBySignInId(String signInId) throws ObjectNotSupportedException;
	
	//Add Truck
	boolean addTruck(TruckDTO truckDTO) throws RegistrationFailedException,
			ObjectNotSupportedException, EmailException,
			LoginIDRegisteredException, EDistrickRegistrationException;
	
	//Get All Truck
	
	List<TruckDTO> getTruckList() throws ObjectNotSupportedException;
	List<TruckDTO> getTruckDetails() throws ObjectNotSupportedException;
	long getTruckCount() throws ObjectNotSupportedException;
}
