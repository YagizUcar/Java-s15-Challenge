package model;

public class Librarian extends Person {
    private String password;

    public Librarian(String name, String password) {
        super(name);
        this.password = password;
    }

    public boolean verifyMember(String password) {
        return this.password.equals(password);
    }

    public void searchBook(Book book) {
        book.display();
    }

    public void issueBook(Book book, Reader reader) {
        if (book.isStatus() && reader.addBook(book)) {
            book.setStatus(false);
            book.setOwner(reader.getName());
            System.out.println(book.getTitle() + " kitabı " + reader.getName() + " adlı okuyucuya ödünç verildi.");
        } else {
            System.out.println("Kitap ödünç verilemedi! (Müsait değil veya kullanıcı limit dolu)");
        }
    }

    public double calculateFine(int lateDays) {
        return lateDays * 5.0; // Gün başına 5 TL ceza örneği
    }

    public void createBill(Reader reader, double amount) {
        System.out.println(reader.getName() + " için " + amount + " TL tutarında fatura oluşturuldu.");
    }

    public void returnBook(Book book, Reader reader) {
        reader.removeBook(book);
        book.setStatus(true);
        book.setOwner("Kütüphane");
        System.out.println(book.getTitle() + " kitabı kütüphaneye iade edildi.");
    }

    @Override
    public void whoYouAre() {
        System.out.println("Ben bir Kütüphaneciyim (Librarian): " + getName());
    }
}