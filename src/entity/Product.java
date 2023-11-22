package entity;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private int price;
    private int count;
    private int boughtTimes;

    public Product(String name, int price, int count) {
        this.name = name;
        this.price = price;
        this.count = count;
        this.boughtTimes = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public int getBoughtTimes() {
        return boughtTimes;
    }

    public void setBoughtTimes(int boughtTimes) {
        this.boughtTimes = boughtTimes;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", boughtTimes=" + boughtTimes +
                '}';
    }
}
