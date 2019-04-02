package com.dczajkowski.theatre.Beans;

import com.dczajkowski.theatre.Contracts.LocalTheatreInterface;
import com.dczajkowski.theatre.Contracts.TicketBuyerInterface;
import com.dczajkowski.theatre.Exceptions.NotEnoughFundsException;
import com.dczajkowski.theatre.Exceptions.SeatDoesNotExistException;
import com.dczajkowski.theatre.Exceptions.SeatUnavailableException;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;

@Stateful
@Remote(TicketBuyerInterface.class)
public class TicketBuyer implements TicketBuyerInterface {
    @EJB
    private LocalTheatreInterface theatre;

    private int wallet = 1000;

    @Override
    public void buyTicket(int number) throws SeatDoesNotExistException, NotEnoughFundsException, SeatUnavailableException {
        int price = theatre.getSeatPrice(number);

        if (price > wallet) {
            throw new NotEnoughFundsException(String.format("Seat #%d costs %d, but user's wallet has only %d in funds.", number, price, wallet));
        }

        theatre.buyTicket(number);
        wallet -= price;
    }

    @Override
    public int getWallet() {
        return wallet;
    }
}
