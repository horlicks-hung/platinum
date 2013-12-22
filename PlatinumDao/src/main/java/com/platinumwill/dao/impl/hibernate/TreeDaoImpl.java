package com.platinumwill.dao.impl.hibernate;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.platinumwill.dao.TreeDao;
import com.platinumwill.entity.jpa.TreeItem;

@Component
public class TreeDaoImpl extends AbstractDaoImpl implements TreeDao {
	public void persist(TreeItem treeItem) {
		Session session = this.getSession();
		session.persist(treeItem);
	}
}
