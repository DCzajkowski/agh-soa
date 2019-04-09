package com.dczajkowski.bookmanager;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book implements Cloneable {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author_first_name", nullable = false)
    private String authorFirstName;

    @Column(name = "author_last_name", nullable = false)
    private String authorLastName;

    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "price", nullable = false)
    private int price;

    public Book(String title, String authorFirstName, String authorLastName, String isbn, int year, int price) {
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.isbn = isbn;
        this.year = year;
        this.price = price;
    }

    public Book(Book book) {
        this.title = book.title;
        this.authorFirstName = book.authorFirstName;
        this.authorLastName = book.authorLastName;
        this.isbn = book.isbn;
        this.year = book.year;
        this.price = book.price;
    }

    public Book() {
        //
    }

    public int getId() {
        return id;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPriceFormatted() {
        return String.format("%.2fPLN", (double) price / 100);
    }

    public void setPriceFormatted(String price) {
        this.price = (int) (Float.parseFloat(price.substring(0, price.length() - 3)) * 100);
    }

    public String getAuthor() {
        return authorFirstName + " " + authorLastName;
    }
}
