![SPAT](spat.jpg) FileWalker
=====


The **FileWalker** class provide methods to recursively iterate over a directory and its subdirectories
for all files that match the specified `extension`.




###Methods
- [getExtension(String path)](#-1260235468)  returns String
- [walk(File file, String ext, List results)](#1342175930) 
- [walk(String path, String ext, List results)](#-1236208314) 


#### <span style="font-size:12px;color:#AAAAAA">String</span> <a style="font-size:16px;" name="-1260235468">getExtension</a><span style="font-size:16px;">(String path)</span>
- <b>path</b>: file path

- <b>returns</b>: the file extension or an extension if no extension is detected

    Returns the extension for a given file path/name

```java
 getExtension("/tmp/junit1087035737113730538/sample4.java");
 // returns java

 getExtension("/tmp/junit1087035737113730538/sample5.txt");
 // returns txt
```



#### <a style="font-size:16px;" name="1342175930">walk</a><span style="font-size:16px;">(File file, String ext, List results)</span>
- <b>file</b>: - root file or directory
- <b>ext</b>: - extension of files
- <b>results</b>: - list of files with the specified extension


Returns a list of files that match the given extensions a specific directory & its subdirectories

<pre>
/sample.java
/sample.txt
/sample/sample5.java
/sample/sample4.txt

//-->
sample.java
sample5.java
</pre>



#### <a style="font-size:16px;" name="-1236208314">walk</a><span style="font-size:16px;">(String path, String ext, List results)</span>
- <b>path</b>: - file or directory path
- <b>ext</b>: - extension of files
- <b>results</b>: - list of files with the specified extension


Returns a list of files that match the given extensions a specific directory & its subdirectories

<pre>
/sample.java
/sample.txt
/sample/sample5.java
/sample/sample4.txt

//-->
sample.java
sample5.java
</pre>



