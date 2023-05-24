package pl.pierwszyprogram.library.io.file;

import pl.pierwszyprogram.library.exception.DataExportException;
import pl.pierwszyprogram.library.exception.DataImportException;
import pl.pierwszyprogram.library.exception.InvalidDataException;
import pl.pierwszyprogram.library.model.*;

import java.io.*;
import java.util.Collection;
import java.util.Scanner;

public class CsvFileManager implements FileManager {

    private static final String FILE_NAME = "Library.csv";
    private static final String USERS_FILE_NAME = "Library_users.csv";

    @Override
    public Library importData() {
        Library library = new Library();
        importPublications(library);
        importUsers(library);
        return library;
    }

    private void importPublications(Library library) {
        try (Scanner fileReader = new Scanner(new File(FILE_NAME))
        ) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                Publication publication = createObjectFromString(line);
                library.addPublication(publication);
            }

        } catch (FileNotFoundException e) {
            throw new DataImportException("Błąd odczytu pliku " + FILE_NAME);
        }
    }

    private Publication createObjectFromString(String line) {
        String[] split = line.split(";");
        String type =  split[0];
        if (Book.TYPE.equals(type)) {
            return createBook(split);
        } else if (Magazine.TYPE.equals(type)) {
            return createMagazine(split);
        } else {
            throw new InvalidDataException("Nieprawidłowy typ publikacji " + type);
        }
    }

    private void importUsers(Library library) {
        try (Scanner fileReader = new Scanner(new File(USERS_FILE_NAME))
        ) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                LibraryUser user = createUserFromString(line);
                library.addUser(user);
            }

        } catch (FileNotFoundException e) {
            throw new DataImportException("Błąd odczytu pliku " + USERS_FILE_NAME);
        }
    }

    private LibraryUser createUserFromString(String csvLine) {
        String[] split = csvLine.split(";");
        String fistName =  split[0];
        String lastName =  split[1];
        String peselName =  split[2];
        return new LibraryUser(fistName, lastName, peselName);
    }

    private Magazine createMagazine(String[] split) {
        return new Magazine(split[1], split[2], split[3], Integer.parseInt(split[4]), Integer.parseInt(split[5]), Integer.parseInt(split[6]));
    }

    private Book createBook(String[] split) {
        return new Book(split[1], split[2], Integer.parseInt(split[3]), Integer.parseInt(split[4]), split[5], split[6]);
    }

    @Override
    public void exportData(Library library) {
        exportPublications(library);
        exportUsers(library);

    }

    private void exportUsers(Library library) {
        Collection<LibraryUser> libraryUsers = library.getUsers().values();
        try (var bw = new BufferedWriter(new FileWriter(USERS_FILE_NAME))
        ) {
            for (LibraryUser libraryUser : libraryUsers) {
                bw.write(libraryUser.toCsv());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new DataExportException("Błąd zapisu danych do pliku " + USERS_FILE_NAME);
        }
    }

    private void exportPublications(Library library) {
        Collection<Publication> publications = library.getPublications().values();
        try (var bw = new BufferedWriter(new FileWriter(FILE_NAME))
        ) {
            for (Publication publication : publications) {
                bw.write(publication.toCsv());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new DataExportException("Błąd zapisu danych do pliku " + FILE_NAME);
        }
    }
}
