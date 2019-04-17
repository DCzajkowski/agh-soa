package com.dczajkowski.library.Domain;

import com.dczajkowski.library.Models.Author;
import com.dczajkowski.library.Models.Book;
import com.dczajkowski.library.Models.Loan;
import com.dczajkowski.library.Models.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Named("QueryHandler")
@ApplicationScoped
public class QueryHandler {
    private EntityManager em;

    public QueryHandler() {
        em = Persistence.createEntityManagerFactory("BookManager").createEntityManager();
    }

    private String userFirstName;
    private String userLastName;
    private String loanDateFrom;
    private String loanDateTo;
    private String returnDateFrom;
    private String returnDateTo;
    private String title;
    private String authorFirstName;
    private String authorLastName;

    private List<Object[]> result;

    public void performQuery() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = cb.createQuery(Object[].class);
        Root<User> u = q.from(User.class);
        Join<User, Loan> l = u.join("loans");
        Join<Loan, Book> b = l.join("book");
        Join<Book, Author> a = b.join("author");

        List<Predicate> conditions = new ArrayList<>();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        if (userFirstName != null && !userFirstName.equals("")) {
            conditions.add(cb.equal(u.get("firstName"), userFirstName));
        }

        if (userLastName != null && !userLastName.equals("")) {
            conditions.add(cb.equal(u.get("lastName"), userLastName));
        }

        if (loanDateFrom != null && !loanDateFrom.equals("")) {
            try {
                conditions.add(cb.greaterThan(l.get("from_date"), formatter.parse(loanDateFrom)));
            } catch (ParseException e) {
                loanDateFrom = null;
            }
        }

        if (loanDateTo != null && !loanDateTo.equals("")) {
            try {
                conditions.add(cb.lessThan(l.get("from_date"), formatter.parse(loanDateTo)));
            } catch (ParseException e) {
                loanDateTo = null;
            }
        }

        if (returnDateFrom != null && !returnDateFrom.equals("")) {
            try {
                conditions.add(cb.greaterThan(l.get("to_date"), formatter.parse(returnDateFrom)));
            } catch (ParseException e) {
                returnDateFrom = null;
            }
        }

        if (returnDateTo != null && !returnDateTo.equals("")) {
            try {
                conditions.add(cb.lessThan(l.get("to_date"), formatter.parse(returnDateTo)));
            } catch (ParseException e) {
                returnDateTo = null;
            }
        }

        if (title != null && !title.equals("")) {
            conditions.add(cb.equal(b.get("title"), title));
        }

        if (authorFirstName != null && !authorFirstName.equals("")) {
            conditions.add(cb.equal(a.get("authorFirstName"), authorFirstName));
        }

        if (authorLastName != null && !authorLastName.equals("")) {
            conditions.add(cb.equal(a.get("authorLastName"), authorLastName));
        }

        CriteriaQuery<Object[]> query = q.multiselect(u, a, b, l).where(conditions.toArray(new Predicate[]{}));
        result = em.createQuery(query).getResultList();
    }

    public List<Object[]> getResult() {
        return result;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getLoanDateFrom() {
        return loanDateFrom;
    }

    public void setLoanDateFrom(String loanDateFrom) {
        this.loanDateFrom = loanDateFrom;
    }

    public String getLoanDateTo() {
        return loanDateTo;
    }

    public void setLoanDateTo(String loanDateTo) {
        this.loanDateTo = loanDateTo;
    }

    public String getReturnDateFrom() {
        return returnDateFrom;
    }

    public void setReturnDateFrom(String returnDateFrom) {
        this.returnDateFrom = returnDateFrom;
    }

    public String getReturnDateTo() {
        return returnDateTo;
    }

    public void setReturnDateTo(String returnDateTo) {
        this.returnDateTo = returnDateTo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }
}
