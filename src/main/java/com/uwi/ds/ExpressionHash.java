package com.uwi.ds;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import com.uwi.utils.KeyValue;

// TODO: Auto-generated Javadoc
/**
 * The Class ExpressionHash.
 */
public class ExpressionHash {

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
	 * Adds the.
	 *
	 * @param value
	 *            the value
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
	 * Cherry pick.
	 *
	 * @param value
	 *            the value
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
	 * Clear.
	 *
	 * @return the expression hash
	 */
	public ExpressionHash clear() {
		bucket.clear();
		keyValues.clear();
		return this;
	}

	/**
	 * Flatten.
	 *
	 * @return the list
	 */
	public List<KeyValue> flatten() {
		List<KeyValue> result = new ArrayList<KeyValue>();
		Iterator<Entry<String, List<KeyValue>>> bIter = bucket.entrySet()
				.iterator();
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
	 * Checks for paren.
	 *
	 * @param kv
	 *            the kv
	 * @return true, if successful
	 */
	private boolean hasParen(KeyValue kv) {
		return hasParen(kv.getValue());
	}

	/**
	 * Checks for paren.
	 *
	 * @param inc
	 *            the inc
	 * @return true, if successful
	 */
	private boolean hasParen(String inc) {
		return Pattern.matches("^\\(.*\\)$", inc);
	}

	/**
	 * List to string.
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
		return String.format("( %s )", merge());
	}

}
