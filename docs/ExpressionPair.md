![SPAT](spat.jpg) ExpressionPair
=====


An ExpressionPair is an internal Data Structure used by **BinaryExpressionTree**.
An ExpressionPair consists of an <a href="https://github.com/ferronrsmith/jsqltoxml/blob/master/docs/ExpressionType.md">ExpressionType</a>
and a BinaryExpression. The ExpressionType is derived from the BinaryExpression Type. which is then passed to a
parser which will determine if the type is supported by the JSQLParser.




###Constructors
- [ExpressionPair(BinaryExpression exp)](#-1257017123)
- [ExpressionPair(ExpressionType type, BinaryExpression expression)](#330851472)

###Methods
- [getExpression()](#-1521228785)  returns BinaryExpression
- [getId()](#-75456558)  returns String
- [getType()](#837210289)  returns ExpressionType
- [setExpression(BinaryExpression expression)](#-322463144) 
- [setId(String _id)](#-1084708685) 
- [setType(ExpressionType type)](#571135885) 


<a name="-1257017123">ExpressionPair</a>(BinaryExpression exp)
-----

- <b>exp</b>: 
        BinaryExpression


Instantiates a new expression pair.
The type will be derived from the BinaryExpression and added into the ExpressionPair



<a name="330851472">ExpressionPair</a>(ExpressionType type, BinaryExpression expression)
-----

- <b>type</b>: 
        the type
- <b>expression</b>: 
        the expression


#### <span style="font-size:12px;color:#AAAAAA">BinaryExpression</span> <a style="font-size:16px;" name="-1521228785">getExpression</a><span style="font-size:16px;">()</span>
- <b>returns</b>: the expression

Gets the expression.



#### <span style="font-size:12px;color:#AAAAAA">String</span> <a style="font-size:16px;" name="-75456558">getId</a><span style="font-size:16px;">()</span>
- <b>returns</b>: the id

Returns the ExpressionPair unique identifier



#### <span style="font-size:12px;color:#AAAAAA">ExpressionType</span> <a style="font-size:16px;" name="837210289">getType</a><span style="font-size:16px;">()</span>
- <b>returns</b>: the type

Gets the ExpressionType.



#### <a style="font-size:16px;" name="-322463144">setExpression</a><span style="font-size:16px;">(BinaryExpression expression)</span>
- <b>expression</b>: 
        the new expression


Sets the expression.



#### <a style="font-size:16px;" name="-1084708685">setId</a><span style="font-size:16px;">(String _id)</span>
- <b>_id</b>: 
        the new id


Sets the id. The identifier is usually an unique identifier
That's generated from the **LinkIdentifierGenerator** class



#### <a style="font-size:16px;" name="571135885">setType</a><span style="font-size:16px;">(ExpressionType type)</span>
- <b>type</b>: 
        the new type


Sets the ExpressionType.



