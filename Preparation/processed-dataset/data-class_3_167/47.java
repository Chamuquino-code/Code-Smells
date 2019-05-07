/**
	 * Changes the visibility state of the cells passed in. Note that the arrays
	 * must contain cells, not cell views.
	 * NOTE: Your GraphLayoutCache must be <code>partial</code> (set 
	 * <code>partial</code> to <code>true</code> in the constructor)
	 * in order to use the visibility functionality of expand/collapse,
	 * setVisible, etc.
	 * 
	 * @param visible
	 *            cells to be made visible
	 * @param invisible
	 *            cells to be made invisible
	 * @param attributes
	 *            a nested attribute map of cells/attribute maps
	 * @param cs
	 *            a <code>ConnectionSet</code> describing the new state of
	 *            edge connections in the graph
	 */
public void setVisible(Object[] visible, Object[] invisible, Map attributes, ConnectionSet cs) {
    GraphLayoutCacheEdit edit = new GraphLayoutCacheEdit(null, attributes, visible, invisible);
    edit.end();
    graphModel.edit(attributes, cs, null, new UndoableEdit[] { edit });
}
