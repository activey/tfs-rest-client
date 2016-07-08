package org.reactor.tfs.client.model;

/**
 * @author grabslu
 */
public enum WorkItemType {

    BUG("Bug");

    private final String tfsValue;

    WorkItemType(String tfsValue) {
        this.tfsValue = tfsValue;
    }

    public String getTfsValue() {
        return tfsValue;
    }
}
