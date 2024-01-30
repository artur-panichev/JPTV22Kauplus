package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String surname;
    private int balance;
    private int productsPurchanced;
    private List<Product> boughtProducts;

    public Customer() {
    }
    
    
    
    public Customer(String name, String surname){
        this.name = name;
        this.surname = surname;
        this.balance = 0;
        this.productsPurchanced = 0;
        this.boughtProducts = new ArrayList<Product>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getProductsPurchanced() {
        return productsPurchanced;
    }

    public void setProductsPurchanced(int productsPurchanced) {
        this.productsPurchanced = productsPurchanced;
    }

    public List<Product> getBoughtProducts() {
        return boughtProducts;
    }

    public void setBoughtProducts(List<Product> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", balance=" + balance +
                ", productsPurchanced=" + productsPurchanced +
                '}';
    }
}
