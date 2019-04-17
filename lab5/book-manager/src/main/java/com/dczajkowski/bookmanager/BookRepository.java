package com.dczajkowski.bookmanager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Named("BookRepository")
@ApplicationScoped
public class BookRepository {
    private EntityManagerFactory factory;
    private EntityManager em;

    public BookRepository()
    {
        factory = Persistence.createEntityManagerFactory("BookManager");
        em = factory.createEntityManager();
    }

    public List<Book> getBooks() {
        return em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }

    public boolean delete(int id) {
        try {
            Book foundBook = em.find(Book.class, id);

            em.getTransaction().begin();
            em.remove(foundBook);
            em.getTransaction().commit();

            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();

            return false;
        }
    }

    public boolean create(Book book) {
        try {
            em.getTransaction().begin();
            em.persist(book);
            em.getTransaction().commit();

            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();

            return false;
        }
    }

    public boolean update(Book book) {
        try {
            em.getTransaction().begin();

            em.persist(book);

            em.getTransaction().commit();

            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();

            return false;
        }
    }
}
