package org.reactor.tfs.client.model;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author grabslu
 */
public class WorkItems {

    private final WorkItemColumn[] itemColumns;
    private final Collection<WorkItem> workItems;

    public WorkItems(WorkItemColumn[] itemColumns, Set<Set<String>> rows) {
        this.itemColumns = itemColumns;
        this.workItems = new LinkedHashSet<>();
        rows.stream().forEach(row -> parseRow(row.stream().toArray(size -> new String[size])));
    }

    private void parseRow(String[] workItemRowData) {
        WorkItem workItem = new WorkItem();
        for (int columnIndex = 0; columnIndex < itemColumns.length; columnIndex ++) {
            WorkItemColumn itemColumn = itemColumns[columnIndex];
            WorkItemColumnValue columnValue = new WorkItemColumnValue(itemColumn, workItemRowData[columnIndex]);
            workItem.addColumnValue(columnValue);
        }
        workItems.add(workItem);
    }

    public Collection<WorkItem> getWorkItems() {
        return workItems;
    }
}
