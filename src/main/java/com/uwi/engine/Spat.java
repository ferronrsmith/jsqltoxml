package com.uwi.engine;

import com.uwi.enums.ResultType;
import com.uwi.utils.Misc;
import com.uwi.utils.QueryXML;
import com.uwi.visitors.DefaultSelectVisitor;
import com.uwi.visitors.abst.AbstractSelectVisitor;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectVisitor;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.dom4j.Element;

import java.util.List;

/**
 * The Class JSql.
 */
public final class Spat {

    /**
     * Xpath string.
     *
     * @param sql
     *         the sql
     *
     * @return the string
     *
     * @throws JSQLParserException
     *         the JSQL parser exception
     */
    public static String generateXpath(String sql) throws JSQLParserException {
        return visit(sql).getXPath();
    }

    /**
     * Parses the sql query and return a specific type.
     *
     * @param xmlfile
     *         the xmlfile
     * @param sql
     *         the sql
     * @param type
     *         the type
     * @param outputFile
     *         the output file
     *
     * @throws JSQLParserException
     *         the JSQL parser exception
     */
    public static void parse(String xmlfile, String sql, ResultType type, String outputFile)
            throws JSQLParserException {
        List<String> result = parseXML(xmlfile, sql, type, outputFile);
        if (result == null || result.size() == 0) {
            System.out.println("no result(s) found");
            return;
        }
        for (String aResult : result) {
            System.out.println(aResult);
        }
    }

    /**
     * Parses the xml.
     *
     * @param xmlfile
     *         the xmlfile
     * @param sql
     *         the sql
     * @param type
     *         the type
     * @param outputFile
     *         the output file
     *
     * @return the list
     *
     * @throws JSQLParserException
     *         the JSQL parser exception
     */
    public static List<String> parseXML(String xmlfile, String sql, ResultType type, String outputFile)
            throws JSQLParserException {

        if(type.equals(ResultType.ELEMENT)) {
            throw new IllegalArgumentException("Cannot process elements with this method!");
        }

        DefaultSelectVisitor visitor = visit(sql);
        List<String> result = new QueryXML().query(
                visitor.getXPath(), xmlfile, type).getData();

        Misc.saveToFile(outputFile, result);
        return result;
    }
    public static List<Element> getElements(String xmlfile, String sql) throws JSQLParserException {
        DefaultSelectVisitor visitor = visit(sql);
        return new QueryXML().query(
                visitor.getXPath(), xmlfile, ResultType.ELEMENT).getElements();
    }

    /**
     * {@link DefaultSelectVisitor} extend {@link AbstractSelectVisitor} which
     * implements {@link SelectVisitor} which wraps everything in a default
     * visitor that can be used to traverse an SQL Query.
     *
     * @param sql
     *         the sql
     *
     * @return the default select visitor
     *
     * @throws JSQLParserException
     *         the JSQL parser exception
     */
    private static DefaultSelectVisitor visit(String sql) throws JSQLParserException {
        try {
            Select stmt = (Select) CCJSqlParserUtil.parse(sql);
            DefaultSelectVisitor visitor = new DefaultSelectVisitor(
                    new TablesNamesFinder().getTableList(stmt));
            stmt.getSelectBody().accept(visitor);
            return visitor;
        } catch (Exception e) {
            e.printStackTrace();
            // bubble back up exception
            throw new JSQLParserException("Error parsing : " + sql, e);
        }
    }
}
