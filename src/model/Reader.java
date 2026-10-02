package model;

import java.util.ArrayList;
import java.util.List;

public class Reader extends Person {
    private List<Book> borrowedBooks;
    private static final int MAX_BOOK_LIMIT = 5;

    public Reader(String name) {
        super(name);
        this.borrowedBooks = new ArrayList<>();
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public boolean addBook(Book book) {
        if (borrowedBooks.size() < MAX_BOOK_LIMIT) {
            borrowedBooks.add(book);
            return true;
        }
        return false; // 5 kitap limiti
    }

    public boolean removeBook(Book book) {
        return borrowedBooks.remove(book);
    }

    @Override
    public void whoYouAre() {
        System.out.println("Ben bir Okuyucuyum (Reader): " + getName());
    }
}