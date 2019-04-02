package com.dczajkowski.theatre.Beans;

import com.dczajkowski.theatre.Contracts.LocalSeatsServiceInterface;
import com.dczajkowski.theatre.Contracts.TicketsServiceInterface;
import com.dczajkowski.theatre.Exceptions.NotEnoughFundsException;
import com.dczajkowski.theatre.Exceptions.SeatDoesNotExistException;
import com.dczajkowski.theatre.Exceptions.SeatUnavailableException;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;

@Stateful
@Remote(TicketsServiceInterface.class)
public class TicketsService implements TicketsServiceInterface {
    @EJB
    private LocalSeatsServiceInterface seatsService;

    private int wallet = 1000;

    @Override
    public void buyTicket(int number) throws SeatDoesNotExistException, NotEnoughFundsException, SeatUnavailableException {
        int price = seatsService.getSeatPrice(number);

        if (price > wallet) {
            throw new NotEnoughFundsException(number, price, wallet);
        }

        seatsService.buyTicket(number);
        wallet -= price;
    }

    @Override
    public int getWallet() {
        return wallet;
    }
}
