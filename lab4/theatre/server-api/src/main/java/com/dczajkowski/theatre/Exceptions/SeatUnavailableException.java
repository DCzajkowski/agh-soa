package com.dczajkowski.theatre.Exceptions;

import java.io.Serializable;

public class SeatUnavailableException extends Exception implements Serializable {
    public SeatUnavailableException(String message) {
        super(message);
    }
}
