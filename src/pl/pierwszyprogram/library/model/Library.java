package pl.pierwszyprogram.library.model;

public class Library {
    private static final int MAX_PUBLICATIONS = 2000;
    private final Publication[] publications = new Publication[2000];
    private int publicationNumber = 0;

    public void addBook(Book book) {
        if (publicationNumber < MAX_PUBLICATIONS) {
            publications[publicationNumber] = book;
            publicationNumber++;
        } else {
            System.out.println("Maksymalna liczba książek została osiągnięta");
        }
    }

    public void addMagazine(Magazine magazine) {
        if (publicationNumber < MAX_PUBLICATIONS) {
            publications[publicationNumber] = magazine;
            publicationNumber++;
        } else {
            System.out.println("Maksymalna liczba czasopism została osiągnięta");
        }
    }

    public void printBooks() {
        int booksCount = 0;
        for (int i = 0; i < publicationNumber; i++) {
            if (publications[i] instanceof Book) {
                System.out.println(publications[i].toString());
                booksCount++;
            }
        }
        if (booksCount == 0) {
            System.out.println("Brak książek w bibliotece");
        }
    }

    public void printMagazines() {
        int magazineCount =0;
        for (int i = 0; i < publicationNumber; i++) {
            if (publications[i] instanceof Magazine) {
                System.out.println(publications[i].toString());
                magazineCount++;
            }
        }
        if (magazineCount == 0) {
            System.out.println("Brak magazynów w bibliotece");
        }
    }
}
