package com.lei.service.transaction;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lei.dto.user.UserDTO;
import com.lei.dto.wallet.EdisttDTO;
import com.lei.dto.wallet.PaidServiceDTO;
import com.lei.dto.wallet.WalletDTO;
import com.lei.maintenance.transaction.IDisttMaintrnance;
import com.lei.maintenance.transaction.ITransactionMaintenance;
import com.lei.maintenance.user.IUserMaintenance;
import com.lei.utility.EDistricEncryptionControl;
import com.lei.utility.MessageUtils;
import com.lei.utility.PropertyUtility;
import com.lei.utility.constants.ApplicationConstants;
import com.lei.utility.constants.EmailEvent;
import com.lei.utility.constants.MessageKeyConstants;
import com.lei.utility.constants.PaidServices;
import com.lei.utility.email.SendMail;

@Slf4j
@RestController
@RequestMapping("/edistt")
public class EdisttServices {
	
	 @Autowired
	 ServletContext context; 
	 
	 @Autowired
	 IDisttMaintrnance disttMaintrnance;
	 
	 @Autowired
	 ITransactionMaintenance transactionMaintenance;
	 
	 @Autowired
	 IUserMaintenance userMaintenance;
	 
	 
	 @RequestMapping(value = "/paymentRequest", method = RequestMethod.GET)
	 public void paymentRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 String tokenValue=request.getParameter("xwcparm_DSPqStR");
		 log.info("payment Request : "+tokenValue);
		 if(tokenValue==null || tokenValue.equals("") || tokenValue.equals("null")){
				log.info("Invaild Request"); 
				request.setAttribute("message", "Invaild Request");
				context.getRequestDispatcher("/template/error.jsp").forward(request, response);
		
		 }else{
			 log.info("Going to redirect page >>");
			 request.setAttribute("action","http://nathap.in/tech/edistrictdecode.php");
			 
			 request.setAttribute("xwcparm_DSPqStR",tokenValue.replace(" ", "XXXYYYZZZ"));
			 log.info("Going to redirect page >> /template/redirect.jsp");
			 RequestDispatcher rd = context.getRequestDispatcher("/template/redirect.jsp");
			 rd.forward(request, response);
	 	 }
	 }
	 
	 
	 @RequestMapping(value = "/paymentRequestNext", method = RequestMethod.POST)
		public void paymentRequestNext(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("paymentRequestNext Request:"+request.getParameter("xwcparm_DSPqStR")); 
		EDistricEncryptionControl eDistricEncryptionControl=new EDistricEncryptionControl(); 
		String encodedString=request.getParameter("xwcparm_DSPqStR");
		String valStr=eDistricEncryptionControl.Validate(encodedString);
		
		if(valStr!=null){
			log.info("Invaild Request:: "+encodedString); 
			request.setAttribute("message", valStr);
			RequestDispatcher rd = context.getRequestDispatcher("/template/error.jsp");
			rd.forward(request, response);
		}else{
						EdisttDTO edisttDTO=eDistricEncryptionControl.decrypt(encodedString);
					    log.info("Edistrict user request:"+edisttDTO.getG2cId());
						request.setAttribute("encodedString", encodedString);
						request.setAttribute("vleId", edisttDTO.getG2cId());
						request.setAttribute("action1", "../edistt/checkEx");
						RequestDispatcher rd = context.getRequestDispatcher("/template/edisttAuth.jsp");
						log.info("paymentRequestNext --> going for  Authontication Page "+edisttDTO.getApplicationNo());
						rd.forward(request, response);
		}
	 }
	 
	 @RequestMapping(value = "/checkEx", method = RequestMethod.POST)
		public void checkEx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("checkEx --> edisttAuth");
		EDistricEncryptionControl eDistricEncryptionControl=new EDistricEncryptionControl(); 
		String encodedString=request.getParameter("encodedString");
		String password=request.getParameter("password");
		EdisttDTO edisttDTO=eDistricEncryptionControl.decrypt(encodedString);
		UserDTO userDTO=userMaintenance.getUserByEdistt(edisttDTO.getG2cId());
		if(userDTO==null){
			log.info("paymentRequestNext --> Invalid G2CID for Request Not Exist in our application"+edisttDTO.getG2cId());
			edisttDTO.setTransstatus("N");
			edisttDTO.setRes("RES"); 
			request.setAttribute("action", "http://nathap.in/tech/edistrictencode.php");
			request.setAttribute("action", "http://nathap.in/tech/edistrictencode.php");
			request.setAttribute("message", "Invalid G2CID for Request Not Exist in our application:"+edisttDTO.getG2cId());
			RequestDispatcher rd = context.getRequestDispatcher("/template/redirect.jsp");
			rd.forward(request, response);
		}else{
				if(!password.equals(userDTO.getStatus().getPassword())){
					        log.info("checkEx First Attempt Failed");
							edisttDTO.setTransstatus("N");
							edisttDTO.setRes("RES");
							log.info("checkEx --> Authenticaton process Failed !!"+edisttDTO.getG2cId());
							edisttDTO.setRemark("Authenticaton process Failed !!"+edisttDTO.getG2cId());
							disttMaintrnance.edistrictRequestSaved(edisttDTO);
							request.setAttribute("action", "http://nathap.in/tech/edistrictencode.php");
							request.setAttribute("xwcparm_DSPqStR",eDistricEncryptionControl.encrypt(edisttDTO) );
							request.setAttribute("message"," Authenticaton process Failed !!"+edisttDTO.getG2cId());
							
							RequestDispatcher rd = context.getRequestDispatcher("/template/redirect.jsp");
							log.info("Response Ciper Text:"+eDistricEncryptionControl.encrypt(edisttDTO));
							rd.forward(request, response);
				}else{
					log.info("Valid Request (Password Validation):-"+edisttDTO.getApplicationNo());
					EdisttDTO temp=disttMaintrnance.isExist(edisttDTO);
					if(temp!=null){
						log.info("Old Request : "+edisttDTO.getApplicationNo());
						request.setAttribute("action", "http://nathap.in/tech/edistrictencode.php");
						request.setAttribute("xwcparm_DSPqStR",eDistricEncryptionControl.encrypt(edisttDTO) );
						RequestDispatcher rd = context.getRequestDispatcher("/template/redirect.jsp");
						log.info("Response Ciper Text:"+eDistricEncryptionControl.encrypt(edisttDTO));
						rd.forward(request, response);
					}else{
							WalletDTO walletDTO=transactionMaintenance.getUserWallet(userDTO.getId());
							PaidServiceDTO paidServiceDTO=disttMaintrnance.getServiceDTO(PaidServices.Paidservice1.getId());
							if(walletDTO.getBalance() >= paidServiceDTO.getCharges()){
								log.info("Wallet Check and balance is available"+edisttDTO.getApplicationNo());
								edisttDTO.setTransstatus("Y");
								edisttDTO.setRes("RES");   
								edisttDTO=disttMaintrnance.edistrictRequestProcess(edisttDTO);
								request.setAttribute("message","Transaction Process completed");
								log.info("Request for Payment ::-Transaction Process completed and redirected to Edistt Site:"+edisttDTO.getApplicationNo());
								String emailBody = MessageUtils.getMessage(MessageKeyConstants.WALLETDEDUCTSUCCESS.getKey(), userDTO.getFirstName(),edisttDTO.getApplicationNo(),(walletDTO.getBalance()-paidServiceDTO.getCharges())+"",paidServiceDTO.getCharges()+"",edisttDTO.getTrnid()+"");
								SendMail.sendMail(EmailEvent.EDISTRICTSUCCESS.getEvent(), userDTO.getEmail(), null, emailBody, null);
							}else{
								log.info("Request for Payment Failed ::Wallet Amount Low  :"+walletDTO.getBalance());
								String emailBody = MessageUtils.getMessage(MessageKeyConstants.INSUFFICIENTBALANCE.getKey(), userDTO.getFirstName(),walletDTO.getBalance()+"");
								SendMail.sendMail(EmailEvent.INSUFFICIENTBALANCE.getEvent(), userDTO.getEmail(), null, emailBody, null);
								edisttDTO.setTransstatus("N");
								edisttDTO.setRes("RES"); 
								request.setAttribute("message","Request for Payment Failed ::Wallet Amount Low  :"+walletDTO.getBalance());
								edisttDTO.setRemark("Request for Payment process Failed ::Wallet Amount Low  :"+walletDTO.getBalance());
								disttMaintrnance.edistrictRequestSaved(edisttDTO);
							}
							log.info("Response Ciper Text:"+eDistricEncryptionControl.encrypt(edisttDTO));
							request.setAttribute("action", "http://nathap.in/tech/edistrictencode.php");
							request.setAttribute("xwcparm_DSPqStR",eDistricEncryptionControl.encrypt(edisttDTO) );
							RequestDispatcher rd = context.getRequestDispatcher("/template/redirect.jsp");
							log.info("Response Ciper Text:"+eDistricEncryptionControl.encrypt(edisttDTO));
							rd.forward(request, response);
							//response.sendRedirect(PropertyUtility.getValueString(ApplicationConstants.CONFIGURATIONSBUNDLE.getValue(), "ENCODE")+eDistricEncryptionControl.encrypt(edisttDTO));
						}
					}
				}
	 }
	 
	 @RequestMapping(value = "/paymentResponse", method = RequestMethod.POST)
	 public void paymentResponse(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 String encodedString=request.getParameter("xwcparm_DSPqStR");
		 log.info("paymentResponse ::-"+encodedString);
		 response.sendRedirect(PropertyUtility.getValueString(ApplicationConstants.CONFIGURATIONSBUNDLE.getValue(), "EDISTRIC_RESPONSE")+encodedString);

	 }
	 
	

}
