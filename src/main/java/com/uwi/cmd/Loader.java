package com.uwi.cmd;

import org.kohsuke.args4j.Option;

import com.uwi.enums.ResultType;
import com.uwi.utils.Configuration;

// TODO: Auto-generated Javadoc
/**
 * The Class Loader.
 */
public class Loader extends Configuration {

	/** The sql. */
	@Option(name = "-sql", usage = "SQL String", aliases = { "-s" }, required = true)
	private String sql;

	/** The xml file. */
	@Option(name = "-xml", usage = "XML file to be queried", aliases = { "-x" }, required = true)
	private String xmlFile;

	/** The type. */
	@Option(name = "-type", usage = "Output type - {XML|TRIM|TEXT|DATA}", aliases = { "-t" })
	private ResultType type;

	/** The output file. */
	@Option(name = "-output", usage = "Writes resultset to output file", aliases = { "-o" })
	private String outputFile;

	/**
	 * Gets the output file.
	 *
	 * @return the output file
	 */
	public String getOutputFile() {
		return outputFile;
	}

	/**
	 * Gets the sql.
	 *
	 * @return the sql
	 */
	public String getSql() {
		return sql;
	}

	/**
	 * Gets the type. The Default Type is {@link Type#XML}
	 *
	 * @return the type
	 */
	public ResultType getType() {
		return type == null ? DEFAULT_TYPE : type;
	}

	/**
	 * Gets the xml file.
	 *
	 * @return the xml file
	 */
	public String getXmlFile() {
		return xmlFile;
	}

	/**
	 * Sets the output file.
	 *
	 * @param outputFile
	 *            the new output file
	 */
	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}

	/**
	 * Sets the sql.
	 *
	 * @param sql
	 *            the new sql
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}

	/**
	 * Sets the type.
	 *
	 * @param type
	 *            the new type
	 */
	public void setType(ResultType type) {
		this.type = type;
	}

	/**
	 * Sets the xml file.
	 *
	 * @param xmlFile
	 *            the new xml file
	 */
	public void setXmlFile(String xmlFile) {
		this.xmlFile = xmlFile;
	}
}
