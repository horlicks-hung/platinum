package com.platinumwill.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

public class AbstractTestNgTest extends AbstractTestNGSpringContextTests {
	
	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	protected Logger log() {
		return LOGGER;
	}
}
