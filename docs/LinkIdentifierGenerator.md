<img src='spat.jpg' alt='SPAT' style='position: relative;top: 5px;'/> LinkIdentifierGenerator
=====


The **LinkIdentifierGenerator** class is responsible for generating
pseudo-random sessionId that will be used to uniquely identify an
Expression and an Expression group




###Methods
- [nextSessionId()](#1690290719)  returns String


#### <span style="font-size:12px;color:#AAAAAA">String</span> <a style="font-size:16px;" name="1690290719">nextSessionId</a><span style="font-size:16px;">()</span>
- <b>returns</b>: unique identifier string

Generates a new session id with radix 32.

```java
 // generates a unique id
 new LinkIdentifierGenerator().nextSessionId();
```



