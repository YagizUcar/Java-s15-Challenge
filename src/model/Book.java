package model;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private double price;
    private boolean status; // true: rafta/müsait, false: ödünç verilmiş
    private String edition;
    private String dateOfPurchase;
    private String owner;

    public Book(int bookId, String title, String author, double price, String edition, String dateOfPurchase) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.price = price;
        this.status = true;
        this.edition = edition;
        this.dateOfPurchase = dateOfPurchase;
        this.owner = "Kütüphane";
    }

    public int getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public double getPrice() { return price; }
    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }

    public void display() {
        System.out.println("Kitap ID: " + bookId + " | Başlık: " + title + " | Yazar: " + author + " | Durum: " + (status ? "Müsait" : "Ödünç Verildi (" + owner + ")"));
    }
}