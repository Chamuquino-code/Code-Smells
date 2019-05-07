/**
     * Domains are shown if the authorization is the user or a role given to the
     * user.
     *
     * <p>
     *
     * @return Table
     */
Table COLUMN_DOMAIN_USAGE(Session session) {
    Table t = sysTables[COLUMN_DOMAIN_USAGE];
    if (t == null) {
        t = createBlankTable(sysTableHsqlNames[COLUMN_DOMAIN_USAGE]);
        addColumn(t, "DOMAIN_CATALOG", SQL_IDENTIFIER);
        addColumn(t, "DOMAIN_SCHEMA", SQL_IDENTIFIER);
        addColumn(t, "DOMAIN_NAME", SQL_IDENTIFIER);
        addColumn(t, "TABLE_CATALOG", SQL_IDENTIFIER);
        addColumn(t, "TABLE_SCHEMA", SQL_IDENTIFIER);
        addColumn(t, "TABLE_NAME", SQL_IDENTIFIER);
        // not null 
        addColumn(t, "COLUMN_NAME", SQL_IDENTIFIER);
        // not null 
        HsqlName name = HsqlNameManager.newInfoSchemaObjectName(sysTableHsqlNames[COLUMN_DOMAIN_USAGE].name, false, SchemaObject.INDEX);
        t.createPrimaryKeyConstraint(name, new int[] { 0, 1, 2, 3, 4, 5, 6 }, false);
        return t;
    }
    PersistentStore store = session.sessionData.getRowStore(t);
    Session sys = database.sessionManager.newSysSession(SqlInvariants.INFORMATION_SCHEMA_HSQLNAME, session.getUser());
    Result rs = sys.executeDirectStatement("SELECT DOMAIN_CATALOG, DOMAIN_SCHEMA, DOMAIN_NAME, TABLE_CATALOG, " + "TABLE_SCHEMA, TABLE_NAME, COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS " + "WHERE DOMAIN_NAME IS NOT NULL;", ResultProperties.defaultPropsValue);
    t.insertSys(store, rs);
    sys.close();
    return t;
}
