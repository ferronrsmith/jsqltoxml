package com.uwi.engine;

import com.uwi.engine.Spat;
import com.uwi.utils.Misc;
import net.sf.jsqlparser.JSQLParserException;
import org.jaxen.JaxenException;
import org.jaxen.dom4j.Dom4jXPath;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

// TODO: Auto-generated Javadoc

/**
 * The Class JSqlTest.
 */
public class SpatTest {

    /**
     * Checks if is in valid xpath.
     *
     * @throws Exception
     *         the exception
     */
    @Test
    public void isInValidXpath() throws Exception {
        assertFalse(isValidXpath("//countries[contains(.,'people']]"));
    }

    /**
     * Checks if is valid xpath.
     *
     * @throws Exception
     *         the exception
     */
    @Test
    public void isValidXpath() throws Exception {
        assertTrue(isValidXpath("//countries[contains(.,'people')]"));
    }

    /**
     * Checks if is valid xpath.
     *
     * @param paths
     *         the paths
     *
     * @return true, if is valid xpath
     */
    private boolean isValidXpath(String... paths) {
        boolean result = true;
        for (String path : paths) {
            result &= isValidXpath(path);
        }
        return result;
    }

    /**
     * Checks if is valid xpath.
     *
     * @param xpath
     *         the xpath
     *
     * @return true, if is valid xpath
     */
    private boolean isValidXpath(String xpath) {
        try {
            return Misc.isNotBlank(new Dom4jXPath(xpath).toString());
        } catch (JaxenException e) {
            return false;
        }
    }

    /**
     * Test attribute querying.
     *
     * @throws Exception
     *         the exception
     */
    @Test
    public void testAttributeQuerying() throws Exception {
        String xpath = Spat.generateXpath("select * from country where attr_name='India'");
        assertEquals("//country[@name='India']/.", xpath);
        assertTrue(isValidXpath(xpath));
    }

    @Test
    public void testAttributeQueryingWithCount() throws Exception {
        String xpath = Spat.generateXpath("select count(*) from book where attr_category = 'COOKING'");
        assertEquals("count(//book[@category='COOKING'])", xpath);
        assertTrue(isValidXpath(xpath));
    }

    /**
     * Test attribute querying.
     *
     * @throws Exception
     *         the exception
     */
    @Test
    public void testAttributeQueryingWithOr() throws Exception {
        String xpath = Spat.generateXpath("select * from country where attr_name='India' or attr_food = 'rice'");
        assertEquals("//country[@name='India' or @food='rice']/.", xpath);
        assertTrue(isValidXpath(xpath));
    }

    /**
     * Test attribute querying.
     *
     * @throws Exception
     *         the exception
     */
    @Test
    public void testAttributeQueryingWithOrAnd() throws Exception {
        String xpath = Spat.generateXpath(
                "select * from country where attr_name='India' or (attr_food = 'rice' and attr_food='pizza')");
        assertEquals(
                "//country[@name='India' or ( @food='rice' and @food='pizza' )]/.", xpath);
        assertTrue(isValidXpath(xpath));
    }

    /**
     * Test attribute querying.
     *
     * @throws Exception
     *         the exception
     */
    @Test
    public void testAttributeQueryingWithOrOr() throws Exception {
        String xpath = Spat.generateXpath(
                "select * from country where attr_name='India' or (attr_food = 'rice' or attr_food='pizza')");
        assertEquals(
                "//country[@name='India' or ( @food='rice' or @food='pizza' )]/.", xpath);
        assertTrue(isValidXpath(xpath));
    }

    /**
     * Test count all columns.
     *
     * @throws Exception
     *         the exception
     */
    @Test
    public void testCountAllColumns() throws Exception {
        String xpath = Spat.generateXpath("select count(*) from country");
        assertEquals("count(//country/*)", xpath);
        assertTrue(isValidXpath(xpath));
    }

    @Test
    public void testCountOperationWithWhereClause() throws Exception {
        String xpath = Spat.generateXpath("select count(*) from country where (name='India' and food='roti')");
        assertEquals(
                "count(//country[( name/text()='India' and food/text()='roti' )])", xpath);
        assertTrue(isValidXpath(xpath));
    }

