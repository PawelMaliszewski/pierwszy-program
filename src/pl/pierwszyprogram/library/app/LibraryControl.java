package pl.pierwszyprogram.library.app;

import pl.pierwszyprogram.library.io.DataReader;
import pl.pierwszyprogram.library.model.Book;
import pl.pierwszyprogram.library.model.Library;

public class LibraryControl {
    private static final int EXIT = 0;
    private static final int ADD_BOOK = 1;
    private static final int PRINT_BOOKS = 2;
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

    private void addBook() {
        Book book =  dataReader.readAndCreate();
        library.addBook(book);
    }

    private void printOption() {
        System.out.println("Wybierz opcję\n"
                + EXIT +  " - wyjście z programu\n"
                + ADD_BOOK + " - dodanie nowej książki\n"
                + PRINT_BOOKS + " - wyświetl dostępne książki");
    }
}
