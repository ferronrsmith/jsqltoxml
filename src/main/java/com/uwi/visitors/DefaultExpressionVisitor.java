package com.uwi.visitors;

import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.GreaterThan;
import net.sf.jsqlparser.expression.operators.relational.GreaterThanEquals;
import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
import net.sf.jsqlparser.expression.operators.relational.MinorThan;
import net.sf.jsqlparser.expression.operators.relational.MinorThanEquals;
import net.sf.jsqlparser.expression.operators.relational.NotEqualsTo;

import com.uwi.ds.BinaryExpressionTree;
import com.uwi.ds.ExpressionHash;
import com.uwi.enums.ExpressionType;
import com.uwi.utils.KeyValue;
import com.uwi.utils.LinkIdentifierGenerator;
import com.uwi.visitors.abst.AbstractExpressionVisitor;

// TODO: Auto-generated Javadoc
/**
 * The Class DefaultExpressionVisitor.
 */
public class DefaultExpressionVisitor extends AbstractExpressionVisitor {

	/** The has paren. */
	private boolean hasParen = false;

	/** The columns. */
	// List<KeyValue> columns; // handle duplicate columns

	/** The columns. */
	ExpressionHash _temp; // utility hash for saving data
	// for later

	/** The tree. */
	ExpressionHash tree;

	/** The expression tree. */
	BinaryExpressionTree expressionTree;

	/**
	 * Gets the where clause.
	 *
	 * @param exp
	 *            the exp
	 * @return the where clause
	 */
	public String getWhereClause(Expression exp) {
		// don't traverse if null
		if (exp == null) {
			return null;
		}
		init();
		exp.accept(this);
		// [{and,name/text()='India'}, {and,food/text()='roti'}]
		return tree.merge();
	}

	/**
	 * Inits the.
	 */
	private void init() {
		// columns = new ArrayList<KeyValue>();
		_temp = new ExpressionHash();
		expressionTree = new BinaryExpressionTree();
		tree = new ExpressionHash();
	}

	/**
	 * Like operand.
	 *
	 * @param be
	 *            the be
	 * @return the string
	 */
	private String likeOperand(BinaryExpression be) {
		String left = be.getLeftExpression().toString();
		String right = be.getRightExpression().toString().replaceAll("'", "");
		String result = "";
		char fst = right.charAt(0);
		char lst = right.charAt(right.length() - 1);
		right = right.replaceAll("%", "");
		if (fst == '%' && lst == '%') {
			result = String.format("contains (., '%s')", right);
		} else if (fst == '%' && lst != '%') {
			result = String.format("starts-with (., '%s')", right);
		} else if (fst != '%' && lst == '%') {
			result = String.format("ends-with (., '%s')", right);
		}
		return String.format("%s:%s", left, result);
	}

	/**
	 * Manage operands.
	 *
	 * @param exp
	 *            the exp
	 * @param isNot
	 *            the is not
	 */
	private void manageOperands(BinaryExpression exp) {
		String equalTo = null;
		switch (exp.getStringExpression()) {
		case "!=":
			equalTo = String.format("not(%s/text()=%s)",
					exp.getLeftExpression(), exp.getRightExpression());
			break;
		case "=":
			equalTo = String.format("%s/text()=%s", exp.getLeftExpression(),
					exp.getRightExpression());
			break;
		case ">":
		case "<":
		case ">=":
		case "<=":
			equalTo = String.format("%s%s%s", exp.getLeftExpression(),
					exp.getStringExpression(), exp.getRightExpression());
			break;
		default:
			throw new IllegalArgumentException(
					"Cannot parse string expression : "
							+ exp.getStringExpression());
		}

		// check if currentType is null
		KeyValue keyValue = new KeyValue(
				expressionTree.peekAtType().getValue(), equalTo,
				expressionTree.peekAtId());

		if (hasParen) {
			_temp.add(keyValue);
		} else {
			tree.add(keyValue);
		}
	}

	/**
	 * Merge paren records.
	 */
	public void mergeParenRecords() {
		// select * from country where food='roti' and (food='roti' or
		// food='hotdog')
		tree.add(_temp.cherryPick(new KeyValue().setValue(_temp.mergeParen())));
		_temp.clear();
	}

	/**
	 * Visit.
	 *
	 * @param andExpression
	 *            the and expression
	 */
	@Override
	public void visit(AndExpression andExpression) {
		visitBinaryExpression(andExpression);
	}

	/**
	 * Visit.
	 *
	 * @param equalsTo
	 *            the equals to
	 */
	@Override
	public void visit(EqualsTo equalsTo) {
		manageOperands(equalsTo);
	}

	/**
	 * Visit.
	 *
	 * @param greaterThan
	 *            the greater than
	 */
	@Override
	public void visit(GreaterThan greaterThan) {
		manageOperands(greaterThan);
	}

	/**
	 * Visit.
	 *
	 * @param greaterThanEquals
	 *            the greater than equals
	 */
	@Override
	public void visit(GreaterThanEquals greaterThanEquals) {
		manageOperands(greaterThanEquals);
	}

	/**
	 * Visit.
	 *
	 * @param likeExpression
	 *            the like expression
	 */
	@Override
	public void visit(LikeExpression likeExpression) {
		// TODO Auto-generated method stub
		tree.add(new KeyValue(ExpressionType.LIKE.getValue(),
				likeOperand(likeExpression), new LinkIdentifierGenerator()
						.nextSessionId()));
	}

	/**
	 * Visit.
	 *
	 * @param minorThan
	 *            the minor than
	 */
	@Override
	public void visit(MinorThan minorThan) {
		manageOperands(minorThan);
	}

	/**
	 * Visit.
	 *
	 * @param minorThanEquals
	 *            the minor than equals
	 */
	@Override
	public void visit(MinorThanEquals minorThanEquals) {
		manageOperands(minorThanEquals);
	}

	/**
	 * Visit.
	 *
	 * @param notEqualsTo
	 *            the not equals to
	 */
	@Override
	public void visit(NotEqualsTo notEqualsTo) {
		manageOperands(notEqualsTo);
	}

	/**
	 * Visit.
	 *
	 * @param orExpression
	 *            the or expression
	 */
	@Override
	public void visit(OrExpression orExpression) {
		visitBinaryExpression(orExpression);
	}

	/**
	 * Visit.
	 *
	 * @param parenthesis
	 *            the parenthesis
	 */
	@Override
	public void visit(Parenthesis parenthesis) {
		hasParen = true;
		parenthesis.getExpression().accept(this);
		hasParen = false;
		mergeParenRecords();
	}

	/**
	 * Visit binary expression.
	 *
	 * @param binaryExpression
	 *            the binary expression
	 */
	public void visitBinaryExpression(BinaryExpression binaryExpression) {
		expressionTree.add(binaryExpression);
		binaryExpression.getLeftExpression().accept(this);
		binaryExpression.getRightExpression().accept(this);
		expressionTree.remove();
	}

}
