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
        return tap(new ArrayList<>(), list -> list.add(new Book(1, "W pustyni i w puszczy", "Henryk", "Sienkiewicz", "978-1-56619-909-4 ", 2001, 2100)));
    }
}
