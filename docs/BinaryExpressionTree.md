![SPAT](spat.jpg) BinaryExpressionTree
=====


The *BinaryExpressionTree* class is a **Queue** that stores
Expression traversal. When
**DefaultExpressionVisitor#visitBinaryExpression(BinaryExpression)** is
called, the expression is added to a **ArrayDeque** for later retrieval
and querying. When multiple binary traversals are done such as

```sql
 select * from country where name = 'Jamaica' and id = 99 or (name = 'Trinidad')
```
The expression are added as follows :

- name = 'Jamaica' AND id = 99 OR name = 'Trinidad'
- id = 99 OR name = 'Trinidad'
- name = 'Trinidad' AND id = 99

This allows a visitor to **peek** at the current expression and view the
left or right expression and operand string, allowing the code to be more
flexible. A visitor only ever need to look at the last expression since that
would be the currently processed expression.




###Constructors
- [BinaryExpressionTree()](#-1101328360)

###Methods
- [add(BinaryExpression exp)](#1768379020) 
- [get()](#98244311)  returns List
- [peek()](#-992113764)  returns ExpressionPair
- [peekAtExpression()](#452280231)  returns BinaryExpression
- [peekAtId()](#-324856982)  returns String
- [peekAtType()](#1681571401)  returns ExpressionType
- [remove()](#-512824187) 


<a name="-1101328360">BinaryExpressionTree</a>()
-----


Instantiates a new binary expression tree.



#### <a style="font-size:16px;" name="1768379020">add</a><span style="font-size:16px;">(BinaryExpression exp)</span>
- <b>exp</b>: 
        the BinaryExpression exp


 Adds a *BinaryExpression* to the internal *ArrayDeque*.
 If insertion failed, the *ExpressionPair* is added to a *insertFailedList*
 which can then be logged out or use to redo insertion at a later time

 BinaryExpression are ExpressionType supported by SPAT. Currently there are
 four(4) supported expression type namely:

 - ANY
 - OR
 - AND
 - LIKE



#### <span style="font-size:12px;color:#AAAAAA">List&lt;ExpressionPair&gt;</span> <a style="font-size:16px;" name="98244311">get</a><span style="font-size:16px;">()</span>
- <b>returns</b>: the list of ExpressionPair

    Returns the ArrayDeque as a list of ExpressionPair



#### <span style="font-size:12px;color:#AAAAAA">ExpressionPair</span> <a style="font-size:16px;" name="-992113764">peek</a><span style="font-size:16px;">()</span>
- <b>returns</b>: the expression pair

Peek/Poll on the last expression



#### <span style="font-size:12px;color:#AAAAAA">BinaryExpression</span> <a style="font-size:16px;" name="452280231">peekAtExpression</a><span style="font-size:16px;">()</span>
- <b>returns</b>: the BinaryExpression

 Peek at the last ExpressionPair BinaryExpression value.



#### <span style="font-size:12px;color:#AAAAAA">String</span> <a style="font-size:16px;" name="-324856982">peekAtId</a><span style="font-size:16px;">()</span>
- <b>returns</b>: ExpressionPair unique identifier

Peek at the last ExpressionPair unique identifier.
If the ExpressionPair is null a new identifier is generated



#### <span style="font-size:12px;color:#AAAAAA">ExpressionType</span> <a style="font-size:16px;" name="1681571401">peekAtType</a><span style="font-size:16px;">()</span>
- <b>returns</b>: the ExpressionType || ExpressionType.ANY

Peek at the last ExpressionPair ExpressionType
If the ExpressionPair is null the ANY expression type is returned



#### <a style="font-size:16px;" name="-512824187">remove</a><span style="font-size:16px;">()</span>

Removes the last expression



