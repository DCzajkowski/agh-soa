package com.dczajkowski.theatre.Views;

import com.dczajkowski.theatre.Contracts.SeatAvailabilityServiceInterface;
import com.dczajkowski.theatre.Contracts.TheatreInterface;
import com.dczajkowski.theatre.Contracts.TicketBuyerInterface;
import com.dczajkowski.theatre.Exceptions.NotEnoughFundsException;
import com.dczajkowski.theatre.Exceptions.SeatDoesNotExistException;
import com.dczajkowski.theatre.Exceptions.SeatUnavailableException;
import com.dczajkowski.theatre.Models.Seat;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("IndexView")
@SessionScoped
public class IndexView implements Serializable {
    @EJB(lookup = "java:global/server/TicketBuyer")
    private TicketBuyerInterface ticketBuyer;

    @EJB(lookup = "java:global/server/SeatAvailabilityService")
    private SeatAvailabilityServiceInterface seatAvailabilityService;

    @EJB(lookup = "java:global/server/Theatre!com.dczajkowski.theatre.Contracts.TheatreInterface")
    private TheatreInterface theatre;

    private String error;

    public List<Seat> getSeats() {
        return theatre.getSeatList();
    }

    public boolean isSeatAvailable(int number) throws SeatDoesNotExistException {
        return seatAvailabilityService.isAvailable(number);
    }

    public void buy(int number) {
        try {
            ticketBuyer.buyTicket(number);
            error = null;
        } catch (NotEnoughFundsException | SeatDoesNotExistException | SeatUnavailableException e) {
            error = e.getMessage();
        }
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getWallet() {
        return ticketBuyer.getWallet();
    }
}
