package entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Discount implements Serializable {
    private String name;
    private int discount;
    private LocalDate start;
    private LocalDate end;

    public Discount(String name, int discount, LocalDate start, LocalDate end) {
        this.name = name;
        this.discount = discount;
        this.start = start;
        this.end = end;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }
}
