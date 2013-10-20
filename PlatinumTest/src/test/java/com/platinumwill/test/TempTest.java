package com.platinumwill.test;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.platinumwill.service.TreeService;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "/test-beans.xml", "/beans.xml" })
@ContextConfiguration(locations = { "/test-beans.xml" })
public class TempTest {
	private static Logger LOGGER = LoggerFactory.getLogger(TempTest.class);
	public static Logger getLogger() {
		return LOGGER;
	}
	
	private TreeService treeService;
	public TreeService getTreeService() {
		return this.treeService;
	}
	@Autowired
	public void setTreeService(TreeService treeService) {
		this.treeService = treeService;
	}

	@Test
	public void test() {
		LOGGER.debug("test message");
	}
	
}
