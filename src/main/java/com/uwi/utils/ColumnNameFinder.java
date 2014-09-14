package com.uwi.utils;

import com.uwi.ds.KeyValue;
import com.uwi.visitors.abst.AbstractColumnNameFinder;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.AllColumns;
import net.sf.jsqlparser.statement.select.AllTableColumns;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <code>
 * The **ColumnNameFinder** class retrieves a list of column names from
 * SelectItem. Only the select portion of an SQL statement is processed by this class.
 * Currently only the **count** function has been implemented.
 * The SQL statement contains any other function an *IllegalArgumentException* with be thrown
 *
 * ##### Examples
 *
 * ```sql
 *  select * from
 *  # result [*]
 *
 *  select name, age, sex from
 *  # result [name, age, sex]
 *
 *  select c.name, c.age, c.sex from
 *  # result [name, age, sex]
 *
 *  select count(*)
 *  # result [count(*)]
 *
 *  select count(name)
 *  # result [count(name)]
 *
 *  select count(c.name)
 *  # result [count(name)]
 * ```
 * </code>
 */
public class ColumnNameFinder extends AbstractColumnNameFinder {

    /**
     * The columns_.
     */
    List<KeyValue> columns_;

    /**
     * The columns.
     */
    Set<String> columns; // handle duplicate columns

    /**
     * The output.
     */
    List<String> output;

    /**
     * The select items.
     */
    List<SelectItem> selectItems;

    /**
     * The current tb.
     */
    String currentTb;

    String whereClause;

    /**
     * Instantiates a new column name finder.
     *
     * @param selectItems
     *         the select items :- Anything between "SELECT" and "FROM"
     * @param table
     *         the table name
     * @param whereClause the whereClause
     *
     * ##### Example
     * ```sql
     *   where name = 'joe'
     * ```
     *
     */
    public ColumnNameFinder(List<SelectItem> selectItems, String table, String whereClause) {
        this.selectItems = selectItems;
        this.currentTb = table;
        this.whereClause = whereClause;
    }

    /**
     * Adds the * operator to the set. [*] represents <b>ALL_COLUMNS</b>
     */
    private void addAll() {
        columns.add(".");
    }

    /**
     * Retrieve table names from the list of {@link SelectItem}
     *
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
     * Initialize properties
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
     * net.sf.jsqlparser.statement.select.SelectItemVisitor#visit(net.sf.jsqlparser
     * .statement.select.SelectExpressionItem)
     */
    @Override
    public void visit(SelectExpressionItem selectExpressionItem) {
        selectExpressionItem.getExpression().accept(this);
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
            // only process count fn if it only as 1 expression
            if (expLs != null && expLs.getExpressions().size() > 1) {
                throw new IllegalArgumentException(i18n("c_count_single_param"));
            } else {

                // select portion of the query
                // select *
                // select name
                String selection = expLs == null ? "*" : expLs.getExpressions().get(0).toString();

                // count(//book[auth:ends-with (., 'James')]/author)
                if (Misc.isNotBlank(whereClause)) {
                    // check if expression is a LIKE
                    String parts[] = whereClause.split(":");
                    // if 2 parts then whereClause contains a like expression
                    if (parts.length == 2) {
                        columns.add(
                                i18n(
                                        "c_count_like_exp", currentTb, parts[0], parts[1]));
                    } else if (selection.equals("*")) {
                        // if select is ALL_COLUMNS
                        columns.add(i18n("c_count_exp", currentTb, whereClause));
                    } else {
                        columns.add(
                                i18n(
                                        "c_count_regex_where_param", currentTb, whereClause, selection));
                    }
                } else {
                    columns.add(
                            i18n(
                                    "c_count_regex_param", currentTb, selection));
                }
            }
        }
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
}
