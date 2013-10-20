package com.platinumwill.entity.jpa;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TREE_ITEM")
public class TreeItem {

	private int id;
	@Id
	@Column(name = "ITEM_ID", nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	private TreeItem parent;
	@ManyToOne()
	@JoinColumn(name = "PARENT_ID", nullable = true, insertable = false, updatable = false)
	public TreeItem getParent() {
		return this.parent;
	}
	public void setParent(TreeItem parent) {
		this.parent = parent;
	}
	
	private List<TreeItem> children;
	@OneToMany(mappedBy = "parent")
	public List<TreeItem> getChildren() {
		return this.children;
	}
	public void setChildren(List<TreeItem> children) {
		this.children = children;
	}
	
}
