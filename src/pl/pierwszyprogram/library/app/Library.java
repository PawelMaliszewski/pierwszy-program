package pl.pierwszyprogram.library.app;

import pl.pierwszyprogram.library.io.DataReader;
import pl.pierwszyprogram.library.model.Book;

public class Library {
    public static void main(String[] args) {
        final String appName = "Biblioteka v0.8";
        System.out.println(appName);
        DataReader dataReader = new DataReader();
        Book[] books = new Book[1000];

        books[0] = dataReader.readAndCreate();
        books[1] = dataReader.readAndCreate();
        books[2] = dataReader.readAndCreate();

        System.out.println("Książki dostępne w bibliotece:");

        for (int i = 0; i < books.length - 1; i++) {
            if (books[i] != null) {
                books[i].printInfo();
            }
        }
    }
}
