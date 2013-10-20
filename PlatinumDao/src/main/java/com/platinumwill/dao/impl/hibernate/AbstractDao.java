package com.platinumwill.dao.impl.hibernate;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao {
	private SessionFactory sessionFactory;
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
