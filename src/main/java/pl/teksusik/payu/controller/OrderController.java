package pl.teksusik.payu.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.teksusik.payu.authentication.AuthenticationTemplate;
import pl.teksusik.payu.configuration.PayUConfiguration;
import pl.teksusik.payu.exception.InvalidJsonException;
import pl.teksusik.payu.order.Order;
import pl.teksusik.payu.order.OrderCreateRequest;
import pl.teksusik.payu.order.OrderCreateResponse;

import javax.servlet.http.HttpServletRequest;

@RestController("/api/order")
public class OrderController {
    private final PayUConfiguration configuration;
    private final AuthenticationTemplate authentication;

    public OrderController(PayUConfiguration configuration, AuthenticationTemplate authentication) {
        this.configuration = configuration;
        this.authentication = authentication;
    }

    @PostMapping("/create")
    public OrderCreateResponse createOrder(@RequestBody Order order, HttpServletRequest request) {
        OrderCreateRequest createRequest = new OrderCreateRequest(request.getRemoteAddr(),
                this.configuration.getPosId(),
                this.configuration.getDescription(),
                this.configuration.getCurrency(),
                order.getTotalPrice(),
                order.getBuyer(),
                order.getProducts());

        ResponseEntity<String> createResponse = this.authentication.authenticationTemplate().postForEntity(this.configuration.getOrderUri(), createRequest, String.class);
        try {
            return new ObjectMapper().readValue(createResponse.getBody(), OrderCreateResponse.class);
        } catch (JsonProcessingException exception) {
            throw new InvalidJsonException(exception.getMessage());
        }
    }
}
