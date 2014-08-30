package com.uwi.ds;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import net.sf.jsqlparser.expression.BinaryExpression;

import com.uwi.enums.ExpressionType;
import com.uwi.utils.LinkIdentifierGenerator;
import com.uwi.visitors.DefaultExpressionVisitor;

/**
 * The <code>BinaryExpressionTree</code> class is a {@link Queue} that stores
 * Expression traversal. When
 * {@link DefaultExpressionVisitor#visitBinaryExpression(BinaryExpression)} is
 * called, the expression is added to a {@link ArrayDeque} for later retrieval
 * and querying. When multiple binary traversals are done such as <b>select *
 * from country where name = 'Jamaica' and id = 99 or (name = 'Trinidad')</b>,
 * the expression are added as follows :
 *
 * 1. name = 'Jamaica' AND id = 99 OR name = 'Trinidad'
 *
 * 2. id = 99 OR name = 'Trinidad'
 *
 * 3. name = 'Trinidad' AND id = 99
 *
 * This allows a visitor to <i>peek</i> at the current expression and view the
 * left or right expression and operand string, allowing the code to be more
 * flexible. A visitor only ever need to look at the last expression since that
 * would be the currently processed expression.
 */
public class BinaryExpressionTree {

	/** The insert failed list. */
	private ArrayList<ExpressionPair> insertFailedList;

	/** The expressions. */
	private ArrayDeque<ExpressionPair> expressions;

	/**
	 * Instantiates a new binary expression tree.
	 */
	public BinaryExpressionTree() {
		expressions = new ArrayDeque<ExpressionPair>();
		insertFailedList = new ArrayList<ExpressionPair>();
	}

	/**
	 * Adds a {@link BinaryExpression} to the {@link ArrayDeque}
	 *
	 * @param exp
	 *            the exp
	 */
	public void add(BinaryExpression exp) {
		ExpressionPair pair = new ExpressionPair(exp);
		boolean result = expressions.add(pair);
		if (!result) {
			insertFailedList.add(pair);
		} else {
			System.out.println(pair.getType());
		}
	}

	/**
	 * Gets the {@link ArrayDeque} as a List
	 *
	 * @return the list
	 */
	public List<ExpressionPair> get() {
		return new ArrayList<ExpressionPair>(expressions);
	}

	/**
	 * Gets the insert failed list.
	 *
	 * @return the insert failed list
	 */
	public ArrayList<ExpressionPair> getInsertFailedList() {
		return insertFailedList;
	}

	/**
	 * Peek/Poll on the last expression
	 *
	 * @return the expression pair
	 */
	public ExpressionPair peek() {
		return expressions.peekLast();
	}

	/**
	 * Peek at expression.
	 *
	 * @return the binary expression
	 */
	public BinaryExpression peekAtExpression() {
		ExpressionPair pair;
		if ((pair = peek()) == null) {
			return null;
		}
		return pair.getExpression();
	}

	/**
	 * Peek at id.
	 *
	 * @return the string
	 */
	public String peekAtId() {
		ExpressionPair pair;
		if ((pair = peek()) == null) {
			return new LinkIdentifierGenerator().nextSessionId();
		}
		return pair.getId();
	}

	/**
	 * Peek at type.
	 *
	 * @return the expression type
	 */
	public ExpressionType peekAtType() {
		ExpressionPair pair;
		if ((pair = peek()) == null) {
			return ExpressionType.ANY;
		}
		return pair.getType();
	}

	/**
	 * Remove the last expression
	 */
	public void remove() {
		expressions.removeLast();
	}
}
