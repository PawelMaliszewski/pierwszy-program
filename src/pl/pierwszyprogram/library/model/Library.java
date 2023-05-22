package pl.pierwszyprogram.library.model;

import java.io.Serializable;
import java.util.Arrays;

public class Library implements Serializable {
    private static final int MAX_PUBLICATIONS = 2000;
    private final Publication[] publications = new Publication[2000];
    private int publicationNumber = 0;

    public Publication[] getPublications() {
        return Arrays.copyOfRange(publications, 0, publicationNumber);
    }

    public void addPublication(Publication publication) {
        if (publicationNumber >= MAX_PUBLICATIONS) {
            throw new ArrayIndexOutOfBoundsException("Max publication" + MAX_PUBLICATIONS);
        }
        publications[publicationNumber] = publication;
        publicationNumber++;
    }
}
