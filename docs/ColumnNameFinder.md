![SPAT](spat.jpg) ColumnNameFinder
=====

ColumnNameFinder



###Constructors
- [ColumnNameFinder(SelectItem> selectItems, String table, String whereClause)](#-2104867959)

###Methods
- [getColumnNames()](#1407082077)  returns List
- [visit(AllColumns allColumns)](#107642406) 
- [visit(AllTableColumns allTableColumns)](#1406975942) 
- [visit(Column tableColumn)](#-1867167502) 
- [visit(Function function)](#-1892215770) 
- [visit(SelectExpressionItem selectExpressionItem)](#825865670) 


<a name="-2104867959">ColumnNameFinder</a>(SelectItem> selectItems, String table, String whereClause)
-----

- <b>selectItems</b>: 
        the select items
- <b>table</b>: 
        the table
- <b>whereClause</b>: 


#### <span style="font-size:12px;color:#AAAAAA">List&lt;String&gt;</span> <a style="font-size:16px;" name="1407082077">getColumnNames</a><span style="font-size:16px;">()</span>
- <b>returns</b>: the table names

#### <a style="font-size:16px;" name="107642406">visit</a><span style="font-size:16px;">(AllColumns allColumns)</span>

#### <a style="font-size:16px;" name="1406975942">visit</a><span style="font-size:16px;">(AllTableColumns allTableColumns)</span>

#### <a style="font-size:16px;" name="-1867167502">visit</a><span style="font-size:16px;">(Column tableColumn)</span>

#### <a style="font-size:16px;" name="-1892215770">visit</a><span style="font-size:16px;">(Function function)</span>

#### <a style="font-size:16px;" name="825865670">visit</a><span style="font-size:16px;">(SelectExpressionItem selectExpressionItem)</span>

