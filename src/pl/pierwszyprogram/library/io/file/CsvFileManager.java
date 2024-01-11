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
        try (var br = new BufferedReader(new FileReader(FILE_NAME))
        ) {
            br.lines().map(this::createObjectFromString)
                    .forEach(library::addPublication);
        } catch (FileNotFoundException e) {
            throw new DataImportException("Błąd odczytu pliku " + FILE_NAME);
        } catch (IOException e) {
            throw new DataImportException("Błąd pliku:" + FILE_NAME);
        }
    }

    private Publication createObjectFromString(String line) {
        String[] split = line.split(";");
        String type = split[0];
        if (Book.TYPE.equals(type)) {
            return createBook(split);
        } else if (Magazine.TYPE.equals(type)) {
            return createMagazine(split);
        } else {
            throw new InvalidDataException("Nieprawidłowy typ publikacji " + type);
        }
    }

    private void importUsers(Library library) {
        try (var br = new BufferedReader(new FileReader(USERS_FILE_NAME))
        ) {
            br.lines().map(this::createUserFromString)
                    .forEach(library::addUser);
        } catch (FileNotFoundException e) {
            throw new DataImportException("Błąd pliku " + USERS_FILE_NAME);
        } catch (IOException e) {
            throw new DataImportException("Błąd odczytu piliku: " + USERS_FILE_NAME);
        }
    }

    private LibraryUser createUserFromString(String csvLine) {
        String[] split = csvLine.split(";");
        String fistName = split[0];
        String lastName = split[1];
        String peselName = split[2];
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
        exportTpoCsv(libraryUsers, USERS_FILE_NAME);
    }

    private void exportPublications(Library library) {
        Collection<Publication> publications = library.getPublications().values();
        exportTpoCsv(publications, FILE_NAME);
    }

    private <T extends CsvConvertible> void exportTpoCsv(Collection<T> collection, String fileName) {
        try (var bw = new BufferedWriter(new FileWriter(fileName))
        ) {
            for (T elemenet : collection) {
                bw.write(elemenet.toCsv());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new DataExportException("Błąd zapisu danych do pliku " + fileName);
        }
    }
}
