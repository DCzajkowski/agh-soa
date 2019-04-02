package com.dczajkowski.theatre.Repositories;

import com.dczajkowski.theatre.Models.Seat;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class SeatsRepository {
    private List<Seat> seats = new ArrayList<>();

    public SeatsRepository() {
        this.seats.add(new Seat(1, 1000));
        this.seats.add(new Seat(2, 1000));
        this.seats.add(new Seat(3, 2000));
        this.seats.add(new Seat(4, 2000));
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public Seat getSeatByNumber(int number) {
        return seats.stream().filter(s -> s.getNumber() == number).findFirst().orElse(null);
    }
}
