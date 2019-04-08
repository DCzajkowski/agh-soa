package com.dczajkowski.bookmanager;

public class Book implements Cloneable {
    private int id;
    private String title;
    private String authorFirstName;
    private String authorLastName;
    private String isbn;
    private Genre genre;
    private int year;
    private int price;

    public Book(int id, String title, String authorFirstName, String authorLastName, String isbn, int year, int price) {
        this.id = id;
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.isbn = isbn;
        this.year = year;
        this.price = price;
    }

    public Book(Book book) {
        this.id = book.id;
        this.title = book.title;
        this.authorFirstName = book.authorFirstName;
        this.authorLastName = book.authorLastName;
        this.isbn = book.isbn;
        this.genre = book.genre;
        this.year = book.year;
        this.price = book.price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
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

    public String getAuthor() {
        return authorFirstName + " " + authorLastName;
    }
}
