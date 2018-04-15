package com.lei.maintenance.user;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.lei.dao.base.DaoManager;
import com.lei.dao.user.IUserDao;
import com.lei.domain.user.UserDomain;
import com.lei.dto.common.ResponseMessageDTO;
import com.lei.dto.master.ReportFilter;
import com.lei.dto.user.TruckDTO;
import com.lei.dto.user.UserDTO;
import com.lei.dto.user.UserStatusDto;
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
import com.lei.utility.CommonUtils;
import com.lei.utility.MessageUtils;
import com.lei.utility.constants.EmailEvent;
import com.lei.utility.constants.MessageKeyConstants;
import com.lei.utility.constants.StatusConstants;
import com.lei.utility.email.SendMail;

@Slf4j
@Service
public class UserMaintenanceImpl implements IUserMaintenance{

	@Override
	public boolean changePassword(UserDTO loginDto) throws ConfirmNewPasswordException, OldPasswordException, PasswordFormatException, ObjectNotSupportedException, ProcessFailedException, EmailException {
		IUserDao dao = DaoManager.USER.getDao(IUserDao.class);
		boolean isChangePassword =false;
		String userPassword = null;
		
		UserDTO persistedUser = dao.getUser(loginDto.getEmail());
		if(loginDto.getStatus().getNewPassword().equals(loginDto.getStatus().getConfirmNewPassword())){
			userPassword = loginDto.getStatus().getPassword();
			
			if(userPassword.equals(persistedUser.getStatus().getPassword())){
				persistedUser.getStatus().setPassword(loginDto.getStatus().getNewPassword());
				isChangePassword = dao.updatePasswordAndStatus(persistedUser);
				if(isChangePassword){
					String emailBody = MessageUtils.getMessage(MessageKeyConstants.USERCHANGEPASSWORD.getKey(), persistedUser.getFirstName());
					SendMail.sendMail(EmailEvent.USERCHANGEPASSWORD.getEvent(), persistedUser.getEmail(), null, emailBody, null);
					}
				}else{
				throw new OldPasswordException("old password does not match.");
				}
			}else{
				throw new ConfirmNewPasswordException("New password and confirm password does not match.");
			}

			return isChangePassword;
		}

	@Override
	public boolean exists(String emailId) throws LoginIDFormatException{
		IUserDao dao = DaoManager.USER.getDao(IUserDao.class);
		return dao.exists(emailId);
		}

	@Override
	public boolean forgetPassword(UserDTO userDto) throws InvalidUserException, LoginIDFormatException, ObjectNotSupportedException, ProcessFailedException, EmailException {
		IUserDao dao = DaoManager.USER.getDao(IUserDao.class);
		String temporaryPassword = null;
		boolean isChangePassword =false;
		
		if(dao.exists(userDto.getEmail())){
			temporaryPassword = CommonUtils.generatePassword();
			userDto = dao.getUser(userDto.getEmail());
			userDto.getStatus().setStatusId(StatusConstants.ACTIVE.getID());
			userDto.getStatus().setPassword(temporaryPassword);
			
			isChangePassword = dao.updatePasswordAndStatus(userDto);

			if(isChangePassword){
				String emailBody = MessageUtils.getMessage(MessageKeyConstants.LOGINFORGETPASSWORDBODY.getKey(), userDto.getFirstName(),userDto.getEmail(),temporaryPassword);
				SendMail.sendMail(EmailEvent.USERFORGETPASSWORD.getEvent(), userDto.getEmail(), null, emailBody, null);
			}else{
				throw new ProcessFailedException("Some unknown exception generated at the time of forget password");
			}
		}else{
			throw new InvalidUserException("User is not registered.");
		}
		return isChangePassword;
	}

	@Override
	public boolean logout(UserDTO loginDto) throws SessionDoesNotExistException , ObjectNotSupportedException{
		IUserDao dao = DaoManager.USER.getDao(IUserDao.class);

		return dao.logout(loginDto);
	}

