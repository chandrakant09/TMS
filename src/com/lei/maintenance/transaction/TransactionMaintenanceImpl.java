package com.lei.maintenance.transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lei.dao.base.DaoManager;
import com.lei.dao.transaction.ITransactionDao;
import com.lei.dao.user.IMessageDAO;
import com.lei.dao.user.IUserDao;
import com.lei.dto.master.ReportFilter;
import com.lei.dto.wallet.CashDTO;
import com.lei.dto.wallet.ChequeDTO;
import com.lei.dto.wallet.DashBoardReport;
import com.lei.dto.wallet.DemandDraftDTO;
import com.lei.dto.wallet.RechargeDTO;
import com.lei.dto.wallet.TransactionUpdateDTO;
import com.lei.dto.wallet.TruckRechargeDTO;
import com.lei.dto.wallet.UserStaticsDTO;
import com.lei.dto.wallet.WalletDTO;
import com.lei.exception.common.ObjectNotSupportedException;
import com.lei.exception.common.ProcessFailedException;
import com.lei.utility.constants.PaymentStatus;
import com.lei.utility.constants.TransactionStatus;
import com.lei.utility.constants.TransactionTypeEnum;



@Service
public class TransactionMaintenanceImpl implements ITransactionMaintenance  {

	@Override
	public long rechargeWallet(RechargeDTO rechargeDTO) throws ProcessFailedException, ObjectNotSupportedException {
		ITransactionDao dao = DaoManager.TRABSACTIONDAO.getDao(ITransactionDao.class);
		return dao.walletRechargeProcess(rechargeDTO);
	}
	
	@Override
	public long truckRechargeWallet(TruckRechargeDTO truckRechargeDTO) throws ProcessFailedException, ObjectNotSupportedException {
		ITransactionDao dao = DaoManager.TRABSACTIONDAO.getDao(ITransactionDao.class);
		return dao.truckWalletRechargeProcess(truckRechargeDTO);
	}
	
	@Override
	public double transactionSuccess(long transactionId) throws ProcessFailedException, ObjectNotSupportedException {
		ITransactionDao dao = DaoManager.TRABSACTIONDAO.getDao(ITransactionDao.class);
		return dao.transactionSuccess(transactionId);
	}
	
	@Override
	public boolean transactionFailure(long transactionId) throws ProcessFailedException, ObjectNotSupportedException {
		ITransactionDao dao = DaoManager.TRABSACTIONDAO.getDao(ITransactionDao.class);
		return dao.transactionFailure(transactionId);
	}
	
	
	
	@Override
	public WalletDTO getUserWallet(long userId) throws ProcessFailedException, ObjectNotSupportedException {
		ITransactionDao dao = DaoManager.TRABSACTIONDAO.getDao(ITransactionDao.class);
		WalletDTO walletDTO=dao.getUserWalletByUserId(userId);
		return walletDTO;
		
	}
	
	@Override
	public DashBoardReport getUsersWallet(ReportFilter reportFilter) throws ProcessFailedException, ObjectNotSupportedException {
		ITransactionDao dao = DaoManager.TRABSACTIONDAO.getDao(ITransactionDao.class);
		IUserDao udao = DaoManager.USER.getDao(IUserDao.class);
		DashBoardReport dashBoardReport=new DashBoardReport();
		dashBoardReport.setUserList(udao.getAllUserList(new ReportFilter()));
		dashBoardReport.setWallets(dao.getUsersWallet(reportFilter));
		return dashBoardReport;
		
	}
	
	@Override
	public DashBoardReport getChequesList() throws ObjectNotSupportedException {
		ITransactionDao dao = DaoManager.TRABSACTIONDAO.getDao(ITransactionDao.class);
		DashBoardReport dashBoardReport=new DashBoardReport();
		dashBoardReport.setChequeDtos(dao.getChequeDetails());
		return dashBoardReport;
	}
	
	@Override
	public DashBoardReport getChequesList(long userId)
			throws ObjectNotSupportedException {
		ITransactionDao dao = DaoManager.TRABSACTIONDAO.getDao(ITransactionDao.class);
		DashBoardReport dashBoardReport=new DashBoardReport();
		dashBoardReport.setChequeDtos(dao.getChequeDetailByUserId(userId));
		return dashBoardReport;
	}
	
