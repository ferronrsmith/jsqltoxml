package com.uwi.visitors;

import java.util.List;
import java.util.regex.Pattern;

import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.SelectItem;

import com.uwi.utils.ColumnNameFinder;
import com.uwi.utils.Misc;
import com.uwi.visitors.abst.AbstractSelectVisitor;

// TODO: Auto-generated Javadoc
/**
 * The Class DefaultSelectVisitor.
 */
public class DefaultSelectVisitor extends AbstractSelectVisitor {

	/** The select. */
	String xpath;

	/** The where clause. */
	String whereClause;

	/** The tables. */
	List<String> tables;

	/** The expression visitor. */
	DefaultExpressionVisitor expressionVisitor;

	/**
	 * Instantiates a new default select visitor.
	 *
	 * @param tables
	 *            the tables
	 */
	public DefaultSelectVisitor(List<String> tables) {
		this.tables = tables;
		expressionVisitor = new DefaultExpressionVisitor();
	}

	/**
	 * Adds the node.
	 *
	 * @param selectItems
	 *            the select items
	 * @param table
	 *            the table
	 * @return the string
	 */
	private String addNode(List<SelectItem> selectItems, String table) {
		StringBuilder sb = new StringBuilder();
		List<String> columns = new ColumnNameFinder(selectItems, table)
				.getColumnNames();
		for (int i = 0; i < columns.size(); i++) {
			// String.format("//%s/%s", table, columns.get(i))
			sb.append(formatOutput(table, columns.get(i), whereClause));
			if (i != columns.size() - 1) {
				sb.append("|");
			}
		}
		return sb.toString();
	}

	/**
	 * Format output.
	 *
	 * @param tb
	 *            the tb
	 * @param col
	 *            the col
	 * @param whereClause
	 *            the where clause
	 * @return the string
	 */
	public String formatOutput(String tb, String col, String whereClause) {
		String output = "";
		if (Misc.isBlank(whereClause)) {
			if (Pattern.matches("count\\(//\\w+/\\(?[\\w|\\*]+\\)\\)?", col)) {
				output = col;
			} else {
				output = String.format("//%s/%s", tb, col);
			}
		} else {
			// food:contains(., 'psi')
			String parts[] = whereClause.split(":");
			if (parts.length == 2) {
				output = String
						.format("//%s/%s[%s]/..", tb, parts[0], parts[1]);
			} else {
				output = String.format("//%s[%s]/%s", tb, whereClause, col);
			}
		}
		return output;
	}

	/**
	 * Gets the select portion of SQL query.
	 *
	 * @return the select
	 */
	public String getXPath() {
		return xpath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.statement.select.SelectVisitor#visit(net.sf.jsqlparser
	 * .statement.select.PlainSelect)
	 */
	/**
	 * Visit.
	 *
	 * @param plainSelect
	 *            the plain select
	 */
	@Override
	public void visit(PlainSelect plainSelect) {
		whereClause = expressionVisitor.getWhereClause(plainSelect.getWhere());
		xpath = addNode(plainSelect.getSelectItems(), tables.get(0));
	}
}
