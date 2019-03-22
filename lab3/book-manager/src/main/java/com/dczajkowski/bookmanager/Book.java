package com.dczajkowski.bookmanager;

public class Book implements Cloneable {
    private int id;
    private String title;
    private String author;
    private Genre genre;
    private int price;
    private Currency currency;
    private int pages;
    private boolean selected = false;

    public Book(int id, String title, String author, Genre genre, int price, Currency currency, int pages) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.currency = currency;
        this.pages = pages;
    }

    public Book(Book book) {
        this.id = book.id;
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

    public String getPriceFormattedInCurrency(Currency currency) {
        return String.format("%.2f", (double) getPriceInCurrency(currency) / 100);
    }

    public int getPriceInCurrency(Currency currency) {
        return new CurrencyConverter(price, this.currency).to(currency);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
