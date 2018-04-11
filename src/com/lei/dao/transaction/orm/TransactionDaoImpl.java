package com.lei.dao.transaction.orm;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lei.dao.base.orm.HibernatePersistenceManager;
import com.lei.dao.transaction.ITransactionDao;
import com.lei.domain.transaction.CashDomain;
import com.lei.domain.transaction.ChequeDetailDomain;
import com.lei.domain.transaction.DemandDraftDomain;
import com.lei.domain.transaction.EdisttDomain;
import com.lei.domain.transaction.PaidServices;
import com.lei.domain.transaction.TransactionDetailDomain;
import com.lei.domain.transaction.TransactionUpdate;
import com.lei.domain.wallet.IndividualWalletDomain;
import com.lei.domain.wallet.RechargeWalletDomain;
import com.lei.dto.master.ReportFilter;
import com.lei.dto.wallet.CashDTO;
import com.lei.dto.wallet.ChequeDTO;
import com.lei.dto.wallet.DemandDraftDTO;
import com.lei.dto.wallet.EdisttDTO;
import com.lei.dto.wallet.PaidServiceDTO;
import com.lei.dto.wallet.RechargeDTO;
import com.lei.dto.wallet.TransactionDTO;
import com.lei.dto.wallet.TransactionUpdateDTO;
import com.lei.dto.wallet.WalletDTO;
import com.lei.exception.common.ObjectNotSupportedException;
import com.lei.exception.common.ProcessFailedException;
import com.lei.utility.CommonUtils;
import com.lei.utility.constants.TransactionStatus;
import com.lei.utility.constants.TransactionTypeEnum;

@Slf4j
@Repository("transactionDao")
public class TransactionDaoImpl implements ITransactionDao {
	
	HibernatePersistenceManager  hibernatePersistenceManager=null;
	
	
	public TransactionDaoImpl() {
		hibernatePersistenceManager = new HibernatePersistenceManager();
	}
	
	public WalletDTO getUserWallet(long walletId) throws ObjectNotSupportedException {
		hibernatePersistenceManager.beginTransaction();
		IndividualWalletDomain individualWalletDomain = hibernatePersistenceManager.getPersistentObject(IndividualWalletDomain.class,walletId);
		WalletDTO walletDTO=CommonUtils.convertObject(individualWalletDomain,WalletDTO.class);
		return walletDTO;
	}
	
	public WalletDTO getUserWalletByUserId(long userId) throws ObjectNotSupportedException {
		IndividualWalletDomain individualWalletDomain = null;
		hibernatePersistenceManager.beginTransaction();
		Criteria user = hibernatePersistenceManager.createCriteria(IndividualWalletDomain.class);
		user.add(Restrictions.eq("userId", userId));
		List<IndividualWalletDomain> result = user.list();
		if(result != null && result.size()>0){
			individualWalletDomain = result.get(0);
		}
		WalletDTO walletDTO=CommonUtils.convertObject(individualWalletDomain,WalletDTO.class);
		return walletDTO;
	}
	
	@Override
	public List<WalletDTO> getUsersWallet(ReportFilter filter) throws ObjectNotSupportedException {
		List<WalletDTO> wallets=new LinkedList<WalletDTO>();
		hibernatePersistenceManager.beginTransaction();
		/*Criteria user = hibernatePersistenceManager.createCriteria(IndividualWalletDomain.class);
		List<IndividualWalletDomain> result = user.list();
		for(IndividualWalletDomain domain:result){
			wallets.add(CommonUtils.convertObject(domain,WalletDTO.class));
		}*/
		Query query = hibernatePersistenceManager.createQuery("SELECT wallet.userId, wallet.balance, wallet.lastupdated from IndividualWalletDomain as wallet, UserDomain as user where user.id= wallet.userId and user.distt like '%"+(filter.getDistrict()==null?"":filter.getDistrict())+"%'");
		List result = query.list();
		WalletDTO walletDTO=null;
		Iterator it = result.iterator();
		while(it.hasNext()){
			Object[] obj=(Object[]) it.next();
			walletDTO=new WalletDTO();
			walletDTO.setUserId((Long)obj[0]);
			walletDTO.setBalance((Double)obj[1]);
			walletDTO.setLastupdated((Date)obj[2]);
			wallets.add(walletDTO);
		}
		return wallets;
	}
	
	public WalletDTO getRechargeAmount(long rechargeId) throws ObjectNotSupportedException {
		hibernatePersistenceManager.beginTransaction();
		RechargeWalletDomain rechargeWalletDomain = hibernatePersistenceManager.getPersistentObject(RechargeWalletDomain.class,rechargeId);
		WalletDTO walletDTO = CommonUtils.convertObject(rechargeWalletDomain, WalletDTO.class);
		return walletDTO;
	}
	
	public TransactionDTO getTrasaction(long transactionId) throws ObjectNotSupportedException {
		hibernatePersistenceManager.beginTransaction();
		TransactionDetailDomain transactionDetailDomain = hibernatePersistenceManager.getPersistentObject(TransactionDetailDomain.class,transactionId);
		TransactionDTO transactionDTO = CommonUtils.convertObject(transactionDetailDomain, TransactionDTO.class);
		
		return transactionDTO;
	}
	
	
	private TransactionDetailDomain getTrasactionDomain(long transactionId) throws ObjectNotSupportedException {
		hibernatePersistenceManager.beginTransaction();
		TransactionDetailDomain transactionDetailDomain = hibernatePersistenceManager.getPersistentObject(TransactionDetailDomain.class,transactionId);
		return transactionDetailDomain;
	}
	
