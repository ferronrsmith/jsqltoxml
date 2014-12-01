package com.uwi.enums;

import net.sf.jsqlparser.expression.BinaryExpression;

/**
 * <code>
 * The ExpressionType Enum keeps track of all the supported BinaryExpression.
 * Currently only OR, AND & LIKE expressions are supported.
 * </code>
 */
public enum ExpressionType {

    /**
     * The or.
     */
    OR("or"),
    /**
     * The and.
     */
    AND("and"),
    /**
     * The any.
     */
    ANY(""),
    /**
     * The like.
     */
    LIKE("like"),
    /**
     * The test. for unit testing purposes only !
     */
    TEST("test");
    /**
     * The value.
     */
    private String value;

    /**
     * Instantiates a new type.
     *
     * @param arg
     *         the arg
     */
    ExpressionType(String arg) {
        this.value = arg;
    }

    /**
     * The following function takes a BinaryExpression, parses its
     * name and determine the type of expression. If the expression
     * type is not supported a value of null will be returned.
     *
     * @param exp
     *         the exp
     *
     * @return the expression type
     */
    public static ExpressionType parse(BinaryExpression exp) {
        return parse(exp.getClass().getSimpleName());
    }

    /**
     * The following function takes a class name or type name
     * and determine it's type. If the expression
     * type is not supported a value of null will be returned.
     *
     * @param value
     *         the value
     *
     * @return the expression type
     */
    public static ExpressionType parse(String value) {
        value = value.replaceAll("Expression", "");
        for (ExpressionType type : ExpressionType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        return null;
    }

    /**
     * Gets the value.
     * @internal
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @internal
     */
    @Override
    public String toString() {
        return value;
    }

}
