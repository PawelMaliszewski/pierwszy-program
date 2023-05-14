package pl.pierwszyprogram.library.app;

import pl.pierwszyprogram.library.exception.DataExportException;
import pl.pierwszyprogram.library.exception.DataImportException;
import pl.pierwszyprogram.library.exception.NoSuchOptionException;
import pl.pierwszyprogram.library.io.ConsolePrinter;
import pl.pierwszyprogram.library.io.DataReader;
import pl.pierwszyprogram.library.io.file.FileManager;
import pl.pierwszyprogram.library.io.file.FileManagerBuilder;
import pl.pierwszyprogram.library.model.Book;
import pl.pierwszyprogram.library.model.Library;
import pl.pierwszyprogram.library.model.Magazine;
import pl.pierwszyprogram.library.model.Publication;

import java.io.IOException;
import java.util.InputMismatchException;

public class LibraryControl {
    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader dataReader = new DataReader(printer);
    private FileManager fileManager;
    private Library library;

    LibraryControl() {
        fileManager = new FileManagerBuilder(printer, dataReader). build();
        try {
            library  = fileManager.importData();
            printer.printLine("Zaimportowano dane z pliku");
        } catch (DataImportException e) {
            printer.printLine(e.getMessage());
            library = new Library();
            printer.printLine("Zainicjowano nowa bazę danych");
        } catch (IOException e) {
            printer.printLine(e.getMessage());
        }
    }

    void controlLoop() {
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
        try {
            fileManager.exportData(library);
            printer.printLine("Export danych do pliku zakończony sukcesem");
        } catch (IOException e) {
            throw new DataImportException("nie udało się eksportować danych do pliku");
        }
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

    private enum Option {
        EXIT(0, " - wyjście z programu"),
        ADD_BOOK(1, " - dodanie nowej książki"),
        PRINT_BOOKS(2, " - wyświetl dostępne książki"),
        ADD_MAGAZINE(3, " - dodanie nowego magazynu"),
        PRINT_MAGAZINES(4, " - wyświetl dostępne magazyny");

        private final  int option;
        private final String description;

        Option(int option, String description) {
            this.option = option;
            this.description = description;
        }

        public int getOption() {
            return option;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return option + description;
        }

        static Option createFromInt(int option) throws NoSuchOptionException {
            try {
                return Option.values()[option];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoSuchOptionException("Brak opcji i id:" + option);
            }
        }
    }
}