	private IndividualWalletDomain geIndividualWalletDomain(long userId) throws ObjectNotSupportedException {
		IndividualWalletDomain individualWalletDomain = null;
		hibernatePersistenceManager.beginTransaction();
		Criteria wallets = hibernatePersistenceManager.createCriteria(IndividualWalletDomain.class);
		wallets.add(Restrictions.eq("userId", userId));
		List<IndividualWalletDomain> result = wallets.list();
		if(result != null && result.size()>0){
			individualWalletDomain = result.get(0);
		}
		
		return individualWalletDomain;
	}
	
	public RechargeWalletDomain getRechargeDomain(long transactionId) throws ObjectNotSupportedException {
		hibernatePersistenceManager.beginTransaction();
		RechargeWalletDomain rechargeWalletDomain = null;
		Criteria rwallets = hibernatePersistenceManager.createCriteria(RechargeWalletDomain.class);
		rwallets.add(Restrictions.eq("transactionId", transactionId));
		List<RechargeWalletDomain> result = rwallets.list();
		if(result != null && result.size()>0){
			rechargeWalletDomain = result.get(0);
		}
		
		return rechargeWalletDomain;
	}
	
	
	public boolean updateUserWallet(WalletDTO walletDTO) throws ProcessFailedException, ObjectNotSupportedException{
		
		IndividualWalletDomain individualWalletDomain = CommonUtils.convertObject(walletDTO, IndividualWalletDomain.class);
		boolean result = false;
		try {
			hibernatePersistenceManager.beginTransaction();
			hibernatePersistenceManager.saveOrUpdate(individualWalletDomain);
			result = true;
			hibernatePersistenceManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			hibernatePersistenceManager.rollback();
			throw new ProcessFailedException("Unable to save the individualWalletDomain wallet");
		}
		return result;
	}
	
	public boolean updateTransactionDetail(TransactionDTO transactionDTO) throws ProcessFailedException, ObjectNotSupportedException{
		TransactionDetailDomain transactionDetailDomain=CommonUtils.convertObject(transactionDTO,TransactionDetailDomain.class);
		boolean result = false;
		try {
			hibernatePersistenceManager.beginTransaction();
			hibernatePersistenceManager.saveOrUpdate(transactionDetailDomain);
			result = true;
			hibernatePersistenceManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			hibernatePersistenceManager.rollback();
			throw new ProcessFailedException("Unable to save the TransactionDetailDomain wallet");
		}
		return result;
	}
	
	public boolean updateRechargeWallet(RechargeDTO rechargeDTO) throws ProcessFailedException, ObjectNotSupportedException{
		
		RechargeWalletDomain rechargeWalletDomain = CommonUtils.convertObject(rechargeDTO, RechargeWalletDomain.class);
		boolean result = false;
		try {
			hibernatePersistenceManager.beginTransaction();
			hibernatePersistenceManager.saveOrUpdate(rechargeWalletDomain);
			result = true;
			hibernatePersistenceManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			hibernatePersistenceManager.rollback();
			throw new ProcessFailedException("Unable to save the rechargeWalletDomain wallet");
		}
		return result;
	}
	
	
	@Override
	public long walletRechargeProcess(RechargeDTO rechargeDTO)throws ProcessFailedException,ObjectNotSupportedException{
		
		RechargeWalletDomain rechargeWalletDomain = CommonUtils.convertObject(rechargeDTO, RechargeWalletDomain.class);
		IndividualWalletDomain individualWalletDomain=null;
		TransactionDetailDomain transactionDetailDomain=null;
		long transactionId=0;
		try{
			hibernatePersistenceManager.beginTransaction();
			//IndividualWalletDomain=hibernatePersistenceManager.getPersistentObject(IndividualWalletDomain.class,rechargeWalletDomain.getUserId());
			
			if(rechargeDTO.getStatus().equals(TransactionStatus.Success.getStatus())){
				Criteria walletList = hibernatePersistenceManager.createCriteria(IndividualWalletDomain.class);
				walletList.add(Restrictions.eq("userId", rechargeWalletDomain.getUserId()));
				List<IndividualWalletDomain> results = walletList.list();
				if(results != null && results.size()>0){
					individualWalletDomain = results.get(0);
				}
				
				rechargeWalletDomain.setLastBalance(individualWalletDomain.getBalance());
				individualWalletDomain.setLastupdated(new Date());
				individualWalletDomain.setBalance(individualWalletDomain.getBalance()+rechargeWalletDomain.getAmount());
				
				transactionDetailDomain=new TransactionDetailDomain();
				transactionDetailDomain.setUserId(rechargeDTO.getUserId());
				transactionDetailDomain.setAmount(rechargeDTO.getAmount());
				transactionDetailDomain.setCurrentBalance(individualWalletDomain.getBalance());
				transactionDetailDomain.setTransactionType(TransactionTypeEnum.CREDIT.getValue());
				transactionDetailDomain.setRemark(rechargeDTO.getStatus());
				transactionDetailDomain.setStatus(rechargeDTO.getStatus());
				transactionDetailDomain.setTransactionTime(new Date());
				
				hibernatePersistenceManager.saveOrUpdate(individualWalletDomain);
				TransactionDetailDomain dom=(TransactionDetailDomain) hibernatePersistenceManager.save(transactionDetailDomain);
				rechargeWalletDomain.setTransactionId(dom.getTransactionId());
				transactionId=dom.getTransactionId();
				rechargeWalletDomain.setRechargeTime(new Date());
				rechargeWalletDomain.setUpdated(new Date());
				hibernatePersistenceManager.save(rechargeWalletDomain);
				
			}else{
				transactionDetailDomain=new TransactionDetailDomain();
				transactionDetailDomain.setUserId(rechargeDTO.getUserId());
				transactionDetailDomain.setAmount(rechargeDTO.getAmount());
				transactionDetailDomain.setTransactionType(TransactionTypeEnum.CREDIT.getValue());
				transactionDetailDomain.setRemark(rechargeDTO.getStatus());
				transactionDetailDomain.setStatus(rechargeDTO.getStatus());
				transactionDetailDomain.setTransactionTime(new Date());
				TransactionDetailDomain dom=(TransactionDetailDomain)hibernatePersistenceManager.save(transactionDetailDomain);
				rechargeWalletDomain.setTransactionId(dom.getTransactionId());
				rechargeWalletDomain.setRechargeTime(new Date());
				rechargeWalletDomain.setUpdated(new Date());;
				transactionId=dom.getTransactionId();
				hibernatePersistenceManager.save(rechargeWalletDomain);
				
			}
			hibernatePersistenceManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			hibernatePersistenceManager.rollback();
			throw new ProcessFailedException("Unable to save the rechargeWalletDomain wallet");
		}
		return transactionId;
		
	}
	
