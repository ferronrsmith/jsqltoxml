package com.uwi.visitors;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import net.sf.jsqlparser.expression.AllComparisonExpression;
import net.sf.jsqlparser.expression.AnalyticExpression;
import net.sf.jsqlparser.expression.AnyComparisonExpression;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.CaseExpression;
import net.sf.jsqlparser.expression.CastExpression;
import net.sf.jsqlparser.expression.DateValue;
import net.sf.jsqlparser.expression.DoubleValue;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitor;
import net.sf.jsqlparser.expression.ExtractExpression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.IntervalExpression;
import net.sf.jsqlparser.expression.JdbcNamedParameter;
import net.sf.jsqlparser.expression.JdbcParameter;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.OracleHierarchicalExpression;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.SignedExpression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.TimeValue;
import net.sf.jsqlparser.expression.TimestampValue;
import net.sf.jsqlparser.expression.WhenClause;
import net.sf.jsqlparser.expression.operators.arithmetic.Addition;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseAnd;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseOr;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseXor;
import net.sf.jsqlparser.expression.operators.arithmetic.Concat;
import net.sf.jsqlparser.expression.operators.arithmetic.Division;
import net.sf.jsqlparser.expression.operators.arithmetic.Modulo;
import net.sf.jsqlparser.expression.operators.arithmetic.Multiplication;
import net.sf.jsqlparser.expression.operators.arithmetic.Subtraction;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.Between;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExistsExpression;
import net.sf.jsqlparser.expression.operators.relational.GreaterThan;
import net.sf.jsqlparser.expression.operators.relational.GreaterThanEquals;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.IsNullExpression;
import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
import net.sf.jsqlparser.expression.operators.relational.Matches;
import net.sf.jsqlparser.expression.operators.relational.MinorThan;
import net.sf.jsqlparser.expression.operators.relational.MinorThanEquals;
import net.sf.jsqlparser.expression.operators.relational.NotEqualsTo;
import net.sf.jsqlparser.expression.operators.relational.RegExpMatchOperator;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.SubSelect;

import com.uwi.utils.KeyValue;

// TODO: Auto-generated Javadoc
/**
 * The Class DefaultExpressionVisitor.
 */
public class DefaultExpressionVisitor implements ExpressionVisitor {

	/**
	 * The Enum Type.
	 */
	enum Type {

		/** The or. */
		OR("or"),
		/** The and. */
		AND("and"),
		/** The any. */
		ANY(""),
		/** The like. */
		LIKE("like");

		/** The value. */
		private String value;

		/**
		 * Instantiates a new type.
		 *
		 * @param arg
		 *            the arg
		 */
		Type(String arg) {
			this.value = arg;
		}

		/**
		 * Gets the value.
		 *
		 * @return the value
		 */
		public String getValue() {
			return value;
		}
	}

	/** The has paren. */
	private boolean hasParen = false;

	/** The columns. */
	Set<KeyValue> columns; // handle duplicate columns

	/** The columns. */
	Set<KeyValue> _temp; // utility hash for saving data for later

	/** The current type. */
	Type currentType;

	/**
	 * Gets the where clause.
	 *
	 * @param exp
	 *            the exp
	 * @return the where clause
	 */
	public String getWhereClause(Expression exp) {
		init();
		exp.accept(this);
		return merge(columns);
	}

