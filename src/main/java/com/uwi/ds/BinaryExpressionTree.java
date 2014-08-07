package com.uwi.ds;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import net.sf.jsqlparser.expression.BinaryExpression;

import com.uwi.enums.ExpressionType;
import com.uwi.utils.LinkIdentifierGenerator;

// TODO: Auto-generated Javadoc
/**
 * The Class BinaryExpressionTree.
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
	 * Adds the.
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
	 * Gets the.
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

	public ExpressionPair peek() {
		return expressions.peekLast();
	}

	public BinaryExpression peekAtExpression() {
		ExpressionPair pair;
		if ((pair = peek()) == null) {
			return null;
		}
		return pair.getExpression();
	}

	public String peekAtId() {
		ExpressionPair pair;
		if ((pair = peek()) == null) {
			return new LinkIdentifierGenerator().nextSessionId();
		}
		return pair.getId();
	}

	public ExpressionType peekAtType() {
		ExpressionPair pair;
		if ((pair = peek()) == null) {
			return ExpressionType.ANY;
		}
		return pair.getType();
	}

	/**
	 * Removes the.
	 */
	public void remove() {
		expressions.removeLast();
	}

}
