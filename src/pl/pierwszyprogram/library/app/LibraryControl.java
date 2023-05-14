package pl.pierwszyprogram.library.app;

import pl.pierwszyprogram.library.exception.NoSuchOptionException;
import pl.pierwszyprogram.library.io.ConsolePrinter;
import pl.pierwszyprogram.library.io.DataReader;
import pl.pierwszyprogram.library.model.Book;
import pl.pierwszyprogram.library.model.Library;
import pl.pierwszyprogram.library.model.Magazine;
import pl.pierwszyprogram.library.model.Publication;

import java.util.InputMismatchException;

public class LibraryControl {
    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader dataReader = new DataReader(printer);
    private Library library = new Library();

    public void cotrolLoop() {
        Option option;
        do {
            printOption();
            option = getOption();
            switch (option) {
                case ADD_BOOK -> addBook();
                case PRINT_BOOKS -> printBooks();
                case ADD_MAGAZINE -> addMagazine();
                case PRINT_MAGAZINES -> printMagazine();
                case EXIT -> exit();
                default -> printer.printLine("błędny wybór");
            }
        } while (option != Option.EXIT);
    }

    private Option getOption() {
        boolean optionOk = false;
        Option option = null;
        while (!optionOk) {
            try {
                option = Option.createFromInt(dataReader.getInt());
                optionOk = true;
            } catch (NoSuchOptionException e) {
                printer.printLine("Brak opcji i id:" + option);
            } catch (InputMismatchException e) {
                printer.printLine("Wprowadzono wartość która, nie jest liczbą");
            }
        }
        return option;
    }

    public void exit() {
        printer.printLine("Koniec programu");
        dataReader.close();

    }

    private void printBooks() {
        Publication[] publications = library.getPublications();
        printer.printBooks(publications);
    }

    private void printMagazine() {
        Publication[] publications = library.getPublications();
        printer.printMagazines(publications);
    }

    private void addBook() {
        try {
            Book book = dataReader.readAndCreate();
            library.addBook(book);
        } catch (InputMismatchException e) {
            printer.printLine("Nie udało się utworzyć książki, niepoprawne dane");
        } catch (IndexOutOfBoundsException e) {
            printer.printLine("Osiągnięto limit pojemności");
        }
    }

    private void addMagazine() {
        try {
            Magazine magazine = dataReader.readAndCreateMagazine();
            library.addMagazine(magazine);
        } catch (InputMismatchException e) {
            printer.printLine("Nie udało się utworzyć magazynu, niepoprawne dane");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine("Osiągnięto limit pojemności");
        }
    }

    private void printOption() {
        printer.printLine("Wybierz opcje:");
        for (Option value : Option.values()) {
            printer.printLine(value.toString());
        }
    }
}
