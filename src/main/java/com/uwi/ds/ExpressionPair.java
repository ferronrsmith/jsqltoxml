package com.uwi.ds;

import com.uwi.config.Configuration;
import com.uwi.enums.ExpressionType;
import com.uwi.utils.LinkIdentifierGenerator;
import net.sf.jsqlparser.expression.BinaryExpression;

/**
 * <code>
 * An ExpressionPair is an internal Data Structure used by **BinaryExpressionTree**.
 * An ExpressionPair consists of an <a href="https://github.com/ferronrsmith/jsqltoxml/blob/master/docs/ExpressionType.md">ExpressionType</a>
 * and a BinaryExpression. The ExpressionType is derived from the BinaryExpression Type. which is then passed to a
 * parser which will determine if the type is supported by the JSQLParser.
 * </code>
 */
public final class ExpressionPair extends Configuration {

    /**
     * The type.
     */
    ExpressionType type;

    /**
     * The expression.
     */
    BinaryExpression expression;

    /**
     * link identifier.
     */
    String _id;

    /**
     *<code>
     * Instantiates a new expression pair.
     * The type will be derived from the BinaryExpression and added into the ExpressionPair
     *</code>
     * @param exp
     *         BinaryExpression
     */
    public ExpressionPair(BinaryExpression exp) {
        this(ExpressionType.parse(exp), exp);
        if (this.type == null) {
            throw new IllegalArgumentException(
                    String.format(
                            i18n("missing_exp_pair"), exp.getClass().getSimpleName()));
        }
    }

    /**
     * Instantiates a new expression pair.
     *
     * @param type
     *         the type
     * @param expression
     *         the expression
     */
    public ExpressionPair(ExpressionType type, BinaryExpression expression) {
        this.type = type;
        this.expression = expression;
        this._id = new LinkIdentifierGenerator().nextSessionId();
    }

    /**
     *<code>
     * Gets the expression.
     *</code>
     * @return the expression
     */
    public BinaryExpression getExpression() {
        return expression;
    }

    /**
     * <code>
     * Sets the expression.
     *</code>
     * @param expression
     *         the new expression
     */
    public void setExpression(BinaryExpression expression) {
        this.expression = expression;
    }

    /**
     * <code>
     * Returns the ExpressionPair unique identifier
     * </code>
     * @return the id
     */
    public String getId() {
        return _id;
    }

    /**
     *<code>
     * Sets the id. The identifier is usually an unique identifier
     * That's generated from the **LinkIdentifierGenerator** class
     *</code>
     * @param _id
     *         the new id
     */
    public void setId(String _id) {
        this._id = _id;
    }

    /**
     *<code>
     * Gets the ExpressionType.
     *</code>
     * @return the type
     */
    public ExpressionType getType() {
        return type;
    }

    /**
     * <code>
     * Sets the ExpressionType.
     *</code>
     * @param type
     *         the new type
     */
    public void setType(ExpressionType type) {
        this.type = type;
    }
}
