package com.lei.service.dashoard;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lei.dto.common.ResponseMessageDTO;
import com.lei.dto.master.ReportFilter;
import com.lei.dto.user.UserDTO;
import com.lei.dto.wallet.DashBoardReport;
import com.lei.dto.wallet.EdisttDTO;
import com.lei.exception.common.ObjectNotSupportedException;
import com.lei.exception.common.ProcessFailedException;
import com.lei.exception.user.LoginIDFormatException;
import com.lei.exception.user.SessionDoesNotExistException;
import com.lei.maintenance.transaction.IDisttMaintrnance;
import com.lei.maintenance.transaction.ITransactionMaintenance;
import com.lei.maintenance.user.IUserMaintenance;
import com.lei.utility.CommonUtils;
import com.lei.utility.constants.HttpStatusCodes;
import com.lei.utility.constants.RoleEnum;
import com.lei.utility.constants.TransactionStatus;
import com.lei.utility.constants.TransactionTypeEnum;

@Slf4j
@RestController
@RequestMapping("/dashboard")
public class DashboardServices {
	
	@Autowired
	ITransactionMaintenance transactionMaintenance;
	
	@Autowired
	IUserMaintenance userMaintenance;
	 
	@Autowired
	IDisttMaintrnance disttMaintrnance;
	 

