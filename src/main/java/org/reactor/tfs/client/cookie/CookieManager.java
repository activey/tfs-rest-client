package org.reactor.tfs.client.cookie;

import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;

/**
 * @author grabslu
 */
public class CookieManager {
    private List<HttpCookie> cookies = new ArrayList<>();

    public boolean hasAny() {
        return cookies.size() > 0;
    }

    public Iterable<HttpCookie> getCookies() {
        return cookies;
    }

    public List<HttpCookie> newCookies(String cookie) {
        List<HttpCookie> parsed = HttpCookie.parse(cookie);
        cookies.addAll(parsed);
        return parsed;
    }
}
