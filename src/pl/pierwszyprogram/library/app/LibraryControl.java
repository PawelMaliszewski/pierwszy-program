package pl.pierwszyprogram.library.app;

import pl.pierwszyprogram.library.io.DataReader;
import pl.pierwszyprogram.library.model.Book;
import pl.pierwszyprogram.library.model.Library;
import pl.pierwszyprogram.library.model.Magazine;

public class LibraryControl {
    private static final int EXIT = 0;
    private static final int ADD_BOOK = 1;
    private static final int PRINT_BOOKS = 2;
    private static final int ADD_MAGAZINE = 3;
    private static final int PRINT_MAGAZINES = 4;
    private DataReader dataReader = new DataReader();
    private Library library = new Library();

    public void cotrolLoop() {
        int option;
        do {
            printOption();
            option = dataReader.getInt();
            switch (option) {
                case ADD_BOOK ->  addBook();
                case PRINT_BOOKS -> printBooks();
                case ADD_MAGAZINE -> addMagazine();
                case PRINT_MAGAZINES -> printMagazine();
                case EXIT -> exit();
                default -> System.out.println("błędny wybór");
            }
        } while (option != EXIT);
    }

    public void exit() {
        System.out.println("Koniec programu");
        dataReader.close();

    }

    private void printBooks() {
        library.printBooks();
    }

    private void printMagazine() {
        library.printMagazines();
    }

    private void addBook() {
        Book book =  dataReader.readAndCreate();
        library.addBook(book);
    }

    private void addMagazine() {
        Magazine magazine =  dataReader.readAndCreateMagazine();
        library.addMagazine(magazine);
    }

    private void printOption() {
        System.out.println("Wybierz opcję\n"
                + EXIT +  " - wyjście z programu\n"
                + ADD_BOOK + " - dodanie nowej książki\n"
                + PRINT_BOOKS + " - wyświetl dostępne książki\n"
                + ADD_MAGAZINE + "- dodanie nowego magazynu\n"
                + PRINT_MAGAZINES + "- wyświetl dostępne magazyny");
    }
}
