package org.reactor.tfs.client.model;

/**
 * @author grabslu
 */
public enum WorkItemColumn {
    ID("System.Id"),
    TITLE("System.Title"),
    ASSIGNED_TO("System.AssignedTo"),
    REMAINING_WORK("Microsoft.VSTS.Scheduling.RemainingWork"),
    STATE("System.State"),
    TEAM_PROJECT("System.TeamProject"),
    TYPE("System.WorkItemType");

    private final String tfsFieldName;

    WorkItemColumn(String tfsFieldName) {
        this.tfsFieldName = tfsFieldName;
    }

    public String getTfsFieldName() {
        return tfsFieldName;
    }

    public static WorkItemColumn fromTfsFieldName(String tfsFieldName) {
        for (WorkItemColumn workItemColumn : WorkItemColumn.values()) {
            if (tfsFieldName.equals(workItemColumn.getTfsFieldName())) {
                return workItemColumn;
            }
        }
        return null;
    }
}
