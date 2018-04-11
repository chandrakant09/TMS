 package com.lei.dto.wallet;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.lei.dto.user.MessageDTO;
import com.lei.dto.user.UserDTO;

@XmlRootElement(name = "DashBoardReport")
@JsonRootName(value = "DashBoardReport")
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso(WalletDTO.class)
public class DashBoardReport implements java.io.Serializable{
	
	
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	@XmlElement(name = "Wallets")
	@JsonProperty(value = "Wallets")
	private List<WalletDTO> wallets;
	
	@Getter
	@Setter
	@XmlElement(name = "User")
	@JsonProperty(value = "User")
	private UserDTO user;
	
    @Getter
	@Setter
	@XmlElement(name = "EdisttList")
	@JsonProperty(value = "EdisttList")
	private List<EdisttDTO> edisttList;
	

    @Getter
	@Setter
	@XmlElement(name = "ApplicationList")
	@JsonProperty(value = "ApplicationList")
	private List<EdisttDTO> applicationList;
	
	
	
	
	@Getter
	@Setter
	@XmlElement(name = "CashdetailList")
	@JsonProperty(value = "CashdetailList")
	private List<CashDTO> cashdetailList;
	
	
	@Getter
	@Setter
	@XmlElement(name = "UserList")
	@JsonProperty(value = "UserList")
	private List userList;
	
	@Getter
	@Setter
	@XmlElement(name = "CommonList")
	@JsonProperty(value = "CommonList")
	private List commonList;
	
	@Getter
	@Setter
	@XmlElement(name = "Transactions")
	@JsonProperty(value = "Transactions")
	private List<TransactionDTO> transactionDtos;
	
	@Getter
	@Setter
	@XmlElement(name = "Recharges")
	@JsonProperty(value = "Recharges")
	private List<RechargeDTO> rechargeDtos;
	
	@Getter
	@Setter
	@XmlElement(name = "Cheques")
	@JsonProperty(value = "Cheques")
	private List<ChequeDTO> chequeDtos;
	
	@Getter
	@Setter
	@XmlElement(name = "CashDeposit")
	@JsonProperty(value = "CashDeposit")
	private List<CashDTO> cashDeposit;
	
	@Getter
	@Setter
	@XmlElement(name = "DemandDraftDeposit")
	@JsonProperty(value = "DemandDraftDeposit")
	private List<DemandDraftDTO> demandDraftDeposit;
	
	@Getter
	@Setter
	@XmlElement(name = "RechargeAmount")
	@JsonProperty(value = "RechargeAmount")
	private double rechargeAmount=0.0;
	
	@Getter
	@Setter
	@XmlElement(name = "DebitAmount")
	@JsonProperty(value = "DebitAmount")
	private double debitAmount=0.0;
	
	@Getter
	@Setter
	@XmlElement(name = "CreditAmount")
	@JsonProperty(value = "CreditAmount")
	private double creditAmount=0.0;
	
	@Getter
	@Setter
	@XmlElement(name = "ChequeAmount")
	@JsonProperty(value = "ChequeAmount")
	private double chequeAmount=0.0;
	
	@Getter
	@Setter
	@XmlElement(name = "ChequeClrAmount")
	@JsonProperty(value = "ChequeClrAmount")
	private double chequeClrAmount=0.0;
	
	
	@Getter
	@Setter
	@XmlElement(name = "CashCollection")
	@JsonProperty(value = "CashCollection")
	private double cashCollection=0.0;
	
	@Getter
	@Setter
	@XmlElement(name = "DdCollection")
	@JsonProperty(value = "DdCollection")
	private double ddCollection=0.0;
	
	
	@Getter
	@Setter
	@XmlElement(name = "Earning")
	@JsonProperty(value = "Earning")
	private double earning=0.0;
	
	@Getter
	@Setter
	@XmlElement(name = "Messages")
	@JsonProperty(value = "Messages")
	private List<MessageDTO> messageDTOs;

	@Getter
	@Setter
	@XmlElement(name = "ActiveUser")
	@JsonProperty(value = "ActiveUser")
	private long activeUser;

	
	@Getter
	@Setter
	@XmlElement(name = "UserStatics")
	@JsonProperty(value = "UserStatics")
	private List<UserStaticsDTO> userStatics;
	
	@Getter
	@Setter
	@XmlElement(name = "DisttAmount")
	@JsonProperty(value = "DisttAmount")
	private List disttAmount;
	
	@Getter
	@Setter
	@XmlElement(name = "WalletAmount")
	@JsonProperty(value = "WalletAmount")
	private List walletAmount;

}
