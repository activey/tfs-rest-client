package org.reactor.tfs.client.model;

/**
 * @author grabslu
 */
public enum EqualityCheck {

    EQUAL("="),
    DIFFERENT("<>");

    private final String sign;

    EqualityCheck(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }
}
