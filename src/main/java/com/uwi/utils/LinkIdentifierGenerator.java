package com.uwi.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

// TODO: Auto-generated Javadoc
/**
 * The Class LinkIdentifierGenerator.
 */
public class LinkIdentifierGenerator {

	/** The random. */
	private SecureRandom random = new SecureRandom();

	/**
	 * Next session id.
	 *
	 * @return the string
	 */
	public String nextSessionId() {
		return new BigInteger(130, random).toString(32);
	}
}
