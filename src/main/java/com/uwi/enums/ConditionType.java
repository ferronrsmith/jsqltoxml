package com.uwi.enums;

/**
 * <code>
 * The **ConditionType** conditional types that can be parsed by
 * **DefaultExpressionVisitor#manageOperand** internal methods.
 * </code>
 */
public enum ConditionType {

    /**
     * The not equal.
     */
    NOT_EQUAL("!="),
    /**
     * The equal.
     */
    EQUAL("="),
    /**
     * The greater than.
     */
    GREATER_THAN(">"),
    /**
     * The less than.
     */
    LESS_THAN("<"),
    /**
     * The greater than equal.
     */
    GREATER_THAN_EQUAL(">="),
    /**
     * The less than equal.
     */
    LESS_THAN_EQUAL("<="),
    /**
     * The null.
     */
    NULL("");
    /**
     * The value.
     */
    private String value;

    /**
     * <code>
     * Instantiates a new type.
     *</code>
     * @param arg
     *         the arg
     */
    ConditionType(String arg) {
        this.value = arg;
    }

    /**
     * <code>
     * Parses a String to a **ConditionType**.
     *</code>
     * @param property
     *         the property
     *
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
     * @internal
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return value;
    }
}
