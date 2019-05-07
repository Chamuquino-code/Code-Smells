public boolean equals(Expression other) {
    if (other == this) {
        return true;
    }
    if (other == null) {
        return false;
    }
    if (opType != other.opType || exprSubType != other.exprSubType || !equals(dataType, other.dataType)) {
        return false;
    }
    switch(opType) {
        case OpTypes.SIMPLE_COLUMN:
            return this.columnIndex == other.columnIndex;
        case OpTypes.VALUE:
            return equals(valueData, other.valueData);
        case OpTypes.ARRAY:
        // 
        case OpTypes.ARRAY_SUBQUERY:
        case OpTypes.ROW_SUBQUERY:
        case OpTypes.TABLE_SUBQUERY:
            return (subQuery.queryExpression.isEquivalent(other.subQuery.queryExpression));
        default:
            return equals(nodes, other.nodes) && equals(subQuery, other.subQuery);
    }
}
