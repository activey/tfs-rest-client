package org.reactor.tfs.client.model;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Optional;

/**
 * @author grabslu
 */
public class WorkItem {

    private final Collection<WorkItemColumnValue> columnValues = new LinkedHashSet<>();

    public void addColumnValue(WorkItemColumnValue columnValue) {
        columnValues.add(columnValue);
    }

    public Optional<WorkItemColumnValue> getColumnValue(WorkItemColumn column) {
        return columnValues.stream().filter(columnValue -> columnValue.getColumn().compareTo(column) == 0).findFirst();
    }
}
