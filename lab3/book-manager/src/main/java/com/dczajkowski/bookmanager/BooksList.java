package com.dczajkowski.bookmanager;

import java.util.List;
import java.util.stream.Collectors;

import static com.dczajkowski.helpers.Helpers.tap;

public class BooksList {
    private List<Book> books;

    public BooksList(List<Book> books) {
        this.books = books;
    }

    public BooksList convertPricesTo(String currency) {
        return currency.equals("ORIGINAL")
            ? this
            : tap(this, t -> this.books = this.books.stream().map(book -> tap(book,
                b -> book.setPrice(new CurrencyConverter(book.getPrice(), book.getCurrency()).to(Currency.valueOf(currency))),
                b -> book.setCurrency(Currency.valueOf(currency))
            )).collect(Collectors.toList()));
    }

    public List<Book> get() {
        return books;
    }
}
