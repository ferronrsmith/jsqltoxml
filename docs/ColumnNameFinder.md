![SPAT](spat.jpg) ColumnNameFinder
=====


The **ColumnNameFinder** class retrieves a list of column names from
SelectItem. Only the select portion of an SQL statement is processed by this class.
Currently only the **count** function has been implemented.
The SQL statement contains any other function an *IllegalArgumentException* with be thrown
##### Examples
```sql
select * from
# result [*]

select name, age, sex from
# result [name, age, sex]

select c.name, c.age, c.sex from
# result [name, age, sex]

select count(*)
# result [count(*)]

select count(name)
# result [count(name)]

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


##### Example
```sql
where name = 'joe'
```


Instantiates a new column name finder.



#### <span style="font-size:12px;color:#AAAAAA">List&lt;String&gt;</span> <a style="font-size:16px;" name="1407082077">getColumnNames</a><span style="font-size:16px;">()</span>
- <b>returns</b>: list of column names

Retrieve columns names from the list of SelectItem



#### <a style="font-size:16px;" name="-1867167502">visit</a><span style="font-size:16px;">(Column tableColumn)</span>

   `Gets` the column name from the and adds it to the result list



#### <a style="font-size:16px;" name="-1892215770">visit</a><span style="font-size:16px;">(Function function)</span>

Detects the function type (currently only count is supported) and apply the necessary
XPATH manipulation

Currently supports 4 count variations

```xquery
 count(//book[ends-with (., 'James')]/author)

 count(//book[ends-with (., 'James')])

 count(//book/author[ends-with (., 'James')])

 count(//book/author)
```



