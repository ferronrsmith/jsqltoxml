package com.uwi.config;

import com.uwi.enums.ResultType;
import org.kohsuke.args4j.Option;

/**
 * <code>
 * The *Loader* class stores the configuration/conditional flags for the Command Line Interface (CLI).
 * All required & optional params are defined here.
 *
 * Usage e.g.
 *
 *
 * In the following examples the `jsqltoxml` JAR resource file is invoked with the required `-s or -sql` field
 * and the `-x or -xml` field.
 *
 * `-s or -sql` - takes the sql statement to be used to query the xml
 * `-x or -xml` - takes the name/path of the xml file to be queried
 *
 * ```bash
 * # short form
 * java -jar jsqltoxml.jar -s "select * from engine" -x "country.xml"
 *
 * # long form
 * java -jar jsqltoxml.jar -sql "select * from engine" -xml "country.xml"
 * ```
 *
 * There are several other optional flags that can be passed in that can  be used to perform various task:
 *
 *`-t or -type` - takes a ResultType {XML|TRIM|TEXT|DATA} and returns the DATA in the specified type. NB: Note these are
 * all DATA types, the ELEMENT type should only be used when using the API not the CLI
 *
 *`-o or -output` - takes the name of file. When the output flag is used a file is created with the specified name
 * and the query results are saved within.
 *</code>
 */
public final class Loader extends Configuration {

    /**
     * The sql.
     */
    @Option(name = "-sql", usage = "SQL String", aliases = {"-s"}, required = true)
    private String sql;

    /**
     * The xml file.
     */
    @Option(name = "-xml", usage = "XML file to be queried", aliases = {"-x"}, required = true)
    private String xmlFile;

    /**
     * The type.
     */
    @Option(name = "-type", usage = "Output type - {XML|TRIM|TEXT|DATA}", aliases = {"-t"})
    private ResultType type;

    /**
     * The output file.
     */
    @Option(name = "-output", usage = "Writes xml result to output file", aliases = {"-o"})
    private String outputFile;

    /**
     *<code>
     * Gets the output file.
     *</code>
     * @return the output file
     */
    public String getOutputFile() {
        return outputFile;
    }

    /**
     *<code>
     * Sets the output file.
     *</code>
     * @param outputFile
     *         the new output file
     */
    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    /**
     *<code>
     * Gets the sql.
     *</code>
     * @return the sql
     */
    public String getSql() {
        return sql;
    }

    /**
     *<code>
     * Sets the sql.
     *</code>
     * @param sql
     *         the new sql
     */
    public void setSql(String sql) {
        this.sql = sql;
    }

    /**
     *<code>
     * Gets the type. The Default Type is **ResultType.XML**
     *</code>
     * @return the type
     */
    public ResultType getType() {
        return type == null ? DEFAULT_TYPE : type;
    }

    /**
     *<code>
     * Sets the type.
     *</code>
     * @param type
     *         the new type
     */
    public void setType(ResultType type) {
        this.type = type;
    }

    /**
     *<code>
     * Gets the xml file.
     *</code>
     * @return the xml file
     */
    public String getXmlFile() {
        return xmlFile;
    }

    /**
     *<code>
     * Sets the xml file.
     *</code>
     * @param xmlFile
     *         the new xml file
     */
    public void setXmlFile(String xmlFile) {
        this.xmlFile = xmlFile;
    }
}
