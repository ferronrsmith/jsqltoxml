package com.uwi.config;

import java.util.List;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectVisitor;
import net.sf.jsqlparser.util.TablesNamesFinder;

import org.dom4j.Element;

import com.uwi.utils.Misc;
import com.uwi.utils.QueryXML;
import com.uwi.utils.QueryXML.Type;
import com.uwi.visitors.AbstractSelectVisitor;
import com.uwi.visitors.DefaultSelectVisitor;

// TODO: Auto-generated Javadoc
/**
 * The Class JSql.
 */
/**
 * @author ferron
 *
 */
public class JSql {

	/**
	 * Parses the sql query and return an element.
	 *
	 * @param xmlfile
	 *            the xmlfile
	 * @param sql
	 *            the sql
	 * @throws JSQLParserException
	 *             the JSQL parser exception
	 */
	public static void parse(String xmlfile, String sql)
			throws JSQLParserException {
		DefaultSelectVisitor visitor = visit(sql);
		List<Element> result = new QueryXML<Element>().query(
				visitor.getSelect(), xmlfile, QueryXML.Type.ELEMENT);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
		}
	}

	/**
	 * Parses the sql query and return a specific type.
	 *
	 * @param xmlfile
	 *            the xmlfile
	 * @param sql
	 *            the sql
	 * @param type
	 *            the type
	 * @param outputFile
	 *            the output file
	 * @throws JSQLParserException
	 *             the JSQL parser exception
	 */
	public static void parse(String xmlfile, String sql, Type type,
			String outputFile) throws JSQLParserException {
		List<String> result = parseXML(xmlfile, sql, type, outputFile);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
		}
	}

	/**
	 * Parses the xml.
	 *
	 * @param xmlfile
	 *            the xmlfile
	 * @param sql
	 *            the sql
	 * @param type
	 *            the type
	 * @param outputFile
	 *            the output file
	 * @return the list
	 * @throws JSQLParserException
	 *             the JSQL parser exception
	 */
	public static List<String> parseXML(String xmlfile, String sql, Type type,
			String outputFile) throws JSQLParserException {
		DefaultSelectVisitor visitor = visit(sql);
		List<String> result = new QueryXML<String>().query(visitor.getSelect(),
				xmlfile, type);

		Misc.saveToFile(outputFile, result);
		return result;
	}

	/**
	 * {@link DefaultSelectVisitor} extend {@link AbstractSelectVisitor} which
	 * implements {@link SelectVisitor} which wraps everything in a default
	 * visitor that can be used to traverse an SQL Query.
	 *
	 * @param sql
	 *            the sql
	 * @return the default select visitor
	 * @throws JSQLParserException
	 *             the JSQL parser exception
	 */
	private static DefaultSelectVisitor visit(String sql)
			throws JSQLParserException {
		try {
			Select stmt = (Select) CCJSqlParserUtil.parse(sql);
			DefaultSelectVisitor visitor = new DefaultSelectVisitor(
					new TablesNamesFinder().getTableList(stmt));
			stmt.getSelectBody().accept(visitor);
			return visitor;
		} catch (Exception e) {
			throw new JSQLParserException("Error parsing : " + sql);
		}
	}
}