	@Override
	public long amountDeductionProcess(long userId, double amount)throws ProcessFailedException{
		log.info("Amount Deduction Process :"+userId +"|"+amount);
		IndividualWalletDomain individualWalletDomain=null;
		TransactionDetailDomain transactionDetailDomain=null;
		long transactionId=0;
		try{
			hibernatePersistenceManager.beginTransaction();
			// IndividualWalletDomain=hibernatePersistenceManager.getPersistentObject(IndividualWalletDomain.class,rechargeWalletDomain.getUserId());

			Criteria walletList = hibernatePersistenceManager.createCriteria(IndividualWalletDomain.class);
			walletList.add(Restrictions.eq("userId", userId));
			List<IndividualWalletDomain> results = walletList.list();
			if (results != null && results.size() > 0) {
				individualWalletDomain = results.get(0);
			}

			individualWalletDomain.setBalance(individualWalletDomain.getBalance() - amount);
			individualWalletDomain.setLastupdated(new Date());
			
			transactionDetailDomain = new TransactionDetailDomain();
			transactionDetailDomain.setUserId(userId);
			transactionDetailDomain.setAmount(amount);
			transactionDetailDomain.setCurrentBalance(individualWalletDomain.getBalance());
			transactionDetailDomain.setTransactionType(TransactionTypeEnum.DEBIT.getValue());
			transactionDetailDomain.setRemark(TransactionStatus.Success.getStatus());
			transactionDetailDomain.setStatus(TransactionStatus.Success.getStatus());
			transactionDetailDomain.setTransactionTime(new Date());
			log.info("Update Wallet Balance :"+userId +"|"+individualWalletDomain.getBalance());
			hibernatePersistenceManager.saveOrUpdate(individualWalletDomain);
			TransactionDetailDomain dom = (TransactionDetailDomain) hibernatePersistenceManager	.save(transactionDetailDomain);
			transactionId = dom.getTransactionId();
			log.info("Insert Transaction Success :"+userId +"|"+transactionId);
			hibernatePersistenceManager.commit();
		} catch (Exception e) {
			log.info(e.getMessage());
			hibernatePersistenceManager.rollback();
			throw new ProcessFailedException("Unable to update amountDeductionProcess");
		}
		return transactionId;
		
	}
	
	
	@Override
	public double transactionSuccess(long transactionId)throws ProcessFailedException,ObjectNotSupportedException{
		log.info("transactionSuccess called : "+ transactionId );
		double result=0.0;
		RechargeWalletDomain rechargeWalletDomain = null;
		IndividualWalletDomain individualWalletDomain=null;
		TransactionDetailDomain transactionDetailDomain=null;
		try{
			hibernatePersistenceManager.beginTransaction();
			transactionDetailDomain=getTrasactionDomain(transactionId);
			individualWalletDomain=geIndividualWalletDomain(transactionDetailDomain.getUserId());
			rechargeWalletDomain=getRechargeDomain(transactionId);
			
			
			//Set status Success for recharge and transaction
			transactionDetailDomain.setRemark(TransactionStatus.Success.getStatus());
			transactionDetailDomain.setStatus(TransactionStatus.Success.getStatus());
			transactionDetailDomain.setTransactionTime(new Date());
			transactionDetailDomain.setCurrentBalance(individualWalletDomain.getBalance()+transactionDetailDomain.getAmount());
			
			rechargeWalletDomain.setStatus(TransactionStatus.Success.getStatus());
			rechargeWalletDomain.setLastBalance(individualWalletDomain.getBalance()+transactionDetailDomain.getAmount());
			rechargeWalletDomain.setUpdated(new Date());
			
			//add amount to user wallet
			
			individualWalletDomain.setBalance(individualWalletDomain.getBalance()+transactionDetailDomain.getAmount());
			
			//Update all the records
			log.info("Updated Process individualWalletDomain : "+ transactionId +"|"+transactionDetailDomain.getUserId()+"|"+individualWalletDomain.getWalletId());
			hibernatePersistenceManager.saveOrUpdate(individualWalletDomain);
			log.info("Updated Process rechargeWalletDomain : "+ transactionId +"|"+transactionDetailDomain.getUserId()+"|"+rechargeWalletDomain.getRechargeId());
			hibernatePersistenceManager.saveOrUpdate(rechargeWalletDomain);
			log.info("Updated Process transactionDetailDomain : "+ transactionId +"|"+transactionDetailDomain.getUserId()+"|"+transactionDetailDomain.getAmount());
			hibernatePersistenceManager.saveOrUpdate(transactionDetailDomain);
			hibernatePersistenceManager.commit();
			result = transactionDetailDomain.getAmount();
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
			hibernatePersistenceManager.rollback();
			throw new ProcessFailedException("Unable to Update the trasaction after success");
		}
		return result;
		
	}
	
