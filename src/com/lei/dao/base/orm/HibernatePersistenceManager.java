/**
 * 
 */
package com.lei.dao.base.orm;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;

import com.lei.dao.base.orm.PersistenceManager;

/**
 * Implementing class of {@link PersistenceManager}. This class interacts with the database using hibernate. This is the singleton class so that only
 * single object would be created for the application.
 */
public class HibernatePersistenceManager implements PersistenceManager {

	public static final ThreadLocal<Session> session = new ThreadLocal<Session>();
	public static SessionFactory sessionFactory;

	public HibernatePersistenceManager() {
		init();
	}

	@Override
	public void batchSave(Object object, int count, int size) {
		Session currentSession = currentSession();
		currentSession.save(object);
		if (size < 50 && count == size) {
			flushAndClear(currentSession);
		} else if (count % 50 == 0) {
			flushAndClear(currentSession);
		} else if (size > 50 && count == size) {
			flushAndClear(currentSession);
		}
	}


	/**
	 * Start a transaction.
	 */
	public void beginTransaction() {
		currentSession().beginTransaction();
	}

	/**
	 * Commit the current transaction.
	 */
	public void commit() {
		// In case of batch update the explicitly flush required.
		// Session currentSession = currentSession();
		// currentSession.flush();
		final Transaction currentTransaction = getCurrentTransaction();
		if (currentTransaction != null && currentTransaction.isActive()) { // must check whether tx is active or not before taking an action
			currentTransaction.commit();
		} else {
			throw new RuntimeException("Commit outside a transaction");
		}
	}

	@Override
	public Criteria createCriteria(Class clazz) {
		return currentSession().createCriteria(clazz);
	}

	@Override
	public Criteria createCriteria(Class clazz, String arg) {
		return currentSession().createCriteria(clazz, arg);
	}

	@Override
	public Query createQuery(String query) {
		return currentSession().createQuery(query);
	}


	@Override
	public Query createSQLQuery(String query) {
		return currentSession().createSQLQuery(query);
	}

	private Session currentSession() throws HibernateException {
		org.hibernate.classic.Session currentSession = sessionFactory.getCurrentSession();
		currentSession.setFlushMode(FlushMode.AUTO);
		return currentSession;
	}


	/* (non-Javadoc)
	 * @see com.lei.dao.base.PersistenceManager#delete(java.lang.Object)
	 */
	@Override
	public void delete(Object object) {
		final Session currentSession = currentSession();
		currentSession.delete(object);
		currentSession.flush();
	}

	@Override
	public void evict(Object object) {
		Session currentSession = currentSession();
		currentSession.evict(object);
	}

	/**
	 * Explicit
	 * @param currentSession
	 */
	private void flushAndClear(Session currentSession) {
		currentSession.flush();
		currentSession.clear();
	}


	/* (non-Javadoc)
	 * @see com.lei.dao.base.PersistenceManager#get(java.lang.Class, java.lang.Long)
	 */
	@Override
	public List<Object> get(Class clazz, Long id) {
		List<Object> list = new ArrayList<Object>();
		Object object = currentSession().get(clazz, id);
		list.add(object);
		return list;
		// TODO Validate the return type for this.
	}

	
	
	@Override
	public <T> T getPersistentObject(Class<T> type, Long id) {
		
		return type.cast(currentSession().get(type, id));
		// TODO Validate the return type for this.
	}

	
	
	
	/**
	 * @return The transaction associated with current session, else <code>null</code>.
	 */
	private Transaction getCurrentTransaction() {
		return currentSession().getTransaction();
	}

	/* (non-Javadoc)
	 * @see com.lei.dao.base.PersistenceManager#init()
	 */
	@Override
	public void init() {
		try {
			if (sessionFactory == null) {
				// Create the SessionFactory from hibernate.cfg.xml
				Configuration config = new Configuration().configure();
				// config.setInterceptor(new EntityNameResolverInterceptor());
				sessionFactory = config.buildSessionFactory();
			}
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			ex.printStackTrace();
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * Rollbacks the current transaction.
	 */
	public void rollback() {
		final Transaction currentTransaction = getCurrentTransaction();
		if (currentTransaction != null && currentTransaction.isActive()) { // must check whether tx is active or not before taking an action
			currentTransaction.rollback();
		} else {
			throw new RuntimeException("Rollback outside a transaction");
		}
	}

	/* (non-Javadoc)
	 * @see com.lei.dao.base.PersistenceManager#save(java.lang.Object)
	 */
	@Override
	public Object save(Object object) {
		Session currentSession = currentSession();
		currentSession.save(object);
		currentSession.flush();
		currentSession.clear();
		return object;
	}


	/* (non-Javadoc)
	 * @see com.lei.dao.base.PersistenceManager#saveOrUpdate(java.lang.Object)
	 */
	@Override
	public Object saveOrUpdate(Object object) {
		Session currentSession = currentSession();
		currentSession.saveOrUpdate(object);
		currentSession.flush();
		currentSession.clear();
		return object;
	}
	
	public <T>T getFromSession(Class<T> returnType,int id) {
		return (T) currentSession().get(returnType, id);
	}
	
	public Criteria getExecutableCriteria(DetachedCriteria dc){
		return dc.getExecutableCriteria(currentSession());
	}
	
	public PreparedStatement getPreparedStatement(String statementQuery) throws HibernateException, SQLException{
//		currentSession().connection().prepareStatement(statementQuery);
		return currentSession().connection().prepareStatement(statementQuery); 
	}
	
	public void refresh(Object objectToRefresh){
		currentSession().refresh(objectToRefresh);
	}
	@Override
	public SessionFactory getSessionFactory(){
		return sessionFactory;
	}
}
