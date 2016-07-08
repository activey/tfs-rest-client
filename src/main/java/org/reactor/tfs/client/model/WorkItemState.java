package org.reactor.tfs.client.model;

/**
 * @author grabslu
 */
public enum WorkItemState {

    RESOLVED("Resolved"),
    CLOSED("Closed"),
    IN_PROGRESS("In Progress"),
    BLOCKED("Blocked");

    private final String tfsValue;

    WorkItemState(String tfsValue) {
        this.tfsValue = tfsValue;
    }

    public String getTfsValue() {
        return tfsValue;
    }
}
