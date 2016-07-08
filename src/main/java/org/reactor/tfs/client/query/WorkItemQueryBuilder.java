package org.reactor.tfs.client.query;

import org.reactor.tfs.client.model.WorkItemColumn;

/**
 * @author grabslu
 */
public class WorkItemQueryBuilder {

    private final WorkItemQuery workItemQuery;

    private WorkItemQueryBuilder() {
        workItemQuery = new WorkItemQuery();
    }

    public static WorkItemQueryBuilder query() {
        return new WorkItemQueryBuilder();
    }

    public WorkItemQuery build() {
        return workItemQuery;
    }

    public WorkItemQueryBuilder select(WorkItemColumn... workItemColumns) {
        for (WorkItemColumn workItemColumn : workItemColumns) {
            workItemQuery.addColumn(workItemColumn);
        }
        return this;
    }

    public WorkItemQueryBuilder where(WorkItemQueryWhereBuilder workItemQueryWhereBuilder) {
        workItemQuery.setWhereClause(workItemQueryWhereBuilder.build());
        return this;
    }
}
