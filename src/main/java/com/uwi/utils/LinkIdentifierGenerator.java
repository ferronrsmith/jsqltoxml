package com.uwi.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

import net.sf.jsqlparser.expression.Expression;

/**
 * The <code>LinkIdentifierGenerator</code> class is responsible for generating
 * pseudo-random sessionId that will be used to uniquely identify an
 * {@link Expression} and an {@link Expression} group
 */
public class LinkIdentifierGenerator {

	/** The random. */
	private SecureRandom random = new SecureRandom();

	/**
	 * Generate a new session id with radix 32.
	 *
	 * @return the string
	 */
	public String nextSessionId() {
		return new BigInteger(130, random).toString(32);
	}
}
