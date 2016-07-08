package org.reactor.tfs.client.service.rest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author grabslu
 */
public interface TFSAntiForgeryCrackerHttpService {

    @GET("{projectName}")
    Call<ResponseBody> getRawAntiforgeryData(@Path("projectName") String projectName);
}
