package com.dczajkowski.bookmanager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.*;
import java.util.stream.Collectors;

import static com.dczajkowski.helpers.Helpers.tap;

@Named("BookManager")
@ApplicationScoped
public class BookManager {
    private List<Book> books = new BookRepository().get();
    private Map<Integer, Boolean> selectedBooks = tap(new HashMap<>(), hm -> this.books.forEach(book -> hm.put(book.getId(), false)));
    private String currency = "ORIGINAL";
    private Integer priceFrom = null;
    private Integer priceTo = null;
    private Currency filterCurrency;
    private List<Genre> displayedGenres = new ArrayList<>(Arrays.asList(Genre.values()));
    private final List<Genre> allGenres = new ArrayList<>(Arrays.asList(Genre.values()));

    public List<Book> getBooks() {
        return new BooksList(books.stream().map(Book::new).collect(Collectors.toList()))
            .filterByPriceRange(priceFrom, priceTo, currency)
            .filterByCurrency(filterCurrency)
            .filterByGenre(displayedGenres)
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

    public List<Genre> getDisplayedGenres() {
        return displayedGenres;
    }

    public void setDisplayedGenres(List<Genre> displayedGenres) {
        this.displayedGenres = displayedGenres;
    }

    public List<Genre> getAllGenres() {
        return allGenres;
    }

    public long getSelectedBooksAmount() {
        return getSelectedBooks().values().stream().filter(bool -> bool).count();
    }

    private int getSelectedBooksPriceTotal() {
        return getSelectedBooks()
            .entrySet()
            .stream()
            .filter(Map.Entry::getValue)
            .map(Map.Entry::getKey)
            .mapToInt(id ->
                Objects.requireNonNull(
                    getBooks()
                        .stream()
                        .filter(book -> book.getId() == id)
                        .findFirst()
                        .orElse(null)
                ).getPriceInCurrency(Currency.PLN)
            )
            .sum();
    }

    public String getSelectedBooksPriceTotalFormatted() {
        return String.format("%.2f", (double) getSelectedBooksPriceTotal() / 100);
    }

    public Map<Integer, Boolean> getSelectedBooks() {
        return selectedBooks;
    }

    public void setSelectedBooks(Map<Integer, Boolean> selectedBooks) {
        this.selectedBooks = selectedBooks;
    }
}
