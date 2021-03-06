package com.lei.service.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lei.dto.common.ResponseMessageDTO;
import com.lei.dto.master.ReportFilter;
import com.lei.dto.user.TruckDTO;
import com.lei.dto.user.UserDTO;
import com.lei.dto.user.UserReport;
import com.lei.exception.common.EmailException;
import com.lei.exception.common.ObjectNotSupportedException;
import com.lei.exception.common.ProcessFailedException;
import com.lei.exception.user.ActivationPendingException;
import com.lei.exception.user.EDistrickRegistrationException;
import com.lei.exception.user.InvalidUserException;
import com.lei.exception.user.LoginIDFormatException;
import com.lei.exception.user.LoginIDRegisteredException;
import com.lei.exception.user.RegistrationFailedException;
import com.lei.exception.user.SessionDoesNotExistException;
import com.lei.maintenance.user.IUserMaintenance;
import com.lei.utility.CommonUtils;
import com.lei.utility.MessageUtils;
import com.lei.utility.constants.HttpStatusCodes;
import com.lei.utility.constants.RoleEnum;

/**
 * 
 * @author Shrikant Kushwaha
 * 
 */
@Slf4j
@RestController
@RequestMapping("/usermaintenance")
public class UserMaintenanceService {

	@Autowired
	IUserMaintenance userMaintenance;

	@Autowired
	private AuthenticationManager authenticationManager;

	@RequestMapping(value = "/getMessage", method = RequestMethod.GET, headers = "Accept=application/xml, application/json")
	public ResponseMessageDTO getMessage(
			@RequestParam("MessageKey") String messageKey)
			throws LoginIDRegisteredException, LoginIDFormatException,
			ProcessFailedException {

		log.info("getMessage");

		ResponseMessageDTO response = new ResponseMessageDTO();
		response.setResponseCode(HttpStatusCodes.SUCCESS.getCode());
		response.setData(MessageUtils.getMessage(messageKey));

		return response;
	}

	@RequestMapping(value = "/exists", method = RequestMethod.GET, headers = "Accept=application/xml, application/json")
	public ResponseMessageDTO emailExists(@RequestParam("Email") String email)
			throws LoginIDRegisteredException, LoginIDFormatException,
			ProcessFailedException {

		log.info("EmailExists checked : " + email);

		if (!userMaintenance.exists(email)) {
			ResponseMessageDTO response = new ResponseMessageDTO();
			response.setResponseCode(HttpStatusCodes.AVAILABLE.getCode());
			response.setResponseMessage("AVAILABLE and user can be created with this login ID");
			return response;
		} else {
			throw new LoginIDRegisteredException(
					"ID Already registered in the System");
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, headers = "Accept=application/xml, application/json")
	public ResponseMessageDTO register(@RequestBody UserDTO userDto)
			throws LoginIDFormatException, LoginIDRegisteredException,
			RegistrationFailedException, ObjectNotSupportedException,
			EmailException, EDistrickRegistrationException {

		log.info("User Register >>Register :" + userDto.getEmail());
		log.info("User Register >>Edistt :" + userDto.getEdisttId());
		userMaintenance.register(userDto);
		log.info("User Register successful");

		ResponseMessageDTO dto = new ResponseMessageDTO();
		dto.setResponseMessage("User have been registered successfully");
		dto.setResponseCode(HttpStatusCodes.REGISTERED.getCode());
		return dto;
	}

	

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST, headers = "Accept=application/xml, application/json")
	public ResponseMessageDTO changePassword(@RequestBody UserDTO userDto,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.info("Change Password >> changePassword:" + userDto.getId());
		if (SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal().equals("anonymousUser")) {
			throw new SessionDoesNotExistException(
					"User session does not exists");
		}
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String userId = user.getUsername();

		userDto.setEmail(userId);
		userMaintenance.changePassword(userDto);

		log.info("UserId:" + userDto.getId());

		ResponseMessageDTO dto = new ResponseMessageDTO();
		dto.setResponseMessage("Change password successfull");

		log.info(" password Changed successfull");

		dto.setResponseCode(HttpStatusCodes.CHANGEPASSWORDSUCCESS.getCode());
		return dto;
	}

