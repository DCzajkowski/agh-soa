package com.dczajkowski.rest.Repositories;

import com.dczajkowski.rest.Models.Movie;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class MoviesRepository extends Repository {
    public List<Movie> get() {
        return em.createQuery("SELECT m FROM Movie m").getResultList();
    }

    public List<Movie> getByTitle(String title) {
        return em.createQuery("SELECT m FROM Movie m WHERE m.title = :title")
            .setParameter("title", title)
            .getResultList();
    }

    public void create(Movie movie) {
        update(movie);
    }

    public void update(Movie movie) {
        em.getTransaction().begin();
        em.persist(movie);
        em.getTransaction().commit();
        em.clear();
    }

    public boolean remove(int id) {
        em.getTransaction().begin();
        int status = em.createQuery("DELETE FROM Movie u where u.id = :id").setParameter("id", id).executeUpdate();
        em.getTransaction().commit();
        em.clear();

        return status == 1;
    }

    public Movie find(int id) {
        return em.find(Movie.class, id);
    }
}
