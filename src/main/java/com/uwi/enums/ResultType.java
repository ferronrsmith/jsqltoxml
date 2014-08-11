package com.uwi.enums;

// TODO: Auto-generated Javadoc
/**
 * The Enum Type.
 */
public enum ResultType {

	/** The xml. */
	XML,
	/** The data. */
	DATA,
	/** The path. */
	PATH,
	/** The string. */
	STRING,
	/** The text. */
	TEXT,
	/** The text trim. */
	TEXT_TRIM,
	/** The trim. */
	TRIM,
	/** The element. */
	ELEMENT;

	/**
	 * Parses the.
	 *
	 * @param property the property
	 * @return the result type
	 */
	public static ResultType parse(String property) {
		for (ResultType type : values()) {
			if (type.toString().equalsIgnoreCase(property)) {
				return type;
			}
		}
		return null;
	}

	/**
	 * Parses the.
	 *
	 * @param property the property
	 * @param defaultType the default type
	 * @return the result type
	 */
	public static ResultType parse(String property, ResultType defaultType) {
		ResultType type = parse(property);
		if (type == null) {
			return defaultType;
		} else {
			return type;
		}
	}
}