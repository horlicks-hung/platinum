package com.platinumwill.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.platinumwill.dao.TreeDao;
import com.platinumwill.service.TreeService;

@Component
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
