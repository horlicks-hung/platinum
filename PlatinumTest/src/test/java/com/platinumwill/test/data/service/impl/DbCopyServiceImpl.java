package com.platinumwill.test.data.service.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.platinumwill.test.data.service.DbCopyService;

@Component
public class DbCopyServiceImpl implements DbCopyService {
	
	private SessionFactory sourceSessionFactory;
	public SessionFactory getSourceSessionFactory() {
		return this.sourceSessionFactory;
	}
	@Autowired
	@Qualifier("sourceSessionFactory")
	public void setSourceSessionFactory(SessionFactory zurichSessionFactory) {
		this.sourceSessionFactory = zurichSessionFactory;
	}
	
	private SessionFactory targetSessionFactory;
	public SessionFactory getTargetSessionFactory() {
		return this.targetSessionFactory;
	}
	@Autowired
	@Qualifier("targetSessionFactory")
	public void setTargetSessionFactory(SessionFactory amazonSessionFactory) {
		this.targetSessionFactory = amazonSessionFactory;
	}
	
	public <T> void copyTable(Class<T> clazz) {
		this.copyTable(clazz, this.getSourceSessionFactory(), this.getTargetSessionFactory(), null);
	}
	
	private <T> void copyTable(Class<T> clazz, SessionFactory sourceSessionFactory, SessionFactory targetSessionFactory, Integer max) {
		Session sourceSession = sourceSessionFactory.getCurrentSession();
		Session targetSession = targetSessionFactory.getCurrentSession();
		Criteria criteria = sourceSession.createCriteria(clazz);
		if (max != null) {
			criteria.setMaxResults(max);
		}
		Query delete = targetSession.createQuery("delete from " + clazz.getSimpleName() + " where 1=1");
		delete.executeUpdate();
		
		@SuppressWarnings("rawtypes")
		List list = criteria.list();
		for (Object object: list) {
			targetSession.replicate(object, ReplicationMode.EXCEPTION);
		}
	}
	
	public <T> void deleteAllRecords(Class<T> clazz) {
		Session session = this.getTargetSessionFactory().getCurrentSession();
		Query delete = session.createQuery("delete from " + clazz.getSimpleName());
		delete.executeUpdate();
	}

}
