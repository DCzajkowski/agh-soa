package com.dczajkowski.theatre.Contracts;

import com.dczajkowski.theatre.Exceptions.SeatDoesNotExistException;
import com.dczajkowski.theatre.Exceptions.SeatUnavailableException;
import com.dczajkowski.theatre.Models.Seat;

public interface LocalSeatsServiceInterface {
    Seat getSeatByNumber(int number) throws SeatDoesNotExistException;

    int getSeatPrice(int number) throws SeatDoesNotExistException;

    void buyTicket(int number) throws SeatUnavailableException, SeatDoesNotExistException;
}
