![SPAT](spat.jpg) ColumnNameFinder
=====


The **ColumnNameFinder** class retrieves a list of column names from
SelectItem. Only the select portion of an SQL statement is processed by this class.
Currently only the **count** function has been implemented.
The SQL statement contains any other function an *IllegalArgumentException* with be thrown
<p/>
##### Examples
<p/>
```sql
select * from
# result [*]
<p/>
select name, age, sex from
# result [name, age, sex]
<p/>
select c.name, c.age, c.sex from
# result [name, age, sex]
<p/>
select count(*)
# result [count(*)]
<p/>
select count(name)
# result [count(name)]
<p/>
select count(c.name)
# result [count(name)]
```




###Constructors
- [ColumnNameFinder(SelectItem> selectItems, String table, String whereClause)](#-2104867959)

###Methods
- [getColumnNames()](#1407082077)  returns List
- [visit(Column tableColumn)](#-1867167502) 
- [visit(Function function)](#-1892215770) 


<a name="-2104867959">ColumnNameFinder</a>(SelectItem> selectItems, String table, String whereClause)
-----

- <b>selectItems</b>: 
        the select items :- Anything between "SELECT" and "FROM"
- <b>table</b>: 
        the table name
- <b>whereClause</b>: 
        the whereClause
        <p/>
        ##### Example
        ```sql
        where name = 'joe'
        ```


#### <span style="font-size:12px;color:#AAAAAA">List&lt;String&gt;</span> <a style="font-size:16px;" name="1407082077">getColumnNames</a><span style="font-size:16px;">()</span>
- <b>returns</b>: list of column names

Retrieve columns names from the list of SelectItem



#### <a style="font-size:16px;" name="-1867167502">visit</a><span style="font-size:16px;">(Column tableColumn)</span>

#### <a style="font-size:16px;" name="-1892215770">visit</a><span style="font-size:16px;">(Function function)</span>

