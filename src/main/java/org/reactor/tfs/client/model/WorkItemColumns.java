package org.reactor.tfs.client.model;

import java.util.Collection;
import java.util.Set;

/**
 * @author grabslu
 */
public class WorkItemColumns {

  private WorkItemColumn[] itemColumns;

  public WorkItemColumns(Collection<String> itemColumnsNames) {
    initializeColumnsList(itemColumnsNames);
  }

  private void initializeColumnsList(Collection<String> itemColumnsNames) {
    itemColumns = itemColumnsNames.stream().map(WorkItemColumn::fromTfsFieldName)
        .toArray(size -> new WorkItemColumn[size]);
  }

  public WorkItems parseItems(Set<Set<String>> rows) {
    return new WorkItems(itemColumns, rows);
  }
}
