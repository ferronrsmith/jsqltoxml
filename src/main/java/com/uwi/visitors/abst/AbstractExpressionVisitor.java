package com.uwi.visitors.abst;

import com.uwi.config.Configuration;
import net.sf.jsqlparser.expression.AllComparisonExpression;
import net.sf.jsqlparser.expression.AnalyticExpression;
import net.sf.jsqlparser.expression.AnyComparisonExpression;
import net.sf.jsqlparser.expression.CaseExpression;
import net.sf.jsqlparser.expression.CastExpression;
import net.sf.jsqlparser.expression.DateValue;
import net.sf.jsqlparser.expression.DoubleValue;
import net.sf.jsqlparser.expression.ExpressionVisitor;
import net.sf.jsqlparser.expression.ExtractExpression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.IntervalExpression;
import net.sf.jsqlparser.expression.JdbcNamedParameter;
import net.sf.jsqlparser.expression.JdbcParameter;
import net.sf.jsqlparser.expression.JsonExpression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.OracleHierarchicalExpression;
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
import net.sf.jsqlparser.expression.operators.relational.Between;
import net.sf.jsqlparser.expression.operators.relational.ExistsExpression;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.IsNullExpression;
import net.sf.jsqlparser.expression.operators.relational.Matches;
import net.sf.jsqlparser.expression.operators.relational.RegExpMatchOperator;
import net.sf.jsqlparser.expression.operators.relational.RegExpMySQLOperator;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.SubSelect;

// TODO: Auto-generated Javadoc

/**
 * @internal
 * The Class AbstractExpressionVisitor.
 */
public abstract class AbstractExpressionVisitor extends Configuration implements ExpressionVisitor {

    /**
     * Visit.
     *
     * @param nullValue
     *         the null value
     */
    @Override
    public void visit(NullValue nullValue) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param function
     *         the function
     */
    @Override
    public void visit(Function function) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param signedExpression
     *         the signed expression
     */
    @Override
    public void visit(SignedExpression signedExpression) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param jdbcParameter
     *         the jdbc parameter
     */
    @Override
    public void visit(JdbcParameter jdbcParameter) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param jdbcNamedParameter
     *         the jdbc named parameter
     */
    @Override
    public void visit(JdbcNamedParameter jdbcNamedParameter) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param doubleValue
     *         the double value
     */
    @Override
    public void visit(DoubleValue doubleValue) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param longValue
     *         the long value
     */
    @Override
    public void visit(LongValue longValue) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param dateValue
     *         the date value
     */
    @Override
    public void visit(DateValue dateValue) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param timeValue
     *         the time value
     */
    @Override
    public void visit(TimeValue timeValue) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param timestampValue
     *         the timestamp value
     */
    @Override
    public void visit(TimestampValue timestampValue) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param stringValue
     *         the string value
     */
    @Override
    public void visit(StringValue stringValue) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param addition
     *         the addition
     */
    @Override
    public void visit(Addition addition) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param division
     *         the division
     */
    @Override
    public void visit(Division division) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param multiplication
     *         the multiplication
     */
    @Override
    public void visit(Multiplication multiplication) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param subtraction
     *         the subtraction
     */
    @Override
    public void visit(Subtraction subtraction) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param between
     *         the between
     */
    @Override
    public void visit(Between between) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param inExpression
     *         the in expression
     */
    @Override
    public void visit(InExpression inExpression) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param isNullExpression
     *         the is null expression
     */
    @Override
    public void visit(IsNullExpression isNullExpression) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param tableColumn
     *         the table column
     */
    @Override
    public void visit(Column tableColumn) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param subSelect
     *         the sub select
     */
    @Override
    public void visit(SubSelect subSelect) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param caseExpression
     *         the case expression
     */
    @Override
    public void visit(CaseExpression caseExpression) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param whenClause
     *         the when clause
     */
    @Override
    public void visit(WhenClause whenClause) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param existsExpression
     *         the exists expression
     */
    @Override
    public void visit(ExistsExpression existsExpression) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param allComparisonExpression
     *         the all comparison expression
     */
    @Override
    public void visit(AllComparisonExpression allComparisonExpression) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param anyComparisonExpression
     *         the any comparison expression
     */
    @Override
    public void visit(AnyComparisonExpression anyComparisonExpression) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param concat
     *         the concat
     */
    @Override
    public void visit(Concat concat) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param matches
     *         the matches
     */
    @Override
    public void visit(Matches matches) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param bitwiseAnd
     *         the bitwise and
     */
    @Override
    public void visit(BitwiseAnd bitwiseAnd) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param bitwiseOr
     *         the bitwise or
     */
    @Override
    public void visit(BitwiseOr bitwiseOr) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param bitwiseXor
     *         the bitwise xor
     */
    @Override
    public void visit(BitwiseXor bitwiseXor) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param cast
     *         the cast
     */
    @Override
    public void visit(CastExpression cast) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param modulo
     *         the modulo
     */
    @Override
    public void visit(Modulo modulo) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param aexpr
     *         the aexpr
     */
    @Override
    public void visit(AnalyticExpression aexpr) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param eexpr
     *         the eexpr
     */
    @Override
    public void visit(ExtractExpression eexpr) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param iexpr
     *         the iexpr
     */
    @Override
    public void visit(IntervalExpression iexpr) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param oexpr
     *         the oexpr
     */
    @Override
    public void visit(OracleHierarchicalExpression oexpr) {
        throw new UnsupportedOperationException(i18n("no_support"));

    }

    /**
     * Visit.
     *
     * @param rexpr
     *         the rexpr
     */
    @Override
    public void visit(RegExpMatchOperator rexpr) {
        throw new UnsupportedOperationException(i18n("no_support"));
    }

    @Override
    public void visit(JsonExpression jsonExpr) {
        throw new UnsupportedOperationException(i18n("no_support"));
    }

    @Override
    public void visit(RegExpMySQLOperator regExpMySQLOperator) {
        throw new UnsupportedOperationException(i18n("no_support"));
    }

}
