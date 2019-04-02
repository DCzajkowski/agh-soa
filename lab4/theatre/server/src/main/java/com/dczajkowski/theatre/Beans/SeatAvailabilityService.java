package com.dczajkowski.theatre.Beans;

import com.dczajkowski.theatre.Contracts.LocalTheatreInterface;
import com.dczajkowski.theatre.Contracts.SeatAvailabilityServiceInterface;
import com.dczajkowski.theatre.Exceptions.SeatDoesNotExistException;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(SeatAvailabilityServiceInterface.class)
public class SeatAvailabilityService implements SeatAvailabilityServiceInterface {
    @EJB
    private LocalTheatreInterface theatre;

    @Override
    public boolean isAvailable(int number) throws SeatDoesNotExistException {
        return theatre.getSeatByNumber(number).isAvailable();
    }
}
