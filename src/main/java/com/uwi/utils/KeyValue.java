package com.uwi.utils;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class KeyValue.
 */
public class KeyValue implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 362680946538856026L;

	/** The key. */
	String key;

	/** The value. */
	String value;

	/** The meta data. */
	Object metaData;

	/**
	 * Instantiates a new key value.
	 */
	public KeyValue() {
		// default contructor
	}

	/**
	 * Instantiates a new key value.
	 *
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	public KeyValue(String key, String value) {
		this(key, value, null);
	}

	/**
	 * Instantiates a new key value.
	 *
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 * @param metaData
	 *            the meta data
	 */
	public KeyValue(String key, String value, Object metaData) {
		this.key = key;
		this.value = value;
		this.metaData = metaData;
	}

	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Gets the meta data.
	 *
	 * @return the meta data
	 */
	public Object getMetaData() {
		return metaData;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the key.
	 *
	 * @param key
	 *            the new key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Sets the meta data.
	 *
	 * @param metaData
	 *            the new meta data
	 */
	public void setMetaData(Object metaData) {
		this.metaData = metaData;
	}

	/**
	 * Sets the value.
	 *
	 * @param value
	 *            the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("{%s,%s}", key, value);
	}

}
