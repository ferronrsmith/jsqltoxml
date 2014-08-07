package com.uwi.enums;

import net.sf.jsqlparser.expression.BinaryExpression;

// TODO: Auto-generated Javadoc
/**
 * The Enum ExpressionType.
 */
public enum ExpressionType {

	/** The or. */
	OR("or"),
	/** The and. */
	AND("and"),
	/** The any. */
	ANY(""),
	/** The like. */
	LIKE("like"),
	/** The test. for unit testing purposes only ! */
	TEST("test");

	/**
	 * Parses the.
	 *
	 * @param exp
	 *            the exp
	 * @return the expression type
	 */
	public static ExpressionType parse(BinaryExpression exp) {
		return parse(exp.getClass().getSimpleName());
	}

	/**
	 * Parses the.
	 *
	 * @param clazz
	 *            the clazz
	 * @return the expression type
	 */
	public static ExpressionType parse(Class<BinaryExpression> clazz) {
		return parse(clazz.getSimpleName());
	}

	/**
	 * Parses the.
	 *
	 * @param value
	 *            the value
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

	/** The value. */
	private String value;

	/**
	 * Instantiates a new type.
	 *
	 * @param arg
	 *            the arg
	 */
	ExpressionType(String arg) {
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

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return value;
	}

}
