![SPAT](spat.jpg) ExpressionHash
=====


The *ExpressionHash* class keeps track of the SQL Expression **( KeyValue )** as the Visitor traverses each portion.
At each level of the Visitor "Tree" there are at most two expressions.

e.g.
```sql
id = '2323aa' AND author = "J.K Rowling"
```

The ExpressionHash merges all sub-level expressions into one, all the way down until the right-most part of the SQL
Expression **( KeyValue )** has been processed. The result of this is a partial XPATH expression that is returned
to the requested class for further processing/merging.




###Constructors
- [ExpressionHash()](#-1318638745)

###Methods
- [add(KeyValue value)](#-578619457)  returns ExpressionHash
- [cherryPick(KeyValue value)](#-625778056)  returns KeyValue
- [clear()](#856775694)  returns ExpressionHash
- [flatten()](#-1107036667)  returns List
- [merge()](#953645881)  returns String
- [mergeParen()](#-1528805325)  returns String


<a name="-1318638745">ExpressionHash</a>()
-----


#### <span style="font-size:12px;color:#AAAAAA">ExpressionHash</span> <a style="font-size:16px;" name="-578619457">add</a><span style="font-size:16px;">(KeyValue value)</span>
- <b>value</b>: - KeyValue to be added the bucket

- <b>returns</b>: the expression hash

Adds the a KeyValue to a bucket. The **KeyValue#getMetaData**
contains the unique_id for each <b>KeyValue</b>. If the session_id does
not exist with the bucket then a new item will be added and the
<b>KeyValue</b> added to a list. If the session_id already exists the
<b>KeyValue</b> will be appended to the existing list <b>K<session_id>,
V<List<KeyValue>></b>



#### <span style="font-size:12px;color:#AAAAAA">KeyValue</span> <a style="font-size:16px;" name="-625778056">cherryPick</a><span style="font-size:16px;">(KeyValue value)</span>
- <b>value</b>: - sub-level **KeyValue**

- <b>returns</b>: the key value

Cherry pick checks a record for the existence of a <b>key</b> and
<b>session_id</b>. If these values haven't been set, the first record from
the list will be retrieved and its <b>key</b> and <b>session_id</id> assigned to
the input record.
<b>NB:</b> Only two records can exist at a given sub-level so the first
record key & session_id should be the same as the input record

<pre>
|------ id=8jsjjsjs;book = "Harry"
     | ------ op = "AND"
         |------id=8jsjjsjs;author = "J.K Rowlings"
</pre>



#### <span style="font-size:12px;color:#AAAAAA">ExpressionHash</span> <a style="font-size:16px;" name="856775694">clear</a><span style="font-size:16px;">()</span>
- <b>returns</b>: the expression hash

Clear bucket and keyValue lists. Used in cases where a <b>_temp</b>
*ExpressionHash* is created and must be cleared for re-use



#### <span style="font-size:12px;color:#AAAAAA">List&lt;KeyValue&gt;</span> <a style="font-size:16px;" name="-1107036667">flatten</a><span style="font-size:16px;">()</span>
- <b>returns</b>: the list

The following function flattens a bucket of **KeyValue** list into a
<b>grouped & flatten</b> form that can be later merged with other
expressions.



#### <span style="font-size:12px;color:#AAAAAA">String</span> <a style="font-size:16px;" name="953645881">merge</a><span style="font-size:16px;">()</span>
- <b>returns</b>: the string

Converts a list of flattened KeyValue pairs into a string.

e.g.
```java
[
 { key=or,value=name/text()='India' and food/text()='roti',metadata=2ccgm7g3uogfonfie8mtcgrp0n },
 { key=or,value=food/text()='barfi',metadata=pvgasoj8v4hs09jsqpov2hidlr }
]
```

will be flatten to :

```java
 name/text()='India' and food/text()='roti' or food/text()='barfi'
```



#### <span style="font-size:12px;color:#AAAAAA">String</span> <a style="font-size:16px;" name="-1528805325">mergeParen</a><span style="font-size:16px;">()</span>
- <b>returns</b>: the string

    Invokes the merge function and wraps the result in params



