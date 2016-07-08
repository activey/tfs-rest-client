package org.reactor.tfs.client.interceptor;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.reactor.tfs.client.cookie.CookieManager;

import java.io.IOException;
import java.net.HttpCookie;

/**
 * @author grabslu
 */
public class TFSSecurityInterceptor implements Interceptor {

  private static final String HEADER_COOKIE = "Cookie";

  private CookieManager cookieManager;

  public TFSSecurityInterceptor(CookieManager cookieManager) {
    this.cookieManager = cookieManager;
  }

  public Response intercept(Chain chain) throws IOException {
    if (!cookieManager.hasAny()) {
      return chain.proceed(chain.request());
    }
    Request.Builder builder = chain.request().newBuilder();
    for (HttpCookie httpCookie : cookieManager.getCookies()) {
      builder.addHeader(HEADER_COOKIE, httpCookie.toString());
    }
    return chain.proceed(builder.build());
  }
}
