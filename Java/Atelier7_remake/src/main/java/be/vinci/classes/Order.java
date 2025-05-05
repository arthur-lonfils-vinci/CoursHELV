package be.vinci.classes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    public static int orderNumberSequence = 1;

    private int number;
    private LocalDateTime date;

    private List<OrderLine> lines = new ArrayList<OrderLine>();

    public Order(LocalDateTime date) {
        this.number = orderNumberSequence++;
        this.date = date;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<OrderLine> getLines() {
        return lines;
    }

    public boolean addOrderLine(OrderLine orderLine) {
        return this.lines.add(orderLine);
    }

}
