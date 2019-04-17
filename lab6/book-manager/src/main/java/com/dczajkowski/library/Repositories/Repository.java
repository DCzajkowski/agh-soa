package com.dczajkowski.library.Repositories;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public abstract class Repository {
    protected EntityManager em;

    public Repository() {
        em = Persistence.createEntityManagerFactory("BookManager").createEntityManager();
    }
}