    /**
     * Test count selected column.
     *
     * @throws Exception
     *         the exception
     */
    @Test
    public void testCountSelectedColumn() throws Exception {
        String xpath = Spat.generateXpath("select count(name) from country");
        assertEquals("count(//country/name)", xpath);
        assertTrue(isValidXpath(xpath));
    }

    @Test
    public void testCountSelectedColumnWithLikeContains() throws Exception {
        String xpath = Spat.generateXpath("select count(name) from country where author LIKE '%James%'");
        assertEquals("count(//country/author[contains (., 'James')])", xpath);
        assertTrue(isValidXpath(xpath));
    }

    @Test
    public void testCountSelectedColumnWithLikeEnd() throws Exception {
        String xpath = Spat.generateXpath("select count(name) from country where author LIKE 'James%'");
        assertEquals("count(//country/author[ends-with (., 'James')])", xpath);
        assertTrue(isValidXpath(xpath));
    }

    @Test
    public void testCountSelectedColumnWithLikeStar() throws Exception {
        String xpath = Spat.generateXpath("select count(name) from country where author LIKE '%James'");
        assertEquals("count(//country/author[starts-with (., 'James')])", xpath);
        assertTrue(isValidXpath(xpath));
    }

    @Test
    public void testCountSelectedColumnWithRange() throws Exception {
        String xpath = Spat.generateXpath("select count(name) from country where price >= 10 and price <= 10");
        assertEquals("count(//country[price>=10 and price<=10]/name)", xpath);
        assertTrue(isValidXpath(xpath));
    }

    @Test
    public void testCountSelectedColumnWithWhere() throws Exception {
        String xpath = Spat.generateXpath("select count(name) from country where price = 10");
        assertEquals("count(//country[price=10]/name)", xpath);
        assertTrue(isValidXpath(xpath));
    }

    @Test
    public void testCountSelectedColumnWithWhereAnd() throws Exception {
        String xpath = Spat.generateXpath("select count(name) from country where price = 10 and name = 'Tokyo'");
        assertEquals("count(//country[price=10 and name/text()='Tokyo']/name)", xpath);
        assertTrue(isValidXpath(xpath));
    }

    /**
     * Test count throws exception with multiple columns.
     *
     * @throws Exception
     *         the exception
     */
    @Test
    public void testCountThrowsExceptionWithMultipleColumns() throws Exception {
        try {
            Spat.generateXpath("select count(name, drink) from country");
            fail("should not support multi-range count");
        } catch (Exception e) {
            // should not get here
        }
    }

    /**
     * Test greater than expression expression.
     *
     * @throws Exception
     *         the exception
     */
    @Test
    public void testGreaterThanExpressionExpression() throws Exception {
        String xpath = Spat.generateXpath("select title from book where price > 30.00");
        assertEquals("//book[price>30.00]/title", xpath);
        assertTrue(isValidXpath(xpath));
    }

    /**
     * Test greater than or equal expression expression.
     *
     * @throws Exception
     *         the exception
     */
    @Test
    public void testGreaterThanOrEqualExpressionExpression() throws Exception {
        String xpath = Spat.generateXpath("select title from book where price >= 30.00");
        assertEquals("//book[price>=30.00]/title", xpath);
        assertTrue(isValidXpath(xpath));
    }

    /**
     * Test invalid sql throws exception.
     *
     * @throws Exception
     *         the exception
     */
    @Test
    public void testInvalidSqlThrowsException() throws Exception {
        try {
            Spat.generateXpath("select * from");
            fail("should throw exception : sql invalid");
        } catch (JSQLParserException e) {
            // should should be
        }
    }

    /**
     * Test less than expression expression.
     *
     * @throws Exception
     *         the exception
     */
    @Test
    public void testLessThanExpressionExpression() throws Exception {
        String xpath = Spat.generateXpath("select title from book where price < 30.00");
        assertEquals("//book[price<30.00]/title", xpath);
        assertTrue(isValidXpath(xpath));
    }

    /**
     * Test less than or equal to expression expression.
     *
     * @throws Exception
     *         the exception
     */
    @Test
    public void testLessThanOrEqualToExpressionExpression() throws Exception {
        String xpath = Spat.generateXpath("select title from book where price <= 30.00");
        assertEquals("//book[price<=30.00]/title", xpath);
        assertTrue(isValidXpath(xpath));
    }