	@RequestMapping(value = "/adminDashoardData",
			method = RequestMethod.POST,
			headers="Accept=application/xml, application/json")
	public ResponseMessageDTO adminDashoardData() throws LoginIDFormatException, ObjectNotSupportedException, ProcessFailedException, SessionDoesNotExistException {
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
			throw new SessionDoesNotExistException("User session does not exists");
		}
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userId = user.getUsername();
		UserDTO userDTO=userMaintenance.getUser(userId);
		ReportFilter reportFilter=new ReportFilter();
		reportFilter.setDistrict(userDTO.getDistt());
		DashBoardReport dashBoardReport=null;
		if(userDTO.getUserRole().getRoleId()==RoleEnum.Admin.getID()){
			dashBoardReport=transactionMaintenance.getDashBoardData(reportFilter);
			dashBoardReport.setUser(userDTO);
		}else{
			//Throws Access Denied 
		}
		return CommonUtils.getSuccessMessage(HttpStatusCodes.SUCCESS.getCode(), "Process completed successfully",dashBoardReport);
	}
	
	
	
	@RequestMapping(value = "/userDashoardData",
			method = RequestMethod.POST,
			headers="Accept=application/xml, application/json")
	public ResponseMessageDTO userDashoardData() throws LoginIDFormatException, ObjectNotSupportedException, ProcessFailedException, SessionDoesNotExistException {
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
			throw new SessionDoesNotExistException("User session does not exists");
		}
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userId = user.getUsername();
		UserDTO userDTO=userMaintenance.getUser(userId);
		DashBoardReport dashBoardReport=null;
		if(userDTO.getUserRole().getRoleId()==RoleEnum.User.getID()){
			ReportFilter reportFilter=new ReportFilter();
			reportFilter.setUserId(userDTO.getId());
			dashBoardReport=transactionMaintenance.getIndividualDashBoardData(reportFilter);
			dashBoardReport.setUser(userDTO);
		}else{
			//Throws Access Denied 
		}
		return CommonUtils.getSuccessMessage(HttpStatusCodes.SUCCESS.getCode(), "Process completed successfully",dashBoardReport);
	}
	
	@RequestMapping(value = "/chequeData",
			method = RequestMethod.POST,
			headers="Accept=application/xml, application/json")
	public ResponseMessageDTO getChequeData() throws LoginIDFormatException, ObjectNotSupportedException, ProcessFailedException, SessionDoesNotExistException {
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
			throw new SessionDoesNotExistException("User session does not exists");
		}
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userId = user.getUsername();
		UserDTO userDTO=userMaintenance.getUser(userId);
		DashBoardReport dashBoardReport=null;
		if(userDTO.getUserRole().getRoleId()==RoleEnum.Admin.getID()){
			dashBoardReport=transactionMaintenance.getChequesList();
		}else{
			dashBoardReport=transactionMaintenance.getChequesList(userDTO.getId());
		}
		return CommonUtils.getSuccessMessage(HttpStatusCodes.SUCCESS.getCode(), "Process completed successfully",dashBoardReport);
	}
	/**
	 * This 
	 * @return
	 * @throws LoginIDFormatException
	 * @throws ObjectNotSupportedException
	 * @throws ProcessFailedException
	 * @throws SessionDoesNotExistException 
	 */
	
	@RequestMapping(value = "/demandDraftData",
			method = RequestMethod.POST,
			headers="Accept=application/xml, application/json")
	public ResponseMessageDTO getDemandDraftData() throws LoginIDFormatException, ObjectNotSupportedException, ProcessFailedException, SessionDoesNotExistException {
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
			throw new SessionDoesNotExistException("User session does not exists");
		}
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userId = user.getUsername();
		UserDTO userDTO=userMaintenance.getUser(userId);
		DashBoardReport dashBoardReport=null;
		ReportFilter reportFilter=new ReportFilter();
		if(userDTO.getUserRole().getRoleId()!=RoleEnum.Admin.getID()){
			reportFilter.setUserId(userDTO.getId());
		}
		dashBoardReport=transactionMaintenance.getDemandDraftDepositList(reportFilter);
		return CommonUtils.getSuccessMessage(HttpStatusCodes.SUCCESS.getCode(), "Process completed successfully",dashBoardReport);
	}
	
	
	@RequestMapping(value = "/cashData",
			method = RequestMethod.POST,
			headers="Accept=application/xml, application/json")
	public ResponseMessageDTO getCashData() throws LoginIDFormatException, ObjectNotSupportedException, ProcessFailedException, SessionDoesNotExistException {
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
			throw new SessionDoesNotExistException("User session does not exists");
		}
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userId = user.getUsername();
		UserDTO userDTO=userMaintenance.getUser(userId);
		DashBoardReport dashBoardReport=null;
		ReportFilter reportFilter=new ReportFilter();
		if(userDTO.getUserRole().getRoleId()!=RoleEnum.Admin.getID()){
			reportFilter.setUserId(userDTO.getId());
		}
		dashBoardReport=transactionMaintenance.getCashDepositList(reportFilter);
		return CommonUtils.getSuccessMessage(HttpStatusCodes.SUCCESS.getCode(), "Process completed successfully",dashBoardReport);
	}
	
	/*
	 * This service calling via Admin or super admin
	 */
	@RequestMapping(value = "/getUserAutocompleteData",
			method = RequestMethod.POST,
			headers="Accept=application/xml, application/json")
	public ResponseMessageDTO getUserAutocompleteData() throws LoginIDFormatException, ObjectNotSupportedException, ProcessFailedException, SessionDoesNotExistException {
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
			throw new SessionDoesNotExistException("User session does not exists");
		}
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userId = user.getUsername();
		UserDTO userDTO=userMaintenance.getUser(userId);
		DashBoardReport dashBoardReport=null;
		ReportFilter reportFilter=new ReportFilter();
		if(userDTO.getUserRole().getRoleId()==RoleEnum.Admin.getID()){
			reportFilter.setDistrict(userDTO.getDistt());
		}
		
		dashBoardReport=transactionMaintenance.getUserAutocompleteData(reportFilter);
		return CommonUtils.getSuccessMessage(HttpStatusCodes.SUCCESS.getCode(), "Process completed successfully",dashBoardReport);
	}
	
	@RequestMapping(value = "/getRechargeFilterList",
			method = RequestMethod.POST,
			headers="Accept=application/xml, application/json")
	public ResponseMessageDTO getRechargeFilterList(@RequestBody ReportFilter reportFilter) throws LoginIDFormatException, ObjectNotSupportedException, ProcessFailedException, SessionDoesNotExistException {
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
			throw new SessionDoesNotExistException("User session does not exists");
		}
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userId = user.getUsername();
		UserDTO userDTO=userMaintenance.getUser(userId);
		log.info("UserId:"+userDTO.getId());
		DashBoardReport dashBoardReport= null;
		if(userDTO.getUserRole().getRoleId()!=RoleEnum.Admin.getID()){
			reportFilter.setUserId(userDTO.getId());
		}
		dashBoardReport=transactionMaintenance.getRechargeList(reportFilter);
		return CommonUtils.getSuccessMessage(HttpStatusCodes.AVAILABLE.getCode(), "Process completed successfully", dashBoardReport);
	}
	
	

		@RequestMapping(value = "/getCashDetailstList",
				method = RequestMethod.POST,
				headers="Accept=application/xml, application/json")
		public ResponseMessageDTO getCashDetailstList(@RequestBody ReportFilter reportFilter) throws ObjectNotSupportedException, SessionDoesNotExistException, LoginIDFormatException{
			log.info("Get Cash Detail List >> getCashDetailstList:");
			if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
				throw new SessionDoesNotExistException("User session does not exists");
			}
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String userId = user.getUsername();
			UserDTO userDTO=userMaintenance.getUser(userId);
		    reportFilter.setDistrict(userDTO.getDistt());
			reportFilter.setStatus(TransactionStatus.Success.getStatus());
			DashBoardReport dashBoardReport=null;
			if(userDTO.getUserRole().getRoleId()==RoleEnum.Admin.getID()){
				dashBoardReport = transactionMaintenance.getCashDetailstList(reportFilter);
			}
			
			
			return CommonUtils.getSuccessMessage(HttpStatusCodes.AVAILABLE.getCode(), "Cash Details List Process completed successfully", dashBoardReport);
		}
	
	 
		@RequestMapping(value = "/getEdisttList",
					method = RequestMethod.POST,
					headers="Accept=application/xml, application/json")
		 public ResponseMessageDTO getEdisttList(@RequestBody ReportFilter reportFilter) throws ObjectNotSupportedException, SessionDoesNotExistException, LoginIDFormatException {
			 log.info("Get Edistrict List >> getEdisttList:");
			 List<EdisttDTO> edisttList=null;
			 DashBoardReport dashBoardReport= null;
			 dashBoardReport=new DashBoardReport();
			 edisttList=disttMaintrnance.getEdisttList(reportFilter);
			 dashBoardReport.setEdisttList(edisttList);
			
			 return CommonUtils.getSuccessMessage(HttpStatusCodes.AVAILABLE.getCode(), "Edistrict Process completed successfully", dashBoardReport);
		 }
	
		
		@RequestMapping(value = "/getEdistWorkStatus",
				method = RequestMethod.POST,
				headers="Accept=application/xml, application/json")
		 public ResponseMessageDTO getEdistWorkStatus() throws ObjectNotSupportedException, SessionDoesNotExistException, LoginIDFormatException {
			log.info("Get getEdistWorkStatus");
			if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
				throw new SessionDoesNotExistException("User session does not exists");
			}
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String userId = user.getUsername();
			UserDTO userDTO=userMaintenance.getUser(userId);
			ReportFilter reportFilter=new ReportFilter();
			reportFilter.setDistrict(userDTO.getDistt());
			reportFilter.setStatus(TransactionStatus.Success.getStatus());
			reportFilter.setTransactionType(TransactionTypeEnum.DEBIT.getValue());
			DashBoardReport dashBoardReport=null;
			if(userDTO.getUserRole().getRoleId()==RoleEnum.Admin.getID()){
				dashBoardReport = transactionMaintenance.getEdistWorkStatus(reportFilter);
			}
			return CommonUtils.getSuccessMessage(HttpStatusCodes.AVAILABLE.getCode(), "GetEdistWorkStatus success", dashBoardReport);
		 }
		
		/**
		 * Get User Details (Super Admin Calling)
		 * @return
		 * @throws LoginIDFormatException
		 * @throws ObjectNotSupportedException
		 * @throws ProcessFailedException
		 * @throws SessionDoesNotExistException
		 */
		
		@RequestMapping(value = "/getUserDetails",
				method = RequestMethod.POST,
				headers="Accept=application/xml, application/json")
		public ResponseMessageDTO getUserDetails(@RequestBody ReportFilter reportFilter) throws LoginIDFormatException, ObjectNotSupportedException, ProcessFailedException, SessionDoesNotExistException {
			if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
				throw new SessionDoesNotExistException("User session does not exists");
			}
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String userId = user.getUsername();
			UserDTO userDTO=userMaintenance.getUser(userId);
			DashBoardReport dashBoardReport=null;
			if(userDTO.getUserRole().getRoleId()==RoleEnum.SuperAdmin.getID()){
				dashBoardReport=transactionMaintenance.getIndividualDashBoardData(reportFilter);
				dashBoardReport.setUser(userMaintenance.findById(reportFilter.getUserId()));
			}else{
				//Throws Access Denied 
			}
			return CommonUtils.getSuccessMessage(HttpStatusCodes.SUCCESS.getCode(), "Process completed successfully",dashBoardReport);
		}
		
		
		
		@RequestMapping(value = "/getPendingRechargeList",
				method = RequestMethod.POST,
				headers="Accept=application/xml, application/json")
		public ResponseMessageDTO getPendingRechargeList(@RequestBody ReportFilter reportFilter) throws LoginIDFormatException, ObjectNotSupportedException, ProcessFailedException, SessionDoesNotExistException {
			if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
				throw new SessionDoesNotExistException("User session does not exists");
			}
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String userId = user.getUsername();
			UserDTO userDTO=userMaintenance.getUser(userId);
			log.info("UserId:"+userDTO.getId());
			DashBoardReport dashBoardReport= null;
			if(userDTO.getUserRole().getRoleId()!=RoleEnum.Admin.getID()){
				reportFilter.setUserId(userDTO.getId());
			}
			dashBoardReport=transactionMaintenance.getPendingRechargeList(reportFilter);
			return CommonUtils.getSuccessMessage(HttpStatusCodes.AVAILABLE.getCode(), "Process completed successfully", dashBoardReport);
		}

		
		@RequestMapping(value = "/getApplicationtList",
				method = RequestMethod.POST,
				headers="Accept=application/xml, application/json")
	 public ResponseMessageDTO getapplicationtList(@RequestBody ReportFilter reportFilter) throws ObjectNotSupportedException, SessionDoesNotExistException, LoginIDFormatException {
		 log.info("Get Edistrict List >> getapplicationtList:");
		 
		 List<EdisttDTO> applicationtList=null;
		 DashBoardReport dashBoardReport= null;
		 dashBoardReport=new DashBoardReport();
		 UserDTO userDTO=new UserDTO();
		 reportFilter.setG2cId(userDTO.getEdisttId());
		 reportFilter.setTransstatus("Y");
		 applicationtList=disttMaintrnance.getApplicationtList(reportFilter);
		 dashBoardReport.setApplicationList(applicationtList);
		
		 return CommonUtils.getSuccessMessage(HttpStatusCodes.AVAILABLE.getCode(), "Edistrict Process completed successfully", dashBoardReport);
	 }

}
