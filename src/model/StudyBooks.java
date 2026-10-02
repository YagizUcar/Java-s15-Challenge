package model;

public class StudyBooks extends Book {
    private String subject;

    public StudyBooks(int bookId, String title, String author, double price, String edition, String dateOfPurchase, String subject) {
        super(bookId, title, author, price, edition, dateOfPurchase);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }
}