package org.reactor.tfs.client.model;

/**
 * @author grabslu
 */
public class AntiForgeryData {

    private final String requestVerificationTokenCookie;
    private final String requestVerificationToken;

    public AntiForgeryData(String requestVerificationTokenCookie, String requestVerificationToken) {
        this.requestVerificationTokenCookie = requestVerificationTokenCookie;
        this.requestVerificationToken = requestVerificationToken;
    }

    public String getRequestVerificationToken() {
        return requestVerificationToken;
    }

    public String getRequestVerificationTokenCookie() {
        return requestVerificationTokenCookie;
    }
}
