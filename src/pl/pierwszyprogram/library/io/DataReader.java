package pl.pierwszyprogram.library.io;

import pl.pierwszyprogram.library.model.Book;
import pl.pierwszyprogram.library.model.Magazine;
import java.util.Scanner;

public class DataReader {
    private Scanner sc = new Scanner(System.in);

    public Book readAndCreate() {
        System.out.println("Tytuł: ");
        String title = sc.nextLine();
        System.out.println("Autor: ");
        String author = sc.nextLine();
        System.out.println("Data wydania: ");
        int releaseDate = sc.nextInt();
        System.out.println("Stron: ");
        int pages = sc.nextInt();
        sc.nextLine();
        System.out.println("Wydawca: ");
        String publisher = sc.nextLine();
        System.out.println("ISBN: ");
        String isbn = sc.nextLine();
        return new Book(title, author, releaseDate, pages, publisher, isbn);
    }

    public Magazine readAndCreateMagazine() {
        System.out.println("Tytuł: ");
        String title = sc.nextLine();
        System.out.println("Wydawca: ");
        String publisher = sc.nextLine();
        System.out.println("Język: ");
        String language = sc.nextLine();
        System.out.println("Rok: ");
        int year = sc.nextInt();
        System.out.println("Miesiąc:");
        int month = sc.nextInt();
        System.out.println("Dzień:");
        int day = sc.nextInt();
        return new Magazine(title, publisher, language, year, month, day);
    }

    public void close() {
        sc.close();
    }

    public int getInt() {
        int number = sc.nextInt();
        sc.nextLine();
        return number;
    }
}
