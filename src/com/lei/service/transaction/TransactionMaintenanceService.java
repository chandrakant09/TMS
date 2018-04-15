package com.lei.service.transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lei.dto.common.ResponseMessageDTO;
import com.lei.dto.master.ReportFilter;
import com.lei.dto.user.TruckDTO;
import com.lei.dto.user.UserDTO;
import com.lei.dto.wallet.CashDTO;
import com.lei.dto.wallet.ChequeDTO;
import com.lei.dto.wallet.DashBoardReport;
import com.lei.dto.wallet.DemandDraftDTO;
import com.lei.dto.wallet.RechargeDTO;
import com.lei.dto.wallet.TransactionUpdateDTO;
import com.lei.dto.wallet.TruckRechargeDTO;
import com.lei.dto.wallet.WalletDTO;
import com.lei.exception.common.EmailException;
import com.lei.exception.common.InvalidFileException;
import com.lei.exception.common.InvalidKeyException;
import com.lei.exception.common.ObjectNotSupportedException;
import com.lei.exception.common.ProcessFailedException;
import com.lei.exception.user.LoginIDFormatException;
import com.lei.exception.user.SessionDoesNotExistException;
import com.lei.maintenance.transaction.ITransactionMaintenance;
import com.lei.maintenance.user.IUserMaintenance;
import com.lei.utility.CommonUtils;
import com.lei.utility.MessageUtils;
import com.lei.utility.PropertyUtility;
import com.lei.utility.constants.ApplicationConstants;
import com.lei.utility.constants.EmailEvent;
import com.lei.utility.constants.HttpStatusCodes;
import com.lei.utility.constants.MessageKeyConstants;
import com.lei.utility.constants.PaymentStatus;
import com.lei.utility.constants.RoleEnum;
import com.lei.utility.constants.TransactionStatus;
import com.lei.utility.email.SendMail;

@Slf4j
@RestController
@RequestMapping("/transactionmaintenance")
public class TransactionMaintenanceService {
	
	@Autowired
	ITransactionMaintenance transactionMaintenance;
	
	@Autowired
	IUserMaintenance userMaintenance;
	
	 @Autowired
	 ServletContext context; 

