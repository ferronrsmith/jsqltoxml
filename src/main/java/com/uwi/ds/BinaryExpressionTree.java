package com.uwi.ds;

import com.uwi.enums.ExpressionType;
import com.uwi.utils.LinkIdentifierGenerator;
import net.sf.jsqlparser.expression.BinaryExpression;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * <code>
 * The *BinaryExpressionTree* class is a **Queue** that stores
 * Expression traversal. When
 * **DefaultExpressionVisitor#visitBinaryExpression(BinaryExpression)** is
 * called, the expression is added to a **ArrayDeque** for later retrieval
 * and querying. When multiple binary traversals are done such as
 *
 *```sql
 *  select * from country where name = 'Jamaica' and id = 99 or (name = 'Trinidad')
 *```
 * The expression are added as follows :
 *
 * - name = 'Jamaica' AND id = 99 OR name = 'Trinidad'
 * - id = 99 OR name = 'Trinidad'
 * - name = 'Trinidad' AND id = 99
 *
 * This allows a visitor to **peek** at the current expression and view the
 * left or right expression and operand string, allowing the code to be more
 * flexible. A visitor only ever need to look at the last expression since that
 * would be the currently processed expression.
 * </code>
 */
public class BinaryExpressionTree {

    /**
     * The insert failed list.
     */
    private ArrayList<ExpressionPair> insertFailedList;

    /**
     * The expressions.
     */
    private ArrayDeque<ExpressionPair> expressions;

    /**
     * <code>
     * Instantiates a new binary expression tree.
     * </code>
     */
    public BinaryExpressionTree() {
        expressions = new ArrayDeque<ExpressionPair>();
        insertFailedList = new ArrayList<ExpressionPair>();
    }

    /**
     * <code>
     *  Adds a *BinaryExpression* to the internal *ArrayDeque*.
     *  If insertion failed, the *ExpressionPair* is added to a *insertFailedList*
     *  which can then be logged out or use to redo insertion at a later time
     *
     *  BinaryExpression are ExpressionType supported by SPAT. Currently there are
     *  four(4) supported expression type namely:
     *
     *  - ANY
     *  - OR
     *  - AND
     *  - LIKE
     * </code>
     *
     * @param exp
     *         the BinaryExpression exp
     */
    public void add(BinaryExpression exp) {
        ExpressionPair pair = new ExpressionPair(exp);
        boolean result = expressions.add(pair);
        if (!result) {
            insertFailedList.add(pair);
        } else {
            System.out.println(pair.getType());
        }
    }

    /**
     * <code>
     *     Returns the ArrayDeque as a list of ExpressionPair
     * </code>
     *
     * @return the list of ExpressionPair
     */
    public List<ExpressionPair> get() {
        return new ArrayList<ExpressionPair>(expressions);
    }

    /**
     * Gets the insert failed list.
     * @internal
     * @return the insert failed list
     */
    public ArrayList<ExpressionPair> getInsertFailedList() {
        return insertFailedList;
    }

    /**
     * <code>
     * Peek/Poll on the last expression
     * </code>
     * @return the expression pair
     */
    public ExpressionPair peek() {
        return expressions.peekLast();
    }

    /**
     * <code>
     *  Peek at the last ExpressionPair BinaryExpression value.
     * </code>
     * @return the BinaryExpression
     */
    public BinaryExpression peekAtExpression() {
        ExpressionPair pair;
        if ((pair = peek()) == null) {
            return null;
        }
        return pair.getExpression();
    }

    /**
     * <code>
     * Peek at the last ExpressionPair unique identifier.
     * If the ExpressionPair is null a new identifier is generated
     * </code>
     * @return ExpressionPair unique identifier
     */
    public String peekAtId() {
        ExpressionPair pair;
        if ((pair = peek()) == null) {
            return new LinkIdentifierGenerator().nextSessionId();
        }
        return pair.getId();
    }

    /**
     * <code>
     * Peek at the last ExpressionPair ExpressionType
     * If the ExpressionPair is null the ANY expression type is returned
     * </code>
     * @return the ExpressionType || ExpressionType.ANY
     */
    public ExpressionType peekAtType() {
        ExpressionPair pair;
        if ((pair = peek()) == null) {
            return ExpressionType.ANY;
        }
        return pair.getType();
    }

    /**
     * <code>
     * Removes the last expression
     * </code>
     */
    public void remove() {
        expressions.removeLast();
    }
}