	@Override
	public boolean transactionFailure(long transactionId)throws ProcessFailedException,ObjectNotSupportedException{
		log.info("transactionFailure called : "+ transactionId );
		boolean result=true;
		RechargeWalletDomain rechargeWalletDomain = null;
		TransactionDetailDomain transactionDetailDomain=null;
		try{
			hibernatePersistenceManager.beginTransaction();
			rechargeWalletDomain=getRechargeDomain(transactionId);
			transactionDetailDomain=getTrasactionDomain(transactionId);
			
			//Set status Success for recharge and transaction
			transactionDetailDomain.setRemark(TransactionStatus.Failed.getStatus());
			transactionDetailDomain.setStatus(TransactionStatus.Failed.getStatus());
			transactionDetailDomain.setTransactionTime(new Date());
			//transactionDetailDomain.setCurrentBalance(individualWalletDomain.getBalance()+amount);
			
			rechargeWalletDomain.setStatus(TransactionStatus.Failed.getStatus());
			rechargeWalletDomain.setUpdated(new Date());
			//rechargeWalletDomain.setLastBalance(individualWalletDomain.getBalance()+amount);
			
			//add amount to user wallet
			
			
			//Update all the records
			hibernatePersistenceManager.saveOrUpdate(rechargeWalletDomain);
			hibernatePersistenceManager.saveOrUpdate(transactionDetailDomain);
			hibernatePersistenceManager.commit();
			log.info("transactionFailure Updated Successful : "+ transactionId +""+transactionDetailDomain.getAmount());
			result = true;
		} catch (Exception e) {
			log.info(e.getMessage());
			hibernatePersistenceManager.rollback();
			throw new ProcessFailedException("Unable to Update the trasaction after success");
		}
		return result;
		
	}
	
	
	public ChequeDTO getChequeDetail(long chequeId) throws ObjectNotSupportedException {
		hibernatePersistenceManager.beginTransaction();
		ChequeDetailDomain chequeDetailDomain = hibernatePersistenceManager.getPersistentObject(ChequeDetailDomain.class,chequeId);
		ChequeDTO chequeDTO=CommonUtils.convertObject(chequeDetailDomain,ChequeDTO.class);
		return chequeDTO;
	}

	
	public boolean updateChequeDetail(ChequeDTO chequeDTO) throws ProcessFailedException, ObjectNotSupportedException{
			
		ChequeDetailDomain chequeDetailDomain = CommonUtils.convertObject(chequeDTO, ChequeDetailDomain.class);
			boolean result = false;
			try {
				hibernatePersistenceManager.beginTransaction();
				chequeDetailDomain.setDateOfDepositeCheque(new Date());
				hibernatePersistenceManager.save(chequeDetailDomain);
				hibernatePersistenceManager.commit();
				result = true;
			} catch (Exception e) {
				log.info(e.getMessage());
				hibernatePersistenceManager.rollback();
				throw new ProcessFailedException("Unable to save the rechargeWalletDomain wallet");
			}
			return result;
		}
	
	
	public List<ChequeDTO> getChequeDetailByUserId(long userId) throws ObjectNotSupportedException {
		List<ChequeDTO> list=new ArrayList<ChequeDTO>();
		hibernatePersistenceManager.beginTransaction();
		Criteria chequeDetail = hibernatePersistenceManager.createCriteria(ChequeDetailDomain.class);
		chequeDetail.add(Restrictions.eq("userId", userId));
		List<ChequeDetailDomain> result = chequeDetail.list();
		for(ChequeDetailDomain chequeDetailDomain: result){
			list.add(CommonUtils.convertObject(chequeDetailDomain, ChequeDTO.class));
		}
		return list;
					
	  }
	
	public List<ChequeDTO> getChequeDetails() throws ObjectNotSupportedException {
		List<ChequeDTO> list=new ArrayList<ChequeDTO>();
		hibernatePersistenceManager.beginTransaction();
		Criteria chequeDetail = hibernatePersistenceManager.createCriteria(ChequeDetailDomain.class);
		List<ChequeDetailDomain> result = chequeDetail.list();
		for(ChequeDetailDomain chequeDetailDomain: result){
			list.add(CommonUtils.convertObject(chequeDetailDomain, ChequeDTO.class));
		}
		return list;
					
	  }
	
	@Override
	public List<TransactionDTO> getTransactionsHist(ReportFilter filter) throws ObjectNotSupportedException {
		List<TransactionDTO> list=new ArrayList<TransactionDTO>();
		hibernatePersistenceManager.beginTransaction();
		StringBuffer queryStr=new StringBuffer("SELECT trans.transactionId, trans.amount,trans.transactionType,trans.transactionTime,trans.currentBalance,trans.status,trans.userId  from TransactionDetailDomain as trans, UserDomain as user where "
				+ "user.id= trans.userId and user.distt like '%"+(filter.getDistrict()==null?"":filter.getDistrict())+"%' ");
		if(filter.getUserId()!=0){
			queryStr.append(" and user.id="+filter.getUserId());
		}
		
		if(filter.getStartDate() !=null && filter.getEndDate() !=null){
			queryStr.append(" and trans.transactionTime between '"+filter.getStartDate()+" 00:00:00' and '"+filter.getEndDate()+" 23:59:59'");
		}
		
		Query query = hibernatePersistenceManager.createQuery(queryStr.toString());
		List result = query.list();
		TransactionDTO transactionDTO=null;
		Iterator it = result.iterator();
		while(it.hasNext()){
			Object[] obj=(Object[]) it.next();
			transactionDTO=new TransactionDTO();
			transactionDTO.setTransactionId((Long)obj[0]);
			transactionDTO.setAmount((Double)obj[1]);
			transactionDTO.setTransactionType((String)obj[2]);
			transactionDTO.setTransactionTime((Date)obj[3]);
			transactionDTO.setCurrentBalance((Double)obj[4]);
			transactionDTO.setStatus((String)obj[5]);
			transactionDTO.setUserId((Long)obj[6]);
			list.add(transactionDTO);
		}
		return list;
	}
	
	
	
