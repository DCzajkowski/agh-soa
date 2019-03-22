package com.dczajkowski.bookmanager;

public class Book implements Cloneable {
    private String title;
    private String author;
    private Genre genre;
    private int price;
    private Currency currency;
    private int pages;

    public Book(String title, String author, Genre genre, int price, Currency currency, int pages) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.currency = currency;
        this.pages = pages;
    }

    public Book(Book book) {
        this.title = book.title;
        this.author = book.author;
        this.genre = book.genre;
        this.price = book.price;
        this.currency = book.currency;
        this.pages = book.pages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getPrice() {
        return price;
    }

    public String getPriceFormatted() {
        return String.format("%.2f", (double) price / 100);
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
