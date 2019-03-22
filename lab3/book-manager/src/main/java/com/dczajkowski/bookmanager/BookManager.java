package com.dczajkowski.bookmanager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named("BookManager")
@ApplicationScoped
public class BookManager {
    private List<Book> books;
    private BookRepository bookRepository = new BookRepository();
    private String currency = "ORIGINAL";
    private Integer priceFrom = null;
    private Integer priceTo = null;
    private Currency filterCurrency;

    public BookManager() {
        this.books = bookRepository.get();
    }

    public List<Book> getBooks() {
        return new BooksList(books.stream().map(Book::new).collect(Collectors.toList()))
            .filterByPriceRange(priceFrom, priceTo)
            .filterByCurrency(filterCurrency)
            .get();
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getCurrency() {
        return currency;
    }

    public Currency getCurrencyEnum() {
        return Currency.valueOf(currency);
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getPriceFrom() {
        return (priceFrom == null) ? null : (double) priceFrom / 100;
    }

    public void setPriceFrom(Double priceFrom) {
        this.priceFrom = (priceFrom == null) ? null : (int) (priceFrom * 100);
    }

    public Double getPriceTo() {
        return (priceTo == null) ? null : (double) priceTo / 100;
    }

    public void setPriceTo(Double priceTo) {
        this.priceTo = (priceTo == null) ? null : (int) (priceTo * 100);
    }

    public Currency getFilterCurrency() {
        return filterCurrency;
    }

    public void setFilterCurrency(Currency filterCurrency) {
        this.filterCurrency = filterCurrency;
    }
}