	@Override
	public List getRechargeHist(ReportFilter filter)	throws ObjectNotSupportedException {
		List<TransactionDTO> list=new ArrayList<TransactionDTO>();
		hibernatePersistenceManager.beginTransaction();
		StringBuffer queryStr=new StringBuffer("SELECT user.edisttId, user.firstName,user.lastName,user.email,recharge.amount,recharge.lastBalance,recharge.rechargeTime,recharge.transactionId  from RechargeWalletDomain as recharge, UserDomain as user where "
				+ "user.id= recharge.userId and user.distt like '%"+(filter.getDistrict()==null?"":filter.getDistrict())+"%' ");
		
		if(filter.getUserId()!=0){
			queryStr.append(" and user.id="+filter.getUserId());
		}
		if(filter.getStartDate() !=null && filter.getEndDate() !=null){
			queryStr.append(" and recharge.rechargeTime between '"+filter.getStartDate()+" 00:00:00' and '"+filter.getEndDate()+" 23:59:59' ");
		}
		queryStr.append(" order by recharge.rechargeTime");
		
		Query query = hibernatePersistenceManager.createQuery(queryStr.toString());
		List result = query.list();
		return result;
		
		
	}
	
	@Override
	public List<RechargeDTO> getRechargeList(ReportFilter filter)	throws ObjectNotSupportedException {
		List<RechargeDTO> list=new ArrayList<RechargeDTO>();
		hibernatePersistenceManager.beginTransaction();
		Criteria trans = hibernatePersistenceManager.createCriteria(RechargeWalletDomain.class);
		/*if(filter.getUserId() != 0){
			trans.add(Restrictions.eq("userId", filter.getUserId()));
		}*/
		if(filter.getStatus() != null){
			trans.add(Restrictions.eq("status", filter.getStatus()));
		}
		List<RechargeWalletDomain> result = trans.list();
		for(RechargeWalletDomain rechargeWalletDomain: result){
			list.add(CommonUtils.convertObject(rechargeWalletDomain, RechargeDTO.class));
		}
		return list;
	}
	
	
	
	
	
	
	@Override
	public boolean updateCashDetails(CashDTO cashDTO) throws ProcessFailedException, ObjectNotSupportedException{
		
		CashDomain cashDomain = CommonUtils.convertObject(cashDTO, CashDomain.class);
			boolean result = false;
			try {
				hibernatePersistenceManager.beginTransaction();
				cashDomain.setDateOfDepositeCash(new Date());
				hibernatePersistenceManager.save(cashDomain);
				result = true;
				hibernatePersistenceManager.commit();
				
			} catch (Exception e) {
				log.info(e.getMessage());
				hibernatePersistenceManager.rollback();
				throw new ProcessFailedException("Unable to save the cashDomain Wallet");
			}
			return result;
		}
	
	//Methods For DemandDraft Details
	@Override
	public List<DemandDraftDTO> getDemandDraftDetails(ReportFilter reportFilter) throws ObjectNotSupportedException {
		List<DemandDraftDTO> list=new ArrayList<DemandDraftDTO>();
		hibernatePersistenceManager.beginTransaction();
		Criteria demandDraftDetail = hibernatePersistenceManager.createCriteria(DemandDraftDomain.class);
		demandDraftDetail=getFilteredData(demandDraftDetail, reportFilter);
		List<DemandDraftDomain> result = demandDraftDetail.list();
		for(DemandDraftDomain demandDraftdomainDetail: result){
			list.add(CommonUtils.convertObject(demandDraftdomainDetail, DemandDraftDTO.class));
		}
		return list;
	}
	
	@Override
	public List<CashDTO> getCashDetails(ReportFilter reportFilter) throws ObjectNotSupportedException {
		List<CashDTO> list=new ArrayList<CashDTO>();
		hibernatePersistenceManager.beginTransaction();
		Criteria cashDetail = hibernatePersistenceManager.createCriteria(CashDomain.class);
		cashDetail=getFilteredData(cashDetail, reportFilter);
		List<CashDomain> result = cashDetail.list();
		for(CashDomain cashdomaindetail: result){
			list.add(CommonUtils.convertObject(cashdomaindetail, CashDTO.class));
		}
		return list;
	}
	
	private Criteria getFilteredData(Criteria data,ReportFilter reportFilter){
		if(reportFilter.getUserId() != 0){
			data.add(Restrictions.eq("userId", reportFilter.getUserId()));
		}
		return data;
	}
	@Override
    public boolean updateDemandDraftDetails(DemandDraftDTO demandDraftDTO) throws ProcessFailedException, ObjectNotSupportedException{
		
		DemandDraftDomain demandDraftDomain = CommonUtils.convertObject(demandDraftDTO, DemandDraftDomain.class);
			boolean result = false;
			try {
				hibernatePersistenceManager.beginTransaction();
				demandDraftDomain.setDateOfDepositeDD(new Date());
				hibernatePersistenceManager.save(demandDraftDomain);
				result = true;
				hibernatePersistenceManager.commit();
				
			} catch (Exception e) {
				log.info(e.getMessage());
				hibernatePersistenceManager.rollback();
				throw new ProcessFailedException("Unable to save the demandDraftDomain Wallet");
			}
			return result;
		}
    
