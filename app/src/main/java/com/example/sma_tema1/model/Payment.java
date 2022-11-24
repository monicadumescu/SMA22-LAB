package com.example.sma_tema1.model;

import java.io.Serializable;
import java.util.Objects;

public class Payment implements Serializable {
    public String timestamp;
    private double cost;
    private String name;
    private String type;
    String email;

    public Payment() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Payment(String timestamp, double cost, String name, String type, String email) {
        this.timestamp = timestamp;
        this.cost = cost;
        this.name = name;
        this.type = type;
        this.email = email;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public String getType() {
        return type;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Double.compare(payment.cost, cost) == 0 && timestamp.equals(payment.timestamp) && name.equals(payment.name) && type.equals(payment.type) && email.equals(payment.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, cost, name, type, email);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "timestamp='" + timestamp + '\'' +
                ", cost=" + cost +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
