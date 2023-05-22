package pl.pierwszyprogram.library.io.file;

import pl.pierwszyprogram.library.exception.DataExportException;
import pl.pierwszyprogram.library.exception.DataImportException;
import pl.pierwszyprogram.library.exception.InvalidDataException;
import pl.pierwszyprogram.library.model.Book;
import pl.pierwszyprogram.library.model.Library;
import pl.pierwszyprogram.library.model.Magazine;
import pl.pierwszyprogram.library.model.Publication;

import java.io.*;
import java.util.Scanner;

public class CsvFileManager implements FileManager {

    private static final String FILE_NAME = "Library.csv";

    @Override
    public Library importData() {
        Library library = new Library();
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
        return library;
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

    private Magazine createMagazine(String[] split) {
        return new Magazine(split[1], split[2], split[3], Integer.parseInt(split[4]), Integer.parseInt(split[5]), Integer.parseInt(split[6]));
    }

    private Book createBook(String[] split) {
        return new Book(split[1], split[2], Integer.parseInt(split[3]), Integer.parseInt(split[4]), split[5], split[6]);
    }


    @Override
    public void exportData(Library library) {
        Publication[] publications = library.getPublications();
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
