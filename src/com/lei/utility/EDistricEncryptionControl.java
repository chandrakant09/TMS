package com.lei.utility;

import com.lei.dto.wallet.EdisttDTO;

public class EDistricEncryptionControl implements IEDistricEncryptionControl{
	
	//G2CID|VLEID|ApplicationNO|Servicecode|UniqueID|PARAM1|PARAM2|PARAM3
	public EdisttDTO decrypt(String cyperText){
		EdisttDTO edisttDTO=new EdisttDTO();
		String[] strarr=cyperText.split("\\|");
		edisttDTO.setG2cId(strarr[0]);
		edisttDTO.setVleId(strarr[1]);
		edisttDTO.setApplicationNo(strarr[2]);
		edisttDTO.setServicecode(strarr[3]);
		edisttDTO.setUniqueID(strarr[4]);
		edisttDTO.setParam1(strarr[5]);
		edisttDTO.setParam2(strarr[6]);
		edisttDTO.setParam3(strarr[7]);
		return edisttDTO; 
	}
	
	public String Validate(String cyperText){
		if(cyperText==null||cyperText.equals("") ||cyperText.equalsIgnoreCase("null")){
			return "NULL Parameter Invalid Request ";
		}
		String[] strarr=cyperText.split("\\|");
		
		if(strarr.length!=8){
			return "Requested Parameter is not valid";
		}
		
		if(strarr[2].equals("") || strarr[0].equals("") ||strarr[1].equals("")){
			return "Application ID / VLE ID/ G2CID can not be Empty";
		}
		
		return null; 
	}
	
	// ApplicationNO|txnid|transStatus|RES|VLEID|PARAM1|PARAM2|PARAM3
	public String encrypt(EdisttDTO edisttDTO){
		String encodedString=((edisttDTO.getApplicationNo()==null ?"":edisttDTO.getApplicationNo()) +"|"
							 +(edisttDTO.getTrnid()==null ?"":edisttDTO.getTrnid())+"|"
							 +(edisttDTO.getTransstatus()==null ?"":edisttDTO.getTransstatus())+"|"
							 +(edisttDTO.getRes()==null ?"":edisttDTO.getRes())+"|"
							 +(edisttDTO.getVleId()==null ?"":edisttDTO.getVleId())+"|" 
							 +(edisttDTO.getParam1()==null ?"":edisttDTO.getParam1())+"|"
							 +(edisttDTO.getParam2()==null ?"":edisttDTO.getParam2())+"|"
							 +(edisttDTO.getParam3()==null ?"":edisttDTO.getParam3())).trim();
		return encodedString; 
	}
	
}
