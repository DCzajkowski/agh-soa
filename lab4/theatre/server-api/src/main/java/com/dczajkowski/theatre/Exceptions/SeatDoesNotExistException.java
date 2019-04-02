package com.dczajkowski.theatre.Exceptions;

import java.io.Serializable;

public class SeatDoesNotExistException extends Exception implements Serializable {
    public SeatDoesNotExistException(String message) {
        super(message);
    }
}
