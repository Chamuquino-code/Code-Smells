//-------------------------------------------------- Command Implementation 
/**
     * Execute this command, returning a {@link java.sql.ResultSet}.
     * @return the {@link java.sql.ResultSet} generated by this command.
     * @throws SQLException
     */
public AxionResultSet executeQuery(Database db) throws AxionException {
    resolve(db);
    if (_currentDatabase != db) {
        processQuery(db);
    } else {
        _rows.reset();
    }
    RowIterator rows = _rows;
    // apply aggregate function, if any 
    if (_foundAggregateFunction) {
        AggregateFunction vfn = (AggregateFunction) (getSelect(0));
        SimpleRow row = new SimpleRow(1);
        row.set(0, vfn.evaluate(new RowIteratorRowDecoratorIterator(rows, new RowDecorator(_colIdToFieldMap))));
        SingleRowIterator fniter = new SingleRowIterator(row);
        Selectable[] selarray = new Selectable[] { new ColumnIdentifier(new TableIdentifier(), vfn.getName(), null, vfn.getDataType()) };
        HashMap fieldmap = new HashMap();
        fieldmap.put(selarray[0], new Integer(0));
        return new AxionResultSet(new RowIteratorRowDecoratorIterator(fniter, new RowDecorator(fieldmap)), selarray);
    } else {
        return new AxionResultSet(new RowIteratorRowDecoratorIterator(rows, new RowDecorator(_colIdToFieldMap)), _selected);
    }
}
