package org.reactor.tfs.client.service.rest.response;

import org.reactor.tfs.client.model.Payload;

/**
 * @author grabslu
 */
public class WorkItemsQueryResponse {

    private boolean queryRan;
    private Payload payload;

    public boolean isQueryRan() {
        return queryRan;
    }

    public void setQueryRan(boolean queryRan) {
        this.queryRan = queryRan;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }
}
