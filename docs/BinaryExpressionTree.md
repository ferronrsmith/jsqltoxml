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
- [getInsertFailedList()](#1041682443)  returns ArrayList
- [peek()](#-992113764)  returns ExpressionPair
- [peekAtExpression()](#452280231)  returns BinaryExpression
- [peekAtId()](#-324856982)  returns String
- [peekAtType()](#1681571401)  returns ExpressionType
- [remove()](#-512824187) 


<a name="-1101328360">BinaryExpressionTree</a>()
-----


#### <a style="font-size:16px;" name="1768379020">add</a><span style="font-size:16px;">(BinaryExpression exp)</span>
- <b>exp</b>: 
        the exp


#### <span style="font-size:12px;color:#AAAAAA">List&lt;ExpressionPair&gt;</span> <a style="font-size:16px;" name="98244311">get</a><span style="font-size:16px;">()</span>
- <b>returns</b>: the list

#### <span style="font-size:12px;color:#AAAAAA">ArrayList&lt;ExpressionPair&gt;</span> <a style="font-size:16px;" name="1041682443">getInsertFailedList</a><span style="font-size:16px;">()</span>
- <b>returns</b>: the insert failed list

#### <span style="font-size:12px;color:#AAAAAA">ExpressionPair</span> <a style="font-size:16px;" name="-992113764">peek</a><span style="font-size:16px;">()</span>
- <b>returns</b>: the expression pair

#### <span style="font-size:12px;color:#AAAAAA">BinaryExpression</span> <a style="font-size:16px;" name="452280231">peekAtExpression</a><span style="font-size:16px;">()</span>
- <b>returns</b>: the binary expression

#### <span style="font-size:12px;color:#AAAAAA">String</span> <a style="font-size:16px;" name="-324856982">peekAtId</a><span style="font-size:16px;">()</span>
- <b>returns</b>: the string

#### <span style="font-size:12px;color:#AAAAAA">ExpressionType</span> <a style="font-size:16px;" name="1681571401">peekAtType</a><span style="font-size:16px;">()</span>
- <b>returns</b>: the expression type

#### <a style="font-size:16px;" name="-512824187">remove</a><span style="font-size:16px;">()</span>

