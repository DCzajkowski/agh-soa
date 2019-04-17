package com.dczajkowski.library;

import com.dczajkowski.library.Domain.QueryHandler;
import com.dczajkowski.library.Models.Author;
import com.dczajkowski.library.Models.Book;
import com.dczajkowski.library.Models.Loan;
import com.dczajkowski.library.Models.User;
import com.dczajkowski.library.Repositories.AuthorRepository;
import com.dczajkowski.library.Repositories.BooksRepository;
import com.dczajkowski.library.Repositories.LoanRepository;
import com.dczajkowski.library.Repositories.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named("BooksManager")
@ApplicationScoped
public class BooksManager {
    @Inject
    private BooksRepository booksRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private LoanRepository loanRepository;

    @Inject
    private AuthorRepository authorRepository;

    @Inject
    private QueryHandler queryHandler;

    private User selectedUser;
    private Author selectedAuthor;
    private Book selectedBook;

    public List<Book> getBooks() {
        return booksRepository.get();
    }

    public List<User> getUsers() {
        return userRepository.get();
    }

    public List<Loan> getLoans() {
        return loanRepository.get();
    }

    public List<Author> getAuthors() {
        return authorRepository.get();
    }

    public void addUser() {
        userRepository.add();
    }

    public void addAuthor() {
        authorRepository.add();
    }

    public void removeBook(Book book) {
        booksRepository.remove(book);
        selectedBook = null;
    }

    public void removeLoan(Loan loan) {
        loanRepository.remove(loan);
    }

    public void endLoan(Loan loan) {
        loanRepository.returnLoan(loan);
    }

    public void removeUser(User user) {
        userRepository.remove(user);
        selectedUser = null;
    }

    public void removeAuthor(Author author) {
        authorRepository.remove(author);
        selectedAuthor = null;
    }

    public void addBook() {
        booksRepository.add(selectedAuthor);
        selectedAuthor = null;
    }

    public void addLoan() {
        loanRepository.add(selectedUser, selectedBook);
        selectedUser = null;
        selectedBook = null;
    }

    public QueryHandler getQueryHandler() {
        return queryHandler;
    }

    public BooksRepository getBookRepository() {
        return booksRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public LoanRepository getLoanRepository() {
        return loanRepository;
    }

    public AuthorRepository getAuthorRepository() {
        return authorRepository;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public Author getSelectedAuthor() {
        return selectedAuthor;
    }

    public void setSelectedAuthor(Author selectedAuthor) {
        this.selectedAuthor = selectedAuthor;
    }

    public Book getSelectedBook() {
        return selectedBook;
    }

    public void setSelectedBook(Book selectedBook) {
        this.selectedBook = selectedBook;
    }
}
