package com.dczajkowski.rest.Repositories;

import com.dczajkowski.rest.Models.Movie;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class MoviesRepository {
    @Inject
    ApplicationEntityManager em;

    public List<Movie> get() {
        return em.get().createQuery("SELECT m FROM Movie m").getResultList();
    }

    public List<Movie> getByTitle(String title) {
        return em.get()
            .createQuery("SELECT m FROM Movie m WHERE m.title = :title")
            .setParameter("title", title)
            .getResultList();
    }

    public void create(Movie movie) {
        update(movie);
    }

    public void update(Movie movie) {
        em.get().getTransaction().begin();
        em.get().persist(movie);
        em.get().getTransaction().commit();
        em.get().clear();
    }

    public boolean remove(int id) {
        em.get().getTransaction().begin();
        int status = em.get().createQuery("DELETE FROM Movie u where u.id = :id").setParameter("id", id).executeUpdate();
        em.get().getTransaction().commit();
        em.get().clear();

        return status == 1;
    }

    public Movie find(int id) {
        return em.get().find(Movie.class, id);
    }
}