	@RequestMapping(value = "/forgetPassword", method = RequestMethod.POST, headers = "Accept=application/xml, application/json")
	public ResponseMessageDTO forgetPassword(@RequestBody UserDTO userDto)
			throws InvalidUserException, LoginIDFormatException,
			ObjectNotSupportedException, ProcessFailedException, EmailException {

		log.info("Forget Password >> forgetPassword:" + userDto.getEmail());
		userMaintenance.forgetPassword(userDto);
		log.info("Forget Password >>Success :" + userDto.getEmail());
		ResponseMessageDTO dto = new ResponseMessageDTO();
		dto.setResponseMessage("Password have been sent on email");
		dto.setResponseCode(HttpStatusCodes.FORGETSUCCESS.getCode());
		return dto;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, headers = "Accept=application/xml, application/json")
	public ResponseMessageDTO login(@RequestBody UserDTO userDto,
			HttpServletRequest request, HttpServletResponse response)
			throws InvalidUserException, LoginIDFormatException,
			ObjectNotSupportedException, ActivationPendingException,
			ProcessFailedException {
		log.info("login User :" + userDto.getEmail());
		ResponseMessageDTO dto = new ResponseMessageDTO();

		Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
				userDto.getEmail(), userDto.getStatus().getPassword());
		try {
			Authentication authentication = authenticationManager
					.authenticate(authenticationToken);

			SecurityContextHolder.getContext()
					.setAuthentication(authentication);
			dto.setResponseMessage("User have been logedin successfully");

			dto.setResponseCode(HttpStatusCodes.LOGINSUCCESS.getCode());

			HttpSession session = request.getSession(true);
			session.setAttribute("userEmail", userDto.getEmail());
			
			userDto = userMaintenance.getUser(userDto.getEmail());

			log.info("UserEmail:" + userDto.getEmail());

		} catch (UsernameNotFoundException ex) {
			throw new InvalidUserException("Invalid Username!!");

		} catch (AuthenticationException ex) {
			throw new InvalidUserException("Authentication failed!! ");
		}

		dto.setResponseMessage("User have been logedin successfully");

		log.info("User have been logedin successfully");

		dto.setResponseCode(HttpStatusCodes.LOGINSUCCESS.getCode());
		dto.setData(userDto.getUserRole());

