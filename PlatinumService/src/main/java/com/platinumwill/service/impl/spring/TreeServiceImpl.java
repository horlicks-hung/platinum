package com.platinumwill.service.impl.spring;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
	
	public void mockService2() {
		TreeItem dad = this.treeDao.find(1);
		TreeItem son = this.treeDao.find(2);
		TreeItem daughter = this.treeDao.find(3);

//		son.setParent(dad);
//		daughter.setParent(dad);
//		
//		this.treeDao.persist(daughter);
//		this.treeDao.persist(son);
		
		dad.getChildren().add(son);
		dad.getChildren().add(daughter);
		this.treeDao.persist(dad);
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
