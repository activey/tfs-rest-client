package org.reactor.tfs.client;

import java.util.Properties;

/**
 * @author grabslu
 */
public class TFSClientProperties {

  private static final String PROPERTY_SERVICE_URL = "serviceUrl";
  private static final String PROPERTY_USERNAME = "user";
  private static final String PROPERTY_PASSWORD = "password";
  private static final String PROPERTY_DOMAIN = "domain";

  private String serviceUrl;
  private String username;
  private String password;
  private String domain;

  public TFSClientProperties() {}

  public TFSClientProperties(Properties properties) {
    readProperties(properties);
  }

  private void readProperties(Properties properties) {
    serviceUrl = properties.getProperty(PROPERTY_SERVICE_URL);
    username = properties.getProperty(PROPERTY_USERNAME);
    password = properties.getProperty(PROPERTY_PASSWORD);
    domain = properties.getProperty(PROPERTY_DOMAIN);
  }

  public String getServiceUrl() {
    return serviceUrl;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getDomain() {
    return domain;
  }
}
