package pl.teksusik.payu.order;

import java.util.List;

public class Order {
    private Buyer buyer;
    private List<Product> products;

    public Order(Buyer buyer, List<Product> products) {
        this.buyer = buyer;
        this.products = products;
    }

    public Order() {
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
