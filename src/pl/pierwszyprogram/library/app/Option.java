package pl.pierwszyprogram.library.app;

import pl.pierwszyprogram.library.exception.NoSuchOptionException;

public enum Option {
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
