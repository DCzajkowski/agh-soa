package com.dczajkowski.theatre.Beans;

import com.dczajkowski.theatre.Contracts.LocalTheatreInterface;
import com.dczajkowski.theatre.Contracts.TheatreInterface;
import com.dczajkowski.theatre.Exceptions.SeatDoesNotExistException;
import com.dczajkowski.theatre.Exceptions.SeatUnavailableException;
import com.dczajkowski.theatre.Models.Seat;

import javax.ejb.*;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Remote(TheatreInterface.class)
@Local(LocalTheatreInterface.class)
public class Theatre implements TheatreInterface, LocalTheatreInterface {
    private List<Seat> seats = new ArrayList<>();

    public Theatre() {
        this.seats.add(new Seat(1, 1000));
        this.seats.add(new Seat(2, 1000));
        this.seats.add(new Seat(3, 2000));
        this.seats.add(new Seat(4, 2000));
    }

    @Override
    public List<Seat> getSeatList() {
        return seats;
    }

    public int getSeatPrice(int number) throws SeatDoesNotExistException {
        return getSeatPrice(getSeatByNumber(number));
    }

    public int getSeatPrice(Seat seat) {
        return seat.getPrice();
    }

    public void buyTicket(int number) throws SeatUnavailableException, SeatDoesNotExistException {
        buyTicket(getSeatByNumber(number));
    }

    @Lock
    public void buyTicket(Seat seat) throws SeatUnavailableException {
        if (!seat.isAvailable()) {
            throw new SeatUnavailableException(String.format("Seat #%d is unavailable.", seat.getNumber()));
        }

        seat.setAvailable(false);
    }

    public Seat getSeatByNumber(int number) throws SeatDoesNotExistException {
        Seat seat = seats.stream().filter(s -> s.getNumber() == number).findFirst().orElse(null);

        if (seat == null) {
            throw new SeatDoesNotExistException(String.format("Seat with number %d does not exist", number));
        }

        return seat;
    }
}
