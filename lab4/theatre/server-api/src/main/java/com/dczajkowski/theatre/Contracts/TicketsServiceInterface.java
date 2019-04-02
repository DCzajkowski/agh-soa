package com.dczajkowski.theatre.Contracts;

import com.dczajkowski.theatre.Exceptions.NotEnoughFundsException;
import com.dczajkowski.theatre.Exceptions.SeatDoesNotExistException;
import com.dczajkowski.theatre.Exceptions.SeatUnavailableException;

public interface TicketsServiceInterface {
    void buyTicket(int number) throws SeatDoesNotExistException, NotEnoughFundsException, SeatUnavailableException;

    int getWallet();
}
