package com.lei.utility;

public class EdistrictEncryptionFactory{
	
	public static IEDistricEncryptionControl getInstance(String encodedURL){
		String[] strarr=encodedURL.split("\\|");
		if(strarr[3].equalsIgnoreCase("IS")){
			return new RationCardEDistricEncryptionControl();
		}else{
			return new EDistricEncryptionControl();
		}
	}

}
