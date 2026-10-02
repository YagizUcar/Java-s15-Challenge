package main;

import model.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Librarian librarian = new Librarian("Ahmet", "1234");
        Scanner scanner = new Scanner(System.in);

        // Eğer dosya boşsa başlangıç verisi ekle
        if (library.searchBookById(1) == null && library.searchBookById(2) == null) {
            Book b1 = new Book(1, "Suç ve Ceza", "Dostoyevski", 45.0, "1. Baskı", "2023-01-10");
            Book b2 = new StudyBooks(2, "Java Programlama", "Oracle", 120.0, "2. Baskı", "2023-05-12", "Yazılım");
            library.addBook(b1);
            library.addBook(b2);
        }

        if (library.getReaders().isEmpty()) {
            Student defaultReader = new Student("Yağız", "202601");
            library.addReader(defaultReader);
        }

        boolean running = true;
        while (running) {
            System.out.println("\n===== KÜTÜPHANE OTOMASYONU (ULTIMATE PRO) =====");
            System.out.println("1. Tüm Kitapları Listele");
            System.out.println("2. Türüne Göre Listele (Ders Kitabı / Dergi / Roman)");
            System.out.println("3. Yeni Üye (Okuyucu) Kaydet");
            System.out.println("4. Tüm Üyeleri ve Detaylarını Listele");
            System.out.println("5. Yeni Kitap Ekle");
            System.out.println("6. Kitap Ara (ID, İsim veya Yazar ile)");
            System.out.println("7. Kitap Ödünç Al (5 Kitap Limiti & Fatura)");
            System.out.println("8. Kitap İade Et (Gecikme Cezası Hesaplama & Ücret İadesi)");
            System.out.println("9. Kitap Sil");
            System.out.println("10. Çıkış");
            System.out.print("Seçiminiz: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Buffer temizleme
            } catch (InputMismatchException e) {
                System.out.println("Hata: Lütfen geçerli bir sayı giriniz!");
                scanner.nextLine(); // Hatalı girdiyi temizle
                continue;
            }

            switch (choice) {
                case 1:
                    library.showAllBooks();
                    break;
                case 2:
                    System.out.print("Listenecek Tür (Ders Kitabi / Dergi / Roman): ");
                    String type = scanner.nextLine();
                    library.showBooksByType(type);
                    break;
                case 3:
                    System.out.print("Yeni Üye Adı: ");
                    String memberName = scanner.nextLine();
                    System.out.print("Öğrenci Numarası: ");
                    String studentId = scanner.nextLine();
                    Student newStudent = new Student(memberName, studentId);
                    library.addReader(newStudent);
                    System.out.println("Yeni öğrenci üye başarıyla kaydedildi: " + memberName);
                    break;
                case 4:
                    library.showAllReaders();
                    break;
                case 5:
                    try {
                        System.out.print("Kitap ID: ");
                        int newId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Kitap Adı: ");
                        String newTitle = scanner.nextLine();
                        System.out.print("Yazar: ");
                        String newAuthor = scanner.nextLine();
                        System.out.print("Fiyat: ");
                        double newPrice = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Baskı (Edition): ");
                        String newEdition = scanner.nextLine();
                        System.out.print("Alış Tarihi: ");
                        String newDate = scanner.nextLine();

                        Book newBook = new Book(newId, newTitle, newAuthor, newPrice, newEdition, newDate);
                        library.addBook(newBook);
                        System.out.println("Başarıyla yeni kitap eklendi ve dosyaya kaydedildi!");
                    } catch (Exception e) {
                        System.out.println("Hatalı veri girişi! Kitap eklenemedi.");
                        scanner.nextLine();
                    }
                    break;
                case 6:
                    System.out.println("Arama türü seçin: 1- ID ile | 2- İsim ile | 3- Yazar ile");
                    int searchType = scanner.nextInt();
                    scanner.nextLine();
                    if (searchType == 1) {
                        System.out.print("ID girin: ");
                        int searchId = scanner.nextInt();
                        Book found = library.searchBookById(searchId);
                        if (found != null) found.display();
                        else System.out.println("Kitap bulunamadı!");
                    } else if (searchType == 2) {
                        System.out.print("Kitap adı girin: ");
                        String searchTitle = scanner.nextLine();
                        List<Book> results = library.searchBookByTitle(searchTitle);
                        results.forEach(Book::display);
                    } else if (searchType == 3) {
                        System.out.print("Yazar adı girin: ");
                        String searchAuthor = scanner.nextLine();
                        List<Book> results = library.searchBookByAuthor(searchAuthor);
                        results.forEach(Book::display);
                    }
                    break;
                case 7:
                    System.out.print("Ödünç alacak üyenin adı (Örn: Yağız): ");
                    String readerName = scanner.nextLine();
                    Reader targetReader = null;
                    for (Reader r : library.getReaders()) {
                        if (r.getName().equalsIgnoreCase(readerName)) {
                            targetReader = r;
                            break;
                        }
                    }
                    if (targetReader == null) {
                        System.out.println("Üye bulunamadı! Önce üye kaydı yapın.");
                        break;
                    }

                    System.out.print("Ödünç alınacak Kitap ID: ");
                    int borrowId = scanner.nextInt();
                    Book targetBook = library.searchBookById(borrowId);
                    if (targetBook != null) {
                        librarian.issueBook(targetBook, targetReader);
                        if (!targetBook.isStatus()) {
                            librarian.createBill(targetReader, targetBook.getPrice() * 0.1);
                        }
                    } else {
                        System.out.println("Kitap bulunamadı!");
                    }
                    break;
                case 8:
                    System.out.print("İade edecek üyenin adı: ");
                    String returnReaderName = scanner.nextLine();
                    Reader returnReader = null;
                    for (Reader r : library.getReaders()) {
                        if (r.getName().equalsIgnoreCase(returnReaderName)) {
                            returnReader = r;
                            break;
                        }
                    }
                    if (returnReader == null) {
                        System.out.println("Üye bulunamadı!");
                        break;
                    }

                    System.out.print("İade edilecek Kitap ID: ");
                    int returnId = scanner.nextInt();
                    Book returnBook = library.searchBookById(returnId);
                    if (returnBook != null) {
                        librarian.returnBook(returnBook, returnReader);

                        System.out.print("Kitap kaç gün gecikti? (Gecikme yoksa 0 yazın): ");
                        int lateDays = scanner.nextInt();
                        if (lateDays > 0) {
                            double fine = librarian.calculateFine(lateDays);
                            System.out.println("Gecikme Cezası Kesildi: " + fine + " TL");
                        } else {
                            System.out.println("Zamanında iade! Ücret iadesi yapıldı. Teşekkürler.");
                        }
                    } else {
                        System.out.println("Kitap bulunamadı!");
                    }
                    break;
                case 9:
                    System.out.print("Silinecek Kitap ID: ");
                    int deleteId = scanner.nextInt();
                    if (library.deleteBook(deleteId)) {
                        System.out.println("Kitap sistemden silindi ve dosyadan güncellendi.");
                    } else {
                        System.out.println("Kitap bulunamadı!");
                    }
                    break;
                case 10:
                    running = false;
                    System.out.println("Sistemden çıkılıyor. Başarılar Yağız!");
                    break;
                default:
                    System.out.println("Geçersiz seçim! 1 ile 10 arasında bir sayı girin.");
            }
        }
        scanner.close();
    }
}