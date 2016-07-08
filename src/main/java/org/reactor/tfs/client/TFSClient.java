package org.reactor.tfs.client;

import okhttp3.OkHttpClient.Builder;
import org.apache.http.impl.auth.NTLMEngineException;
import org.reactor.tfs.client.authentication.NTLMAuthenticator;
import org.reactor.tfs.client.cookie.CookieManager;
import org.reactor.tfs.client.interceptor.TFSSecurityInterceptor;
import org.reactor.tfs.client.service.TFSAntiForgeryCrackerService;
import org.reactor.tfs.client.service.WorkItemsService;
import org.reactor.tfs.client.service.rest.TFSAntiForgeryCrackerHttpService;
import org.reactor.tfs.client.service.rest.WorkItemsRestService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author grabslu
 */
public class TFSClient {

  private Retrofit retrofit;
  private CookieManager cookieManager;

  public void initialize(String tfsServiceUrl, String username, String password, String domain)
      throws NTLMEngineException {
    cookieManager = new CookieManager();

    retrofit = new Retrofit.Builder()
        .client(new Builder().authenticator(new NTLMAuthenticator(username, password, domain))
            .addInterceptor(new TFSSecurityInterceptor(cookieManager)).build())
        .baseUrl(tfsServiceUrl).addConverterFactory(GsonConverterFactory.create()).build();
  }

  public WorkItemsService getWorkItemsService() {
    return new WorkItemsService(retrofit.create(WorkItemsRestService.class));
  }

  public TFSAntiForgeryCrackerService getAntiForgeryCrackerService() {
    return new TFSAntiForgeryCrackerService(
        retrofit.create(TFSAntiForgeryCrackerHttpService.class), cookieManager);
  }

}
