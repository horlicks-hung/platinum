package com.platinumwill.dao;

import com.platinumwill.entity.jpa.TreeItem;


public interface TreeDao {
	public void persist(TreeItem treeItem);
	public TreeItem find(int id);
}
