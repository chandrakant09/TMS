package com.lei.dto.wallet;

import lombok.Getter;
import lombok.Setter;


public class EdisttDTO {
	
	@Getter
	@Setter
	private long requestId;
	
	@Getter
	@Setter
	private String g2cId;
	
	@Getter
	@Setter
	private String vleId;
	
	@Getter
	@Setter
	private String applicationNo;
	

	
	@Getter
	@Setter
	private String opt1;
	
	@Getter
	@Setter
	private String opt2;
	
	@Getter
	@Setter
	private String opt3;
	

	
	
	
	@Getter
	@Setter
	private String transstatus;
	
	@Getter
	@Setter
	private String res;
	
	@Getter
	@Setter
	private String ropt1;
	
	@Getter
	@Setter
	private String ropt2;
	
	@Getter
	@Setter
	private String ropt3;
	
	@Getter
	@Setter
	private String status;
	
	@Getter
	@Setter
	private String servicecode;
	
	@Getter
	@Setter
	private String uniqueID;
	
	@Getter
	@Setter
	private String param1;
	
	@Getter
	@Setter
	private String param2;
	
	@Getter
	@Setter
	private String param3;

	@Getter
	@Setter
	private String trnid;
	
	@Getter
	@Setter
	private String remark;
	
	@Getter
	@Setter
	private double charges;
	
	@Setter
	@Getter
	private String quotaid;
	
	@Getter
	@Setter
	private int reqappcount;

	@Override
	public String toString() {
		return "EdisttDTO [requestId=" + requestId + ", g2cId=" + g2cId
				+ ", vleId=" + vleId + ", applicationNo=" + applicationNo
				+ ", opt1=" + opt1 + ", opt2=" + opt2 + ", opt3=" + opt3
				+ ", transstatus=" + transstatus + ", res=" + res + ", ropt1="
				+ ropt1 + ", ropt2=" + ropt2 + ", ropt3=" + ropt3 + ", status="
				+ status + ", servicecode=" + servicecode + ", uniqueID="
				+ uniqueID + ", param1=" + param1 + ", param2=" + param2
				+ ", param3=" + param3 + ", trnid=" + trnid + ", remark="
				+ remark + ", charges=" + charges + ", quotaid=" + quotaid
				+ ", reqappcount=" + reqappcount + "]";
	}
	
	
	
	

}