    /**
     * Test like contains.
     *
     * @throws Exception
     *         the exception
     */
    @Test
    public void testLikeContains() throws Exception {
        String xpath = Spat.generateXpath("select * from country where food LIKE '%hot%'");
        assertEquals("//country/food[contains (., 'hot')]/..", xpath);
        assertTrue(isValidXpath(xpath));
    }

    @Test
    public void testLikeContainsSelect() throws Exception {
        String xpath = Spat.generateXpath("select name, age from country where food LIKE '%hot%'");
        assertEquals(
                "//country/food[contains (., 'hot')]/../age|//country/food[contains (., 'hot')]/../name", xpath);
        assertTrue(isValidXpath(xpath));
    }

    /**
     * Test like ends with.
     *
     * @throws Exception
     *         the exception
     */
    @Test
    public void testLikeEndsWith() throws Exception {
        String xpath = Spat.generateXpath("select * from country where food LIKE 'dog%'");
        assertEquals("//country/food[ends-with (., 'dog')]/..", xpath);
        assertTrue(isValidXpath(xpath));
    }

    @Test
    public void testLikeEndsWithSelect() throws Exception {
        String xpath = Spat.generateXpath("select name, age from country where food LIKE 'dog%'");
        assertEquals(
                "//country/food[ends-with (., 'dog')]/../age|//country/food[ends-with (., 'dog')]/../name", xpath);
        assertTrue(isValidXpath(xpath));
    }

    /**
     * Test like starts with.
     *
     * @throws Exception
     *         the exception
     */
    @Test
    public void testLikeStartsWith() throws Exception {
        String xpath = Spat.generateXpath("select * from country where food LIKE '%hot'");
        assertEquals("//country/food[starts-with (., 'hot')]/..", xpath);
        assertTrue(isValidXpath(xpath));
    }

    @Test
    public void testLikeStartsWithSelect() throws Exception {
        String xpath = Spat.generateXpath("select name, age from country where food LIKE '%hot'");
        assertEquals(
                "//country/food[starts-with (., 'hot')]/../age|//country/food[starts-with (., 'hot')]/../name", xpath);
        assertTrue(isValidXpath(xpath));
    }

    /**
     * Test select all.
     *
     * @throws JSQLParserException
     *         the JSQL parser exception
     */
    @Test
    public void testSelectAll() throws JSQLParserException {
        String xpath1 = Spat.generateXpath("select * from countries");
        String xpath2 = Spat.generateXpath("select * from country");
        assertEquals("//countries/.", xpath1);
        assertEquals("//country/.", xpath2);
        assertTrue(isValidXpath(xpath1, xpath2));
    }

    /**
     * Test select specific column.
     *
     * @throws Exception
     *         the exception
     */
    @Test
    public void testSelectSpecificColumn() throws Exception {
        String xpath1 = Spat.generateXpath("select name from countries");
        String xpath2 = Spat.generateXpath("select name from country");
        assertEquals("//countries/name", xpath1);
        assertEquals("//country/name", xpath2);
        assertTrue(isValidXpath(xpath1, xpath2));
    }

    /**
     * Test select specific column and alias.
     *
     * @throws Exception
     *         the exception
     */
    @Test
    public void testSelectSpecificColumnAndAlias() throws Exception {
        String xpath1 = Spat.generateXpath("select c.name from countries as c");
        String xpath2 = Spat.generateXpath("select c.name from country as c");
        assertEquals("//countries/name", xpath1);
        assertEquals("//country/name", xpath2);
        assertTrue(isValidXpath(xpath1, xpath2));
    }

    /**
     * Test select with multiple columns.
     *
     * @throws Exception
     *         the exception
     */
    @Test
    public void testSelectWithMultipleColumns() throws Exception {
        String xpath1 = Spat.generateXpath("select name, drink, food from country");
        String xpath2 = Spat.generateXpath("select drink, name from country");
        assertEquals("//country/food|//country/drink|//country/name", xpath1);
        assertEquals("//country/name|//country/drink", xpath2);
        assertTrue(isValidXpath(xpath1, xpath2));
    }

