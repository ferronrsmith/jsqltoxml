package com.uwi.enums;

import com.uwi.visitors.DefaultExpressionVisitor;

/**
 * The <code>ConditionType</code> conditional types that can be parsed by
 * {@link DefaultExpressionVisitor} <b>manageOperand</b> internal methods.
 */
public enum ConditionType {

	/** The not equal. */
	NOT_EQUAL("!="),
	/** The equal. */
	EQUAL("="),
	/** The greater than. */
	GREATER_THAN(">"),
	/** The less than. */
	LESS_THAN("<"),
	/** The greater than equal. */
	GREATER_THAN_EQUAL(">="),
	/** The less than equal. */
	LESS_THAN_EQUAL("<="),
	/** The null. */
	NULL("");

	/**
	 * Parses a String to a {@link ConditionType}.
	 *
	 * @param property
	 *            the property
	 * @return the result type
	 */
	public static ConditionType parse(String property) {
		for (ConditionType type : values()) {
			if (type.toString().equalsIgnoreCase(property)) {
				return type;
			}
		}
		return ConditionType.NULL;
	}

	/** The value. */
	private String value;

	/**
	 * Instantiates a new type.
	 *
	 * @param arg
	 *            the arg
	 */
	ConditionType(String arg) {
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