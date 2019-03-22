package com.dczajkowski.bookmanager;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.dczajkowski.helpers.Helpers.tap;

public class BooksList {
    private List<Book> books;

    public BooksList(List<Book> books) {
        this.books = books;
    }

    public List<Book> get() {
        return books;
    }

    public BooksList filterByPriceRange(Integer priceFrom, Integer priceTo, String currency) {
        return currency.equals("ORIGINAL")
            ? filterBy(book -> (priceFrom == null || book.getPrice() >= priceFrom) && (priceTo == null || book.getPrice() <= priceTo))
            : filterBy(book -> (priceFrom == null || book.getPriceInCurrency(Currency.valueOf(currency)) >= priceFrom) && (priceTo == null || book.getPriceInCurrency(Currency.valueOf(currency)) <= priceTo));
    }

    public BooksList filterByCurrency(Currency filterCurrency) {
        return filterCurrency == null ? this : filterBy(book -> book.getCurrency() == filterCurrency);
    }

    private BooksList filterBy(Predicate<Book> predicate) {
        return tap(
            this,
            t -> t.books = t.books.stream()
                .filter(predicate)
                .collect(Collectors.toList())
        );
    }

    public BooksList filterByGenre(List<Genre> displayedGenres) {
        return filterBy(book -> displayedGenres.contains(book.getGenre()));
    }
}
