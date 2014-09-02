package com.uwi.ds;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import com.uwi.utils.Configuration;
import com.uwi.utils.KeyValue;

/**
 * The Class ExpressionHash.
 */
public class ExpressionHash extends Configuration {

	/** The bucket. */
	LinkedHashMap<String, List<KeyValue>> bucket;

	/** The key values. */
	List<KeyValue> keyValues;

	/**
	 * Instantiates a new expression hash.
	 */
	public ExpressionHash() {
		bucket = new LinkedHashMap<String, List<KeyValue>>();
		keyValues = new ArrayList<KeyValue>();
	}

	/**
	 * Adds the a KeyValue to a bucket. The {@link KeyValue#getMetaData()}
	 * contains the unique_id for each <b>KeyValue</b>. If the session_id does
	 * not exist with the bucket then a new item will be added and the
	 * <b>KeyValue</b> added to a list. If the session_id already exists the
	 * <b>KeyValue</b> will be appended to the existing list <b>K<session_id>,
	 * V<List<KeyValue>></b>
	 *
	 * @param value
	 *            {@link KeyValue} to be added
	 * @return the expression hash
	 */
	public ExpressionHash add(KeyValue value) {
		if (bucket.containsKey(value.getMetaData())) {
			bucket.get(value.getMetaData()).add(value);
		} else {
			List<KeyValue> kvs = new ArrayList<KeyValue>();
			kvs.add(value);
			bucket.put(value.getMetaData().toString(), kvs);
		}
		keyValues.add(value);
		return this;
	}

	/**
	 * Cherry pick checks a record for the existence of a <b>key</b> and
	 * <b>session_id</b>. If these values haven't been set, the first record
	 * will be retrieved and its <b>key</b> and <b>session_id</id> assigned to
	 * the input record.
	 *
	 * <b>NB:</b> Only two records exist at a given sub-level so the first
	 * record key & session_id would be the same as the input record
	 *
	 * @param value
	 *            incomplete {@link KeyValue}
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
	 * Clear bucket and keyValue lists. Used in cases where a <b>_temp</b>
	 * {@link ExpressionHash} is created and must be cleared for re-use
	 *
	 * @return the expression hash
	 */
	public ExpressionHash clear() {
		bucket.clear();
		keyValues.clear();
		return this;
	}

	/**
	 * The following function flattens a bucket of {@link KeyValue} list into a
	 * <b>grouped & flatten</b> form that can be later merged with other
	 * expressions.
	 *
	 * @return the list
	 */
	public List<KeyValue> flatten() {
		List<KeyValue> result = new ArrayList<KeyValue>();
		Iterator<Entry<String, List<KeyValue>>> bIter = bucket.entrySet()
				.iterator();

		// the following logic places related values into the KeyValue.
		// Values are related by a unique session_id. Only 2 values (L<->R) at
		// any level can have the same session_id
		while (bIter.hasNext()) {
			Entry<String, List<KeyValue>> entrySet = bIter.next();
			List<KeyValue> kList = entrySet.getValue();
			result.add(new KeyValue(kList.get(0).getKey(), listToString(kList),
					kList.get(0).getMetaData()));
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
	 * Checks the {@link KeyValue#getValue()} for paren.
	 *
	 * @param kv
	 *            {@link KeyValue} object
	 * @return true, if successful
	 */
	private boolean hasParen(KeyValue kv) {
		return hasParen(kv.getValue());
	}

	/**
	 * Checks for paren.
	 *
	 * <pre>
	 * 	e.g. the following expression consists of parenthesis
	 *  hasParen("select * from"); 	-> false;
	 *  hasParen("(name)"); 		-> true;
	 *  hasParen("(name "); 		-> false;
	 *  hasParen("name)"); 			-> false;
	 * </pre>
	 *
	 * @param input
	 *            the value to be checked
	 * @return true, if successful
	 */
	private boolean hasParen(String input) {
		return Pattern.matches(i18n("c_paren_regex"), input);
	}

	/**
	 * Converts a list of {@link KeyValue} to a space delimited string.
	 *
	 * @param kList
	 *            the k list
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
	 * Merge.
	 *
	 * @return the string
	 */
	public String merge() {
		return listToString(flatten());
	}

	/**
	 * Merge paren.
	 *
	 * @return the string
	 */
	public String mergeParen() {
		return i18n("c_param_exp", merge());
	}

}
