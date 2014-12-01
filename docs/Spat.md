![SPAT](spat.jpg) Spat
=====


 Spat is a facade for the JSQLParser that offers an easy API that is used by the CLI, GUI front-ends.




###Methods
- [generateXpath(String sql)](#-1659832918)  returns String
- [parse(String xmlfile, String sql, ResultType type, String outputFile)](#-1099670828) 
- [parseXML(String xmlfile, String sql, ResultType type, String outputFile)](#-1075670907)  returns List


#### <span style="font-size:12px;color:#AAAAAA">String</span> <a style="font-size:16px;" name="-1659832918">generateXpath</a><span style="font-size:16px;">(String sql)</span>
- <b>sql</b>: 
        the sql statement

- <b>returns</b>: unvalidated xpath expression

Generates an XPATH expression from a given SQL statement.
If a valid SQL query is passed to Spat it will parse that expression
creating visitors which are then traversed. An Expression Tree is generated
from the SQL segments which are organized and converted to an XPATH expression.
If an invalid SQL statement is passed an exception will be thrown. This method
is mainly a helper method that is used by different interfaces & methods
> Please note that this expression has not been validated by **QueryXML** class



#### <a style="font-size:16px;" name="-1099670828">parse</a><span style="font-size:16px;">(String xmlfile, String sql, ResultType type, String outputFile)</span>
- <b>xmlfile</b>: 
        the xml file to be queried
- <b>sql</b>: 
        the sql statement
- <b>type</b>: 
        the ResultType to be returned. Please see <a href="https://github
        .com/ferronrsmith/jsqltoxml/blob/master/docs/ResultType.md"></a> for a listing of all supported types
- <b>outputFile</b>: 
        the output file results are to be written to.


The following function takes an sql statement which is then parsed.
Unlike the *generateXpath* method, validation is performed on the XPATH
expression after generation. If the XPATH expression is valid, it will then
be passed to the internal XPATH Querying library which will query the supplied
XML file. The output is written to the terminal (CLI) and written to
an output file if supplied.



#### <span style="font-size:12px;color:#AAAAAA">List&lt;String&gt;</span> <a style="font-size:16px;" name="-1075670907">parseXML</a><span style="font-size:16px;">(String xmlfile, String sql, ResultType type, String outputFile)</span>
- <b>xmlfile</b>: 
        the xml file to be queried
- <b>sql</b>: 
        the sql statement
- <b>type</b>: 
        the ResultType to be returned. Please see <a href="https://github
        .com/ferronrsmith/jsqltoxml/blob/master/docs/ResultType.md"></a> for a listing of all supported types
- <b>outputFile</b>: 
        the output file results are to be written to.

- <b>returns</b>: A list of xml nodes that match the xpath expression

The following function takes an sql statement which is then parsed.
Unlike the *generateXpath* method, validation is performed on the XPATH
expression after generation. If the XPATH expression is valid, it will then
be passed to the internal XPATH Querying library which will query the supplied
XML file. The output is written as a list of string and written to
an output file if supplied.



