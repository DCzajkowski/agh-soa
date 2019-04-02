package com.dczajkowski.theatre.Exceptions;

import java.io.Serializable;

public class NotEnoughFundsException extends Exception implements Serializable {
    public NotEnoughFundsException(int number, int price, int wallet) {
        super(String.format("Seat #%d costs %d, but user's wallet has only %d in funds.", number, price, wallet));
    }
}
