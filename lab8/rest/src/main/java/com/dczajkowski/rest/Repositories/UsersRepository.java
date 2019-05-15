package com.dczajkowski.rest.Repositories;

import com.dczajkowski.rest.Models.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class UsersRepository {
    @Inject
    ApplicationEntityManager em;

    public List<User> get() {
        return em.get().createQuery("SELECT u FROM User u LEFT JOIN u.favouriteMovies fm").getResultList();
    }

    public void create(User user) {
        update(user);
    }

    public void update(User user) {
        em.get().getTransaction().begin();
        em.get().persist(user);
        em.get().getTransaction().commit();
        em.get().clear();
    }

    public boolean remove(int id) {
        em.get().getTransaction().begin();
        int status = em.get().createQuery("DELETE FROM User u where u.id = :id").setParameter("id", id).executeUpdate();
        em.get().getTransaction().commit();
        em.get().clear();

        return status == 1;
    }

    public User find(int id) {
        return em.get().find(User.class, id);
    }
}
