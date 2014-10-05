![SPAT](spat.jpg) QueryXML
=====


The *QueryXML* class is responsible for validating the generated XPath Expression against
an XML file. If validation fails the user will be notified and no result returned. If the statement
is valid a "query" will be executed against the supplied xml and the result returned.




###Methods
- [getData()](#357094689)  returns List
- [getElements()](#278207534)  returns List
- [query(String xpath, String xmlFileName, ResultType type)](#346184395)  returns QueryXML


#### <span style="font-size:12px;color:#AAAAAA">List&lt;String&gt;</span> <a style="font-size:16px;" name="357094689">getData</a><span style="font-size:16px;">()</span>
- <b>returns</b>: result list

Returns XML results in an String List



#### <span style="font-size:12px;color:#AAAAAA">List&lt;org.dom4j.Element&gt;</span> <a style="font-size:16px;" name="278207534">getElements</a><span style="font-size:16px;">()</span>
- <b>returns</b>: element list

Returns XML results as an XML Element



#### <span style="font-size:12px;color:#AAAAAA">QueryXML</span> <a style="font-size:16px;" name="346184395">query</a><span style="font-size:16px;">(String xpath, String xmlFileName, ResultType type)</span>
- <b>xpath</b>: the xpath
- <b>xmlFileName</b>: the xml file name
- <b>type</b>: the ResultType

- <b>returns</b>: the list

 Checks if the XPATH expression is valid then queries the XML document
 returning the result to the user. If the a count action is performed the numeric
 value will be wrapped in a `<resultCount>` tag.



