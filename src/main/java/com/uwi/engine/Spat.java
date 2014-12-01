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
 * <code>
 *  Spat is a facade for the JSQLParser that offers an easy API that is used by the CLI, GUI front-ends.
 * </code>
 */
public final class Spat {

    /**
     * <code>
     * Generates an XPATH expression from a given SQL statement.
     * If a valid SQL query is passed to Spat it will parse that expression
     * creating visitors which are then traversed. An Expression Tree is generated
     * from the SQL segments which are organized and converted to an XPATH expression.
     * If an invalid SQL statement is passed an exception will be thrown. This method
     * is mainly a helper method that is used by different interfaces & methods
     * > Please note that this expression has not been validated by **QueryXML** class
     *</code>
     * @param sql
     *         the sql statement
     *
     * @return unvalidated xpath expression
     *
     * @throws JSQLParserException
     *         the JSQL parser exception
     */
    public static String generateXpath(String sql) throws JSQLParserException {
        return visit(sql).getXPath();
    }

    /**
     * <code>
     * The following function takes an sql statement which is then parsed.
     * Unlike the *generateXpath* method, validation is performed on the XPATH
     * expression after generation. If the XPATH expression is valid, it will then
     * be passed to the internal XPATH Querying library which will query the supplied
     * XML file. The output is written to the terminal (CLI) and written to
     * an output file if supplied.
     * </code>
     *
     * @param xmlfile
     *         the xml file to be queried
     * @param sql
     *         the sql statement
     * @param type
     *         the ResultType to be returned. Please see <a href="https://github
     *         .com/ferronrsmith/jsqltoxml/blob/master/docs/ResultType.md"></a> for a listing of all supported types
     * @param outputFile
     *         the output file results are to be written to.
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
     * <code>
     * The following function takes an sql statement which is then parsed.
     * Unlike the *generateXpath* method, validation is performed on the XPATH
     * expression after generation. If the XPATH expression is valid, it will then
     * be passed to the internal XPATH Querying library which will query the supplied
     * XML file. The output is written as a list of string and written to
     * an output file if supplied.
     * </code>
     *
     * @param xmlfile
     *         the xml file to be queried
     * @param sql
     *         the sql statement
     * @param type
     *         the ResultType to be returned. Please see <a href="https://github
     *         .com/ferronrsmith/jsqltoxml/blob/master/docs/ResultType.md"></a> for a listing of all supported types
     * @param outputFile
     *         the output file results are to be written to.
     *
     * @return A list of xml nodes that match the xpath expression
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

    /**
     * Used for testing purposes only. this method return element ResultType
     * @internal
     * @throws JSQLParserException
     */
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
