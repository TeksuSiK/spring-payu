package pl.teksusik.payu.authentication;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class AuthenticationTemplate {
    private final ClientCredentialsAuthenticator authenticator;

    public AuthenticationTemplate(ClientCredentialsAuthenticator authenticator) {
        this.authenticator = authenticator;
    }

    public RestTemplate authenticationTemplate() {
        RestTemplate template = new RestTemplate();
        template.setInterceptors(Collections.singletonList((httpRequest, bytes, clientHttpRequestExecution) -> {
           OAuthToken oAuthToken = this.authenticator.authenticate();
            HttpHeaders httpHeaders = httpRequest.getHeaders();
            httpHeaders.add("Authorization", oAuthToken.getTokenType() + " " + oAuthToken.getAccessToken());
            if (!httpHeaders.containsKey("Content-Type")) {
                httpHeaders.add("Content-Type", "application/json");
            }
            return clientHttpRequestExecution.execute(httpRequest, bytes);
        }));

        return template;
    }
}
