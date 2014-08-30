package com.uwi.config;

import static org.junit.Assert.assertEquals;

import java.util.List;

import net.sf.jsqlparser.JSQLParserException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.uwi.enums.ResultType;

public class XMLQueryTest {

	String countryXMl;
	String bookXML;

	@Before
	public void setUp() throws Exception {
		countryXMl = "xml/countries.xml";
		bookXML = "xml/books.xml";
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCountRoot() throws JSQLParserException {
		List<String> results = Spat.parseXML(bookXML,
				"select count(book) from bookstore", ResultType.STRING, "");
		assertEquals(1, results.size());
		assertEquals("4", results.get(0));
	}

	@Test
	public void testElementCount() throws Exception {
		List<String> results = Spat.parseXML(bookXML,
				"select count(*) from book where attr_category = 'COOKING'",
				ResultType.STRING, "");
		assertEquals(1, results.size());
		assertEquals("1", results.get(0));
	}

	@Test
	public void testQuerLangyAttributeRetrieveCount()
			throws JSQLParserException {
		List<String> results = Spat.parseXML(bookXML,
				"select count(*) from title where attr_lang = 'en'",
				ResultType.STRING, "");
		assertEquals(1, results.size());
		assertEquals("4", results.get(0));
	}

	@Test
	public void testQuerLangyAttributeRetrieveElement()
			throws JSQLParserException {
		List<String> results = Spat.parseXML(bookXML,
				"select * from title where attr_lang = 'en'", ResultType.XML,
				"");
		assertEquals(4, results.size());
		assertEquals("[<title lang=\"en\">Everyday Italian</title>, "
				+ "<title lang=\"en\">Harry Potter</title>, "
				+ "<title lang=\"en\">XQuery Kick Start</title>, "
				+ "<title lang=\"en\">Learning XML</title>]",
				results.toString());
	}

	@Test
	public void testQueryAttribute() throws JSQLParserException {
		List<String> results = Spat.parseXML(bookXML,
				"select * from book where attr_category = 'COOKING'",
				ResultType.XML, "");
		assertEquals(1, results.size());
		assertEquals("<book category=\"COOKING\"> \n"
				+ "  <title lang=\"en\">Everyday Italian</title>  \n"
				+ "  <author>Giada De Laurentiis</author>  \n"
				+ "  <year>2005</year>  \n" + "  <price>30.00</price> \n"
				+ "</book>", results.get(0));
	}

	@Test
	public void testQueryAttributeForNothing() throws JSQLParserException {
		List<String> results = Spat.parseXML(bookXML, "select * from country",
				ResultType.XML, "");
		assertEquals(0, results.size());
		assertEquals("[]", results.toString());
	}

	@Test
	public void testQueryAttributeRetrieveCount() throws JSQLParserException {
		List<String> results = Spat.parseXML(bookXML,
				"select count(*) from book where attr_category = 'COOKING'",
				ResultType.STRING, "");
		assertEquals(1, results.size());
		assertEquals("1", results.get(0));
	}

	@Test
	public void testQueryAttributeRetrieveData() throws JSQLParserException {
		List<String> results = Spat.parseXML(bookXML,
				"select author, price from book where attr_category = 'WEB'",
				ResultType.XML, "");
		assertEquals(8, results.size());
		assertEquals("[<author>James McGovern</author>, "
				+ "<author>Per Bothner</author>, "
				+ "<author>Kurt Cagle</author>, "
				+ "<author>James Linn</author>, "
				+ "<author>Vaidyanathan Nagarajan</author>, "
				+ "<price>49.99</price>, " + "<author>Erik T. Ray</author>, "
				+ "<price>39.95</price>]", results.toString());
	}

	@Test
	public void testQueryAttributeRetrieveDataAndConditional()
			throws JSQLParserException {
		List<String> results = Spat
				.parseXML(
						bookXML,
						"select author, price from book where attr_category = 'WEB' and year = 2003",
						ResultType.XML, "");
		assertEquals(8, results.size());
		assertEquals("[<author>James McGovern</author>, "
				+ "<author>Per Bothner</author>, "
				+ "<author>Kurt Cagle</author>, "
				+ "<author>James Linn</author>, "
				+ "<author>Vaidyanathan Nagarajan</author>, "
				+ "<price>49.99</price>, " + "<author>Erik T. Ray</author>, "
				+ "<price>39.95</price>]", results.toString());
	}

	@Test
	public void testQueryAttributeRetrieveElementCount()
			throws JSQLParserException {
		List<String> results = Spat.parseXML(bookXML,
				"select count(author) from book", ResultType.STRING, "");
		assertEquals(1, results.size());
		assertEquals("8", results.get(0));
	}

	@Test
	public void testQueryAttributeRetrieveElementCountWithCondition()
			throws JSQLParserException {
		List<String> results = Spat.parseXML(bookXML,
				"select count(author) from book where year = 2003",
				ResultType.STRING, "");
		assertEquals(1, results.size());
		assertEquals("6", results.get(0));
	}

	@Test
	public void testQueryAttributeRetrieveElementCountWithConditionals()
			throws JSQLParserException {
		List<String> results = Spat
				.parseXML(
						bookXML,
						"select count(author) from book where price > 30.00 and price < 40.00",
						ResultType.STRING, "");
		assertEquals(1, results.size());
		assertEquals("1", results.get(0));
	}

	@Test
	public void testQueryAttributeRetrieveElementCountWithConditionCreate()
			throws JSQLParserException {
		List<String> results = Spat.parseXML(bookXML,
				"select count(author) from book where year >= 2005",
				ResultType.STRING, "");
		assertEquals(1, results.size());
		assertEquals("2", results.get(0));
	}

	@Test
	public void testQueryAttributeRetrieveElementCountWithLIKECondition()
			throws JSQLParserException {
		List<String> results = Spat.parseXML(bookXML,
				"select count(author) from book where author LIKE '%James'",
				ResultType.STRING, "");
		assertEquals(1, results.size());
		assertEquals("2", results.get(0));
	}

	@Test
	public void testQueryAttributeRetrieveElementWithConditionals()
			throws JSQLParserException {
		List<String> results = Spat
				.parseXML(
						bookXML,
						"select author from book where price > 30.00 and price < 40.00",
						ResultType.STRING, "");
		assertEquals(1, results.size());
		assertEquals("Erik T. Ray", results.get(0));
	}

	@Test
	public void testQueryAttributeRetrieveElementWithConditionGreate()
			throws JSQLParserException {
		List<String> results = Spat.parseXML(bookXML,
				"select author from book where year >= 2005", ResultType.XML,
				"");
		assertEquals(2, results.size());
		assertEquals(
				"[<author>Giada De Laurentiis</author>, <author>J K. Rowling</author>]",
				results.toString());
	}

	@Test
	public void testQueryAttributeRetrieveElementWithLIKECondition()
			throws JSQLParserException {
		List<String> results = Spat.parseXML(bookXML,
				"select * from book where author LIKE '%James'",
				ResultType.XML, "");
		assertEquals(1, results.size());
		assertEquals("<book category=\"WEB\"> \n"
				+ "  <title lang=\"en\">XQuery Kick Start</title>  \n"
				+ "  <author>James McGovern</author>  \n"
				+ "  <author>Per Bothner</author>  \n"
				+ "  <author>Kurt Cagle</author>  \n"
				+ "  <author>James Linn</author>  \n"
				+ "  <author>Vaidyanathan Nagarajan</author>  \n"
				+ "  <year>2003</year>  \n" + "  <price>49.99</price> \n"
				+ "</book>", results.get(0).trim());
	}

	@Test
	public void testQueryAttributeRetrieveElementWithLIKEConditionSpecificColumns()
			throws JSQLParserException {
		List<String> results = Spat.parseXML(bookXML,
				"select year, title from book where author LIKE '%James'",
				ResultType.XML, "");
		assertEquals(2, results.size());
		assertEquals("<title lang=\"en\">XQuery Kick Start</title>", results
				.get(0).trim());
		assertEquals("<year>2003</year>", results.get(1).trim());
	}

	@Test
	public void testQueryAttributeRetrieveElementWithLIKEContainsCondition()
			throws JSQLParserException {
		List<String> results = Spat.parseXML(bookXML,
				"select * from book where author LIKE '%Nagarajan%'",
				ResultType.XML, "");
		assertEquals(1, results.size());
		assertEquals("<book category=\"WEB\"> \n"
				+ "  <title lang=\"en\">XQuery Kick Start</title>  \n"
				+ "  <author>James McGovern</author>  \n"
				+ "  <author>Per Bothner</author>  \n"
				+ "  <author>Kurt Cagle</author>  \n"
				+ "  <author>James Linn</author>  \n"
				+ "  <author>Vaidyanathan Nagarajan</author>  \n"
				+ "  <year>2003</year>  \n" + "  <price>49.99</price> \n"
				+ "</book>", results.get(0).trim());
	}

	@Test
	public void testQueryAttributeRetrieveElementWithLIKEContainsConditionSelectElem()
			throws JSQLParserException {
		List<String> results = Spat.parseXML(bookXML,
				"select price from book where author LIKE '%Nagarajan%'",
				ResultType.XML, "");
		assertEquals(1, results.size());
		assertEquals("<price>49.99</price>", results.get(0).trim());
	}

	@Test
	public void testQueryAttributeRetrieveMultipleElement()
			throws JSQLParserException {
		List<String> results = Spat.parseXML(bookXML,
				"select author from book", ResultType.XML, "");
		assertEquals(8, results.size());
		assertEquals("[<author>Giada De Laurentiis</author>, "
				+ "<author>J K. Rowling</author>, "
				+ "<author>James McGovern</author>, "
				+ "<author>Per Bothner</author>, "
				+ "<author>Kurt Cagle</author>, "
				+ "<author>James Linn</author>, "
				+ "<author>Vaidyanathan Nagarajan</author>, "
				+ "<author>Erik T. Ray</author>]", results.toString());
	}

	@Test
	public void testQueryAttributeRetrieveMultipleElements()
			throws JSQLParserException {
		List<String> results = Spat.parseXML(bookXML,
				"select price, author from book", ResultType.XML, "");
		assertEquals(12, results.size());
		assertEquals("[<author>Giada De Laurentiis</author>, "
				+ "<price>30.00</price>, " + "<author>J K. Rowling</author>, "
				+ "<price>29.99</price>, "
				+ "<author>James McGovern</author>, "
				+ "<author>Per Bothner</author>, "
				+ "<author>Kurt Cagle</author>, "
				+ "<author>James Linn</author>, "
				+ "<author>Vaidyanathan Nagarajan</author>, "
				+ "<price>49.99</price>, " + "<author>Erik T. Ray</author>, "
				+ "<price>39.95</price>]", results.toString());
	}

}
