package com.dczajkowski.library.Repositories;

import com.dczajkowski.library.Models.Book;
import com.dczajkowski.library.Models.Loan;
import com.dczajkowski.library.Models.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.Date;
import java.util.List;

@Named("LoanRepository")
@ApplicationScoped
public class LoanRepository extends Repository {
    public LoanRepository() {
        super();
    }

    public List<Loan> get() {
        return em.createQuery("SELECT b FROM Loan b").getResultList();
    }

    public void remove(Loan loan) {
        em.getTransaction().begin();
        em.remove(loan);
        em.getTransaction().commit();
        em.clear();
    }

    public void returnLoan(Loan loan) {
        em.getTransaction().begin();
        loan.setTo(new Date());
        em.persist(loan);
        em.getTransaction().commit();
    }

    public void add(User user, Book book) {
        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);
        update(loan);
    }

    public void update(Loan loan) {
        em.getTransaction().begin();
        em.persist(loan);
        em.getTransaction().commit();
        em.clear();
    }
}
