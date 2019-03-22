package com.dczajkowski.bookmanager;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named("BookManager")
@ApplicationScoped
public class BookManager {
    private List<Book> books;
    private BookRepository bookRepository = new BookRepository();
    private String currency = "ORIGINAL";

    public BookManager() {
        this.books = bookRepository.get();
    }

    public List<Book> getBooks() {
        return new BooksList(books.stream().map(Book::new).collect(Collectors.toList())).convertPricesTo(currency).get();
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
