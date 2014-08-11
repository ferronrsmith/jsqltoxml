package com.uwi.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class ConfigurationTest.
 */
public class ConfigurationTest {

	/**
	 * Test load configuration file.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testLoadConfigurationFile() throws Exception {
		Configuration conf = new Configuration();
		assertEquals("count\\(//\\w+/\\(?[\\w|\\*]+\\)\\)?",
				conf.i18n("count_regex"));
		assertEquals("Not supported yet.", conf.i18n("no_support"));
		assertEquals(
				"Only 1 parameter is supported at this time. e.g. count(*)|count(name) not count(x,y,.)",
				conf.i18n("count_single_param"));
		assertEquals("en", Configuration.DEFAULT_LANG);
		assertEquals("xml", Configuration.DEFAULT_TYPE);
	}
}
