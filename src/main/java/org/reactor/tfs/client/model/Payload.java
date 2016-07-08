package org.reactor.tfs.client.model;

import java.util.Set;

/**
 * @author grabslu
 */
public class Payload {

    private Set<String> columns;
    private Set<Set<String>> rows;

    public Set<String> getColumns() {
        return columns;
    }

    public void setColumns(Set<String> columns) {
        this.columns = columns;
    }

    public Set<Set<String>> getRows() {
        return rows;
    }

    public void setRows(Set<Set<String>> rows) {
        this.rows = rows;
    }
}
