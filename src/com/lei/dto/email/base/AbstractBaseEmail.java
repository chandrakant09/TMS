package com.lei.dto.email.base;

import java.util.LinkedList;
import java.util.List;

import com.lei.utility.PropertyUtility;
import com.lei.utility.constants.ApplicationConstants;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
public class AbstractBaseEmail implements IBaseEmail{
	private String temp_to;
	private String temp_cc;
	
	private String event;
	private String from;
	private String[] to;
	private String[] cc;
	private String[] bcc;
	private String subject;
	private String body;
	private String header;
	private String footer;
	private String[] attachments;
	private String[] attachmentsName;
	private boolean supportAttachment;
	public AbstractBaseEmail(String event,String to,String cc,String body,String[] attachments,String[] attachmentsName) {
		this.event = event;
		this.body=body;
		this.attachments=attachments;
		this.attachmentsName = attachmentsName;
		
		temp_to = to;
		temp_cc = cc;
		addDefaultConfiguration();
	}
	
	private void addDefaultConfiguration(){
		from = PropertyUtility.getValueString(ApplicationConstants.EMAILBUNDLE.getValue(), event+"_FROM",null);
		subject = PropertyUtility.getValueString(ApplicationConstants.EMAILBUNDLE.getValue(), event+"_SUBJECT",null);
		supportAttachment = PropertyUtility.getValueBoolean(ApplicationConstants.EMAILBUNDLE.getValue(), event+"_SUPPORTATTACHMENT",false);
		
		String TO = PropertyUtility.getValueString(ApplicationConstants.EMAILBUNDLE.getValue(), event+"_TO",null);
		String CC = PropertyUtility.getValueString(ApplicationConstants.EMAILBUNDLE.getValue(), event+"_CC",null);
		String BCC = PropertyUtility.getValueString(ApplicationConstants.EMAILBUNDLE.getValue(), event+"_BCC",null);
		
		String HEADER = PropertyUtility.getValueString(ApplicationConstants.EMAILBUNDLE.getValue(), event+"_HEADER",null);
		String FOOTER = PropertyUtility.getValueString(ApplicationConstants.EMAILBUNDLE.getValue(), event+"_FOOTER",null);
		
		
		this.to=parse(temp_to,TO);
		this.cc=parse(temp_cc,CC);
		this.bcc=parse(BCC);
		
		this.body = prepareBody(body, HEADER, FOOTER);
	}
	
	private final String prepareBody(String inBody,String header,String footer){
		if(inBody!=null){
			if(header!=null){
				inBody = header + inBody;
			}
			if(footer!=null){
				inBody = inBody + footer;
			}
		}else{
			if(header!=null){
				inBody = header;
			}
			if(footer!=null){
				if(inBody!=null){
					inBody = inBody + footer;
				}else{
					inBody = footer;
				}
			}
		}
		
		return inBody;
	}
	
	private String[] parse(String... emailsCommaSeperated){
		String[] mailID = null;
		List<String> mailIds = null;
		if(emailsCommaSeperated!=null){
			mailIds = new LinkedList<String>();
			for(String value : emailsCommaSeperated){
				if(value!=null){
					mailID = value.split(",");
					if(mailID!=null){
						for(String currentMailID : mailID){
							if(currentMailID!=null && currentMailID.length()>0){
								mailIds.add(currentMailID);
							}
						}
					}
				}
			}
		}
		if(mailIds!=null){
			return mailIds.toArray(new String[mailIds.size()]);
		}
		return null;
	}
	
	final public String getEvent(){
		return event;
	}
	final public String getFrom() {
		return from;
	}
	final public String[] getTo() {
		return to;
	}
	final public String[] getCc() {
		return cc;
	}
	final public String[] getBcc() {
		return bcc;
	}
	final public String getSubject() {
		return subject;
	}
	final public String getBody() {
		return body;
	}
	final public String getHeader() {
		return header;
	}
	final public String getFooter() {
		return footer;
	}
	final public String[] getAttachments() {
		return attachments;
	}
	final public String[] getAttachmentsName() {
		return attachmentsName;
	}
	final public boolean getSupportAttachment() {
		return supportAttachment;
	}
}
