package com.dczajkowski.theatre.Contracts;

import com.dczajkowski.theatre.Exceptions.SeatDoesNotExistException;

public interface SeatAvailabilityServiceInterface {
    boolean isAvailable(int number) throws SeatDoesNotExistException;
}
