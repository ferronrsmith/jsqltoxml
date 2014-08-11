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
	public void testQueryAttribute() throws JSQLParserException {
		List<String> results = JSql.parseXML(bookXML,
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
	public void testQueryAttributeRetrieveCount() throws JSQLParserException {
		List<String> results = JSql.parseXML(bookXML,
				"select count(*) from book where attr_category = 'COOKING'",
				ResultType.STRING, "");
		assertEquals(1, results.size());
		assertEquals("1.0", results.get(0));
	}

	@Test
	public void testQueryAttributeRetrieveElement() throws JSQLParserException {
		List<String> results = JSql.parseXML(bookXML,
				"select title from book where attr_category = 'COOKING'",
				ResultType.STRING, "");
		assertEquals(1, results.size());
		assertEquals("Everyday Italian", results.get(0));
	}

	@Test
	public void testQueryAttributeRetrieveMultipleElement()
			throws JSQLParserException {
		List<String> results = JSql.parseXML(bookXML,
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
}
