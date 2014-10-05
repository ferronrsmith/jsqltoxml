package com.uwi.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * <code>
 * The **LinkIdentifierGenerator** class is responsible for generating
 * pseudo-random sessionId that will be used to uniquely identify an
 * Expression and an Expression group
 * </code>
 */
public final class LinkIdentifierGenerator {

    /**
     * The random.
     */
    private SecureRandom random = new SecureRandom();

    /**
     * <code>
     * Generates a new session id with radix 32.
     *
     *```java
     *  // generates a unique id
     *  new LinkIdentifierGenerator().nextSessionId();
     *```
     *</code>
     * @return unique identifier string
     */
    public String nextSessionId() {
        return new BigInteger(130, random).toString(32);
    }
}
