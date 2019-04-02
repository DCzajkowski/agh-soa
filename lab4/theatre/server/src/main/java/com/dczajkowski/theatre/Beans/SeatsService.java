package com.dczajkowski.theatre.Beans;

import com.dczajkowski.theatre.Contracts.LocalSeatsServiceInterface;
import com.dczajkowski.theatre.Contracts.SeatsServiceInterface;
import com.dczajkowski.theatre.Exceptions.SeatDoesNotExistException;
import com.dczajkowski.theatre.Exceptions.SeatUnavailableException;
import com.dczajkowski.theatre.Models.Seat;
import com.dczajkowski.theatre.Repositories.SeatsRepository;

import javax.ejb.*;
import java.util.List;

@Singleton
@Remote(SeatsServiceInterface.class)
@Local(LocalSeatsServiceInterface.class)
public class SeatsService implements SeatsServiceInterface, LocalSeatsServiceInterface {
    @EJB
    private SeatsRepository seatsRepository;

    @Override
    public List<Seat> getSeatList() {
        return seatsRepository.getSeats();
    }

    public int getSeatPrice(int number) throws SeatDoesNotExistException {
        return getSeatPrice(getSeatByNumber(number));
    }

    private int getSeatPrice(Seat seat) {
        return seat.getPrice();
    }

    public void buyTicket(int number) throws SeatUnavailableException, SeatDoesNotExistException {
        buyTicket(getSeatByNumber(number));
    }

    @Lock
    private void buyTicket(Seat seat) throws SeatUnavailableException {
        if (!seat.isAvailable()) {
            throw new SeatUnavailableException(String.format("Seat #%d is unavailable.", seat.getNumber()));
        }

        seat.setAvailable(false);
    }

    public Seat getSeatByNumber(int number) throws SeatDoesNotExistException {
        Seat seat = seatsRepository.getSeatByNumber(number);

        if (seat == null) {
            throw new SeatDoesNotExistException(String.format("Seat with number %d does not exist", number));
        }

        return seat;
    }
}
