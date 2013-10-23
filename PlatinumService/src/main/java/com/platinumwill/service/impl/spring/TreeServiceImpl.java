package com.platinumwill.service.impl.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.platinumwill.dao.TreeDao;
import com.platinumwill.entity.jpa.TreeItem;
import com.platinumwill.service.TreeService;

@Component
public class TreeServiceImpl implements TreeService {

	public void mockService() {
		TreeItem dad = new TreeItem("dad");
		TreeItem son = new TreeItem("son");
		TreeItem daughter = new TreeItem("daughter");
		son.setParent(dad);
		daughter.setParent(dad);
		this.treeDao.persist(dad);
		this.treeDao.persist(daughter);
		this.treeDao.persist(son);
	}
	
	private TreeDao treeDao;
	public TreeDao getTreeDao() {
		return this.treeDao;
	}
	@Autowired
	public void setTreeDao(TreeDao treeDao) {
		this.treeDao = treeDao;
	}
}
