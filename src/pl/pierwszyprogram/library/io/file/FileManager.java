package pl.pierwszyprogram.library.io.file;

import pl.pierwszyprogram.library.model.Library;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileManager {
    Library importData() throws IOException;

    void exportData(Library library) throws IOException;
}
