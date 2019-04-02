package com.dczajkowski.theatre.Beans;

import com.dczajkowski.theatre.Contracts.LocalSeatsServiceInterface;
import com.dczajkowski.theatre.Contracts.SeatAvailabilityServiceInterface;
import com.dczajkowski.theatre.Exceptions.SeatDoesNotExistException;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(SeatAvailabilityServiceInterface.class)
public class SeatAvailabilityService implements SeatAvailabilityServiceInterface {
    @EJB
    private LocalSeatsServiceInterface seatsService;

    @Override
    public boolean isAvailable(int number) throws SeatDoesNotExistException {
        return seatsService.getSeatByNumber(number).isAvailable();
    }
}
