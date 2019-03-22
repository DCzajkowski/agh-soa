package com.dczajkowski.bookmanager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

import static com.dczajkowski.helpers.Helpers.tap;

@Named("BookRepository")
@ApplicationScoped
public class BookRepository {
    public List<Book> get() {
        return tap(new ArrayList<>(), list -> {
            list.add(new Book(1, "Pan Tadeusz", "Adam Mickiewicz", Genre.DRAMA, 2300, Currency.PLN, 300));
            list.add(new Book(2, "1984", "George Orwell", Genre.SCIENCE_FICTION, 1000, Currency.EUR, 320));
        });
    }
}
