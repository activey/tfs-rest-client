package org.reactor.tfs.client.model;

import java.util.Optional;

import static java.lang.Integer.parseInt;

/**
 * @author grabslu
 */
public class WorkItemColumnValue {

    private final WorkItemColumn column;
    private final String value;

    public WorkItemColumnValue(WorkItemColumn column, String value) {
        this.column = column;
        this.value = value;
    }

    public WorkItemColumn getColumn() {
        return column;
    }

    public Optional<String> getString() {
        return Optional.ofNullable(value);
    }

    public int getInt() {
        if (value == null || value.length() == 0) {
            return 0;
        }
        return parseInt(value);
    }
}
