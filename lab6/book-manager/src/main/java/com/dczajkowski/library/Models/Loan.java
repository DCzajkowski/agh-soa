package com.dczajkowski.library.Models;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "from_date", nullable = false)
    private Date from;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "to_date", nullable = false)
    private Date to;

    public Loan() {
        from = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getFrom() {
        if (from == null) {
            return null;
        }

        return new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(from);
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public String getTo() {
        if (to == null) {
            return null;
        }

        return new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(to);
    }

    public void setTo(Date to) {
        this.to = to;
    }
}
