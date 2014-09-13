package com.uwi.visitors;

import com.uwi.ds.BinaryExpressionTree;
import com.uwi.ds.ExpressionHash;
import com.uwi.enums.ConditionType;
import com.uwi.enums.ExpressionType;
import com.uwi.ds.KeyValue;
import com.uwi.utils.LinkIdentifierGenerator;
import com.uwi.utils.Misc;
import com.uwi.visitors.abst.AbstractExpressionVisitor;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.GreaterThan;
import net.sf.jsqlparser.expression.operators.relational.GreaterThanEquals;
import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
import net.sf.jsqlparser.expression.operators.relational.MinorThan;
import net.sf.jsqlparser.expression.operators.relational.MinorThanEquals;
import net.sf.jsqlparser.expression.operators.relational.NotEqualsTo;

import java.util.regex.Pattern;

/**
 * The Class DefaultExpressionVisitor.
 */
public class DefaultExpressionVisitor extends AbstractExpressionVisitor {

    /**
     * The columns.
     */
    ExpressionHash _temp; // utility hash for saving data

    /** The columns. */
    // List<KeyValue> columns; // handle duplicate columns
    /**
     * The tree.
     */
    ExpressionHash tree;
    // for later
    /**
     * The expression tree.
     */
    BinaryExpressionTree expressionTree;
    /**
     * The has paren.
     */
    private boolean hasParen = false;

    /**
     * Gets the where clause.
     *
     * @param exp
     *         the exp
     *
     * @return the where clause
     */
    public String getWhereClause(Expression exp) {
        // don't traverse if null
        if (exp == null) {
            return null;
        }
        init();
        exp.accept(this);
        // [{and,name/text()='India'}, {and,food/text()='roti'}]
        return tree.merge();
    }

    /**
     * Inits the.
     */
    private void init() {
        // columns = new ArrayList<KeyValue>();
        _temp = new ExpressionHash();
        expressionTree = new BinaryExpressionTree();
        tree = new ExpressionHash();
    }

    private boolean isAttr(BinaryExpression exp) {
        return Pattern.matches(
                i18n("c_attr_regex"), exp.getLeftExpression().toString());
    }

    /**
     * Like operand.
     *
     * @param be
     *         the be
     *
     * @return the string
     */
    private String likeOperand(BinaryExpression be) {
        String left = be.getLeftExpression().toString();
        String right = be.getRightExpression().toString().replaceAll("'", "");
        String result = "";
        char fst = right.charAt(0);
        char lst = right.charAt(right.length() - 1);
        right = right.replaceAll("%", "");
        if (fst == '%' && lst == '%') {
            result = i18n("c_contains_exp", right);
        } else if (fst == '%') {
            result = i18n("c_startswith_exp", right);
        } else if (lst == '%') {
            result = i18n("c_endswith_exp", right);
        }
        return i18n("c_like_part_exp", left, result);
    }

    /**
     * Loads the alternate numeric template if the expression is a numeric value
     *
     * @param exp
     * @param tmpl
     *
     * @return
     */
    private String loadAlternativeTemplate(Expression exp, String tmpl) {
        // c_equal_exp
        String parts[] = tmpl.split(DEF_CONST);
        if (Misc.isDigits(exp.toString()) && parts.length > 1) {
            return "c_n_" + parts[1];
        } else {
            return tmpl;
        }
    }

