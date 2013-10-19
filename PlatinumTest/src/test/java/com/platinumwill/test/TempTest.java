package com.platinumwill.test;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-beans.xml", "/beans.xml" })
public class TempTest {
	private static Logger LOGGER = LoggerFactory.getLogger(TempTest.class);
	public static Logger getLogger() {
		return LOGGER;
	}

	@Test
	public void test() {
	}
	
}