    //
	@Override
	public double getCollectionAmount(ReportFilter reportFilter) throws ObjectNotSupportedException {
		Double amount=0.0;
		hibernatePersistenceManager.beginTransaction();
		Query query = hibernatePersistenceManager.createQuery("SELECT sum(recharge.amount) from RechargeWalletDomain as recharge,UserDomain as user where user.id= recharge.userId  and user.distt=:district  and  recharge.status=:status");
		query.setString("status", reportFilter.getStatus());
		query.setString("district", reportFilter.getDistrict());
		for(Iterator it=query.iterate();it.hasNext();)  
		  {  
			Object obj=it.next();
			amount= obj == null?0.0 :(Double) obj;     
		  }
		return amount;
	}
	
	/*......................
	
	@Override
	public double getwalletAmount(ReportFilter reportFilter) throws ObjectNotSupportedException {
		Double wallet=0.0;
		hibernatePersistenceManager.beginTransaction();
		
		StringBuffer queryStr = new StringBuffer("SELECT sum(recharge.amount) from RechargeWalletDomain as recharge,UserDomain as user where user.id= recharge.userId  and user.distt=:district  and  recharge.status=:status");
		
		if(reportFilter.getStartDate() !=null && reportFilter.getEndDate() !=null){
			queryStr.append(" and recharge.rechargeTime between '"+reportFilter.getStartDate()+" 00:00:00' and '"+reportFilter.getEndDate()+" 23:59:59'");
		}
		Query query= hibernatePersistenceManager.createQuery(queryStr.toString());
		for(Iterator it=query.iterate();it.hasNext();)  
		  {  
			Object obj=it.next();
			wallet= obj == null?0.0 :(Double) obj;     
		  }
		return wallet;
	}
	..................................*/
	@Override
	public double getCashCollection(ReportFilter reportFilter) throws ObjectNotSupportedException {
		Double amount=0.0;
		hibernatePersistenceManager.beginTransaction();
		Query query = hibernatePersistenceManager.createQuery("SELECT sum(cash.amount) from CashDomain as cash,UserDomain as user where user.id= cash.userId  and user.distt=:district  and  cash.status=:status");
		query.setString("status", reportFilter.getStatus());
		query.setString("district", reportFilter.getDistrict());
		for(Iterator it=query.iterate();it.hasNext();)  
		  {  
			Object obj=it.next();
			amount= obj == null?0.0 :(Double) obj;     
		  }
		return amount;
	}
	
	@Override
	public double getDDCollection(ReportFilter reportFilter) throws ObjectNotSupportedException {
		Double amount=0.0;
		hibernatePersistenceManager.beginTransaction();
		Query query = hibernatePersistenceManager.createQuery("SELECT sum(dd.amount) from DemandDraftDomain as dd,UserDomain as user where user.id= dd.userId  and user.distt=:district  and  dd.status=:status");
		query.setString("status", reportFilter.getStatus());
		query.setString("district", reportFilter.getDistrict());
		for(Iterator it=query.iterate();it.hasNext();)  
		  {  
			Object obj=it.next();
			amount= obj == null?0.0 :(Double) obj;     
		  }
		return amount;
	}
	
	
	@Override
	public double getCollectionAmount(String status, long userId) throws ObjectNotSupportedException {
		Double amount=0.0;
		hibernatePersistenceManager.beginTransaction();
		Query query = hibernatePersistenceManager.createQuery("SELECT sum(recharge.amount) from RechargeWalletDomain as recharge where recharge.status=:status and userId=:userId");
		query.setString("status", status);
		query.setLong("userId", userId);
		for(Iterator it=query.iterate();it.hasNext();)  
		  {  
			Object obj=it.next();
			amount= obj == null?0.0 :(Double) obj;     
		  }
		return amount;
	}
	
		
	
	@Override
	public double getDailyCollectionAmount(String status,Date updated) throws ObjectNotSupportedException {
		Double amount=0.0;
		hibernatePersistenceManager.beginTransaction();
		Query query = hibernatePersistenceManager.createQuery("SELECT sum(recharge.amount) from RechargeWalletDomain as recharge where recharge.status=:status and recharge.updated=:updated");
		query.setString("status", status);
		query.setDate("updated", updated);
		for(Iterator it=query.iterate();it.hasNext();)  
		  {  
			Object obj=it.next();
			amount= obj == null?0.0 :(Double) obj;     
		  }
		return amount;
	}
	
	
	
	
	@Override
	public double getTransactionAmount(ReportFilter reportFilter) throws ObjectNotSupportedException {
		Double amount=0.0;
		hibernatePersistenceManager.beginTransaction();
		//Query query = hibernatePersistenceManager.createQuery("SELECT sum(trans.amount) from TransactionDetailDomain as trans where trans.status=:status and trans.transactionType=:transactionType");
		Query query = hibernatePersistenceManager.createQuery("SELECT sum(trans.amount) from TransactionDetailDomain as trans ,UserDomain as user where user.id= trans.userId and user.distt=:district and trans.status=:status and trans.transactionType=:transactionType");
		query.setString("district", reportFilter.getDistrict());
		query.setString("status", reportFilter.getStatus());
		query.setString("transactionType", reportFilter.getTransactionType());
		for(Iterator it=query.iterate();it.hasNext();)  
		  {  
			Object obj=it.next();
			amount= obj == null?0.0 :(Double) obj;    
		  }
		return amount;
	}
	
	
	@Override
	public double getTransactionAmount(String status,String transactionType, long userId) throws ObjectNotSupportedException {
		Double amount=0.0;
		hibernatePersistenceManager.beginTransaction();
		Query query = hibernatePersistenceManager.createQuery("SELECT sum(trans.amount) from TransactionDetailDomain as trans where trans.status=:status and userId=:userId and trans.transactionType=:transactionType");
		query.setString("status", status);
		query.setLong("userId", userId);
		query.setString("transactionType", transactionType);
		for(Iterator it=query.iterate();it.hasNext();)  
		  {  
			Object obj=it.next();
			amount= obj == null?0.0 :(Double) obj;    
		  }
		return amount;
	}
	
