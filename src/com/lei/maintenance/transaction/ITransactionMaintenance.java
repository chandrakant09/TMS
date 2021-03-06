package com.lei.maintenance.transaction;

import com.lei.dto.master.ReportFilter;
import com.lei.dto.wallet.CashDTO;
import com.lei.dto.wallet.ChequeDTO;
import com.lei.dto.wallet.DashBoardReport;
import com.lei.dto.wallet.DemandDraftDTO;
import com.lei.dto.wallet.RechargeDTO;
import com.lei.dto.wallet.TransactionUpdateDTO;
import com.lei.dto.wallet.TruckRechargeDTO;
import com.lei.dto.wallet.WalletDTO;
import com.lei.exception.common.ObjectNotSupportedException;
import com.lei.exception.common.ProcessFailedException;

public interface ITransactionMaintenance {

	public long rechargeWallet(RechargeDTO rechargeDTO) throws ProcessFailedException, ObjectNotSupportedException ;
	
	public WalletDTO getUserWallet(long userId) throws ProcessFailedException, ObjectNotSupportedException ;
	
	public boolean updateChequeDetail(ChequeDTO chequeDTO) throws ProcessFailedException, ObjectNotSupportedException;
	
	
	
	public DashBoardReport getTransactionList(ReportFilter reportFilter) throws  ObjectNotSupportedException;
	
	public DashBoardReport getChequesList(long userId) throws  ObjectNotSupportedException;
	
	public DashBoardReport getChequesList() throws  ObjectNotSupportedException;
	
	DashBoardReport getUsersWallet(ReportFilter reportFilter)throws ProcessFailedException, ObjectNotSupportedException;

	
	
	boolean updateCashDetail(CashDTO cashDTO) throws ProcessFailedException, ObjectNotSupportedException;

	boolean updateDemandDraftDetail(DemandDraftDTO demansdDraftDTO) throws ProcessFailedException, ObjectNotSupportedException;

	DashBoardReport getDashBoardData(ReportFilter reportFilter) throws ProcessFailedException,ObjectNotSupportedException;

	DashBoardReport getRechargeList(ReportFilter filter)throws ObjectNotSupportedException;
	
	double transactionSuccess(long transactionId) throws ProcessFailedException, ObjectNotSupportedException;

	boolean transactionFailure(long transactionId) throws ProcessFailedException, ObjectNotSupportedException;

	public DashBoardReport getUserAutocompleteData(ReportFilter reportFilter) throws ObjectNotSupportedException;

	DashBoardReport getDemandDraftDepositList(ReportFilter reportFilter) throws ObjectNotSupportedException;

	DashBoardReport getCashDepositList(ReportFilter reportFilter) throws ObjectNotSupportedException;

	public void saveTransactionStatusUpdate(TransactionUpdateDTO transactionUpdateDTO) throws ProcessFailedException, ObjectNotSupportedException;

	DashBoardReport getCashDetailstList(ReportFilter filter)	throws ObjectNotSupportedException;

	DashBoardReport getEdistWorkStatus(ReportFilter filter) 	throws ObjectNotSupportedException;

	DashBoardReport getIndividualDashBoardData(ReportFilter reportFilter)	throws ProcessFailedException, ObjectNotSupportedException;

	DashBoardReport getAreaList(ReportFilter reportFilter) throws ProcessFailedException, ObjectNotSupportedException;

	DashBoardReport getDisttAmount(ReportFilter reportFilter) throws Exception;

	DashBoardReport getPendingRechargeList(ReportFilter filter)	throws ObjectNotSupportedException;

	long truckRechargeWallet(TruckRechargeDTO truckRechargeDTO) throws ProcessFailedException, ObjectNotSupportedException;
			

	
	
	
	

	
}
