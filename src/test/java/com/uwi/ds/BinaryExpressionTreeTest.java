package com.uwi.ds;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitor;
import net.sf.jsqlparser.expression.StringValue;

import org.junit.Before;
import org.junit.Test;

/**
 * The Class BinaryExpressionTreeTest.
 */
public class BinaryExpressionTreeTest {

	/** The expression tree. */
	BinaryExpressionTree expressionTree;

	/**
	 * Deque.
	 */
	@Test
	public void deque() {
		expressionTree.add(new TestExpression(new StringValue("'country'"),
				new StringValue("'countries'")));
		expressionTree.add(new TestExpression(new StringValue("'mountain'"),
				new StringValue("'caves'")));
		expressionTree.remove();
		List<ExpressionPair> exp = expressionTree.get();
		assertEquals(1, exp.size());
		ExpressionPair expPair = exp.get(0);
		BinaryExpression bExp = expPair.getExpression();
		assertEquals("country",
				((StringValue) bExp.getLeftExpression()).getValue());
		assertEquals("countries",
				((StringValue) bExp.getRightExpression()).getValue());
		assertNotNull(expPair.getId());
	}

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		expressionTree = new BinaryExpressionTree();
	}
}

class TestExpression extends BinaryExpression {

	public TestExpression(Expression l, Expression r) {
		setLeftExpression(l);
		setRightExpression(r);
	}

	@Override
	public void accept(ExpressionVisitor expressionVisitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getStringExpression() {
		return super.toString();
	}

}
