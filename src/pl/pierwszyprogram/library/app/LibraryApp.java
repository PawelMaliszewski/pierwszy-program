package pl.pierwszyprogram.library.app;

public class LibraryApp {
    private static final String APP_NAME = "Biblioteka v1.83";

    public static void main(String[] args) {

        System.out.println(APP_NAME);

        LibraryControl libraryControl = new LibraryControl();

        libraryControl.controlLoop();
    }

}