	@Override
	public boolean register(UserDTO userDto) throws RegistrationFailedException, ObjectNotSupportedException, EmailException, LoginIDRegisteredException, EDistrickRegistrationException {
		IUserDao dao = DaoManager.USER.getDao(IUserDao.class);
		String plainPassword = CommonUtils.generatePassword();
		String temporaryPassword = plainPassword;
		boolean registered = false;

		if(!dao.exists(userDto.getEmail())){
			if(dao.existsEDisttId(userDto.getEdisttId())){
				throw new EDistrickRegistrationException("E- Distt Id Is already Registered");
			}
			userDto.setStatus(new UserStatusDto());
			userDto.getStatus().setPassword(temporaryPassword);
			userDto.getStatus().setStatusId(StatusConstants.NEW.getID());
			
			registered = dao.register(userDto);
			
			
			if(registered){
				
				String emailBody = MessageUtils.getMessage(MessageKeyConstants.NEWREGISTRATIONBODY.getKey(), userDto.getFirstName(),userDto.getEmail(),plainPassword);
				SendMail.sendMail(EmailEvent.USERREGISTERED.getEvent(), userDto.getEmail(), null, emailBody, null);
			}
		}else{
			log.info("Email Id Already exist"+userDto.getEmail());
			throw new LoginIDRegisteredException("Email Id Already exist"+userDto.getEmail());
		}
		return registered;
	}
	
	@Override
	public UserDTO getUser(String emailId) throws ObjectNotSupportedException {
		IUserDao dao = DaoManager.USER.getDao(IUserDao.class);
		UserDTO userDomain = dao.getUser(emailId);
		return userDomain;
	}
	
	@Override
	public String getStatus(String emailId) {
		/*IUserDao dao = DaoManager.USER.getDao(IUserDao.class);
		UserDomain userDomain = dao.getUser(emailId);
		
		if(userDomain!=null){
			return userDomain.getStatus().getName();
		}else{
			return null;
		}*/
		return null;
	}

	@Override
	public UserDTO findById(long id) throws ObjectNotSupportedException {
		IUserDao dao = DaoManager.USER.getDao(IUserDao.class);
		
		UserDomain userDomain = dao.findById(id); 
		UserDTO dto= CommonUtils.convertObject(userDomain, UserDTO.class);
		return dto;
	}

	@Override
	public UserDTO findBySignInId(String signInId) throws ObjectNotSupportedException {
		IUserDao dao = DaoManager.USER.getDao(IUserDao.class);
		UserDomain userDomain = dao.findBySignInId(signInId); 
		UserDTO dto= CommonUtils.convertObject(userDomain, UserDTO.class);
		return dto;
	}
	
	
	
	@Override
	public List<UserDTO> getUsersList(ReportFilter reportFilter) throws ObjectNotSupportedException {
		IUserDao dao = DaoManager.USER.getDao(IUserDao.class);
		List<UserDTO> userList = dao.getUsersList(reportFilter); 
		return userList;
	}

	@Override
	public UserDTO getUserByEdistt(String edisttId)	throws ObjectNotSupportedException {
		IUserDao dao = DaoManager.USER.getDao(IUserDao.class);
		UserDTO userDTO= dao.getUserDomainByDisttId(edisttId);
		return userDTO;
	}

	@Override
	public boolean updateUserProfile(UserDTO userDTO)
			throws ProcessFailedException, ObjectNotSupportedException {
		
		IUserDao dao = DaoManager.USER.getDao(IUserDao.class);
		
		boolean userupdated = false;
		
		userupdated = dao.updateUserProfile(userDTO);
		
		return userupdated;
	}
	
	//Add Truck
	@Override
	public boolean addTruck(TruckDTO truckDTO) throws RegistrationFailedException, ObjectNotSupportedException, EmailException, LoginIDRegisteredException, EDistrickRegistrationException {
		IUserDao dao = DaoManager.USER.getDao(IUserDao.class);
		boolean registered = false;
		registered = dao.addTruck(truckDTO);
		return registered;
		}
		
	//Get All Truck List
	@Override
	public List<TruckDTO> getTruckList() throws ObjectNotSupportedException {
		IUserDao dao = DaoManager.USER.getDao(IUserDao.class);
		List<TruckDTO> truckList = dao.getTruckList();
		return truckList;
		}
			
	//Get All Truck details add by owner
	@Override
	public List<TruckDTO> getTruckDetails() throws ObjectNotSupportedException {
		IUserDao dao = DaoManager.USER.getDao(IUserDao.class);
		List truckdetails = dao.getTruckDetails();
		return truckdetails;
		}
	
	//Truck List
	@Override
	public long getTruckCount() throws ObjectNotSupportedException{
		IUserDao dao = DaoManager.USER.getDao(IUserDao.class);
		long countTruck = dao.getTruckCount();
		return countTruck;

		}
	
	
	//Get Truck Id
	@Override
	public TruckDTO geTruckId(long truckid) throws ObjectNotSupportedException {
		IUserDao dao = DaoManager.USER.getDao(IUserDao.class);
		TruckDTO truckDTO = dao.getTruckId(truckid);
		//UserDTO userDomain = dao.getUser(emailId);
		return truckDTO;
	}

	
}
