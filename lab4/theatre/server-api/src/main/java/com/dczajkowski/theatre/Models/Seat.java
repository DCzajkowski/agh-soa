package com.dczajkowski.theatre.Models;

import java.io.Serializable;

public class Seat implements Serializable {
    private int number;
    private int price;
    private boolean available = true;

    public Seat(int number, int price) {
        this.number = number;
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public int getPrice() {
        return price;
    }
}
