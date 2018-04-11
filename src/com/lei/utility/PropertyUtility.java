package com.lei.utility;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.lei.exception.common.InvalidFileException;
import com.lei.exception.common.InvalidKeyException;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
public class PropertyUtility {
	private static final Map<String, Properties> propertiesData = new HashMap<String, Properties>();
	
	public static boolean loadPropertyFile(String... filePath) throws InvalidFileException{
		InputStream inputStream;
		Properties prop = null;
		try{
			if(filePath!=null && filePath.length>0){
				for(String path : filePath){
					if(path!=null){
						inputStream = PropertyUtility.class.getClassLoader().getResourceAsStream(path);
						prop = new Properties();
						
						prop.load(inputStream);
						propertiesData.put(path, prop);
					}
				}
			}else{
				throw new InvalidFileException("No property files provided to load.");
			}
		}catch(Exception e){
			throw new InvalidFileException(e+" -- "+e.getMessage());
		}
		return true;
	}
	
	public static String getValueString(String fileName,String key) throws InvalidFileException,InvalidKeyException{
		if(propertiesData.containsKey(fileName)){
			if(propertiesData.get(fileName).containsKey(key)){
				return propertiesData.get(fileName).getProperty(key);
			}else{
				throw new InvalidKeyException("Supplied key not found in loaded property.."+key);
			}
		}else{
			throw new InvalidFileException("Property Files not Initialized."+fileName);
		}
	}
	public static Boolean getValueBoolean(String fileName,String key) throws InvalidFileException,InvalidKeyException{
		return Boolean.parseBoolean(getValueString(fileName, key));
	}
	public static Integer getValueInteger(String fileName,String key) throws InvalidFileException,InvalidKeyException{
		return Integer.parseInt(getValueString(fileName, key));
	}
	public static Float getValueFloat(String fileName,String key) throws InvalidFileException,InvalidKeyException{
		return Float.parseFloat(getValueString(fileName, key));
	}
	public static Long getValueLong(String fileName,String key) throws InvalidFileException,InvalidKeyException{
		return Long.parseLong(getValueString(fileName, key));
	}
	public static Double getValueDouble(String fileName,String key) throws InvalidFileException,InvalidKeyException{
		return Double.parseDouble(getValueString(fileName, key));
	}
	
	
	public static String getValueString(String fileName,String key,String defaultValue){
		try{
			return getValueString(fileName, key);
		}catch(InvalidFileException|InvalidKeyException e){
			return defaultValue;
		}
	}
	public static Boolean getValueBoolean(String fileName,String key,boolean defaultValue){
		try{
			return Boolean.parseBoolean(getValueString(fileName, key));
		}catch(InvalidFileException|InvalidKeyException e){
			return defaultValue;
		}
	}
	public static Integer getValueInteger(String fileName,String key,int defaultValue){
		try{
			return Integer.parseInt(getValueString(fileName, key));
		}catch(InvalidFileException|InvalidKeyException e){
			return defaultValue;
		}
	}
	public static Float getValueFloat(String fileName,String key,float defaultValue){
		try{
			return Float.parseFloat(getValueString(fileName, key));
		}catch(InvalidFileException|InvalidKeyException e){
			return defaultValue;
		}
	}
	public static Long getValueLong(String fileName,String key,long defaultValue){
		try{
			return Long.parseLong(getValueString(fileName, key));
		}catch(InvalidFileException|InvalidKeyException e){
			return defaultValue;
		}
	}
	public static Double getValueDouble(String fileName,String key,double defaultValue){
		try{
			return Double.parseDouble(getValueString(fileName, key));
		}catch(InvalidFileException|InvalidKeyException e){
			return defaultValue;
		}
	}
	
	public static Set<Object> getAllKeys(String fileName){
		if(propertiesData.containsKey(fileName)){
			Set<Object> allkeys = propertiesData.get(fileName).keySet();
			return allkeys;
		}
		return null;
	}
	public static Set<Entry<Object, Object>> getEntrySet(String fileName){
		if(propertiesData.containsKey(fileName)){
			Set<Entry<Object, Object>> allData = propertiesData.get(fileName).entrySet();
			return allData;
		}
		return null;
	}
}
