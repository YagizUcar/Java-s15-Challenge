package model;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private List<Book> books;
    private List<Reader> readers;
    private Map<Integer, Book> bookMap;
    private final String DATA_FILE = "library_data.txt";

    public Library() {
        this.books = new ArrayList<>();
        this.readers = new ArrayList<>();
        this.bookMap = new HashMap<>();
        loadData(); // Program açıldığında verileri dosyadan yükle
    }

    public void addBook(Book book) {
        books.add(book);
        bookMap.put(book.getBookId(), book);
        saveData(); // Kitap eklendiğinde dosyaya kaydet
    }

    public void addReader(Reader reader) {
        readers.add(reader);
        saveData(); // Üye eklendiğinde dosyaya kaydet
    }

    public List<Reader> getReaders() {
        return readers;
    }

    public Book searchBookById(int id) {
        return bookMap.get(id);
    }

    public List<Book> searchBookByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(b);
            }
        }
        return result;
    }

    public List<Book> searchBookByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                result.add(b);
            }
        }
        return result;
    }

    public void showBooksByType(String type) {
        System.out.println("--- " + type + " Listesi ---");
        for (Book b : books) {
            if (type.equalsIgnoreCase("Ders Kitabi") && b instanceof StudyBooks) {
                b.display();
            } else if (type.equalsIgnoreCase("Dergi") && b instanceof Magazines) {
                b.display();
            } else if (type.equalsIgnoreCase("Roman") && !(b instanceof StudyBooks) && !(b instanceof Magazines)) {
                b.display();
            }
        }
    }

    public void showAllReaders() {
        System.out.println("--- Kütüphane Üyeleri ve Detayları ---");
        if (readers.isEmpty()) {
            System.out.println("Sistemde kayıtlı üye bulunmamaktadır.");
            return;
        }
        for (Reader r : readers) {
            System.out.println("Üye Adı: " + r.getName() + " | Üzerindeki Kitap Sayısı: " + r.getBorrowedBooks().size() + "/5");
            if (!r.getBorrowedBooks().isEmpty()) {
                System.out.println("  Aldığı Kitaplar:");
                for (Book b : r.getBorrowedBooks()) {
                    System.out.println("    - " + b.getTitle() + " (ID: " + b.getBookId() + ")");
                }
            } else {
                System.out.println("  Üzerinde ödünç kitap yok.");
            }
            System.out.println("----------------------------------------");
        }
    }

    public boolean deleteBook(int id) {
        Book b = bookMap.remove(id);
        if (b != null) {
            books.remove(b);
            saveData();
            return true;
        }
        return false;
    }

    public void showAllBooks() {
        System.out.println("--- Kütüphanedeki Tüm Kitaplar ---");
        for (Book book : books) {
            book.display();
        }
    }

    // --- Dosyalama (File I/O) İşlemleri ---
    public void saveData() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            writer.println("BOOKS_COUNT:" + books.size());
            for (Book b : books) {
                writer.println(b.getBookId() + "," + b.getTitle() + "," + b.getAuthor() + "," + b.getPrice() + "," + b.isStatus());
            }
        } catch (IOException e) {
            System.out.println("Veriler kaydedilirken hata oluştu: " + e.getMessage());
        }
    }

    public void loadData() {
        File file = new File(DATA_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (line != null && line.startsWith("BOOKS_COUNT:")) {
                int count = Integer.parseInt(line.split(":")[1]);
                for (int i = 0; i < count; i++) {
                    String bookData = reader.readLine();
                    if (bookData != null) {
                        String[] parts = bookData.split(",");
                        int id = Integer.parseInt(parts[0]);
                        String title = parts[1];
                        String author = parts[2];
                        double price = Double.parseDouble(parts[3]);
                        boolean status = Boolean.parseBoolean(parts[4]);

                        Book book = new Book(id, title, author, price, "1. Baskı", "2026-01-01");
                        book.setStatus(status);
                        books.add(book);
                        bookMap.put(id, book);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Kayıtlı veriler yüklenirken hata oluştu.");
        }
    }
}