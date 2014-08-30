package com.uwi.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

import com.uwi.enums.ResultType;

// TODO: Auto-generated Javadoc
/**
 * The Class ConfigurationTest.
 */
public class ConfigurationTest {

	/**
	 * Test load configuration file.
	 *
	 * @throws Exception
	 *             the exception
	 */

	Configuration conf;

	@Before
	public void before() {
		conf = new Configuration();
	}

	@Test
	public void testCountRegexPasses() throws Exception {
		String COUNT_REGEX = conf.i18n("c_count_regex");
		validateRegex("count(//book[year/text()=2003])", COUNT_REGEX);
		validateRegex("count(//book/author[year/text()=2003])", COUNT_REGEX);
		validateRegex("count(//book[@category='COOKING'])", COUNT_REGEX);
		validateRegex("count(//book/author)", COUNT_REGEX);
		validateRegex("count(//book[year/text()=2003]/author)", COUNT_REGEX);
		validateRegex(
				"count(//country[( name/text()='India' and food/text()='roti' )])",
				COUNT_REGEX);
	}

	@Test
	public void testExpressionHasParen() throws Exception {
		String PAREN_REGEX = conf.i18n("c_paren_regex");
		validateRegex("(name)", PAREN_REGEX);
		validateRegex("(age with something)", PAREN_REGEX);
		validateRegex("(22222)", PAREN_REGEX);
	}

	@Test
	public void testIsValidAttrNames() throws Exception {
		String ATTR_REGEX = conf.i18n("c_attr_regex");
		validateRegex("attr_name", ATTR_REGEX);
		validateRegex("attr_country", ATTR_REGEX);
		validateRegex("atr_name", ATTR_REGEX);
	}

	@Test
	public void testLoadConfigurationFile() throws Exception {
		assertEquals("^(count\\(//)[\\w\\W]+(?:\\)|\\]\\))",
				conf.i18n("c_count_regex"));
		assertEquals("Not supported yet.", conf.i18n("no_support"));
		assertEquals(
				"Only 1 parameter is supported at this time. e.g. count(*)|count(name) not count(x,y,.)",
				conf.i18n("count_single_param"));
		assertEquals("en", Configuration.DEFAULT_LANG);
		assertEquals(ResultType.XML, Configuration.DEFAULT_TYPE);
	}

	private void validateRegex(String input, String regex) {
		assertTrue(Pattern.matches(regex, input));
	}
}
