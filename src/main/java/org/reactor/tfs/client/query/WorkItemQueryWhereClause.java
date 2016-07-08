package org.reactor.tfs.client.query;

import org.reactor.tfs.client.model.EqualityCheck;
import org.reactor.tfs.client.model.WorkItemColumn;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static org.reactor.tfs.client.model.EqualityCheck.EQUAL;

/**
 * @author grabslu
 */
public class WorkItemQueryWhereClause {

  private List<WorkItemQueryWhereClause> nestedWhereClauses = new ArrayList<>();
  private EqualityCheck equalityCheck = EQUAL;
  private boolean nested;
  private WorkItemColumn column;
  private String value;

  public String buildWiql() {
    StringBuffer wiqlBuffer = new StringBuffer();
    if (!nested) {
      wiqlBuffer.append("WHERE ");
    }
    wiqlBuffer.append("(")
        .append(format("[%s] %s '%s'", column.getTfsFieldName(), equalityCheck.getSign(), value));
    for (WorkItemQueryWhereClause nestedWhereClause : nestedWhereClauses) {
      wiqlBuffer.append(format(" AND %s", nestedWhereClause.buildWiql()));
    }
    return wiqlBuffer.append(")").toString();
  }

  public boolean hasAny() {
    return column != null;
  }

  public void setEqualityCheck(EqualityCheck equalityCheck) {
    this.equalityCheck = equalityCheck;
  }

  public void setNested(boolean nested) {
    this.nested = nested;
  }

  public void setColumn(WorkItemColumn column) {
    this.column = column;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public void addNested(WorkItemQueryWhereClause nestedWhereClause) {
    nestedWhereClauses.add(nestedWhereClause);
  }
}