	@RequestMapping(value = "/walletrecharge",
			method = RequestMethod.POST,
			headers="Accept=application/xml, application/json")
	public void walletRecharge(@RequestParam("amount") double amount, @RequestParam("emailId") String emailId, HttpServletRequest request, HttpServletResponse response) throws LoginIDFormatException, ObjectNotSupportedException, ProcessFailedException, ServletException, IOException, InvalidFileException, InvalidKeyException {
		try{
			log.info("walletrecharge amount :"+amount);
			//HttpSession session = request.getSession(true);
			//String emailId =(String) session.getAttribute("userEmail");
			if(emailId==null){
				log.info("Email Id Null in Session ");
				if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
					User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					emailId = user.getUsername();
					log.info("Email Id Got from  in used Context "+emailId);
				}
			}
			
			if(emailId!=null){
				UserDTO userDTO=userMaintenance.getUser(emailId);
				log.info("UserId:"+userDTO.getId());
				RechargeDTO rechargeDTO=new RechargeDTO();
				rechargeDTO.setAmount(amount-(amount*2/100));
				rechargeDTO.setUserId(userDTO.getId());
				rechargeDTO.setStatus(TransactionStatus.Progress.getStatus());
				long transactionId= transactionMaintenance.rechargeWallet(rechargeDTO);
				log.info("Transaction Id :"+transactionId);
				request.setAttribute("key",PropertyUtility.getValueString(ApplicationConstants.CONFIGURATIONSBUNDLE.getValue(), "PAYUMONEY_KEY"));
				request.setAttribute("txnid",transactionId);
				request.setAttribute("amount",amount);
				request.setAttribute("firstname",userDTO.getFirstName() + " " + userDTO.getLastName());
				request.setAttribute("email",userDTO.getEmail());
				request.setAttribute("phone",userDTO.getMobile());
				request.setAttribute("productinfo",PropertyUtility.getValueString(ApplicationConstants.CONFIGURATIONSBUNDLE.getValue(), "PRODUCT_INFO"));
				request.setAttribute("surl",PropertyUtility.getValueString(ApplicationConstants.CONFIGURATIONSBUNDLE.getValue(), "PAYMENTGATEWAYSUCCESS"));
				request.setAttribute("furl",PropertyUtility.getValueString(ApplicationConstants.CONFIGURATIONSBUNDLE.getValue(), "PAYMENTGATEWAYFAILURE"));
				request.setAttribute("udf1",userDTO.getId());
				request.setAttribute("udf2",userDTO.getEdisttId());
				request.setAttribute("service_provider","payu_paisa");
				RequestDispatcher rd = context.getRequestDispatcher("/template/paymentgateway.jsp");
				log.info("Redirected for the payumoney process");
				rd.forward(request, response);
			}else{
				request.setAttribute("message", "Session Expired please login again");
				request.getRequestDispatcher("/template/error.jsp").forward(request, response);
			}
		}catch(Exception e){
			log.info(e.getMessage());
			request.setAttribute("message", "Error During recharge Try after some time");
			request.getRequestDispatcher("/template/error.jsp").forward(request, response);
		}
	}
	
	
	@RequestMapping(value = "/truckwalletrecharge",
			method = RequestMethod.POST,
			headers="Accept=application/xml, application/json")
	public void truckwalletrecharge(@RequestParam("amount") double amount, @RequestParam("truckid") long truckid) throws LoginIDFormatException, ObjectNotSupportedException, ProcessFailedException, ServletException, IOException, InvalidFileException, InvalidKeyException {
		
			log.info("truckwalletrecharge amount :"+amount);
			//HttpSession session = request.getSession(true);
			//String emailId =(String) session.getAttribute("userEmail");
			/*if(truckid==null){
				log.info("Email Id Null in Session ");
				if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
					User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					truckid = user.getUsername();
					log.info("Email Id Got from  in used Context "+truckid);
				}
			}*/
			
				TruckDTO truckDTO = userMaintenance.geTruckId(truckid);
				log.info("TruckID:"+truckDTO.getTruckid());
				TruckRechargeDTO truckRechargeDTO = new TruckRechargeDTO();
				truckRechargeDTO.setTruckid(truckid);
				truckRechargeDTO.setAmount(amount);
				
				
				/*RechargeDTO rechargeDTO=new RechargeDTO();
				rechargeDTO.setAmount(amount-(amount*2/100));*/
				
				truckRechargeDTO.setStatus(TransactionStatus.Progress.getStatus());
			//	long transactionId= transactionMaintenance.rechargeWallet(rechargeDTO);
				
				long transactionId= transactionMaintenance.truckRechargeWallet(truckRechargeDTO);
				ReportFilter reportFilter=new ReportFilter();
				transactionMaintenance.getUsersWallet(reportFilter);
				
				
				log.info("Transaction Id :"+transactionId);
				
	}
	
	
	
	
	
	@RequestMapping(value = "/walletData",
			method = RequestMethod.POST,
			headers="Accept=application/xml, application/json")
	public ResponseMessageDTO getWallet() throws LoginIDFormatException, ObjectNotSupportedException, ProcessFailedException, SessionDoesNotExistException {
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
			throw new SessionDoesNotExistException("User session does not exists");
		}
		User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userId = user.getUsername();
		UserDTO userDTO=userMaintenance.getUser(userId);
		WalletDTO walletDTO= transactionMaintenance.getUserWallet(userDTO.getId());
		return CommonUtils.getSuccessMessage(HttpStatusCodes.AVAILABLE.getCode(), "Process completed successfully", walletDTO);
	}
	
	@RequestMapping(value = "/userswalletData",
			method = RequestMethod.POST,
			headers="Accept=application/xml, application/json")
	public ResponseMessageDTO getUsersWallet() throws LoginIDFormatException, ObjectNotSupportedException, ProcessFailedException, SessionDoesNotExistException {
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
			throw new SessionDoesNotExistException("User session does not exists");
		}
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userId = user.getUsername();
		UserDTO userDTO=userMaintenance.getUser(userId);
		
		log.info("UserId:"+userDTO.getId());
		ReportFilter reportFilter=new ReportFilter();
		reportFilter.setDistrict(userDTO.getDistt());
		DashBoardReport dashBoardReport= transactionMaintenance.getUsersWallet(reportFilter);
		return CommonUtils.getSuccessMessage(HttpStatusCodes.AVAILABLE.getCode(), "Process completed successfully", dashBoardReport);
	}
	
	
	
	@RequestMapping(value = "/getTransactionList",
			method = RequestMethod.POST,
			headers="Accept=application/xml, application/json")
	public ResponseMessageDTO getTransactionList(@RequestBody ReportFilter reportFilter) throws LoginIDFormatException, ObjectNotSupportedException, ProcessFailedException, SessionDoesNotExistException {
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
			throw new SessionDoesNotExistException("User session does not exists");
		}
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userId = user.getUsername();
		UserDTO userDTO=userMaintenance.getUser(userId);
		
		log.info("UserId:"+userDTO.getId());
		
		DashBoardReport dashBoardReport= null;
		if(userDTO.getUserRole().getRoleId()==RoleEnum.Admin.getID()){
			reportFilter.setDistrict(userDTO.getDistt());
			dashBoardReport=transactionMaintenance.getTransactionList(reportFilter);
		}else{
			reportFilter.setUserId(userDTO.getId());
			dashBoardReport=transactionMaintenance.getTransactionList(reportFilter);
		}
		return CommonUtils.getSuccessMessage(HttpStatusCodes.AVAILABLE.getCode(), "Process completed successfully", dashBoardReport);
	}
	
	@RequestMapping(value = "/getRechargeList",
			method = RequestMethod.POST,
			headers="Accept=application/xml, application/json")
	public ResponseMessageDTO getRechargeList(@RequestBody ReportFilter reportFilter) throws LoginIDFormatException, ObjectNotSupportedException, ProcessFailedException, SessionDoesNotExistException {
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
		}else{
			reportFilter.setDistrict(userDTO.getDistt());
		}
		dashBoardReport=transactionMaintenance.getRechargeList(reportFilter);
		return CommonUtils.getSuccessMessage(HttpStatusCodes.AVAILABLE.getCode(), "Process completed successfully", dashBoardReport);
	}
	

	
	
	@RequestMapping(value = "/savechequeDetails",
			method = RequestMethod.POST,
			headers="Accept=application/xml, application/json")
	public ResponseMessageDTO savechequeDetails(@RequestBody ChequeDTO chequeDTO) throws LoginIDFormatException, ObjectNotSupportedException, ProcessFailedException, SessionDoesNotExistException, EmailException {
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
			throw new SessionDoesNotExistException("User session does not exists");
		}
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userId = user.getUsername(); 
		UserDTO userDTO=userMaintenance.getUser(userId);
		
		log.info("UserId:"+userDTO.getId());
		
		if(userDTO.getUserRole().getRoleId()==RoleEnum.Admin.getID()){
			chequeDTO.setRechargeBy(userDTO.getId());
			long transactionId=0;
			
			RechargeDTO rechargeDTO=new RechargeDTO();
			rechargeDTO.setAmount(chequeDTO.getAmount());
			rechargeDTO.setUserId(chequeDTO.getUserId());
			if(chequeDTO.getStatus().equals(PaymentStatus.Approved.getStatus())){
				rechargeDTO.setStatus(TransactionStatus.Success.getStatus());
			}else{
				rechargeDTO.setStatus(TransactionStatus.Progress.getStatus());
			}
			transactionId = transactionMaintenance.rechargeWallet(rechargeDTO);
			chequeDTO.setTransactionId(transactionId);
			transactionMaintenance.updateChequeDetail(chequeDTO);
			//Mailed to the super Admin
			if(chequeDTO.getStatus().equals(PaymentStatus.Approved.getStatus())){
				String emailBody = MessageUtils.getMessage(MessageKeyConstants.COLLECTIONMAILED.getKey(), transactionId+"","Cash",chequeDTO.getAmount()+"",chequeDTO.getUserId()+"",userDTO.getFirstName()+userDTO.getLastName());
				SendMail.sendMail(EmailEvent.COLLECTIONMAILED.getEvent(), "shri110@gmail.com", null, emailBody, null);
			}	
		
		}
		return CommonUtils.getSuccessMessage(HttpStatusCodes.SAVESUCCESSFULLY.getCode(), "Process completed successfully", "");
	}
	
	@RequestMapping(value = "/savedemandDraftDetails",
			method = RequestMethod.POST,
			headers="Accept=application/xml, application/json")
	public ResponseMessageDTO savedemandDraftDetails(@RequestBody DemandDraftDTO demandDraftDTO) throws LoginIDFormatException, ObjectNotSupportedException, ProcessFailedException, SessionDoesNotExistException, EmailException {
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
			throw new SessionDoesNotExistException("User session does not exists");
		}
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userId = user.getUsername(); 
		UserDTO userDTO=userMaintenance.getUser(userId);
		long transactionId=0;
		log.info("UserId:"+userDTO.getId());
		
		if(userDTO.getUserRole().getRoleId()==RoleEnum.Admin.getID()){
			demandDraftDTO.setRechargeBy(userDTO.getId());
			RechargeDTO rechargeDTO=new RechargeDTO();
			rechargeDTO.setAmount(demandDraftDTO.getAmount());
			rechargeDTO.setUserId(demandDraftDTO.getUserId());
			
			if(demandDraftDTO.getStatus().equals(PaymentStatus.Approved.getStatus())){
				rechargeDTO.setStatus(TransactionStatus.Success.getStatus());
			}else{
				rechargeDTO.setStatus(TransactionStatus.Progress.getStatus());
			}
			transactionId=transactionMaintenance.rechargeWallet(rechargeDTO);
			demandDraftDTO.setTransactionId(transactionId);
			transactionMaintenance.updateDemandDraftDetail(demandDraftDTO);
			//Mailed to the super Admin
			if(demandDraftDTO.getStatus().equals(PaymentStatus.Approved.getStatus())){
				String emailBody = MessageUtils.getMessage(MessageKeyConstants.COLLECTIONMAILED.getKey(), transactionId+"","Cash",demandDraftDTO.getAmount()+"",demandDraftDTO.getUserId()+"",userDTO.getFirstName()+userDTO.getLastName());
				SendMail.sendMail(EmailEvent.COLLECTIONMAILED.getEvent(), "shri110@gmail.com", null, emailBody, null);
			}
		
			
		}
		return CommonUtils.getSuccessMessage(HttpStatusCodes.SAVESUCCESSFULLY.getCode(), "Process completed successfully", "");
	}
	
	@RequestMapping(value = "/savecashDetails",
			method = RequestMethod.POST,
			headers="Accept=application/xml, application/json")
	public ResponseMessageDTO savecashDetails(@RequestBody CashDTO cashDto) throws LoginIDFormatException, ObjectNotSupportedException, ProcessFailedException, SessionDoesNotExistException, EmailException {
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
			throw new SessionDoesNotExistException("User session does not exists");
		}
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userId = user.getUsername(); 
		UserDTO userDTO=userMaintenance.getUser(userId);
		
		log.info("UserId:"+userDTO.getId());
		long transactionId=0;
		if(userDTO.getUserRole().getRoleId()==RoleEnum.Admin.getID()){
			cashDto.setRechargeBy(userDTO.getId());
			RechargeDTO rechargeDTO=new RechargeDTO();
			rechargeDTO.setAmount(cashDto.getAmount());
			rechargeDTO.setUserId(cashDto.getUserId());
			rechargeDTO.setStatus(TransactionStatus.Success.getStatus());
			transactionId=transactionMaintenance.rechargeWallet(rechargeDTO);
			cashDto.setTransactionId(transactionId);
			cashDto.setStatus(rechargeDTO.getStatus());
			transactionMaintenance.updateCashDetail(cashDto);
			//Mailed to the super Admin
			String emailBody = MessageUtils.getMessage(MessageKeyConstants.COLLECTIONMAILED.getKey(), transactionId+"","Cash",cashDto.getAmount()+"",cashDto.getUserId()+"",userDTO.getFirstName()+userDTO.getLastName());
			SendMail.sendMail(EmailEvent.COLLECTIONMAILED.getEvent(), userDTO.getEmail(), null, emailBody, null);
			
		}
		return CommonUtils.getSuccessMessage(HttpStatusCodes.SAVESUCCESSFULLY.getCode(), "Process completed successfully", "");
	}
	
	@RequestMapping(value = "/trasactionStatusUpdate",
			method = RequestMethod.POST,
			headers="Accept=application/xml, application/json")
	public ResponseMessageDTO trasactionStatusUpdate(@RequestBody TransactionUpdateDTO transactionUpdateDTO) throws LoginIDFormatException, ObjectNotSupportedException, ProcessFailedException, SessionDoesNotExistException, EmailException {
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
			throw new SessionDoesNotExistException("User session does not exists");
		}
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userId = user.getUsername(); 
		UserDTO userDTO=userMaintenance.getUser(userId);
		
		
		if(userDTO.getUserRole().getRoleId()==RoleEnum.Admin.getID()){
			// Updated By details
			UserDTO elientDTO=userMaintenance.findById(transactionUpdateDTO.getId());
			log.info("UserId: transactionId"+userDTO.getId()+">Upadated"+transactionUpdateDTO.getTransactionId());
			double trans=0.0;
			if(TransactionStatus.Success.getStatus().equalsIgnoreCase(transactionUpdateDTO.getStatus())){
				trans=transactionMaintenance.transactionSuccess(transactionUpdateDTO.getTransactionId());
			}else if(TransactionStatus.Failed.getStatus().equalsIgnoreCase(transactionUpdateDTO.getStatus())){
				if(transactionMaintenance.transactionFailure(transactionUpdateDTO.getTransactionId())){
					trans=1.0;
				}
				
			}
			
			if(trans !=0.0){
				transactionUpdateDTO.setUserId(userDTO.getId());
				transactionMaintenance.saveTransactionStatusUpdate(transactionUpdateDTO);
				log.info("Transaction (Success) Updated Successfully");
				String emailBody = MessageUtils.getMessage(MessageKeyConstants.RECHARGESUCCESS.getKey(), elientDTO.getFirstName(),trans+"",transactionUpdateDTO.getTransactionId()+"");
				SendMail.sendMail(EmailEvent.RECHARGESUCCESS.getEvent(), elientDTO.getEmail(), null, emailBody, null);
			}else{
				log.info("Transaction (Incomplete) ");
				String emailBody = MessageUtils.getMessage(MessageKeyConstants.INCOMPLETETRANSACTION.getKey(), elientDTO.getFirstName(),trans+"",transactionUpdateDTO.getTransactionId()+"");
				SendMail.sendMail(EmailEvent.INCOMPLETETRANSACTION.getEvent(), elientDTO.getEmail(), null, emailBody, null);
			}
			log.info("Transaction (Success) Updated Successfully");
		}
		return CommonUtils.getSuccessMessage(HttpStatusCodes.SAVESUCCESSFULLY.getCode(), "Process completed successfully", "");
	}
	

	@RequestMapping(value = "/getDisttAmount",
			method = RequestMethod.POST,
			headers="Accept=application/xml, application/json")
	public ResponseMessageDTO getDisttAmount(@RequestBody ReportFilter reportFilter) throws Exception {
	    reportFilter.setDistrict("MAHOBA,CHITRAKOOT,HAMIRPUR");
		reportFilter.setTransactionType("DEBIT");
		DashBoardReport dashBoardReport = new DashBoardReport();
		dashBoardReport= transactionMaintenance.getDisttAmount(reportFilter);
		return CommonUtils.getSuccessMessage(HttpStatusCodes.AVAILABLE.getCode(), "Process completed successfully", dashBoardReport);
	}
	
	@RequestMapping(value = "/getAreaList",
			method = RequestMethod.GET,
			headers="Accept=application/xml, application/json")

   public ResponseMessageDTO getAreaList() throws Exception {
		ReportFilter reportFilter=new ReportFilter();
		reportFilter.setDistrict("MAHOBA,CHITRAKOOT,HAMIRPUR");
		DashBoardReport dashBoardReport = new DashBoardReport();
		dashBoardReport= transactionMaintenance.getAreaList(reportFilter);
		return CommonUtils.getSuccessMessage(HttpStatusCodes.AVAILABLE.getCode(), "Process completed successfully", dashBoardReport);
	}
		
	
	@RequestMapping(value = "/cegdataList", method = RequestMethod.POST, headers = "Accept=application/xml, application/json")
	public ResponseMessageDTO cegdataList() throws Exception {
		ReportFilter reportFilter = new ReportFilter();
		reportFilter.setDistrict("MAHOBA,CHITRAKOOT,HAMIRPUR");
		DashBoardReport dashBoardReport = new DashBoardReport();
		dashBoardReport = transactionMaintenance.getAreaList(reportFilter);
		return CommonUtils.getSuccessMessage(
				HttpStatusCodes.AVAILABLE.getCode(),
				"Process completed successfully", dashBoardReport);
	}
	

}
