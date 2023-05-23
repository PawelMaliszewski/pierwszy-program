package pl.pierwszyprogram.library.model;

import java.io.Serializable;
import java.util.Arrays;

public class Library implements Serializable {
    private static final int INITIAL_CAPACITY = 1;
    private Publication[] publications = new Publication[INITIAL_CAPACITY];
    private int publicationNumber = 0;

    public Publication[] getPublications() {
        return Arrays.copyOfRange(publications, 0, publicationNumber);
    }

    public void addPublication(Publication publication) {
        if (publicationNumber == publications.length) {
            publications = Arrays.copyOf(publications, publications.length * 2);
        }
        publications[publicationNumber] = publication;
        publicationNumber++;
    }

    public boolean removePublication(Publication pub) {
        final int notFound = -1;
        int found = notFound;
        int i = 0;
        while (i < publicationNumber && found == notFound) {
            if (pub.equals(publications[i])) {
                found = i;
            } else  {
                i++;
            }
        }
        if (found != notFound) {
            System.arraycopy(publications, found + 1, publications, found, publications.length - notFound - 1);
            publicationNumber--;
            publications[publicationNumber] = null;
        }
        return found != notFound;
    }

}
