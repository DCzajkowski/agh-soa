package com.dczajkowski.rest.Repositories;

import com.dczajkowski.rest.Models.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class UsersRepository extends Repository {
    public List<User> get() {
        return em.createQuery("SELECT u FROM User u INNER JOIN u.favouriteMovies fm").getResultList();
    }
}
