package org.reactor.tfs.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author grabslu
 */
public class TFSClientPropertiesReader {

  private final static Logger LOGGER =
      LoggerFactory.getLogger(TFSClientPropertiesReader.class);

  private final static String FILE_CLIENT_PROPERTIES = "client.properties";

  private TFSClientPropertiesReader() {}

  public static TFSClientProperties read() {
    return new TFSClientPropertiesReader().readClientProperties();
  }

  private TFSClientProperties readClientProperties() {
    InputStream propertiesStream = TFSClientPropertiesReader.class.getClassLoader()
        .getResourceAsStream(FILE_CLIENT_PROPERTIES);
    if (propertiesStream == null) {
      LOGGER.warn("Properties file not present. Returning empty properties instead.");
      return new TFSClientProperties();
    }

    Properties properties = new Properties();
    try {
      properties.load(propertiesStream);
    } catch (IOException e) {
      LOGGER.warn("Properties file not present or can't read. Returning empty properties instead.");
      return new TFSClientProperties();
    }
    LOGGER.debug("Reading '{}' client properties file.", FILE_CLIENT_PROPERTIES);
    return new TFSClientProperties(properties);
  }
}
