package com.github.therycn.tyideapp;

import org.springframework.http.HttpHeaders;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Abstract Integration Test.
 *
 * @author THERY
 */
public class AbstractIntegrationTest {

    public HttpHeaders createHeaders() {
        return createHeaders("Thery", "ChangeIt");
    }

    public HttpHeaders createHeaders(String username, String password) {
        return new HttpHeaders() {
            /** Serial version. */
            private static final long serialVersionUID = -4968652194130326824L;

            {
                String auth = username + ":" + password;
                byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.US_ASCII));
                String authHeader = "Basic " + new String(encodedAuth);
                set("Authorization", authHeader);
            }
        };
    }

}
