package org.reactor.tfs.client.query;

import org.reactor.tfs.client.model.WorkItemColumn;
import org.reactor.tfs.client.model.WorkItemState;
import org.reactor.tfs.client.model.WorkItemType;

import static org.reactor.tfs.client.model.EqualityCheck.DIFFERENT;
import static org.reactor.tfs.client.model.WorkItemColumn.STATE;
import static org.reactor.tfs.client.model.WorkItemColumn.TEAM_PROJECT;
import static org.reactor.tfs.client.model.WorkItemColumn.TYPE;

/**
 * @author grabslu
 */
public class WorkItemQueryWhereBuilder {

  private WorkItemQueryWhereClause whereClause = new WorkItemQueryWhereClause();

  private WorkItemQueryWhereBuilder(WorkItemColumn workItemColumn, String value) {
    whereClause.setColumn(workItemColumn);
    whereClause.setValue(value);
  }

  public static WorkItemQueryWhereBuilder teamProject(String teamProject) {
    return new WorkItemQueryWhereBuilder(TEAM_PROJECT, teamProject);
  }

  public static WorkItemQueryWhereBuilder systemState(WorkItemState workItemState) {
    return new WorkItemQueryWhereBuilder(STATE, workItemState.getTfsValue());
  }

  public static WorkItemQueryWhereBuilder type(WorkItemType workItemType) {
    return new WorkItemQueryWhereBuilder(TYPE, workItemType.getTfsValue());
  }

  public WorkItemQueryWhereBuilder and(WorkItemQueryWhereBuilder workItemQueryWhereBuilder) {
    whereClause.addNested(workItemQueryWhereBuilder.nested().build());
    return this;
  }

  private WorkItemQueryWhereBuilder nested() {
    whereClause.setNested(true);
    return this;
  }

  public WorkItemQueryWhereBuilder not() {
    whereClause.setEqualityCheck(DIFFERENT);
    return this;
  }

  public WorkItemQueryWhereClause build() {
    return whereClause;
  }
}
