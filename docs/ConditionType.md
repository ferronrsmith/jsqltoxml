![SPAT](spat.jpg) ConditionType
=====


The **ConditionType** enum stores conditional types that can be parsed by
**DefaultExpressionVisitor#manageOperand** and its internal methods.




###Methods
- [getValue()](#1268464124)  returns String
- [parse(String property)](#685274522)  returns ConditionType


#### <span style="font-size:12px;color:#AAAAAA">String</span> <a style="font-size:16px;" name="1268464124">getValue</a><span style="font-size:16px;">()</span>
- <b>returns</b>: the value

Returns the ConditionType String Value
##### Example
 ```java
 ConditionType ct = ConditionType.GREATER_THAN
 ct.getValue()
 // returns ">"
```



#### <span style="font-size:12px;color:#AAAAAA">ConditionType</span> <a style="font-size:16px;" name="685274522">parse</a><span style="font-size:16px;">(String property)</span>
- <b>property</b>: 
        conditional string to be parsed.

- <b>returns</b>: mapped ConditionType

 Parses a String to a **ConditionType**. If the type is not supported by SPAT
 the `ConditionType.NULL` will be returned.
##### Example
 ```java
 ConditionType.parse(">");
 // returns ConditionType.GREATER_THAN
 ```



