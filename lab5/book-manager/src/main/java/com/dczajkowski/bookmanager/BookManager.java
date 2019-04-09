package com.dczajkowski.bookmanager;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named("BookManager")
@RequestScoped
public class BookManager {
    private String error = "";

    @Inject
    private BookRepository bookRepository;

    private String title;
    private String authorFirstName;
    private String authorLastName;
    private String isbn;
    private Integer year;
    private Float price;

    private boolean editing = false;

    public List<Book> getBooks() {
        return bookRepository.getBooks();
    }

    public void addBook() {
        if (!bookRepository.create(new Book(title, authorFirstName, authorLastName, isbn, year, (int) (price * 100)))) {
            error = "Could not create a book. Please try again later.";
        }
    }

    public void deleteBook(int id) {
        if (!bookRepository.delete(id)) {
            error = "Could not delete the book. Please try again later.";
        }
    }

    public void updateBook(Book book) {
        if (!bookRepository.update(book)) {
            error = "Could not update the book. Please try again later.";
        }
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void enterUpdateMode(Book book) {
        this.editing = true;

        title = book.getTitle();
        authorFirstName = book.getAuthorFirstName();
        authorLastName = book.getAuthorLastName();
        isbn = book.getIsbn();
        year = book.getYear();
        price = (float) book.getPrice() / 100;
    }

    public void exitUpdateMode() {
        this.editing = false;
    }

    public boolean isEditing() {
        return editing;
    }

    public void setEditing(boolean editing) {
        this.editing = editing;
    }
}
