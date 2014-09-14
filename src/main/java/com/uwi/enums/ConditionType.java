package com.uwi.enums;

/**
 * <code>
 * The **ConditionType** enum stores conditional types that can be parsed by
 * **DefaultExpressionVisitor#manageOperand** and its internal methods.
 * </code>
 */
public enum ConditionType {

    /**
     * <code>
     * The not equal.
     * </code>
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
     *  Parses a String to a **ConditionType**. If the type is not supported by SPAT
     *  the `ConditionType.NULL` will be returned.
     *  ##### Example
     *  ```java
     *  ConditionType.parse(">");
     *  // returns ConditionType.GREATER_THAN
     *  ```
     * </code>
     * @param property
     *         conditional string to be parsed.
     *
     * @return mapped ConditionType
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
     * <code>
     * Returns the ConditionType String Value
     * ##### Example
     * ```java
     *  ConditionType ct = ConditionType.GREATER_THAN
     *  ct.getValue()
     *  // returns ">"
     * ```
     * </code>
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /*
     * @internal
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return value;
    }
}
