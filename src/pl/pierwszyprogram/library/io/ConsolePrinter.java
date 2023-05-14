package pl.pierwszyprogram.library.io;

import pl.pierwszyprogram.library.model.Book;
import pl.pierwszyprogram.library.model.Publication;

public class ConsolePrinter {

    public void printBooks(Publication[] publications) {
        int booksCount = publications.length;
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

    public void printMagazines(Publication[] publications) {
        int magazineCount = publications.length;
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

    public void printLine(String text) {
        System.out.println(text.toUpperCase());
    }

}
