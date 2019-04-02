package com.dczajkowski.theatre.Exceptions;

import java.io.Serializable;

public class NotEnoughFundsException extends Exception implements Serializable {
    public NotEnoughFundsException(String message) {
        super(message);
    }
}
