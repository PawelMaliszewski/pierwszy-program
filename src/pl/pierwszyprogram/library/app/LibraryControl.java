package pl.pierwszyprogram.library.app;

import pl.pierwszyprogram.library.io.DataReader;
import pl.pierwszyprogram.library.model.Book;
import pl.pierwszyprogram.library.model.Library;

public class LibraryControl {
    private int exit = 0;
    private int addBook = 1;
    private int showInfo = 2;
    private DataReader dataReader = new DataReader();
    private Library library = new Library();

    public void cotrolLoop() {
        int option;

        do {
            printOption();
            option = dataReader.getInt();
            switch (option) {
                case  -> option = 0;
                case 1 ->  addBook();
                default -> System.out.println("błędny wybór");
            }
        } while (option == 0);
    }

    private void addBook() {
        Book book =  dataReader.readAndCreate();
        library.addBook(book);
    }

    private void printOption() {
        System.out.println("Wybierz opcję\n"
                + exit +  " - wyjście z programu\n"
                + addBook + " - dodanie nowej książki\n"
                + showInfo + " - wyświetl dostępne książki");
    }
}
