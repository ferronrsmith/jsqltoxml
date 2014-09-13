package com.uwi.enums;

import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EnumTest {

    @Test
    public void testConditionTypeParsedProperly() {
        assertEquals(ConditionType.parse("="), ConditionType.EQUAL);
        assertEquals(ConditionType.parse("!="), ConditionType.NOT_EQUAL);
        assertEquals(ConditionType.parse(">"), ConditionType.GREATER_THAN);
        assertEquals(ConditionType.parse("<"), ConditionType.LESS_THAN);
        assertEquals(ConditionType.parse("<="), ConditionType.LESS_THAN_EQUAL);
        assertEquals(
                ConditionType.parse(">="), ConditionType.GREATER_THAN_EQUAL);
        assertEquals(ConditionType.parse("!!"), ConditionType.NULL);
    }

    @Test
    public void testExpressionTypeParseProperly() throws Exception {
        assertEquals(ExpressionType.parse("ORExpression"), ExpressionType.OR);
        assertEquals(ExpressionType.parse("OrExpression"), ExpressionType.OR);
        assertEquals(
                ExpressionType.parse("LikeExpression"), ExpressionType.LIKE);
        assertEquals(ExpressionType.parse("AndExpression"), ExpressionType.AND);
        assertEquals(
                ExpressionType.parse(
                        new AndExpression(
                                new StringValue(
                                        "'p'"), new StringValue("'q'"))), ExpressionType.AND);
    }
}
