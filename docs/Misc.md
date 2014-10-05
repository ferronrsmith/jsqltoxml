![SPAT](spat.jpg) Misc
=====


The **Misc** class defines static utility methods that is used
through-out the library.




###Methods
- [closeQuietly(Closeable closeable)](#1215678180) 
- [isBlank(CharSequence cs)](#646194830)  returns boolean
- [isDigits(String str)](#84840495)  returns boolean
- [isNotBlank(CharSequence cs)](#-162085523)  returns boolean
- [prettyPrint(String xml)](#1115311536)  returns String
- [readFilesToString(String path)](#877720880)  returns String
- [saveToFile(String outFile, List data)](#-1160258560) 


#### <a style="font-size:16px;" name="1215678180">closeQuietly</a><span style="font-size:16px;">(Closeable closeable)</span>
- <b>closeable</b>: 
        the object to close, may be null or already closed


Unconditionally close a <code>Closeable


#### <span style="font-size:12px;color:#AAAAAA">boolean</span> <a style="font-size:16px;" name="646194830">isBlank</a><span style="font-size:16px;">(CharSequence cs)</span>
- <b>cs</b>: 
        the CharSequence to check, may be null

- <b>returns</b>: **true** if the CharSequence is null, empty or whitespace

<p>
Checks if a CharSequence is whitespace, empty ("") or null.
</p>
<p/>
```java
Misc.isBlank(null);      //= true
Misc.isBlank("");        //= true
Misc.isBlank(" ");       //= true
Misc.isBlank("bob");     //= false
Misc.isBlank("  bob  "); //= false
```



#### <span style="font-size:12px;color:#AAAAAA">boolean</span> <a style="font-size:16px;" name="84840495">isDigits</a><span style="font-size:16px;">(String str)</span>
- <b>str</b>: 
        the <code>String</code> to check


<p>
Checks whether the *String* contains only digit characters.
</p>
**Null** and empty String will return **false**.



#### <span style="font-size:12px;color:#AAAAAA">boolean</span> <a style="font-size:16px;" name="-162085523">isNotBlank</a><span style="font-size:16px;">(CharSequence cs)</span>
- <b>cs</b>: 
        the CharSequence to check, may be null

- <b>returns</b>: **true** if the CharSequence is not empty and not null and
not whitespace

<p>
Checks if a CharSequence is not empty (""), not null and not whitespace
only.
</p>
<p/>
```java
Misc.isNotBlank(null);      // = false
Misc.isNotBlank("");        // = false
Misc.isNotBlank(" ");       // = false
Misc.isNotBlank("bob");     // = true
Misc.isNotBlank("  bob  "); // = true
```



#### <span style="font-size:12px;color:#AAAAAA">String</span> <a style="font-size:16px;" name="1115311536">prettyPrint</a><span style="font-size:16px;">(String xml)</span>
- <b>xml</b>: 
        the xml document

- <b>returns</b>: the string

Properly formats XML document



#### <span style="font-size:12px;color:#AAAAAA">String</span> <a style="font-size:16px;" name="877720880">readFilesToString</a><span style="font-size:16px;">(String path)</span>
- <b>path</b>: 
        - file path

- <b>returns</b>: string with file contents

Reads a file contents into a string



#### <a style="font-size:16px;" name="-1160258560">saveToFile</a><span style="font-size:16px;">(String outFile, List data)</span>
- <b>outFile</b>: 
        the out file
- <b>data</b>: 
        the data


Save data to file.



