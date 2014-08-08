package com.uwi.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.AllColumns;
import net.sf.jsqlparser.statement.select.AllTableColumns;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItem;

import com.uwi.visitors.abst.AbstractColumnNameFinder;

// TODO: Auto-generated Javadoc
/**
 * The Class ColumnNameFinder.
 */
public class ColumnNameFinder extends AbstractColumnNameFinder {

	/** The columns_. */
	List<KeyValue> columns_;

	/** The columns. */
	Set<String> columns; // handle duplicate columns

	/** The output. */
	List<String> output;

	List<SelectItem> selectItems;

	String currentTb;

	public ColumnNameFinder(List<SelectItem> selectItems, String table) {
		this.selectItems = selectItems;
		this.currentTb = table;
	}

	/**
	 * Adds the * operator to the set.
	 */
	private void addAll() {
		columns.add(".");
	}

	/**
	 * Gets the table names.
	 *
	 * @param items
	 *            the items
	 * @return the table names
	 */
	public List<String> getColumnNames() {
		init();
		for (SelectItem selectItem : selectItems) {
			selectItem.accept(this);
		}
		output.addAll(columns);
		return output;
	}

	/**
	 * Inits the.
	 */
	private void init() {
		output = new ArrayList<String>();
		columns = new HashSet<String>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.statement.select.SelectItemVisitor#visit(net.sf.jsqlparser
	 * .statement.select.AllColumns)
	 */
	@Override
	public void visit(AllColumns allColumns) {
		addAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.statement.select.SelectItemVisitor#visit(net.sf.jsqlparser
	 * .statement.select.AllTableColumns)
	 */
	@Override
	public void visit(AllTableColumns allTableColumns) {
		addAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .schema.Column)
	 */
	@Override
	public void visit(Column tableColumn) {
		columns.add(tableColumn.getColumnName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.Function)
	 */
	@Override
	public void visit(Function function) {
		// TODO Auto-generated method stub
		if (function.getName().equalsIgnoreCase("count")) {
			ExpressionList expLs = function.getParameters();
			if (expLs != null && expLs.getExpressions().size() > 1) {
				throw new IllegalArgumentException(
						"Only 1 parameter is supported at this time. e.g. count(*)|count(name) not count(x,y,.)");
			} else {
				columns.add(String.format("count(//%s/%s)", currentTb,
						expLs == null ? "*" : expLs.getExpressions().get(0)
								.toString()));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.statement.select.SelectItemVisitor#visit(net.sf.jsqlparser
	 * .statement.select.SelectExpressionItem)
	 */
	@Override
	public void visit(SelectExpressionItem selectExpressionItem) {
		selectExpressionItem.getExpression().accept(this);
	}
}
