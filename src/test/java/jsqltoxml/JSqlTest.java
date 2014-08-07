package jsqltoxml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import net.sf.jsqlparser.JSQLParserException;

import org.jaxen.JaxenException;
import org.jaxen.dom4j.Dom4jXPath;
import org.junit.Test;

import com.uwi.config.JSql;
import com.uwi.utils.Misc;

// TODO: Auto-generated Javadoc
/**
 * The Class JSqlTest.
 */
public class JSqlTest {

	@Test
	public void isInValidXpath() throws Exception {
		assertFalse(isValidXpath("//countries[contains(.,'people']]"));
	}

	@Test
	public void isValidXpath() throws Exception {
		assertTrue(isValidXpath("//countries[contains(.,'people')]"));
	}

	private boolean isValidXpath(String... paths) {
		boolean result = true;
		for (String path : paths) {
			result &= isValidXpath(path);
		}
		return result;
	}

	private boolean isValidXpath(String xpath) {
		try {
			return Misc.isNotBlank(new Dom4jXPath(xpath).toString());
		} catch (JaxenException e) {
			return false;
		}
	}

	/**
	 * Test invalid sql throws exception.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testInvalidSqlThrowsException() throws Exception {
		try {
			JSql.generateXpath("select * from");
			fail("should throw exception : sql invalid");
		} catch (JSQLParserException e) {
			// should should be
		}
	}

	/**
	 * Test select all.
	 *
	 * @throws JSQLParserException
	 *             the JSQL parser exception
	 */
	@Test
	public void testSelectAll() throws JSQLParserException {
		String xpath1 = JSql.generateXpath("select * from countries");
		String xpath2 = JSql.generateXpath("select * from country");
		assertEquals("//countries/.", xpath1);
		assertEquals("//country/.", xpath2);
		assertTrue(isValidXpath(xpath1, xpath2));
	}

	/**
	 * Test select specific column.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testSelectSpecificColumn() throws Exception {
		String xpath1 = JSql.generateXpath("select name from countries");
		String xpath2 = JSql.generateXpath("select name from country");
		assertEquals("//countries/name", xpath1);
		assertEquals("//country/name", xpath2);
		assertTrue(isValidXpath(xpath1, xpath2));
	}

	/**
	 * Test select specific column and alias.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testSelectSpecificColumnAndAlias() throws Exception {
		String xpath1 = JSql.generateXpath("select c.name from countries as c");
		String xpath2 = JSql.generateXpath("select c.name from country as c");
		assertEquals("//countries/name", xpath1);
		assertEquals("//country/name", xpath2);
		assertTrue(isValidXpath(xpath1, xpath2));
	}

	/**
	 * Test select with multiple columns.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testSelectWithMultipleColumns() throws Exception {
		String xpath1 = JSql
				.generateXpath("select name, drink, food from country");
		String xpath2 = JSql.generateXpath("select drink, name from country");
		assertEquals("//country/food|//country/drink|//country/name", xpath1);
		assertEquals("//country/name|//country/drink", xpath2);
		assertTrue(isValidXpath(xpath1, xpath2));
	}

	/**
	 * Test select with multiple columns and alias.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testSelectWithMultipleColumnsAndAlias() throws Exception {
		String xpath1 = JSql
				.generateXpath("select c.name, c.drink, c.food from country as c");
		String xpath2 = JSql
				.generateXpath("select c.drink, c.name from country as c");
		assertEquals("//country/food|//country/drink|//country/name", xpath1);
		assertEquals("//country/name|//country/drink", xpath2);
		assertTrue(isValidXpath(xpath1, xpath2));
	}

	/**
	 * Test where clause.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testWhereClause() throws Exception {
		String xpath = JSql
				.generateXpath("select * from country where name='India'");
		assertEquals("//country[name/text()='India']/.", xpath);
		assertTrue(isValidXpath(xpath));

	}

	/**
	 * Test where clause with and.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testWhereClauseWithAnd() throws Exception {
		String xpath = JSql
				.generateXpath("select * from country where name='India' and food='roti'");
		assertEquals("//country[name/text()='India' and food/text()='roti']/.",
				xpath);
		assertTrue(isValidXpath(xpath));
	}

	/**
	 * Test where clause with and or.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testWhereClauseWithAndOR() throws Exception {
		String xpath = JSql
				.generateXpath("select * from country where name='India' and food='roti' or food='barfi'");
		assertEquals(
				"//country[name/text()='India' and food/text()='roti' or food/text()='barfi']/.",
				xpath);
		assertTrue(isValidXpath(xpath));
	}

	/**
	 * Test where clause with and or enclosed paren.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testWhereClauseWithAndOREnclosedParen() throws Exception {
		String xpath = JSql
				.generateXpath("select * from country where (name='India' and food='roti') or (food='barfi')");
		assertEquals(
				"//country[( name/text()='India' and food/text()='roti' ) or ( food/text()='barfi' )]/.",
				xpath);
		assertTrue(isValidXpath(xpath));
	}

	/**
	 * Test where clause with and or left paren.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testWhereClauseWithAndORLeftParen() throws Exception {
		String xpath = JSql
				.generateXpath("select * from country where (name='India' and food='roti') or food='barfi'");
		assertEquals(
				"//country[( name/text()='India' and food/text()='roti' ) or food/text()='barfi']/.",
				JSql.generateXpath("select * from country where (name='India' and food='roti') or food='barfi'"));
		assertTrue(isValidXpath(xpath));
	}

	/**
	 * Test where clause with and or right paren.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testWhereClauseWithAndORRightParen() throws Exception {
		String xpath = JSql
				.generateXpath("select * from country where name='India' and (food='roti' or food='barfi')");
		assertEquals(
				"//country[name/text()='India' and ( food/text()='roti' or food/text()='barfi' )]/.",
				xpath);
		assertTrue(isValidXpath(xpath));
	}

	/**
	 * Test where clause with paren.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testWhereClauseWithParen() throws Exception {
		String xpath = JSql
				.generateXpath("select * from country where (name='India' and food='roti')");
		assertEquals(
				"//country[( name/text()='India' and food/text()='roti' )]/.",
				xpath);
		assertTrue(isValidXpath(xpath));
	}
}
