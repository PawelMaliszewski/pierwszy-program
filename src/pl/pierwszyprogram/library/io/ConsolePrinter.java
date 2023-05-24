package pl.pierwszyprogram.library.io;

import pl.pierwszyprogram.library.model.Book;
import pl.pierwszyprogram.library.model.LibraryUser;
import pl.pierwszyprogram.library.model.Publication;

import java.util.Collection;

public class ConsolePrinter {

    public void printBooks(Collection<Publication> publications) {
        int booksCount = 0;
        for (Publication publication : publications) {
            if (publication instanceof Book) {
                printLine(publication.toString());
                booksCount++;
            }
        }
        if (booksCount == 0) {
            printLine("Brak książek w bibliotece");
        }
    }

    public void printMagazines(Collection<Publication> publications) {
        int magazineCount = 0;
        for (Publication publication : publications) {
            if (publication instanceof Book) {
                printLine(publication.toString());
                magazineCount++;
            }
        }
        if (magazineCount == 0) {
            printLine("Brak magazynów w bibliotece");
        }
    }

    public void printUsers(Collection<LibraryUser> users) {
        for (LibraryUser user : users) {
            printLine(user.toString());
        }
    }

    public void printLine(String text) {
        System.out.println(text.toUpperCase());
    }

}
