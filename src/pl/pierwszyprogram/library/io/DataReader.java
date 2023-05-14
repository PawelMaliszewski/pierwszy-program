package pl.pierwszyprogram.library.io;

import pl.pierwszyprogram.library.model.Book;
import pl.pierwszyprogram.library.model.Magazine;
import java.util.Scanner;

public class DataReader {
    private Scanner sc = new Scanner(System.in);
    private ConsolePrinter printer;

    public DataReader(ConsolePrinter printer) {
        this.printer = printer;
    }

    public Book readAndCreate() {
        System.out.println("Tytuł: ");
        String title = sc.nextLine();
        printer.printLine("Autor: ");
        String author = sc.nextLine();
        printer.printLine("Data wydania: ");
        int releaseDate = sc.nextInt();
        printer.printLine("Stron: ");
        int pages = sc.nextInt();
        sc.nextLine();
        printer.printLine("Wydawca: ");
        String publisher = sc.nextLine();
        printer.printLine("ISBN: ");
        String isbn = sc.nextLine();
        return new Book(title, author, releaseDate, pages, publisher, isbn);
    }

    public Magazine readAndCreateMagazine() {
        printer.printLine("Tytuł: ");
        String title = sc.nextLine();
        printer.printLine("Wydawca: ");
        String publisher = sc.nextLine();
        printer.printLine("Język: ");
        String language = sc.nextLine();
        printer.printLine("Rok: ");
        int year = sc.nextInt();
        printer.printLine("Miesiąc:");
        int month = sc.nextInt();
        printer.printLine("Dzień:");
        int day = sc.nextInt();
        return new Magazine(title, publisher, language, year, month, day);
    }

    public void close() {
        sc.close();
    }

    public int getInt() {
        try {
            return sc.nextInt();
        } finally {
            sc.nextLine();
        }

    }
}
