package org.reactor.tfs.client;

import org.apache.http.impl.auth.NTLMEngineException;
import org.junit.Test;
import org.reactor.tfs.client.model.AntiForgeryData;
import org.reactor.tfs.client.model.WorkItem;
import org.reactor.tfs.client.service.TFSAntiForgeryCrackerService;
import org.reactor.tfs.client.service.WorkItemsService;

import java.io.IOException;
import java.util.Collection;

import static org.fest.assertions.Assertions.assertThat;
import static org.reactor.tfs.client.TFSClientBuilder.clientBuilder;
import static org.reactor.tfs.client.model.WorkItemColumn.ASSIGNED_TO;
import static org.reactor.tfs.client.model.WorkItemColumn.REMAINING_WORK;
import static org.reactor.tfs.client.model.WorkItemColumn.TITLE;
import static org.reactor.tfs.client.model.WorkItemColumn.TYPE;
import static org.reactor.tfs.client.query.WorkItemQueryBuilder.query;
import static org.reactor.tfs.client.query.WorkItemQueryWhereBuilder.systemState;
import static org.reactor.tfs.client.query.WorkItemQueryWhereBuilder.teamProject;
import static org.reactor.tfs.client.query.WorkItemQueryWhereBuilder.type;
import static org.reactor.tfs.client.model.WorkItemState.CLOSED;
import static org.reactor.tfs.client.model.WorkItemState.RESOLVED;
import static org.reactor.tfs.client.model.WorkItemType.BUG;

/**
 * @author grabslu
 */
public class TSFClientIntegrationTest extends AbstractIntegrationTest {

  private static final String PROJECT_NAME = "project-name";

  @Test
  public void shouldReturnWorkItems() throws IOException, NTLMEngineException {
    // given
    TFSClient tfsClient = clientBuilder().build();
    TFSAntiForgeryCrackerService crackerService = tfsClient.getAntiForgeryCrackerService();
    WorkItemsService workItemsService = tfsClient.getWorkItemsService();

    // when
    AntiForgeryData antiForgeryData = crackerService.getAntiForgeryData(PROJECT_NAME);
    Collection<WorkItem> workItems = workItemsService.queryWorkItems(PROJECT_NAME,
        query().select(TITLE, ASSIGNED_TO, REMAINING_WORK, TYPE)
            .where(teamProject(PROJECT_NAME)
                .and(systemState(RESOLVED).not().and(systemState(CLOSED).not())).and(type(BUG)))
        .build(), antiForgeryData.getRequestVerificationToken());

    // then
    assertThat(workItems).isNotEmpty();
  }

  @Test
  public void shouldRetrieveAntiforgeryData() throws NTLMEngineException, IOException {
    // given
    TFSClient tfsClient = clientBuilder().build();
    TFSAntiForgeryCrackerService crackerService = tfsClient.getAntiForgeryCrackerService();

    // when
    AntiForgeryData antiForgeryData = crackerService.getAntiForgeryData(PROJECT_NAME);

    // then
    assertThat(antiForgeryData.getRequestVerificationTokenCookie()).isNotEmpty();
    assertThat(antiForgeryData.getRequestVerificationToken()).isNotEmpty();
  }
}