	@Override
	public DashBoardReport getCashDepositList(ReportFilter reportFilter) throws ObjectNotSupportedException {
		ITransactionDao dao = DaoManager.TRABSACTIONDAO.getDao(ITransactionDao.class);
		DashBoardReport dashBoardReport=new DashBoardReport();
		dashBoardReport.setCashDeposit(dao.getCashDetails(reportFilter));
		return dashBoardReport;
	}
	
	
	
	@Override
	public DashBoardReport getDemandDraftDepositList(ReportFilter reportFilter) throws ObjectNotSupportedException {
		ITransactionDao dao = DaoManager.TRABSACTIONDAO.getDao(ITransactionDao.class);
		DashBoardReport dashBoardReport=new DashBoardReport();
		dashBoardReport.setDemandDraftDeposit(dao.getDemandDraftDetails(reportFilter));
		return dashBoardReport;
	}
	
	
	
	@Override
	public DashBoardReport getTransactionList(ReportFilter reportFilter)
			throws ObjectNotSupportedException {
		ITransactionDao dao = DaoManager.TRABSACTIONDAO.getDao(ITransactionDao.class);
		IUserDao udao = DaoManager.USER.getDao(IUserDao.class);
		DashBoardReport dashBoardReport=new DashBoardReport();
		dashBoardReport.setTransactionDtos(dao.getTransactionsHist(reportFilter));
		dashBoardReport.setUserList(udao.getAllUserList(new ReportFilter()));
		return dashBoardReport;
	}
	
	
	
	@Override
	public DashBoardReport getRechargeList(ReportFilter filter) throws ObjectNotSupportedException {
		ITransactionDao dao = DaoManager.TRABSACTIONDAO.getDao(ITransactionDao.class);
		DashBoardReport dashBoardReport=new DashBoardReport();
		dashBoardReport.setCommonList(dao.getRechargeHist(filter));
		return dashBoardReport;
	}
	
	@Override
	public DashBoardReport getPendingRechargeList(ReportFilter filter) throws ObjectNotSupportedException {
		ITransactionDao dao = DaoManager.TRABSACTIONDAO.getDao(ITransactionDao.class);
		IUserDao udao = DaoManager.USER.getDao(IUserDao.class);
		DashBoardReport dashBoardReport=new DashBoardReport();
		dashBoardReport.setRechargeDtos(dao.getRechargeList(filter));
		dashBoardReport.setUserList(udao.getAllUserList(new ReportFilter()));
		return dashBoardReport;
	}
	
	

	@Override
	public boolean updateChequeDetail(ChequeDTO chequeDTO)
			throws ProcessFailedException, ObjectNotSupportedException {
		ITransactionDao dao = DaoManager.TRABSACTIONDAO.getDao(ITransactionDao.class);
		return dao.updateChequeDetail(chequeDTO);
	}
	
	@Override
	public boolean updateCashDetail(CashDTO cashDTO)
			throws ProcessFailedException, ObjectNotSupportedException {
		ITransactionDao dao = DaoManager.TRABSACTIONDAO.getDao(ITransactionDao.class);
		return dao.updateCashDetails(cashDTO);
	}
	
