package pl.pierwszyprogram.library.app;

import pl.pierwszyprogram.library.io.DataReader;
import pl.pierwszyprogram.library.model.Book;
import pl.pierwszyprogram.library.model.Library;

public class LibraryControl {
    private final int exit = 0;
    private final int addBook = 1;
    private final int printBooks = 2;
    private DataReader dataReader = new DataReader();
    private Library library = new Library();

    public void cotrolLoop() {
        int option;

        do {
            printOption();
            option = dataReader.getInt();
            switch (option) {
                case addBook ->  addBook();
                case printBooks -> printBooks();
                case exit -> exit();
                default -> System.out.println("błędny wybór");
            }
        } while (option != exit);
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
                + exit +  " - wyjście z programu\n"
                + addBook + " - dodanie nowej książki\n"
                + printBooks + " - wyświetl dostępne książki");
    }
}
