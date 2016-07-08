package org.reactor.tfs.client;

import org.apache.http.impl.auth.NTLMEngineException;

/**
 * @author grabslu
 */
public class TFSClientBuilder {

  private TFSClientProperties clientProperties = new TFSClientProperties();

  private TFSClientBuilder() {
    readClientProperties();
  }

  private void readClientProperties() {
    clientProperties = TFSClientPropertiesReader.read();
  }

  public static TFSClientBuilder clientBuilder() {
    return new TFSClientBuilder();
  }

  public TFSClient build() throws NTLMEngineException {
    TFSClient tfsClient = new TFSClient();
    tfsClient.initialize(clientProperties.getServiceUrl(), clientProperties.getUsername(),
        clientProperties.getPassword(), clientProperties.getDomain());
    return tfsClient;
  }
}
