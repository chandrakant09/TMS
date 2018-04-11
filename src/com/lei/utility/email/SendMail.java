package com.lei.utility.email;

import java.io.IOException;

import javax.mail.MessagingException;

import com.lei.dto.email.EmailDTO;
import com.lei.dto.email.base.IBaseEmail;
import com.lei.exception.common.EmailException;
import com.lei.exception.common.InvalidFileException;
import com.lei.exception.common.InvalidKeyException;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
public final class SendMail extends SendMailAbstract{
	static{
		try {
			initiate();
		} catch (InvalidFileException | InvalidKeyException e) {
			e.printStackTrace();
		}
	}
	
	public SendMail() throws MessagingException {
		super();
	}

	public void sendMail(IBaseEmail baseData) throws MessagingException, IOException {
		setFrom(baseData.getFrom());
		setRecepiants(baseData.getTo(),baseData.getCc(),baseData.getBcc());
		setSubject(baseData.getSubject());
		prepare(baseData.getBody(),baseData.getAttachments(),baseData.getAttachmentsName(),baseData.getSupportAttachment());
		send();
	}
	public static void sendMail(String event,String to,String cc,String body,String[] attachments) throws EmailException{
		sendMail(event, to, cc, body, attachments,null);
	}
	public static void sendMail(String event,String to,String cc,String body,String[] attachments,String[] attachmentsName) throws EmailException{
		EmailDTO mailDto = new EmailDTO(event, to, cc, body, attachments,attachmentsName);
		try{
			new SendMail().sendMail(mailDto);
		}catch(MessagingException|IOException e){
			throw new EmailException("Exception generated while sending mail: "+e);
		}
	}
}