	@Override
	public double getDailyTransactionAmount(String status) throws ObjectNotSupportedException {
		Double amount=0.0;
		hibernatePersistenceManager.beginTransaction();
		Query query = hibernatePersistenceManager.createQuery("SELECT sum(cheque.amount) from ChequeDetailDomain as cheque where cheque.status=:status");
		query.setString("status", status);
		for(Iterator it=query.iterate();it.hasNext();)  
		  {  
			Object obj=it.next();
			amount= obj == null?0.0 :(Double) obj;     
		  }
		return amount;
	}
	
	@Override
	public double getChequeAmount(ReportFilter reportFilter) throws ObjectNotSupportedException {
		Double amount=0.0;
		hibernatePersistenceManager.beginTransaction();
		Query query = hibernatePersistenceManager.createQuery("SELECT sum(cheque.amount) from ChequeDetailDomain as cheque , UserDomain as user where user.id= cheque.userId and user.distt=:district and cheque.status=:status");
		
		query.setString("status", reportFilter.getStatus());
		query.setString("district", reportFilter.getDistrict());
		for(Iterator it=query.iterate();it.hasNext();)  
		  {  
			Object obj=it.next();
			amount= obj == null?0.0 :(Double) obj;   
		  }
		return amount;
	}
	
	@Override
	public double getChequeAmount(String status,long userId) throws ObjectNotSupportedException {
		Double amount=0.0;
		hibernatePersistenceManager.beginTransaction();
		Query query = hibernatePersistenceManager.createQuery("SELECT sum(cheque.amount) from ChequeDetailDomain as cheque where cheque.status=:status and cheque.userId:userId");
		query.setString("status", status);
		query.setLong("userId", userId);
		for(Iterator it=query.iterate();it.hasNext();)  
		  {  
			Object obj=it.next();
			amount= obj == null?0.0 :(Double) obj;   
		  }
		return amount;
	}
	
	
	@Override
	public double getDailyChequeAmount(String status) throws ObjectNotSupportedException {
		Double amount=0.0;
		hibernatePersistenceManager.beginTransaction();
		Query query = hibernatePersistenceManager.createQuery("SELECT sum(cheque.amount) from ChequeDetailDomain as cheque where cheque.status=:status");
		query.setString("status", status);
		for(Iterator it=query.iterate();it.hasNext();)  
		  {  
			Object obj=it.next();
			amount= obj == null?0.0 :(Double) obj;      
		  }
		return amount;
	}
	@Override
	public double getCashAmount(String status) throws ObjectNotSupportedException {
		Double amount=0.0;
		hibernatePersistenceManager.beginTransaction();
		Query query = hibernatePersistenceManager.createQuery("SELECT sum(cheque.amount) from ChequeDetailDomain as cheque where cheque.status=:status");
		query.setString("status", status);
		for(Iterator it=query.iterate();it.hasNext();)  
		  {  
			Object obj=it.next();
			amount= obj == null?0.0 :(Double) obj;      
		  }
		return amount;
	}
	@Override
	public double getDailyCashAmount(String status) throws ObjectNotSupportedException {
		Double amount=0.0;
		hibernatePersistenceManager.beginTransaction();
		Query query = hibernatePersistenceManager.createQuery("SELECT sum(cheque.amount) from ChequeDetailDomain as cheque where cheque.status=:status");
		query.setString("status", status);
		for(Iterator it=query.iterate();it.hasNext();){  
			Object obj=it.next();
			amount= obj == null?0.0 :(Double) obj;     
		  }
		return amount;
	}
	
	@Override
	public PaidServiceDTO getDeductionAmount(String serviceId) throws ObjectNotSupportedException {
		PaidServices paidServices=null;
		hibernatePersistenceManager.beginTransaction();
		Criteria user = hibernatePersistenceManager.createCriteria(PaidServices.class);
		user.add(Restrictions.eq("serviceId", serviceId));
		List<PaidServices> result = user.list();
		if(result != null && result.size()>0){
			paidServices = result.get(0);
		}
		PaidServiceDTO paidServiceDTO = CommonUtils.convertObject(paidServices, PaidServiceDTO.class);
		return paidServiceDTO;
	}
	
	@Override
	public EdisttDTO isExist(EdisttDTO edisttDTO) throws ObjectNotSupportedException {
		
		hibernatePersistenceManager.beginTransaction();
		Criteria edistt = hibernatePersistenceManager.createCriteria(EdisttDomain.class);
		edistt.add(Restrictions.eq("applicationNo", edisttDTO.getApplicationNo()));
		edistt.add(Restrictions.eq("transstatus", "Y"));
		List<EdisttDomain> result = edistt.list();
		if(result != null && result.size()>0){
			log.info("Application Id allready process (Going to second time process)"+edisttDTO.getApplicationNo());
			EdisttDomain e = result.get(0);
			edisttDTO.setTransstatus("Y");
			edisttDTO.setTrnid(e.getTrnid());
			edisttDTO.setRes("RES");
			return edisttDTO;
		}else{
			log.info("New Application Id Request :"+edisttDTO.getApplicationNo());
			return null;
		}
	
	}
	
