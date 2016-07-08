package org.reactor.tfs.client.authentication;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import org.apache.http.impl.auth.NTLMEngineException;
import org.apache.http.impl.auth.NTLMEnginePublic;

import java.io.IOException;
import java.util.List;

import static java.lang.String.format;

/**
 * @author grabslu
 */
public class NTLMAuthenticator implements Authenticator {

  private static final String WORKSTATION = "tfs-client";
  private static final String HEADER_NTLM = "NTLM";
  private static final String HEADER_WWW_AUTHENTICATE = "WWW-Authenticate";
  private static final String HEADER_AUTHORIZATION = "Authorization";

  private final NTLMEnginePublic engine = new NTLMEnginePublic();
  private final String domain;
  private final String username;
  private final String password;
  private final String ntlmMsg1;

  public NTLMAuthenticator(String username, String password, String domain)
      throws NTLMEngineException {
    this.domain = domain;
    this.username = username;
    this.password = password;
    ntlmMsg1 = engine.generateType1Msg(null, null);
  }

  public Request authenticate(Route route, Response response) throws IOException {
    List<String> wwwAuthenticate = response.headers().values(HEADER_WWW_AUTHENTICATE);
    if (wwwAuthenticate.contains(HEADER_NTLM)) {
      return response.request().newBuilder()
          .header(HEADER_AUTHORIZATION, buildNtlmNegotiationHeader(ntlmMsg1)).build();
    }
    try {
      String ntlmMsg3 = engine.generateType3Msg(username, password, domain, WORKSTATION,
          extractChallengeString(wwwAuthenticate));
      return response.request().newBuilder()
          .header(HEADER_AUTHORIZATION, buildNtlmNegotiationHeader(ntlmMsg3)).build();
    } catch (Exception e) {
      throw new IOException(e);
    }
  }

  private String extractChallengeString(List<String> wwwAuthenticate) {
    return wwwAuthenticate.get(0).substring(5);
  }

  private String buildNtlmNegotiationHeader(String ntlmMessage) {
    return format("%s %s", HEADER_NTLM, ntlmMessage);
  }
}
