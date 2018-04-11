package com.lei.service.transaction;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lei.dto.user.UserDTO;
import com.lei.maintenance.transaction.ITransactionMaintenance;
import com.lei.maintenance.user.IUserMaintenance;
import com.lei.utility.MessageUtils;
import com.lei.utility.PropertyUtility;
import com.lei.utility.constants.ApplicationConstants;
import com.lei.utility.constants.EmailEvent;
import com.lei.utility.constants.MessageKeyConstants;
import com.lei.utility.email.SendMail;



@Slf4j
@RestController
@RequestMapping("/pym")
public class PayUMoneyServices {
	
	 @Autowired
	 ServletContext context; 
	 
	 @Autowired
	 ITransactionMaintenance transactionMaintenance;
	 
	 @Autowired
	 IUserMaintenance userMaintenance;
	 
	
	@RequestMapping(value = "/success", method = RequestMethod.POST)
	public void successTransaction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		        log.info("Recharge Transaction Id :"+request.getParameter("txnid"));
				long userId=0,transactionId=0;
		        String emailId=null,firstname=null;
				double amount=0.0;
		
				
				if(request.getParameter("txnid") !=null){
					transactionId = Long.parseLong((String)request.getParameter("txnid"));
				}
				
				if(request.getParameter("email") !=null && request.getParameter("email") !="" ){
					emailId=request.getParameter("email");
				}
				
				if(request.getParameter("amount") !=null && request.getParameter("amount") !="" ){
					amount = Double.parseDouble(request.getParameter("amount"));
				}
				
				if(request.getParameter("firstname") !=null && request.getParameter("firstname") !="" ){
					firstname=request.getParameter("firstname");
				}
				
				log.info("Transaction Id:"+transactionId);
				log.info("User Id:"+userId);
				log.info("Amount:"+amount);
				double trans=transactionMaintenance.transactionSuccess(transactionId);
				if(trans !=0.0){
					log.info("Transaction (Success) Updated Successfully");
					String emailBody = MessageUtils.getMessage(MessageKeyConstants.RECHARGESUCCESS.getKey(), firstname,amount+"",transactionId+"");
					SendMail.sendMail(EmailEvent.RECHARGESUCCESS.getEvent(),emailId, null, emailBody, null);
				}else{
					log.info("Transaction (Incomplete) ");
					String emailBody = MessageUtils.getMessage(MessageKeyConstants.INCOMPLETETRANSACTION.getKey(), firstname,amount+"",transactionId+"");
					SendMail.sendMail(EmailEvent.INCOMPLETETRANSACTION.getEvent(), emailId, null, emailBody, null);
				
				}
				log.info("Transaction (Success) Updated Successfully");
				response.sendRedirect(PropertyUtility.getValueString(ApplicationConstants.CONFIGURATIONSBUNDLE.getValue(), "PAYMENTGATEWAYSUCCESS_MESSAGE"));	
		}
	
	

	
	@RequestMapping(value = "/failure", method = RequestMethod.POST)
	public void failure(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.info("Failure Transaction Id :"+request.getParameter("txnid"));
		
			long userId = 0, transactionId = 0, udf2 = 0;
			String emailId = null, firstname = null;
			double amount = 0.0;
	
			if (request.getParameter("txnid") != null) {
				transactionId = Long.parseLong((String) request
						.getParameter("txnid"));
			}
	
			if (request.getParameter("email") != null
					&& request.getParameter("email") != "") {
				emailId = request.getParameter("email");
			}
	
			if (request.getParameter("amount") != null
					&& request.getParameter("amount") != "") {
				amount = Double.parseDouble(request.getParameter("amount"));
			}
	
			if (request.getParameter("firstname") != null
					&& request.getParameter("firstname") != "") {
				firstname = request.getParameter("firstname");
			}
			log.info("Failure Transaction Id :"+transactionId+ ":" +amount +" : " + userId +" :" +udf2);
			transactionMaintenance.transactionFailure(transactionId);
			log.info("Transaction (Failed) Updated Successfully");
			
			//Mailed to client
			String emailBody = MessageUtils.getMessage(MessageKeyConstants.RECHARGEFailed.getKey(), firstname,amount+"",transactionId+"");
			SendMail.sendMail(EmailEvent.RECHARGEFAILED.getEvent(),emailId, null, emailBody, null);
			response.sendRedirect(PropertyUtility.getValueString(ApplicationConstants.CONFIGURATIONSBUNDLE.getValue(), "PAYMENTGATEWAYFAILURE_MESSAGE"));
			}
	
	
	
}
