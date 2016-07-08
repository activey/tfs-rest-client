package org.reactor.tfs.client.service;

import org.reactor.tfs.client.model.Payload;
import org.reactor.tfs.client.model.WorkItem;
import org.reactor.tfs.client.model.WorkItemColumns;
import org.reactor.tfs.client.model.WorkItems;
import org.reactor.tfs.client.service.rest.response.WorkItemsQueryResponse;
import org.reactor.tfs.client.query.WorkItemQuery;
import org.reactor.tfs.client.service.rest.WorkItemsRestService;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Collection;

/**
 * @author grabslu
 */
public class WorkItemsService {

  private WorkItemsRestService workItemsRestService;

  public WorkItemsService(WorkItemsRestService workItemsRestService) {
    this.workItemsRestService = workItemsRestService;
  }

  public Collection<WorkItem> queryWorkItems(String projectName, WorkItemQuery query, String token)
      throws IOException {
    Call<WorkItemsQueryResponse> workItemsCall =
        workItemsRestService.queryWorkItems(projectName, query.buildWiql(), token);
    Response<WorkItemsQueryResponse> workItemResponse = workItemsCall.execute();

    // parse RAW payload first
    Payload payload = workItemResponse.body().getPayload();
    // then read columns definitions
    WorkItemColumns workItemColumns = new WorkItemColumns(payload.getColumns());
    // and then read WorkItem elements
    WorkItems workItems = workItemColumns.parseItems(payload.getRows());
    return workItems.getWorkItems();
  }
}
