package com.dczajkowski.library.Repositories;

import com.dczajkowski.library.Models.Author;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.List;

@Named("AuthorRepository")
@ApplicationScoped
public class AuthorRepository extends Repository {
    public AuthorRepository() {
        super();
    }

    public List<Author> get() {
        return em.createQuery("SELECT b FROM Author b").getResultList();
    }

    public void remove(Author author) {
        em.getTransaction().begin();
        em.remove(author);
        em.getTransaction().commit();
    }

    public void add() {
        Author author = new Author("", "");
        update(author);
    }

    public void update(Author author) {
        em.getTransaction().begin();
        em.persist(author);
        em.getTransaction().commit();
    }
}
