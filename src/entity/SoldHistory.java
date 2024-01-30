package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity 
public class SoldHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Product product;
    private int priceOfOne;
    private Customer customer;
    private LocalDateTime date;

    public SoldHistory() {
    }

    
    
    public SoldHistory(Product product, int priceOfOne, Customer customer, LocalDateTime date) {
        this.product = product;
        this.priceOfOne = priceOfOne;
        this.customer = customer;
        this.date = date;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getPriceOfOne() {
        return priceOfOne;
    }

    public void setPriceOfOne(int priceOfOne) {
        this.priceOfOne = priceOfOne;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
