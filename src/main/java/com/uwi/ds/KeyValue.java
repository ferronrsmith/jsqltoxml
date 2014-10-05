package com.uwi.ds;

import com.uwi.config.Configuration;

import java.io.Serializable;

/**
 * The Class <code>KeyValue</code> is a container class that is used to store
 * <K,V> throughout the library. The metaData Object is used to store additional
 * information about the value contained within the class.
 */
public final class KeyValue extends Configuration implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 362680946538856026L;

    /**
     * The key.
     */
    String key;

    /**
     * The value.
     */
    String value;

    /**
     * The meta data.
     */
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
     *         the key
     * @param value
     *         the value
     * @param metaData
     *         the meta data
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
     * Sets the key.
     *
     * @param key
     *         the new key
     *
     * @return the key value
     */
    public KeyValue setKey(String key) {
        this.key = key;
        return this;
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
     * Sets the meta data.
     *
     * @param metaData
     *         the new meta data
     *
     * @return the key value
     */
    public KeyValue setMetaData(Object metaData) {
        this.metaData = metaData;
        return this;
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
     * Sets the value.
     *
     * @param value
     *         the new value
     *
     * @return the key value
     */
    public KeyValue setValue(String value) {
        this.value = value;
        return this;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return i18n("kv_format", key, value, metaData);
    }

}
