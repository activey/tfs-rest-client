package org.reactor.tfs.client.service.rest;

import org.reactor.tfs.client.service.rest.response.WorkItemsQueryResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author grabslu
 */
public interface WorkItemsRestService {

  @FormUrlEncoded
  @POST("{projectName}/_api/_wit/query")
  Call<WorkItemsQueryResponse> queryWorkItems(@Path("projectName") String projectName,
      @Field("wiql") String queryString, @Field("__RequestVerificationToken") String token);
}
