package com.lei.utility;

import java.util.Locale;

import org.springframework.context.ApplicationContext;

import com.lei.utility.serverstart.ApplicationContextProvider;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
public class MessageUtils {
	private static ApplicationContext appContext;
	static{
		appContext = ApplicationContextProvider.getApplicationContext();
	}
	
	public static String getMessage(String messageKey,String... arguments){
		String response = null;
		if(appContext==null){
			
		}else{
			response = appContext.getMessage(messageKey, arguments, Locale.US);
		}
		return response;
	}
	
	
}
