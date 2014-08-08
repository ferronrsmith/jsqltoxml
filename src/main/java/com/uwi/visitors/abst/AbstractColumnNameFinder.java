package com.uwi.visitors.abst;

import net.sf.jsqlparser.expression.AllComparisonExpression;
import net.sf.jsqlparser.expression.AnalyticExpression;
import net.sf.jsqlparser.expression.AnyComparisonExpression;
import net.sf.jsqlparser.expression.CaseExpression;
import net.sf.jsqlparser.expression.CastExpression;
import net.sf.jsqlparser.expression.DateValue;
import net.sf.jsqlparser.expression.DoubleValue;
import net.sf.jsqlparser.expression.ExpressionVisitor;
import net.sf.jsqlparser.expression.ExtractExpression;
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
import net.sf.jsqlparser.statement.select.SelectItemVisitor;
import net.sf.jsqlparser.statement.select.SubSelect;

public abstract class AbstractColumnNameFinder implements SelectItemVisitor,
		ExpressionVisitor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.operators.arithmetic.Addition)
	 */
	@Override
	public void visit(Addition addition) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.AllComparisonExpression)
	 */
	@Override
	public void visit(AllComparisonExpression allComparisonExpression) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.AnalyticExpression)
	 */
	@Override
	public void visit(AnalyticExpression aexpr) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.operators.conditional.AndExpression)
	 */
	@Override
	public void visit(AndExpression andExpression) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.AnyComparisonExpression)
	 */
	@Override
	public void visit(AnyComparisonExpression anyComparisonExpression) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.operators.relational.Between)
	 */
	@Override
	public void visit(Between between) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.operators.arithmetic.BitwiseAnd)
	 */
	@Override
	public void visit(BitwiseAnd bitwiseAnd) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.operators.arithmetic.BitwiseOr)
	 */
	@Override
	public void visit(BitwiseOr bitwiseOr) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.operators.arithmetic.BitwiseXor)
	 */
	@Override
	public void visit(BitwiseXor bitwiseXor) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.CaseExpression)
	 */
	@Override
	public void visit(CaseExpression caseExpression) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.CastExpression)
	 */
	@Override
	public void visit(CastExpression cast) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.operators.arithmetic.Concat)
	 */
	@Override
	public void visit(Concat concat) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.DateValue)
	 */
	@Override
	public void visit(DateValue dateValue) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.operators.arithmetic.Division)
	 */
	@Override
	public void visit(Division division) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.DoubleValue)
	 */
	@Override
	public void visit(DoubleValue doubleValue) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.operators.relational.EqualsTo)
	 */
	@Override
	public void visit(EqualsTo equalsTo) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.operators.relational.ExistsExpression)
	 */
	@Override
	public void visit(ExistsExpression existsExpression) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.ExtractExpression)
	 */
	@Override
	public void visit(ExtractExpression eexpr) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.operators.relational.GreaterThan)
	 */
	@Override
	public void visit(GreaterThan greaterThan) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.operators.relational.GreaterThanEquals)
	 */
	@Override
	public void visit(GreaterThanEquals greaterThanEquals) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.operators.relational.InExpression)
	 */
	@Override
	public void visit(InExpression inExpression) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.IntervalExpression)
	 */
	@Override
	public void visit(IntervalExpression iexpr) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.operators.relational.IsNullExpression)
	 */
	@Override
	public void visit(IsNullExpression isNullExpression) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.JdbcNamedParameter)
	 */
	@Override
	public void visit(JdbcNamedParameter jdbcNamedParameter) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.JdbcParameter)
	 */
	@Override
	public void visit(JdbcParameter jdbcParameter) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.operators.relational.LikeExpression)
	 */
	@Override
	public void visit(LikeExpression likeExpression) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.LongValue)
	 */
	@Override
	public void visit(LongValue longValue) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.operators.relational.Matches)
	 */
	@Override
	public void visit(Matches matches) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.operators.relational.MinorThan)
	 */
	@Override
	public void visit(MinorThan minorThan) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.operators.relational.MinorThanEquals)
	 */
	@Override
	public void visit(MinorThanEquals minorThanEquals) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.operators.arithmetic.Modulo)
	 */
	@Override
	public void visit(Modulo modulo) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.operators.arithmetic.Multiplication)
	 */
	@Override
	public void visit(Multiplication multiplication) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.operators.relational.NotEqualsTo)
	 */
	@Override
	public void visit(NotEqualsTo notEqualsTo) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.NullValue)
	 */
	@Override
	public void visit(NullValue nullValue) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.OracleHierarchicalExpression)
	 */
	@Override
	public void visit(OracleHierarchicalExpression oexpr) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.operators.conditional.OrExpression)
	 */
	@Override
	public void visit(OrExpression orExpression) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.Parenthesis)
	 */
	@Override
	public void visit(Parenthesis parenthesis) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.operators.relational.RegExpMatchOperator)
	 */
	@Override
	public void visit(RegExpMatchOperator rexpr) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.SignedExpression)
	 */
	@Override
	public void visit(SignedExpression signedExpression) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.StringValue)
	 */
	@Override
	public void visit(StringValue stringValue) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .statement.select.SubSelect)
	 */
	@Override
	public void visit(SubSelect subSelect) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.operators.arithmetic.Subtraction)
	 */
	@Override
	public void visit(Subtraction subtraction) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.TimestampValue)
	 */
	@Override
	public void visit(TimestampValue timestampValue) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.TimeValue)
	 */
	@Override
	public void visit(TimeValue timeValue) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.WhenClause)
	 */
	@Override
	public void visit(WhenClause whenClause) {
		throw new UnsupportedOperationException("Not supported yet.");

	}

}