	@Override
	public boolean updateDemandDraftDetail(DemandDraftDTO demandDraftDTO)
			throws ProcessFailedException, ObjectNotSupportedException {
		ITransactionDao dao = DaoManager.TRABSACTIONDAO.getDao(ITransactionDao.class);
		return dao.updateDemandDraftDetails(demandDraftDTO);
	}
	
	
	@Override
	public DashBoardReport getDashBoardData(ReportFilter reportFilter) throws ProcessFailedException, ObjectNotSupportedException {
		ITransactionDao dao = DaoManager.TRABSACTIONDAO.getDao(ITransactionDao.class);
		IUserDao udao = DaoManager.USER.getDao(IUserDao.class);
		DashBoardReport dashBoardReport=new DashBoardReport();
		
		//dashBoardReport.setWallets(dao.getUsersWallet(reportFilter));
		//dashBoardReport.setTransactionDtos(dao.getTransactionsHist(reportFilter));
		//dashBoardReport.setChequeDtos(dao.getChequeDetails());
		
		List users=udao.getAllUserList(reportFilter);
		
		reportFilter.setStatus(TransactionStatus.Success.getStatus());
		dashBoardReport.setCashCollection(dao.getCashCollection(reportFilter));
		dashBoardReport.setDdCollection(dao.getDDCollection(reportFilter));
		reportFilter.setTransactionType(TransactionTypeEnum.CREDIT.getValue());
		dashBoardReport.setCreditAmount(dao.getTransactionAmount(reportFilter));
		reportFilter.setTransactionType(TransactionTypeEnum.DEBIT.getValue());
		dashBoardReport.setDebitAmount(dao.getTransactionAmount(reportFilter));
		dashBoardReport.setRechargeAmount(dao.getCollectionAmount(reportFilter));
		reportFilter.setStatus(PaymentStatus.Approved.getStatus());
		dashBoardReport.setChequeAmount(dao.getChequeAmount(reportFilter));
		reportFilter.setStatus(PaymentStatus.Approved.getStatus());
		dashBoardReport.setChequeClrAmount(dao.getChequeAmount(reportFilter));
		dashBoardReport.setEarning(dashBoardReport.getDebitAmount()*6/20);
		dashBoardReport.setActiveUser(dao.getActiveUser(reportFilter.getDistrict()));
		dashBoardReport.setUserStatics(getUserAreaIsNewCount(users,reportFilter.getDistrict()));
		
		
		return dashBoardReport;
	}
	
	
	@Override
	public DashBoardReport getIndividualDashBoardData(ReportFilter reportFilter) throws ProcessFailedException, ObjectNotSupportedException {
		ITransactionDao dao = DaoManager.TRABSACTIONDAO.getDao(ITransactionDao.class);
		IMessageDAO mdao = DaoManager.MESSAGEDAO.getDao(IMessageDAO.class);
		IUserDao udao = DaoManager.USER.getDao(IUserDao.class);
		DashBoardReport dashBoardReport=new DashBoardReport();
		
		/*dashBoardReport.setChequeAmount(dao.getChequeAmount(PaymentStatus.Approved.getStatus(),userId));
		dashBoardReport.setChequeAmount(dao.getChequeAmount(PaymentStatus.Pending_For_Approval.getStatus(),userId));
		dashBoardReport.setChequeAmount(dao.getChequeAmount(PaymentStatus.Rejected.getStatus(),userId));*/
		dashBoardReport.setMessageDTOs(mdao.getActiveMessage());
		dashBoardReport.setDebitAmount(dao.getTransactionAmount(TransactionStatus.Success.getStatus(),TransactionTypeEnum.DEBIT.getValue(),reportFilter.getUserId()));
		dashBoardReport.setCreditAmount(dao.getTransactionAmount(TransactionStatus.Success.getStatus(),TransactionTypeEnum.CREDIT.getValue(),reportFilter.getUserId()));
		dashBoardReport.setRechargeAmount(dao.getCollectionAmount(TransactionStatus.Success.getStatus(),reportFilter.getUserId()));
		/*dashBoardReport.setChequeClrAmount(dao.getChequeAmount(PaymentStatus.Pending_For_Approval.getStatus()));*/
		//Add the remaining data like recharge /cash/demanddraft
		
		return dashBoardReport;
		
	}
	

	@Override
	public DashBoardReport getUserAutocompleteData(ReportFilter reportFilter) throws ObjectNotSupportedException {
		IUserDao udao = DaoManager.USER.getDao(IUserDao.class);
		DashBoardReport dashBoardReport=new DashBoardReport();
		List completeList=new ArrayList<String>();
		List ulist= udao.getAllUserList(reportFilter);
		Iterator it = ulist.iterator();
		while(it.hasNext()){
			Object[] x=(Object[]) it.next();
			String temp=null;
				for (Object obj:x){
					if(temp==null){
						temp=obj+"";
					}else{
						temp+="~"+obj;
					}
					
				}
				completeList.add(temp);
		}
		
		dashBoardReport.setUserList(completeList);
		return dashBoardReport;
	}

