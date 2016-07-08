package org.reactor.tfs.client;

import org.junit.Before;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.parseBoolean;
import static java.lang.System.getProperty;
import static org.junit.Assume.assumeTrue;

/**
 * @author grabslu
 */
public class AbstractIntegrationTest {

    public static final String PROPERTY_INTEGRATION_TESTS = "integrationTests";

    @Before
    public void before() {
        assumeTrue(parseBoolean(getProperty(PROPERTY_INTEGRATION_TESTS, FALSE.toString())));
    }
}
