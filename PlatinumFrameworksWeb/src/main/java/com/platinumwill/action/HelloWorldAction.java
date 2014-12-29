package com.platinumwill.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.platinumwill.dao.TreeDao;

public class HelloWorldAction {

	private static final Logger logger = LogManager.getLogger(HelloWorldAction.class);

	public String execute() throws Exception {
		this.treeDao.doSomething();
		logger.debug("==================== TreeDao has done something ====================");
		return "success";
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
