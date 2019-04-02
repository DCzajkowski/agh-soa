package com.dczajkowski.theatre.Views;

import com.dczajkowski.theatre.Contracts.SeatAvailabilityServiceInterface;
import com.dczajkowski.theatre.Contracts.SeatsServiceInterface;
import com.dczajkowski.theatre.Contracts.TicketsServiceInterface;
import com.dczajkowski.theatre.Exceptions.NotEnoughFundsException;
import com.dczajkowski.theatre.Exceptions.SeatDoesNotExistException;
import com.dczajkowski.theatre.Exceptions.SeatUnavailableException;
import com.dczajkowski.theatre.Models.Seat;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("Theatre")
@SessionScoped
public class Theatre implements Serializable {
    @EJB(lookup = "java:global/server/TicketsService")
    private TicketsServiceInterface ticketsService;

    @EJB(lookup = "java:global/server/SeatAvailabilityService")
    private SeatAvailabilityServiceInterface seatAvailabilityService;

    @EJB(lookup = "java:global/server/SeatsService!com.dczajkowski.theatre.Contracts.SeatsServiceInterface")
    private SeatsServiceInterface seatsService;

    private String error;

    public List<Seat> getSeats() {
        return seatsService.getSeatList();
    }

    public boolean isSeatAvailable(int number) throws SeatDoesNotExistException {
        return seatAvailabilityService.isAvailable(number);
    }

    public void buy(int number) {
        try {
            ticketsService.buyTicket(number);
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
        return ticketsService.getWallet();
    }
}
