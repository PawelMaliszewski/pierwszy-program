package pl.pierwszyprogram.library.io.file;

import pl.pierwszyprogram.library.exception.DataExportException;
import pl.pierwszyprogram.library.exception.DataImportException;
import pl.pierwszyprogram.library.model.Library;

import java.io.*;

public class SerializableFileManger implements FileManager {
    private static final String FILE_NAME = "Library.o";

    @Override
    public Library importData() {
        try (
                var ois = new ObjectInputStream(new FileInputStream(FILE_NAME))
        ) {
            return (Library) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new DataImportException("Brak pliku " + FILE_NAME);
        } catch (IOException e) {
            throw new DataImportException("Błąd odczytu danych z pliku " + FILE_NAME);
        } catch (ClassNotFoundException e) {
            throw new DataImportException("Niezgodny typ danych w pliku");
        }
    }

    @Override
    public void exportData(Library library) {
        try (
                var oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))
        ) {
            oos.writeObject(library);
        } catch (FileNotFoundException e) {
            throw new DataExportException("Brak pliku " + FILE_NAME);
        } catch (IOException e) {
            throw new DataExportException("Błąd zapisu danych do pliku " + FILE_NAME);
        }
    }
}
