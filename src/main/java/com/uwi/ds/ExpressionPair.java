package com.uwi.ds;

import net.sf.jsqlparser.expression.BinaryExpression;

import com.uwi.enums.ExpressionType;
import com.uwi.utils.LinkIdentifierGenerator;

// TODO: Auto-generated Javadoc
/**
 * The Class ExpressionPair.
 */
public class ExpressionPair {

	/** The type. */
	ExpressionType type;

	/** The expression. */
	BinaryExpression expression;

	/** link identifier */
	String _id;

	/**
	 * Instantiates a new expression pair.
	 *
	 * @param exp
	 *            the exp
	 */
	public ExpressionPair(BinaryExpression exp) {
		this(ExpressionType.parse(exp), exp);
		if (this.type == null) {
			throw new IllegalArgumentException(
					String.format(
							"This type : [%s] as not yet been implement in [ExpressionType Enum]",
							exp.getClass().getSimpleName()));
		}
	}

	/**
	 * Instantiates a new expression pair.
	 *
	 * @param type
	 *            the type
	 * @param expression
	 *            the expression
	 */
	public ExpressionPair(ExpressionType type, BinaryExpression expression) {
		this.type = type;
		this.expression = expression;
		this._id = new LinkIdentifierGenerator().nextSessionId();
	}

	/**
	 * Gets the expression.
	 *
	 * @return the expression
	 */
	public BinaryExpression getExpression() {
		return expression;
	}

	public String getId() {
		return _id;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public ExpressionType getType() {
		return type;
	}

	/**
	 * Sets the expression.
	 *
	 * @param expression
	 *            the new expression
	 */
	public void setExpression(BinaryExpression expression) {
		this.expression = expression;
	}

	public void setId(String _id) {
		this._id = _id;
	}

	/**
	 * Sets the type.
	 *
	 * @param type
	 *            the new type
	 */
	public void setType(ExpressionType type) {
		this.type = type;
	}

}
