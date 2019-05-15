package com.dczajkowski.rest.Repositories;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Persistence;

@ApplicationScoped
class ApplicationEntityManager {
    private javax.persistence.EntityManager em;

    ApplicationEntityManager() {
        em = Persistence.createEntityManagerFactory("Rest").createEntityManager();
    }

    javax.persistence.EntityManager get() {
        return em;
    }
}
