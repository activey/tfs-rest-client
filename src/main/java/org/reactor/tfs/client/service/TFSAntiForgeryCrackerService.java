package org.reactor.tfs.client.service;

import okhttp3.Headers;
import okhttp3.ResponseBody;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.reactor.tfs.client.cookie.CookieManager;
import org.reactor.tfs.client.model.AntiForgeryData;
import org.reactor.tfs.client.service.rest.TFSAntiForgeryCrackerHttpService;
import retrofit2.Response;

import java.io.IOException;
import java.net.HttpCookie;
import java.util.List;

/**
 * @author grabslu
 */
public class TFSAntiForgeryCrackerService {

  private static final String HEADER_COOKIE = "Set-Cookie";
  private static final String TOKEN_COOKIE = "__RequestVerificationToken_L3Rmcw2";
  private static final String TOKEN_PARAMETER = "__RequestVerificationToken";
  private static final String TOKEN_PARAMETER_NAME = "name";
  private static final String TOKEN_PARAMETER_VALUE = "value";

  private TFSAntiForgeryCrackerHttpService tfsAntiForgeryCrackerHttpService;
  private CookieManager cookieManager;

  public TFSAntiForgeryCrackerService(
      TFSAntiForgeryCrackerHttpService tfsAntiForgeryCrackerHttpService,
      CookieManager cookieManager) {
    this.tfsAntiForgeryCrackerHttpService = tfsAntiForgeryCrackerHttpService;
    this.cookieManager = cookieManager;
  }

  public AntiForgeryData getAntiForgeryData(String projectName) throws IOException {
    Response<ResponseBody> response =
        tfsAntiForgeryCrackerHttpService.getRawAntiforgeryData(projectName).execute();
    Headers headers = response.headers();
    List<String> cookies = headers.values(HEADER_COOKIE);
    for (String cookie : cookies) {
      List<HttpCookie> parsedCookies = cookieManager.newCookies(cookie);
      for (HttpCookie parsedCookie : parsedCookies) {
        if (TOKEN_COOKIE.equals(parsedCookie.getName())) {
          String cookieValue = parsedCookie.getValue();
          String tokenValue = parseTokenValue(response.body());
          return new AntiForgeryData(cookieValue, tokenValue);
        }
      }
    }
    return null;
  }

  private String parseTokenValue(ResponseBody body) throws IOException {
    Document document = Jsoup.parse(body.string());
    return document.getElementsByAttributeValue(TOKEN_PARAMETER_NAME, TOKEN_PARAMETER).first()
        .attr(TOKEN_PARAMETER_VALUE);
  }
}
