package com.lei.utility;

import com.lei.dto.wallet.EdisttDTO;

public class RationCardEDistricEncryptionControl implements IEDistricEncryptionControl{
	
	//G2CID|VLEID|QUOTAID|SERVICECODE|UNIQUEID|REQAPPCOUNT|PARAM1|PARAM2
		@Override
		public EdisttDTO decrypt(String cyperText){
			EdisttDTO edisttDTO=new EdisttDTO();
			String[] strarr=cyperText.split("\\|");
			edisttDTO.setG2cId(strarr[0]);
			edisttDTO.setVleId(strarr[1]);
			edisttDTO.setQuotaid(strarr[2]);
			edisttDTO.setServicecode(strarr[3]);
			edisttDTO.setUniqueID(strarr[4]);
		    edisttDTO.setReqappcount(Integer.parseInt(strarr[5]));
			
		    edisttDTO.setParam1(strarr[6]);
			edisttDTO.setParam2(strarr[7]);
			return edisttDTO; 
		}
		
		@Override
		public String Validate(String cyperText){
			if(cyperText==null||cyperText.equals("") ||cyperText.equalsIgnoreCase("null")){
				return "NULL Parameter Invalid Request ";
			}
			String[] strarr=cyperText.split("\\|");
			
			if(strarr.length!=8){
				return "Requested Parameter is not valid";
			}
			
			if(strarr[2].equals("") || strarr[0].equals("") ||strarr[1].equals("")){
				return "QUOTAID / VLE ID/ G2CID can not be Empty";
			}
			
			return null; 
		}
		
		
		
		//QUOTAID|TXNID|TRANS-STATUS|RES|VLEID|SERVICECODE|REQAPPCOUNT|PARAM1
		@Override
		public String encrypt(EdisttDTO edisttDTO){
			String encodedString=((edisttDTO.getQuotaid()==null ?"":edisttDTO.getQuotaid()) +"|"
								 +(edisttDTO.getTrnid()==null ?"":edisttDTO.getTrnid())+"|"
								 +(edisttDTO.getTransstatus()==null ?"":edisttDTO.getTransstatus())+"|"
								 +(edisttDTO.getRes()==null ?"":edisttDTO.getRes())+"|"
								 +(edisttDTO.getVleId()==null ?"":edisttDTO.getVleId())+"|" 
								 +(edisttDTO.getServicecode()==null ?"":edisttDTO.getServicecode())+"|"
								 +(edisttDTO.getReqappcount()==0 ?"0":edisttDTO.getReqappcount())+"|"
								 +(edisttDTO.getParam1()==null ?"":edisttDTO.getParam1())).trim();
			return encodedString;
		}

}