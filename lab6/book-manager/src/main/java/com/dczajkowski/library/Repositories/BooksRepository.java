package com.dczajkowski.library.Repositories;

import com.dczajkowski.library.Models.Author;
import com.dczajkowski.library.Models.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.List;

@Named("BooksRepository")
@ApplicationScoped
public class BooksRepository extends Repository {
    public BooksRepository() {
        super();
    }

    public List<Book> get() {
        System.out.println("FETCH");
        return em.createQuery("SELECT b FROM Book b").getResultList();
    }

    public void remove(Book book) {
        em.getTransaction().begin();
        em.remove(book);
        em.getTransaction().commit();
        em.clear();
    }

    public void add(Author author) {
        Book book = new Book("");
        book.setAuthor(author);
        update(book);
    }

    public void update(Book book) {
        em.getTransaction().begin();
        em.persist(book);
        em.getTransaction().commit();
        em.clear();
    }
}
