package com.platinumwill.test.data;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.platinumwill.test.AbstractTestNgTest;
import com.platinumwill.test.data.service.DbCopyService;

@ContextConfiguration(locations = { "/test-beans-copy-data.xml" })
public class DbCopier extends AbstractTestNgTest {
	
	private DbCopyService dbCopyService;
	public DbCopyService getDbCopyService() {
		return this.dbCopyService;
	}
	@Autowired
	public void setDbCopyService(DbCopyService dbCopyService) {
		this.dbCopyService = dbCopyService;
	}

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	protected Logger log() {
		return LOGGER;
	}
	
	@Test
	public void testNothing() {
		log().debug("========= done ===========");
	}
	
	@Test
	public void copy() {
		this.getDbCopyService().copyTable(null);
	}

}
