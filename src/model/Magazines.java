package model;

public class Magazines extends Book {
    private int issueNumber;

    public Magazines(int bookId, String title, String author, double price, String edition, String dateOfPurchase, int issueNumber) {
        super(bookId, title, author, price, edition, dateOfPurchase);
        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() {
        return issueNumber;
    }
}