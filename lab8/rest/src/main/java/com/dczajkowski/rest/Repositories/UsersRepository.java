package com.dczajkowski.rest.Repositories;

import com.dczajkowski.rest.Models.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class UsersRepository extends Repository {
    public List<User> get() {
        return em.createQuery("SELECT u FROM User u LEFT JOIN u.favouriteMovies fm").getResultList();
    }

    public void create(User user) {
        update(user);
    }

    public void update(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.clear();
    }

    public boolean remove(int id) {
        em.getTransaction().begin();
        int status = em.createQuery("DELETE FROM User u where u.id = :id").setParameter("id", id).executeUpdate();
        em.getTransaction().commit();
        em.clear();

        return status == 1;
    }

    public User find(int id) {
        return em.find(User.class, id);
    }
}
