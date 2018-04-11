package com.lei.utility;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lei.dto.common.ResponseMessageDTO;
import com.lei.exception.common.ObjectNotSupportedException;
import com.lei.utility.constants.HttpStatusCodes;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
public class CommonUtils {
	public static String generatePassword(){
		String password = "";
		final Random rn = new Random();
		final String alphabet = "abcdefghijklmnopqrstuvwxyz";
		int number = 0;

		for (int i = 0; i < 3; i++) {
			int index = rn.nextInt() % 26;
			if (index < 0) {
				index = 0;
			}
			number = rn.nextInt() % 10;
			password += alphabet.substring(index, index + 1) + (number < 0 ? -1 * number : number);
		}
		return password;
	}

	public static Timestamp getCurrentTimestamp() {
		final Calendar cal = Calendar.getInstance();
		final java.sql.Timestamp ts = new java.sql.Timestamp(cal.getTimeInMillis());
		return ts;
	}
	public static Timestamp getFutureTimestamp(long addValue) {
		final Calendar cal = Calendar.getInstance();
		final java.sql.Timestamp ts = new java.sql.Timestamp(cal.getTimeInMillis()+addValue);
		return ts;
	}
	public static <T>T getObjectFromJSON(String json,Class<T> returnType) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false);
		return mapper.readValue(json, returnType);
	}

	@SuppressWarnings("unchecked")
	public static <T>T getObjectFromXML(String json,Class<T> returnType) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(returnType);
		Unmarshaller un = context.createUnmarshaller();
		InputStream is = new ByteArrayInputStream(json.getBytes());
		return (T) un.unmarshal(is);
	}
	public static String getRequestedURI(ServletRequest request){
		String contextPath = ((HttpServletRequest)request).getContextPath();
		String requestedURI = ((HttpServletRequest)request).getRequestURI();
		if(contextPath!=null && requestedURI!=null){
			if(contextPath!=null){
				if(requestedURI.startsWith(contextPath)){
					requestedURI = requestedURI.substring(contextPath.length(),requestedURI.length());
					if(requestedURI!=null && requestedURI.startsWith("/")){
						requestedURI = requestedURI.substring(1);
					}
					if(requestedURI!=null && requestedURI.endsWith("/")){
						requestedURI = requestedURI.substring(0,requestedURI.length()-1);
					}
				}
			}
		}
		return requestedURI;
	}
	public static String getUniqueToken() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}


	public static String getJsonFromObject(Object objectToSerialize){
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(objectToSerialize);
		} catch (Exception e) {
			System.out.println("Error Generated"+e);
			return null;
		}
	}


	public static String getXMLFromObject(Object objectToSerialize){
		try {
			ObjectMapper objectMapper = new ObjectMapper();

			System.out.println(objectMapper.writeValueAsString(objectToSerialize));

			Object json = objectMapper.readValue(
					objectMapper.writeValueAsString(objectToSerialize), Object.class);
			System.out.println(json);

			JAXBContext jaxbContext = JAXBContext.newInstance(objectToSerialize.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			StringWriter stringWriter = new StringWriter();
			jaxbMarshaller.marshal(objectToSerialize, stringWriter);
			return stringWriter.toString();
		} catch (Exception e) {
			System.out.println("Error Generated"+e);
			return null;
		}
	}
	
	public static HttpServletResponse setHeader(HttpServletResponse response,String headerKey,String headerValue){
		response.setHeader(headerKey, headerValue);
		return response;
	}
	
	public static void prepareErrorResponse(HttpServletRequest request,HttpServletResponse response,String code,String description,boolean isError) throws IOException{
		ResponseMessageDTO responseDto = new ResponseMessageDTO();
		responseDto.setError(isError);
		responseDto.setResponseCode(code);
		if(isError){
			responseDto.setErrorDiscription(description);
		}else{
			responseDto.setResponseMessage(description);
		}
		
		String requestContentType = request.getContentType();
		String responseContentType = response.getContentType();
		String contentType = (responseContentType==null)?requestContentType:responseContentType;
		String responseData = null;
		
		if(contentType!=null && contentType.toUpperCase().endsWith("XML")){
			responseData = CommonUtils.getXMLFromObject(responseDto);
		}else if(contentType!=null && contentType.toUpperCase().endsWith("JSON")){
			responseData = CommonUtils.getJsonFromObject(responseDto);
		}else{
			responseData = CommonUtils.getXMLFromObject(responseDto);
		}
		
		response.getOutputStream().write(responseData.getBytes());
		
	}
	public static void prepareErrorResponse(ServletRequest request,ServletResponse response,String code,String description,boolean isError) throws IOException{
		prepareErrorResponse((HttpServletRequest)request,(HttpServletResponse) response,code,description,isError);
	}
	
	public static ResponseMessageDTO getSuccessMessage(String responseMessage,Object data){
		ResponseMessageDTO response = new ResponseMessageDTO();
		response.setResponseCode(HttpStatusCodes.SUCCESS.getCode());
		response.setResponseMessage(responseMessage);
		response.setData(data);
		
		return response;
	}
	public static ResponseMessageDTO getSuccessMessage(String statusCode,String responseMessage,Object data){
		ResponseMessageDTO response = new ResponseMessageDTO();
		response.setResponseCode(statusCode);
		response.setResponseMessage(responseMessage);
		response.setData(data);
		
		return response;
	}
	public static BufferedInputStream getBufferedInputStream(String filePath) throws FileNotFoundException{
		FileInputStream file = new FileInputStream(new File(filePath));
		BufferedInputStream bis = new BufferedInputStream(file);
		return bis;
	}
	public static <T>T convertObject(Object inObject,Class<T> returnType) throws ObjectNotSupportedException {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(inObject, returnType);
	}
	
	public static long generateTruckTokenNo(){
		long trucktokenno=0;
		final Random rn = new Random();
		
		int number = 0;

		for (int i = 0; i < 1000000; i++) {
			
			number = rn.nextInt();
			trucktokenno = (trucktokenno + (number < 0 ? -1 * number : number));
		}
		return trucktokenno;
	}
}
