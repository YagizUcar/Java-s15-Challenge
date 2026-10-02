package model;

import java.util.ArrayList;
import java.util.List;

public class Author extends Person {
    private List<Book> books;

    public Author(String name) {
        super(name);
        this.books = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addNewBook(Book book) {
        books.add(book);
    }

    public void showBook() {
        System.out.println(getName() + " adlı yazarın kitapları:");
        for (Book book : books) {
            System.out.println("- " + book.getTitle());
        }
    }

    @Override
    public void whoYouAre() {
        System.out.println("Ben bir Yazarım (Author): " + getName());
    }
}