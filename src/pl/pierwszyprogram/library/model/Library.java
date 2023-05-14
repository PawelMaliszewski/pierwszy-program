package pl.pierwszyprogram.library.model;

import java.util.Arrays;

public class Library {
    private static final int MAX_PUBLICATIONS = 2000;
    private final Publication[] publications = new Publication[2000];
    private int publicationNumber = 0;

    public Publication[] getPublications() {
        return Arrays.copyOfRange(publications, 0, publicationNumber);
    }

    public void addBook(Book book) {
        addPublication(book);
    }

    public void addMagazine(Magazine magazine) {
        addPublication(magazine);
    }

    private void addPublication(Publication publication) {
        if (publicationNumber >= MAX_PUBLICATIONS) {
            throw new ArrayIndexOutOfBoundsException("Max publication" + MAX_PUBLICATIONS);
        }
        publications[publicationNumber] = publication;
        publicationNumber++;
    }
}