    /**
     * Test select with multiple columns and alias.
     *
     * @throws Exception
     *         the exception
     */
    @Test
    public void testSelectWithMultipleColumnsAndAlias() throws Exception {
        String xpath1 = Spat.generateXpath("select c.name, c.drink, c.food from country as c");
        String xpath2 = Spat.generateXpath("select c.drink, c.name from country as c");
        assertEquals("//country/food|//country/drink|//country/name", xpath1);
        assertEquals("//country/name|//country/drink", xpath2);
        assertTrue(isValidXpath(xpath1, xpath2));
    }

    /**
     * Test where clause.
     *
     * @throws Exception
     *         the exception
     */
    @Test
    public void testWhereClause() throws Exception {
        String xpath = Spat.generateXpath("select * from country where name='India'");
        assertEquals("//country[name/text()='India']/.", xpath);
        assertTrue(isValidXpath(xpath));

    }

    /**
     * Test where clause with and.
     *
     * @throws Exception
     *         the exception
     */
    @Test
    public void testWhereClauseWithAnd() throws Exception {
        String xpath = Spat.generateXpath("select * from country where name='India' and food='roti'");
        assertEquals("//country[name/text()='India' and food/text()='roti']/.", xpath);
        assertTrue(isValidXpath(xpath));
    }

    /**
     * Test where clause with and or.
     *
     * @throws Exception
     *         the exception
     */
    @Test
    public void testWhereClauseWithAndOR() throws Exception {
        String xpath = Spat.generateXpath("select * from country where name='India' and food='roti' or food='barfi'");
        assertEquals(
                "//country[name/text()='India' and food/text()='roti' or food/text()='barfi']/.", xpath);
        assertTrue(isValidXpath(xpath));
    }

    /**
     * Test where clause with and or enclosed paren.
     *
     * @throws Exception
     *         the exception
     */
    @Test
    public void testWhereClauseWithAndOREnclosedParen() throws Exception {
        String xpath = Spat.generateXpath(
                "select * from country where (name='India' and food='roti') or (food='barfi')");
        assertEquals(
                "//country[( name/text()='India' and food/text()='roti' ) or ( food/text()='barfi' )]/.", xpath);
        assertTrue(isValidXpath(xpath));
    }

    /**
     * Test where clause with and or left paren.
     *
     * @throws Exception
     *         the exception
     */
    @Test
    public void testWhereClauseWithAndORLeftParen() throws Exception {
        String xpath = Spat.generateXpath("select * from country where (name='India' and food='roti') or food='barfi'");
        assertEquals(
                "//country[( name/text()='India' and food/text()='roti' ) or food/text()='barfi']/.", xpath);
        assertTrue(isValidXpath(xpath));
    }

    /**
     * Test where clause with and or.
     *
     * @throws Exception
     *         the exception
     */
    @Test
    public void testWhereClauseWithAndOROR() throws Exception {
        String xpath = Spat.generateXpath(
                "select * from country where name='India' and food='roti' or food='barfi' or food='soup'");
        assertEquals(
                "//country[name/text()='India' and food/text()='roti' or food/text()='barfi' or food/text()='soup']/.",
                xpath);
        assertTrue(isValidXpath(xpath));
    }

    /**
     * Test where clause with and or right paren.
     *
     * @throws Exception
     *         the exception
     */
    @Test
    public void testWhereClauseWithAndORRightParen() throws Exception {
        String xpath = Spat.generateXpath("select * from country where name='India' and (food='roti' or food='barfi')");
        assertEquals(
                "//country[name/text()='India' and ( food/text()='roti' or food/text()='barfi' )]/.", xpath);
        assertTrue(isValidXpath(xpath));
    }

    /**
     * Test where clause with paren.
     *
     * @throws Exception
     *         the exception
     */
    @Test
    public void testWhereClauseWithParen() throws Exception {
        String xpath = Spat.generateXpath("select * from country where (name='India' and food='roti')");
        assertEquals(
                "//country[( name/text()='India' and food/text()='roti' )]/.", xpath);
        assertTrue(isValidXpath(xpath));
    }

    /**
     * Test where not equals.
     *
     * @throws Exception
     *         the exception
     */
    @Test
    public void testWhereNotEquals() throws Exception {
        String xpath = Spat.generateXpath("select * from country where (name!='India' and food!='roti')");
        assertEquals(
                "//country[( not(name/text()='India') and not(food/text()='roti') )]/.", xpath);
        assertTrue(isValidXpath(xpath));
    }
}