    /**
     * Manage operands.
     *
     * @param exp
     *         the exp
     */
    private void manageOperands(BinaryExpression exp) {
        String equalTo = null;
        if (isAttr(exp)) {
            equalTo = processsAttr(exp);
        } else {
            switch (ConditionType.parse(exp.getStringExpression())) {
            case NOT_EQUAL:
                equalTo = i18n(
                        loadAlternativeTemplate(
                                exp.getRightExpression(), "c_not_exp"), exp.getLeftExpression(),
                        exp.getRightExpression());
                break;
            case EQUAL:
                equalTo = i18n(
                        loadAlternativeTemplate(
                                exp.getRightExpression(), "c_equal_exp"), exp.getLeftExpression(),
                        exp.getRightExpression());
                break;
            case GREATER_THAN:
            case LESS_THAN:
            case GREATER_THAN_EQUAL:
            case LESS_THAN_EQUAL:
                equalTo = i18n(
                        "c_alt_exp", exp.getLeftExpression(), exp.getStringExpression(), exp.getRightExpression());
                break;
            default:
                throw new IllegalArgumentException(
                        i18n(
                                "operand_parse", exp.getStringExpression()));
            }
        }

        // check if currentType is null
        KeyValue keyValue = new KeyValue(
                expressionTree.peekAtType().getValue(), equalTo, expressionTree.peekAtId());

        if (hasParen) {
            _temp.add(keyValue);
        } else {
            tree.add(keyValue);
        }
    }

    /**
     * Merge paren records.
     */
    public void mergeParenRecords() {
        // select * from country where food='roti' and (food='roti' or
        // food='hotdog')
        tree.add(_temp.cherryPick(new KeyValue().setValue(_temp.mergeParen())));
        _temp.clear();
    }

    private String processsAttr(BinaryExpression expression) {
        String[] str = expression.getLeftExpression().toString().split("_");
        String exp = expression.getStringExpression();
        String equalTo = i18n(
                "c_attribute_exp", str[1], exp, expression.getRightExpression());
        return equalTo;
    }

    /**
     * Visit.
     *
     * @param parenthesis
     *         the parenthesis
     */
    @Override
    public void visit(Parenthesis parenthesis) {
        hasParen = true;
        parenthesis.getExpression().accept(this);
        hasParen = false;
        mergeParenRecords();
    }

    /**
     * Visit.
     *
     * @param andExpression
     *         the and expression
     */
    @Override
    public void visit(AndExpression andExpression) {
        visitBinaryExpression(andExpression);
    }

    /**
     * Visit.
     *
     * @param orExpression
     *         the or expression
     */
    @Override
    public void visit(OrExpression orExpression) {
        visitBinaryExpression(orExpression);
    }

    /**
     * Visit.
     *
     * @param equalsTo
     *         the equals to
     */
    @Override
    public void visit(EqualsTo equalsTo) {
        manageOperands(equalsTo);
    }

    /**
     * Visit.
     *
     * @param greaterThan
     *         the greater than
     */
    @Override
    public void visit(GreaterThan greaterThan) {
        manageOperands(greaterThan);
    }

    /**
     * Visit.
     *
     * @param greaterThanEquals
     *         the greater than equals
     */
    @Override
    public void visit(GreaterThanEquals greaterThanEquals) {
        manageOperands(greaterThanEquals);
    }

    /**
     * Visit.
     *
     * @param likeExpression
     *         the like expression
     */
    @Override
    public void visit(LikeExpression likeExpression) {
        // TODO Auto-generated method stub
        tree.add(
                new KeyValue(
                        ExpressionType.LIKE.getValue(), likeOperand(likeExpression),
                        new LinkIdentifierGenerator().nextSessionId()));
    }

    /**
     * Visit.
     *
     * @param minorThan
     *         the minor than
     */
    @Override
    public void visit(MinorThan minorThan) {
        manageOperands(minorThan);
    }

    /**
     * Visit.
     *
     * @param minorThanEquals
     *         the minor than equals
     */
    @Override
    public void visit(MinorThanEquals minorThanEquals) {
        manageOperands(minorThanEquals);
    }

    /**
     * Visit.
     *
     * @param notEqualsTo
     *         the not equals to
     */
    @Override
    public void visit(NotEqualsTo notEqualsTo) {
        manageOperands(notEqualsTo);
    }

    /**
     * Visit binary expression.
     *
     * @param binaryExpression
     *         the binary expression
     */
    public void visitBinaryExpression(BinaryExpression binaryExpression) {
        expressionTree.add(binaryExpression);
        binaryExpression.getLeftExpression().accept(this);
        binaryExpression.getRightExpression().accept(this);
        expressionTree.remove();
    }

}
