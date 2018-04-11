package com.lei.dto.email;

import com.lei.dto.email.base.AbstractBaseEmail;

/**
 * @author Shrikant Kushwaha
 */
public class EmailDTO extends AbstractBaseEmail{
	public EmailDTO(String event,String to,String cc,String body,String[] attachments,String[] attachmentsName) {
		super(event,to,cc,body,attachments,attachmentsName);
	}
}
