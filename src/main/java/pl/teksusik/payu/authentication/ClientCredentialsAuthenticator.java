package pl.teksusik.payu.authentication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.teksusik.payu.configuration.PayUConfiguration;
import pl.teksusik.payu.exception.InvalidJsonException;

@Service
public class ClientCredentialsAuthenticator {
    private final PayUConfiguration configuration;

    public ClientCredentialsAuthenticator(PayUConfiguration configuration) {
        this.configuration = configuration;
    }

    public OAuthToken authenticate() {
        String authenticationRequest = String.format(this.configuration.getAuthorizationUri() + "?grant_type=client_credentials&client_id=%s&client_secret=%s",
            this.configuration.getClientId(),
            this.configuration.getClientSecret());

        ResponseEntity<String> authenticationResponse = new RestTemplate().postForEntity(authenticationRequest, null, String.class);
        try {
            return new ObjectMapper().readValue(authenticationResponse.getBody(), OAuthToken.class);
        } catch (JsonProcessingException exception) {
            throw new InvalidJsonException(exception.getMessage());
        }
    }
}
