package com.lei.dao.base.orm;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

/**
 * This is responsible for interaction with the transactional persistence store.
 */
public interface PersistenceManager {

	public void batchSave(Object object, int count, int size);

	Criteria createCriteria(Class clazz);

	Criteria createCriteria(Class clazz, String arg);;

	Query createQuery(String query);

	Query createSQLQuery(String query);

	/**
	 * Delete any entity on the basis of provided class and id.
	 * @param id
	 */
	void delete(Object object);

	public void evict(Object object);

	/**
	 * Fetch object on the basis of id provided.
	 * @param clazz
	 * @param id
	 * @return
	 * @throws OrganisationException
	 */
	List<Object> get(Class clazz, Long id);
	
	
	/**
	 * Fetch persistent object on the basis of id provided.
	 * @param clazz
	 * @param id
	 * @return
	 * @throws OrganisationException
	 */
	public <T> T getPersistentObject(Class<T> clazz, Long id);
	
	

	/**
	 * Initializing the configuration.
	 */
	void init();

	/**
	 * Saves any entity.
	 * @param object
	 */
	Object save(Object object);

	/**
	 * Saving or Updating the entity.
	 * @param object
	 * @return TODO
	 */
	Object saveOrUpdate(Object object);
	
	public SessionFactory getSessionFactory();
}