	/**
	 * Inits the.
	 */
	private void init() {
		columns = new HashSet<KeyValue>();
		_temp = new HashSet<KeyValue>();
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
	private void manageOperands(BinaryExpression exp, boolean isNot) {
		String equalTo = String.format(isNot ? "not(%s/text()=%s)"
				: "%s/text()=%s", exp.getLeftExpression(), exp
				.getRightExpression());

		KeyValue keyValue = new KeyValue(currentType.getValue(), equalTo);

		if (hasParen) {
			_temp.add(keyValue);
		} else {
			columns.add(keyValue);
		}
	}

	/**
	 * Merge.
	 *
	 * @param _temp
	 *            the _temp
	 * @return the string
	 */
	public String merge(Set<KeyValue> _temp) {
		int size = _temp.size();
		int count = 0;
		StringBuilder sb = new StringBuilder();
		Iterator<KeyValue> iter = _temp.iterator();
		while (iter.hasNext()) {
			KeyValue kv = iter.next();
			sb.append(kv.getValue());
			if (count != size - 1) {
				sb.append(" ");
				sb.append(kv.getKey());
				sb.append(" ");
			}
			count++;
		}
		return sb.toString();
	}

	/**
	 * Merge paren records.
	 */
	public void mergeParenRecords() {
		// select * from country where food='roti' and (food='roti' or
		// food='hotdog')
		columns.add(new KeyValue("", String.format("( %s )", merge(_temp))));
	}

	/**
	 * Visit.
	 *
	 * @param addition
	 *            the addition
	 */
	@Override
	public void visit(Addition addition) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param allComparisonExpression
	 *            the all comparison expression
	 */
	@Override
	public void visit(AllComparisonExpression allComparisonExpression) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param aexpr
	 *            the aexpr
	 */
	@Override
	public void visit(AnalyticExpression aexpr) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param andExpression
	 *            the and expression
	 */
	@Override
	public void visit(AndExpression andExpression) {
		currentType = Type.AND;
		visitBinaryExpression(andExpression);
	}

	/**
	 * Visit.
	 *
	 * @param anyComparisonExpression
	 *            the any comparison expression
	 */
	@Override
	public void visit(AnyComparisonExpression anyComparisonExpression) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param between
	 *            the between
	 */
	@Override
	public void visit(Between between) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param bitwiseAnd
	 *            the bitwise and
	 */
	@Override
	public void visit(BitwiseAnd bitwiseAnd) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param bitwiseOr
	 *            the bitwise or
	 */
	@Override
	public void visit(BitwiseOr bitwiseOr) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param bitwiseXor
	 *            the bitwise xor
	 */
	@Override
	public void visit(BitwiseXor bitwiseXor) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param caseExpression
	 *            the case expression
	 */
	@Override
	public void visit(CaseExpression caseExpression) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param cast
	 *            the cast
	 */
	@Override
	public void visit(CastExpression cast) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param tableColumn
	 *            the table column
	 */
	@Override
	public void visit(Column tableColumn) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param concat
	 *            the concat
	 */
	@Override
	public void visit(Concat concat) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param dateValue
	 *            the date value
	 */
	@Override
	public void visit(DateValue dateValue) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param division
	 *            the division
	 */
	@Override
	public void visit(Division division) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param doubleValue
	 *            the double value
	 */
	@Override
	public void visit(DoubleValue doubleValue) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param equalsTo
	 *            the equals to
	 */
	@Override
	public void visit(EqualsTo equalsTo) {
		manageOperands(equalsTo, false);
	}

	/**
	 * Visit.
	 *
	 * @param existsExpression
	 *            the exists expression
	 */
	@Override
	public void visit(ExistsExpression existsExpression) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param eexpr
	 *            the eexpr
	 */
	@Override
	public void visit(ExtractExpression eexpr) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param function
	 *            the function
	 */
	@Override
	public void visit(Function function) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param greaterThan
	 *            the greater than
	 */
	@Override
	public void visit(GreaterThan greaterThan) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param greaterThanEquals
	 *            the greater than equals
	 */
	@Override
	public void visit(GreaterThanEquals greaterThanEquals) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param inExpression
	 *            the in expression
	 */
	@Override
	public void visit(InExpression inExpression) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param iexpr
	 *            the iexpr
	 */
	@Override
	public void visit(IntervalExpression iexpr) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param isNullExpression
	 *            the is null expression
	 */
	@Override
	public void visit(IsNullExpression isNullExpression) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param jdbcNamedParameter
	 *            the jdbc named parameter
	 */
	@Override
	public void visit(JdbcNamedParameter jdbcNamedParameter) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param jdbcParameter
	 *            the jdbc parameter
	 */
	@Override
	public void visit(JdbcParameter jdbcParameter) {
		// TODO Auto-generated method stub

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
		columns.add(new KeyValue(Type.LIKE.value, likeOperand(likeExpression)));
	}

	/**
	 * Visit.
	 *
	 * @param longValue
	 *            the long value
	 */
	@Override
	public void visit(LongValue longValue) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param matches
	 *            the matches
	 */
	@Override
	public void visit(Matches matches) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param minorThan
	 *            the minor than
	 */
	@Override
	public void visit(MinorThan minorThan) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param minorThanEquals
	 *            the minor than equals
	 */
	@Override
	public void visit(MinorThanEquals minorThanEquals) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param modulo
	 *            the modulo
	 */
	@Override
	public void visit(Modulo modulo) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param multiplication
	 *            the multiplication
	 */
	@Override
	public void visit(Multiplication multiplication) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param notEqualsTo
	 *            the not equals to
	 */
	@Override
	public void visit(NotEqualsTo notEqualsTo) {
		manageOperands(notEqualsTo, true);
	}

	/**
	 * Visit.
	 *
	 * @param nullValue
	 *            the null value
	 */
	@Override
	public void visit(NullValue nullValue) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param oexpr
	 *            the oexpr
	 */
	@Override
	public void visit(OracleHierarchicalExpression oexpr) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param orExpression
	 *            the or expression
	 */
	@Override
	public void visit(OrExpression orExpression) {
		currentType = Type.OR;
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
	 * Visit.
	 *
	 * @param rexpr
	 *            the rexpr
	 */
	@Override
	public void visit(RegExpMatchOperator rexpr) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param signedExpression
	 *            the signed expression
	 */
	@Override
	public void visit(SignedExpression signedExpression) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param stringValue
	 *            the string value
	 */
	@Override
	public void visit(StringValue stringValue) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param subSelect
	 *            the sub select
	 */
	@Override
	public void visit(SubSelect subSelect) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param subtraction
	 *            the subtraction
	 */
	@Override
	public void visit(Subtraction subtraction) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param timestampValue
	 *            the timestamp value
	 */
	@Override
	public void visit(TimestampValue timestampValue) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param timeValue
	 *            the time value
	 */
	@Override
	public void visit(TimeValue timeValue) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit.
	 *
	 * @param whenClause
	 *            the when clause
	 */
	@Override
	public void visit(WhenClause whenClause) {
		// TODO Auto-generated method stub

	}

	/**
	 * Visit binary expression.
	 *
	 * @param binaryExpression
	 *            the binary expression
	 */
	public void visitBinaryExpression(BinaryExpression binaryExpression) {
		binaryExpression.getLeftExpression().accept(this);
		binaryExpression.getRightExpression().accept(this);
	}

}
