package com.platinumwill.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.platinumwill.dao.TreeDao;
import com.platinumwill.service.TreeService;

public class TreeServiceImpl implements TreeService {

	private TreeDao treeDao;
	public TreeDao getTreeDao() {
		return this.treeDao;
	}
	@Autowired
	public void setTreeDao(TreeDao treeDao) {
		this.treeDao = treeDao;
	}
}
