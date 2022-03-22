package pl.teksusik.payu.order;

public class OrderCreateResponse {
    private String redirectUri;
    private String orderId;
    private String extOrderId;
    private Status status;

    public OrderCreateResponse(String redirectUri, String orderId, String extOrderId, Status status) {
        this.redirectUri = redirectUri;
        this.orderId = orderId;
        this.extOrderId = extOrderId;
        this.status = status;
    }

    public OrderCreateResponse() {
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getExtOrderId() {
        return extOrderId;
    }

    public void setExtOrderId(String extOrderId) {
        this.extOrderId = extOrderId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
