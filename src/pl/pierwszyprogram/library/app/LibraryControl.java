package pl.pierwszyprogram.library.app;

import pl.pierwszyprogram.library.io.DataReader;
import pl.pierwszyprogram.library.model.Book;
import pl.pierwszyprogram.library.model.Library;
import pl.pierwszyprogram.library.model.Magazine;

public class LibraryControl {
    private DataReader dataReader = new DataReader();
    private Library library = new Library();

    public void cotrolLoop() {
        Option option;
        do {
            printOption();
            option = Option.createFromInt(dataReader.getInt());
            switch (option) {
                case ADD_BOOK ->  addBook();
                case PRINT_BOOKS -> printBooks();
                case ADD_MAGAZINE -> addMagazine();
                case PRINT_MAGAZINES -> printMagazine();
                case EXIT -> exit();
                default -> System.out.println("błędny wybór");
            }
        } while (option != Option.EXIT);
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
        System.out.println("Wybierz opcje:");
        for (Option value : Option.values()) {
            System.out.println(value);
        }
    }
}
