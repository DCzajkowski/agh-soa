package com.dczajkowski.library.Repositories;

import com.dczajkowski.library.Models.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.List;

@Named("UserRepository")
@ApplicationScoped
public class UserRepository extends Repository {
    public UserRepository() {
        super();
    }

    public List<User> get() {
        return em.createQuery("SELECT b FROM User b").getResultList();
    }

    public void remove(User user) {
        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
    }

    public void add() {
        User user = new User("", "");
        update(user);
    }

    public void update(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }
}