	@Override
	public boolean saveTransactionStatusUpdate(TransactionUpdateDTO transactionUpdateDTO) throws ProcessFailedException, ObjectNotSupportedException{
		
		TransactionUpdate transactionUpdate = CommonUtils.convertObject(transactionUpdateDTO, TransactionUpdate.class);
		boolean result = false;
		try {
			hibernatePersistenceManager.beginTransaction();
			transactionUpdate.setUpdatedDate(new Date());
			hibernatePersistenceManager.save(transactionUpdate);
			result = true;
			hibernatePersistenceManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			hibernatePersistenceManager.rollback();
			throw new ProcessFailedException("Unable to save the individualWalletDomain wallet");
		}
		return result;
	}

	
	
	
	// Get All Cash Details  List
	@Override
	public List<CashDTO> getCashDetailstList(ReportFilter filter) throws ObjectNotSupportedException {
		
		List<CashDTO> cashdetailList = new ArrayList<CashDTO>();
		hibernatePersistenceManager.beginTransaction();
        StringBuffer querystr=new StringBuffer("SELECT cash.userId,cash.rechargeBy,cash.amount,cash.status,cash.dateOfDepositeCash,cash.remark,cash.transactionId from CashDomain as cash,UserDomain as user where user.id= cash.userId  and user.distt=:district  and  cash.status=:status ");
        if(filter.getStartDate() !=null && filter.getEndDate() !=null){
        	querystr.append(" and cash.dateOfDepositeCash between '"+filter.getStartDate().trim()+" 00:00:00' and '"+filter.getEndDate().trim() +" 23:59:59' ");
		}
        Query query = hibernatePersistenceManager.createQuery(querystr.toString());
		query.setString("status", filter.getStatus());
		query.setString("district", filter.getDistrict());
		
		List result = query.list();
		WalletDTO walletDTO=null;
		CashDTO cashDTO=null;
		Iterator it = result.iterator();
		while(it.hasNext()){
			Object[] obj=(Object[]) it.next();
			cashDTO=new CashDTO();
			cashDTO.setUserId((Long)obj[0]);
			cashDTO.setRechargeBy((Long)obj[1]);
			cashDTO.setAmount((Double)obj[2]);
			cashDTO.setStatus((String)obj[3]);
			cashDTO.setDateOfDepositeCash((Date)obj[4]);
			cashDTO.setRemark((String)obj[5]);
			cashDTO.setTransactionId((Long)obj[6]);
			
			cashdetailList.add(cashDTO);
		}
		return cashdetailList;

	}
	
	@Override
	public List getEdistWorkStatus(ReportFilter filter)
			throws ObjectNotSupportedException {
		hibernatePersistenceManager.beginTransaction();
		Query query = hibernatePersistenceManager.createQuery("select  t.userId,u.edisttId, u.firstName,u.lastName,u.block,sum(t.amount) FROM TransactionDetailDomain as t ,UserDomain as u where t.userId=u.id and u.distt='"+filter.getDistrict()+"' and t.status='SUCCESS' and t.transactionType='"+filter.getTransactionType()+"' group by t.userId");
		return query.list();

	}
	
	
	@Override
	public double getTodayTransaction(ReportFilter filter)
			throws ObjectNotSupportedException {
		double amount=0.0;
		hibernatePersistenceManager.beginTransaction();
		Query query = hibernatePersistenceManager.createQuery("select sum(amount) TransactionDetailDomain  where to_char(transactionTime,'YYYY/MM/DD') =:transdate and status=:status and transactionType=:transactionType and userId=:userId");
		query.setString("status", filter.getStatus());
		query.setString("transdate","2016/05/22" );
		query.setString("transactionType", filter.getTransactionType());
		query.setLong("userId", filter.getUserId());
		for(Iterator it=query.iterate();it.hasNext();){  
			Object obj=it.next();
			amount= obj == null?0.0 :(Double) obj;     
		}
		return amount;

	}

	@Override
	public long getActiveUser(String distt) throws ObjectNotSupportedException {
		long activeuser=0;
		hibernatePersistenceManager.beginTransaction();
		Query query = hibernatePersistenceManager.createQuery("select count(*) from UserDomain as user , IndividualWalletDomain as ind  where user.id = ind.userId and  ind.balance> 0 and user.distt=:distt ");
		query.setString("distt", distt);
		for(Iterator it=query.iterate();it.hasNext();){  
			Object obj=it.next();
			activeuser= (long) (obj == null?0.0 :(long) obj);     
		}
		
		return activeuser;
	}
	
	
	@Override
	public List getDisttAmount(ReportFilter reportFilter) throws Exception {
		hibernatePersistenceManager.beginTransaction();
		
		StringBuffer queryStr = new StringBuffer("select u.distt, sum(t.amount) FROM UserDomain as u, TransactionDetailDomain as t where u.id=t.userId");
		
		if(reportFilter.getStartDate() !=null && reportFilter.getEndDate() !=null){
			queryStr.append(" and t.transactionTime between '"+reportFilter.getStartDate()+" 00:00:00' and '"+reportFilter.getEndDate()+" 23:59:59'");
		}
		
		queryStr.append("and  t.status='SUCCESS' and t.transactionType=:transactionType group by u.distt");
		
		Query query = hibernatePersistenceManager.createQuery(queryStr.toString());
		
		query.setString("transactionType", reportFilter.getTransactionType());
		List list=query.list();
		return list;

	}

	@Override
	public List getwalletAmount(ReportFilter reportFilter){
		hibernatePersistenceManager.beginTransaction();
		
		StringBuffer queryStr = new StringBuffer("select u.distt, sum(r.amount) FROM RechargeWalletDomain r,UserDomain as u where u.id=r.userId");
		
		if(reportFilter.getStartDate() !=null && reportFilter.getEndDate() !=null){
			queryStr.append(" and r.rechargeTime between '"+reportFilter.getStartDate()+" 00:00:00' and '"+reportFilter.getEndDate()+" 23:59:59'");
		}
		
		queryStr.append("and  r.status='SUCCESS' group by u.distt");
		
		Query query = hibernatePersistenceManager.createQuery(queryStr.toString());
		
		List list=query.list();
	    	
		return list;
	}

}
