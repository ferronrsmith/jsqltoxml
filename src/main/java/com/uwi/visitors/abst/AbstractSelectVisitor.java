package com.uwi.visitors.abst;

import com.uwi.config.Configuration;
import net.sf.jsqlparser.statement.select.SelectVisitor;
import net.sf.jsqlparser.statement.select.SetOperationList;
import net.sf.jsqlparser.statement.select.WithItem;

// TODO: Auto-generated Javadoc

/**
 * @internal
 * The Class AbstractSelectVisitor.
 */
public abstract class AbstractSelectVisitor extends Configuration implements SelectVisitor {

    /*
     * (non-Javadoc)
     *
     * @see
     * net.sf.jsqlparser.statement.select.SelectVisitor#visit(net.sf.jsqlparser
     * .statement.select.SetOperationList)
     */
    @Override
    public void visit(SetOperationList setOpList) {
        throw new UnsupportedOperationException(i18n("no_support"));
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * net.sf.jsqlparser.statement.select.SelectVisitor#visit(net.sf.jsqlparser
     * .statement.select.WithItem)
     */
    @Override
    public void visit(WithItem withItem) {
        throw new UnsupportedOperationException(i18n("no_support"));
    }
}
