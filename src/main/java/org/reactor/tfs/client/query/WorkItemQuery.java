package org.reactor.tfs.client.query;

import org.reactor.tfs.client.model.WorkItemColumn;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

/**
 * @author grabslu
 */
public class WorkItemQuery {

    private static final String WORK_ITEM_QUERY = "SELECT %s FROM WorkItems %s";
    private static final String WHERE_CLAUSE_EMPTY = "";

    private List<WorkItemColumn> columns = new ArrayList<>();
    private WorkItemQueryWhereClause whereClause;

    public String buildWiql() {
        return format(WORK_ITEM_QUERY, generateSelectColumns(), generateWhereStatement());
    }

    private String generateSelectColumns() {
        return columns.stream().map(this::generateSingleColumn).collect(joining(","));
    }

    private String generateWhereStatement() {
        if (!whereClause.hasAny()) {
            return WHERE_CLAUSE_EMPTY;
        }
        return whereClause.buildWiql();
    }

    private String generateSingleColumn(WorkItemColumn column) {
        return format("[%s]", column.getTfsFieldName());
    }

    public void addColumn(WorkItemColumn column) {
        columns.add(column);
    }

    public void setWhereClause(WorkItemQueryWhereClause whereClause) {
        this.whereClause = whereClause;
    }
}
