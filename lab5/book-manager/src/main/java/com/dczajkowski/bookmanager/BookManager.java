package com.dczajkowski.bookmanager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named("BookManager")
@ApplicationScoped
public class BookManager {
    @Inject
    private BookRepository bookRepository;

    public List<Book> getBooks() {
        return bookRepository.get();
    }
}