	@Override
	public void saveTransactionStatusUpdate(
			TransactionUpdateDTO transactionUpdateDTO) throws ProcessFailedException, ObjectNotSupportedException {
		ITransactionDao dao = DaoManager.TRABSACTIONDAO.getDao(ITransactionDao.class);
		dao.saveTransactionStatusUpdate(transactionUpdateDTO);
	}

	
	
	
	
	
	
	
	@Override
	public DashBoardReport getCashDetailstList(ReportFilter filter) throws ObjectNotSupportedException {
		ITransactionDao dao = DaoManager.TRABSACTIONDAO.getDao(ITransactionDao.class);
		IUserDao udao = DaoManager.USER.getDao(IUserDao.class);
		DashBoardReport dashBoardReport=new DashBoardReport();
		dashBoardReport.setCashdetailList(dao.getCashDetailstList(filter));
		dashBoardReport.setUserList(udao.getAllUserList(filter));
		
		return dashBoardReport;
	}

	@Override
	public DashBoardReport getEdistWorkStatus(ReportFilter filter) throws ObjectNotSupportedException {
		ITransactionDao dao = DaoManager.TRABSACTIONDAO.getDao(ITransactionDao.class);
		DashBoardReport dashBoardReport=new DashBoardReport();
		dashBoardReport.setUserList(dao.getEdistWorkStatus(filter));
		return dashBoardReport;
	}
	
	
	
	private int getUserCount(List users,String area, String newUser,String distt)  {
		int count=0; 
	    Iterator it = users.iterator();
		while(it.hasNext()){
			Object[] obj=(Object[]) it.next();
			if(area.equals(obj[5]) && newUser.equals(obj[6]) && distt.equals(obj[7]))
			count++;
		}
		return count;
	}
	
	private List<UserStaticsDTO> getUserAreaIsNewCount(List users,String disttlist)  {
		List<UserStaticsDTO> data=new ArrayList<UserStaticsDTO>();
		UserStaticsDTO userStatics=null;
		String[] disttarr=disttlist.split(",");
		for(String distt: disttarr){
			userStatics=new UserStaticsDTO();
			userStatics.setDistt(distt);
			userStatics.setNewRural(getUserCount(users,"RURAL", "Yes",distt));
			userStatics.setOldRural(getUserCount(users,"RURAL", "No",distt));
			userStatics.setNewUrban(getUserCount(users,"URBAN", "Yes",distt));
			userStatics.setOldUrban(getUserCount(users,"URBAN", "No",distt));
			data.add(userStatics);
		}
		return data;
	}
	
	
	@Override
	public DashBoardReport getAreaList(ReportFilter reportFilter) throws ProcessFailedException, ObjectNotSupportedException {
		IUserDao udao = DaoManager.USER.getDao(IUserDao.class);
		
		DashBoardReport dashBoardReport = new DashBoardReport();
		ITransactionDao dao = DaoManager.TRABSACTIONDAO.getDao(ITransactionDao.class);
		ITransactionDao tdao = DaoManager.TRABSACTIONDAO.getDao(ITransactionDao.class);
	    List users=udao.getAllUserList(reportFilter);
	    List<UserStaticsDTO> uslist=getUserAreaIsNewCount(users,reportFilter.getDistrict());
	    for(UserStaticsDTO u: uslist){
	    	u.setActiveUser(dao.getActiveUser(u.getDistt()));
	    }
	    dashBoardReport.setUserStatics(uslist);
		return dashBoardReport;
		
	}
	
	
	@Override
	public DashBoardReport getDisttAmount(ReportFilter reportFilter)
			throws Exception {
		ITransactionDao tdao = DaoManager.TRABSACTIONDAO.getDao(ITransactionDao.class);
        DashBoardReport dashBoardReport = new DashBoardReport();
	   	dashBoardReport.setDisttAmount(tdao.getDisttAmount(reportFilter));
	   	dashBoardReport.setWalletAmount(tdao.getwalletAmount(reportFilter));
		return dashBoardReport;
		
	}
	
	
	
	
	
	
	
	
}
