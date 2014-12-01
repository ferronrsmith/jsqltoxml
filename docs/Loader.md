![SPAT](spat.jpg) Loader
=====


The *Loader* class stores the configuration/conditional flags for the Command Line Interface (CLI).
All required & optional params are defined here.

Usage e.g.


In the following examples the `jsqltoxml` JAR resource file is invoked with the required `-s or -sql` field
and the `-x or -xml` field.

`-s or -sql` - takes the sql statement to be used to query the xml
`-x or -xml` - takes the name/path of the xml file to be queried

```bash
# short form
java -jar jsqltoxml.jar -s "select * from engine" -x "country.xml"

# long form
java -jar jsqltoxml.jar -sql "select * from engine" -xml "country.xml"
```

There are several other optional flags that can be passed in that can  be used to perform various task:

`-t or -type` - takes a ResultType {XML|TRIM|TEXT|DATA} and returns the DATA in the specified type. NB: Note these are
all DATA types, the ELEMENT type should only be used when using the API not the CLI

`-o or -output` - takes the name of file. When the output flag is used a file is created with the specified name
and the query results are saved within.




###Methods
- [getOutputFile()](#1113628244)  returns String
- [getSql()](#1965501849)  returns String
- [getType()](#837210289)  returns ResultType
- [getXmlFile()](#-1997534274)  returns String
- [setOutputFile(String outputFile)](#-1747223578) 
- [setSql(String sql)](#77812110) 
- [setType(ResultType type)](#-577695534) 
- [setXmlFile(String xmlFile)](#-1903643730) 


#### <span style="font-size:12px;color:#AAAAAA">String</span> <a style="font-size:16px;" name="1113628244">getOutputFile</a><span style="font-size:16px;">()</span>
- <b>returns</b>: the output file

Gets the output file.



#### <span style="font-size:12px;color:#AAAAAA">String</span> <a style="font-size:16px;" name="1965501849">getSql</a><span style="font-size:16px;">()</span>
- <b>returns</b>: the sql

Gets the sql statement that was passed in.



#### <span style="font-size:12px;color:#AAAAAA">ResultType</span> <a style="font-size:16px;" name="837210289">getType</a><span style="font-size:16px;">()</span>
- <b>returns</b>: the type

Retrnst the ResultType. The Default Type is **ResultType.XML**



#### <span style="font-size:12px;color:#AAAAAA">String</span> <a style="font-size:16px;" name="-1997534274">getXmlFile</a><span style="font-size:16px;">()</span>
- <b>returns</b>: the xml file

Gets the xml file name/path



#### <a style="font-size:16px;" name="-1747223578">setOutputFile</a><span style="font-size:16px;">(String outputFile)</span>
- <b>outputFile</b>: 
        the new output file


Sets the output file.



#### <a style="font-size:16px;" name="77812110">setSql</a><span style="font-size:16px;">(String sql)</span>
- <b>sql</b>: 
        the new sql


Sets the sql statment



#### <a style="font-size:16px;" name="-577695534">setType</a><span style="font-size:16px;">(ResultType type)</span>
- <b>type</b>: 
        the new type


Sets the ResultType.



#### <a style="font-size:16px;" name="-1903643730">setXmlFile</a><span style="font-size:16px;">(String xmlFile)</span>
- <b>xmlFile</b>: 
        the new xml file


Sets the xml file path



