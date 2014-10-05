package com.uwi.ds;

import com.uwi.config.Configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;

/**
 * <code>
 * The *ExpressionHash* class keeps track of the SQL Expression **( KeyValue )** as the Visitor traverses each portion.
 * At each level of the Visitor "Tree" there are at most two expressions.
 *
 * e.g.
 *```sql
 * id = '2323aa' AND author = "J.K Rowling"
 * ```
 *
 * The ExpressionHash merges all sub-level expressions into one, all the way down until the right-most part of the SQL
 * Expression **( KeyValue )** has been processed. The result of this is a partial XPATH expression that is returned
 * to the requested class for further processing/merging.
 * </code>
 */
public final class ExpressionHash extends Configuration {

    /**
     * The bucket.
     */
    LinkedHashMap<String, List<KeyValue>> bucket;

    /**
     * The key values.
     */
    List<KeyValue> keyValues;

    /**
     * Instantiates a new expression hash.
     */
    public ExpressionHash() {
        bucket = new LinkedHashMap<String, List<KeyValue>>();
        keyValues = new ArrayList<KeyValue>();
    }

    /**
     * <code>
     * Adds the a KeyValue to a bucket. The **KeyValue#getMetaData**
     * contains the unique_id for each <b>KeyValue</b>. If the session_id does
     * not exist with the bucket then a new item will be added and the
     * <b>KeyValue</b> added to a list. If the session_id already exists the
     * <b>KeyValue</b> will be appended to the existing list <b>K<session_id>,
     * V<List<KeyValue>></b>
     *</code>
     * @param value - KeyValue to be added the bucket
     *
     * @return the expression hash
     */
    public ExpressionHash add(KeyValue value) {
        // unique link identifier
        String key = (String) value.getMetaData();
        if (bucket.containsKey(key)) {
            bucket.get(key).add(value);
        } else {
            List<KeyValue> kvs = new ArrayList<KeyValue>();
            kvs.add(value);
            bucket.put(key, kvs);
        }
        keyValues.add(value);
        return this;
    }

    /**
     *<code>
     * Cherry pick checks a record for the existence of a <b>key</b> and
     * <b>session_id</b>. If these values haven't been set, the first record from
     * the list will be retrieved and its <b>key</b> and <b>session_id</id> assigned to
     * the input record.
     * <b>NB:</b> Only two records can exist at a given sub-level so the first
     * record key & session_id should be the same as the input record
     *
     *<pre>
     * |------ id=8jsjjsjs;book = "Harry"
     *      | ------ op = "AND"
     *          |------id=8jsjjsjs;author = "J.K Rowlings"
     *</pre>
     *</code>
     * @param value - sub-level **KeyValue**
     *
     * @return the key value
     */
    public KeyValue cherryPick(KeyValue value) {
        KeyValue fstRecord = keyValues.get(0);
        if (value.getKey() == null) {
            value.setKey(fstRecord.getKey());
        }
        if (value.getMetaData() == null) {
            value.setMetaData(fstRecord.getMetaData());
        }
        return value;
    }

    /**
     * <code>
     * Clear bucket and keyValue lists. Used in cases where a <b>_temp</b>
     * *ExpressionHash* is created and must be cleared for re-use
     *</code>
     * @return the expression hash
     */
    public ExpressionHash clear() {
        bucket.clear();
        keyValues.clear();
        return this;
    }

    /**
     * <code>
     * The following function flattens a bucket of **KeyValue** list into a
     * <b>grouped & flatten</b> form that can be later merged with other
     * expressions.
     *</code>
     * @return the list
     */
    public List<KeyValue> flatten() {
        List<KeyValue> result = new ArrayList<KeyValue>();

        // the following logic places related values into the KeyValue.
        // Values are related by a unique session_id. Only 2 values (L<->R) at
        // any level can have the same session_id
        for (Entry<String, List<KeyValue>> entrySet : bucket.entrySet()) {
            List<KeyValue> kList = entrySet.getValue();
            result.add(
                    new KeyValue(
                            kList.get(0).getKey(), listToString(kList), kList.get(0).getMetaData()));
        }

        // shift left -> [(a, b), (c, d)] -> [(c, b), (c, d)]
        // the following logic shifts the param from the (idx+1) to (idx)
        // if the inner element has no param or both inner & outer has param
        // then
        // a shift will be performed
        if (result.size() > 1) {
            for (int i = 0; i < result.size(); i++) {
                KeyValue outer = result.get(i);
                for (int j = 1; j < result.size(); j++) {
                    KeyValue inner = result.get(j);
                    if (!hasParen(inner) | (hasParen(outer) && hasParen(inner))) {
                        outer.setKey(result.get(j).getKey());
                    }
                }
            }
        }
        return result;
    }

    /**
     * <code>
     * Checks the **KeyValue#getValue()** for paren.
     *</code>
     * @param kv - KeyValue object
     *
     * @return true, if successful
     */
    private boolean hasParen(KeyValue kv) {
        return hasParen(kv.getValue());
    }

    /**
     * </code>
     * Checks for paren.
     * ```java
     * 	// e.g. the following expression consists of parenthesis
     *  hasParen("select * from"); 	//-> false;
     *  hasParen("(name)"); 		//-> true;
     *  hasParen("(name "); 		//-> false;
     *  hasParen("name)"); 			//-> false;
     * ```
     *</code>
     * @param input
     *         the value to be checked
     *
     * @return true, if successful
     */
    private boolean hasParen(String input) {
        return Pattern.matches(i18n("c_paren_regex"), input);
    }

    /**
     *<code>
     * Converts a list of **KeyValue** to a space delimited string.
     *
     * e.g.
     * ```java
     * [
     *  { key=or,value=name/text()='India' and food/text()='roti',metadata=2ccgm7g3uogfonfie8mtcgrp0n },
     *  { key=or,value=food/text()='barfi',metadata=pvgasoj8v4hs09jsqpov2hidlr }
     * ]
     * ```
     *
     * converted to :
     *
     * ```java
     *  name/text()='India' and food/text()='roti' or food/text()='barfi'
     * ```
     *
     *</code>
     * @param kList
     *         the k list
     *
     * @return the string
     */
    private String listToString(List<KeyValue> kList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < kList.size(); i++) {
            KeyValue keyValue = kList.get(i);
            sb.append(keyValue.getValue());
            if (i != kList.size() - 1) {
                sb.append(" ");
                sb.append(keyValue.getKey());
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    /**
     * <code>
     * Converts a list of flattened KeyValue pairs into a string.
     *
     * e.g.
     * ```java
     * [
     *  { key=or,value=name/text()='India' and food/text()='roti',metadata=2ccgm7g3uogfonfie8mtcgrp0n },
     *  { key=or,value=food/text()='barfi',metadata=pvgasoj8v4hs09jsqpov2hidlr }
     * ]
     * ```
     *
     * will be flatten to :
     *
     * ```java
     *  name/text()='India' and food/text()='roti' or food/text()='barfi'
     * ```
     * </code>
     * @return the string
     */
    public String merge() {
        return listToString(flatten());
    }

    /**
     *<code>
     *     Invokes the merge function and wraps the result in params
     *</code>
     *
     * @return the string
     */
    public String mergeParen() {
        return i18n("c_param_exp", merge());
    }
}
