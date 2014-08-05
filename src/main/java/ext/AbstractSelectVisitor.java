package ext;

import net.sf.jsqlparser.statement.select.SelectVisitor;
import net.sf.jsqlparser.statement.select.SetOperationList;
import net.sf.jsqlparser.statement.select.WithItem;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractSelectVisitor.
 */
public abstract class AbstractSelectVisitor implements SelectVisitor {

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.statement.select.SelectVisitor#visit(net.sf.jsqlparser.statement.select.SetOperationList)
	 */
	public void visit(SetOperationList setOpList) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.statement.select.SelectVisitor#visit(net.sf.jsqlparser.statement.select.WithItem)
	 */
	public void visit(WithItem withItem) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