		return dto;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET, headers = "Accept=application/xml, application/json")
	public ResponseMessageDTO logout(HttpServletRequest request,
			HttpServletResponse response) throws SessionDoesNotExistException,
			ProcessFailedException {

		log.info("logout User");

		ResponseMessageDTO dto = new ResponseMessageDTO();
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
			dto.setResponseMessage("User have been logedout successfully. Please login again to access the portal.");
			dto.setResponseCode(HttpStatusCodes.LOGOUTSUCCESS.getCode());
			return dto;
		} else {
			throw new SessionDoesNotExistException(
					"User session does not exists");
		}
	}

	@RequestMapping(value = "/getUserDetail", method = RequestMethod.POST, headers = "Accept=application/xml, application/json")
	public ResponseMessageDTO getUserDetails() throws LoginIDFormatException,
			ObjectNotSupportedException, SessionDoesNotExistException {
		log.info("Get User Detail >> getUserDetails");

		if (SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal().equals("anonymousUser")) {
			throw new SessionDoesNotExistException(
					"User session does not exists");
		}
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String userId = user.getUsername();
		UserDTO userDTO = userMaintenance.getUser(userId);

		log.info("UserId:" + userDTO.getId());

		return CommonUtils.getSuccessMessage(
				HttpStatusCodes.AVAILABLE.getCode(),
				"Process completed successfully", userDTO);
	}

	@RequestMapping(value = "/getUsersList", method = RequestMethod.POST, headers = "Accept=application/xml, application/json")
	public ResponseMessageDTO getUsersList()
			throws ObjectNotSupportedException, SessionDoesNotExistException,
			LoginIDFormatException {
		log.info("Get User List >> getUsersList:");

		if (SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal().equals("anonymousUser")) {
			throw new SessionDoesNotExistException(
					"User session does not exists");
		}
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String userId = user.getUsername();
		UserDTO userDTO = userMaintenance.getUser(userId);
		List<UserDTO> userList = null;
		UserReport userReport = null;

		if (userDTO.getUserRole().getRoleId() == RoleEnum.Admin.getID()) {
			ReportFilter reportFilter = new ReportFilter();
			reportFilter.setDistrict(userDTO.getDistt());
			userReport = new UserReport();
			userList = userMaintenance.getUsersList(reportFilter);
			userReport.setUserList(userList);
		}
		return CommonUtils.getSuccessMessage(
				HttpStatusCodes.AVAILABLE.getCode(),
				"Process completed successfully", userReport);
	}

	

	@RequestMapping(value = "/updateUserProfile", method = RequestMethod.POST, headers = "Accept=application/xml, application/json")
	public ResponseMessageDTO updateUserProfile(@RequestBody UserDTO userDTO)
			throws ProcessFailedException, ObjectNotSupportedException {
		userMaintenance.updateUserProfile(userDTO);
		ResponseMessageDTO dto = new ResponseMessageDTO();
		dto.setResponseMessage("User Update Profile successfully");
		dto.setResponseCode(HttpStatusCodes.REGISTERED.getCode());
		return dto;
	}

	/**
	 * Check if user is login by remember me cookie, refer
	 * org.springframework.security
	 * .authentication.AuthenticationTrustResolverImpl
	 */
	private boolean isRememberMeAuthenticated() {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		if (authentication == null) {
			return false;
		}

		return RememberMeAuthenticationToken.class
				.isAssignableFrom(authentication.getClass());
	}
	
	// Add Truck
	@RequestMapping(value = "/addTruck", method = RequestMethod.POST, headers = "Accept=application/xml, application/json")
	public ResponseMessageDTO addTruck(@RequestBody TruckDTO truckDTO, HttpServletRequest request, HttpServletResponse response)
			throws LoginIDFormatException, LoginIDRegisteredException,
			RegistrationFailedException, ObjectNotSupportedException,
			EmailException, EDistrickRegistrationException, SessionDoesNotExistException {
			
			//get UserId from session
			if (SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal().equals("anonymousUser")) {
				throw new SessionDoesNotExistException(
						"User session does not exists");
			}
			User user = (User) SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();
			String userId = user.getUsername();
			UserDTO userDTO = userMaintenance.getUser(userId);
			
			truckDTO.setUserid(userDTO.getId());
			
			/*HttpSession session = request.getSession(false);
			Long userid = (Long) session.getAttribute("userid");
			
			truckDTO.setUserid(userid);*/
			
			userMaintenance.addTruck(truckDTO);
			/*if(i){
				userMaintenance.addTruck(truckDTO);
			}else{
				log.info("Truck Register unsuccessful");
			}*/
			log.info("Truck Register successful");

			ResponseMessageDTO dto = new ResponseMessageDTO();
			dto.setResponseMessage("Truck have been registered successfully");
			dto.setResponseCode(HttpStatusCodes.REGISTERED.getCode());
			return dto;
		}
	
	// Get Truck List
	@RequestMapping(value = "/getTruckList", method = RequestMethod.POST, headers = "Accept=application/xml, application/json")
	public ResponseMessageDTO getTruckList()
			throws ObjectNotSupportedException, SessionDoesNotExistException,
			LoginIDFormatException {
		log.info("Get Truck List >>getTruckList():");
		List<TruckDTO> truckList = null;
		truckList = userMaintenance.getTruckList();
		return CommonUtils.getSuccessMessage(
				HttpStatusCodes.AVAILABLE.getCode(),
				"Process completed successfully", truckList);
		}
		
	// Get Truck detail added by owner
	@RequestMapping(value = "/getTrucklistdetail", method = RequestMethod.POST, headers = "Accept=application/xml, application/json")
	public ResponseMessageDTO getTrucklistdetail()
			throws ObjectNotSupportedException, SessionDoesNotExistException,
			LoginIDFormatException {
			log.info("Get Truck List >>getTruckList():");
			List<TruckDTO> truckListDetail = null;
			truckListDetail = userMaintenance.getTruckDetails();
			return CommonUtils.getSuccessMessage(
					HttpStatusCodes.AVAILABLE.getCode(),
					"Process completed successfully", truckListDetail);
			}
	@RequestMapping(value = "/getTruckCount", method = RequestMethod.GET, headers = "Accept=application/xml, application/json")
	public ResponseMessageDTO getTruckCount()
			throws ObjectNotSupportedException, SessionDoesNotExistException,
			LoginIDFormatException {
		Long truckCount = userMaintenance.getTruckCount();
		return CommonUtils.getSuccessMessage(
				HttpStatusCodes.AVAILABLE.getCode(),
				"Process completed successfully", truckCount);
		
	}

	
}
