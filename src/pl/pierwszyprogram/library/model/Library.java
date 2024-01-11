package pl.pierwszyprogram.library.model;

import pl.pierwszyprogram.library.exception.PublicationAlreadyExistsException;
import pl.pierwszyprogram.library.exception.UserAlreadyExistsException;

import java.io.Serializable;
import java.util.*;

public class Library implements Serializable {

    private Map<String, Publication> publications = new HashMap<>();
    private Map<String, LibraryUser> users = new HashMap<>();

    public Map<String, Publication> getPublications() {
        return publications;
    }

    public Collection<Publication> getSortedPublications(Comparator<Publication> comparator) {
        List<Publication> list = new ArrayList<>(this.publications.values());
        list.sort(comparator);
        return list;
    }
    public Map<String, LibraryUser> getUsers() {
        return users;
    }

    public Collection<LibraryUser> getSortedUsers(Comparator<LibraryUser> comparator) {
        List<LibraryUser> list = new ArrayList<>(this.users.values());
        list.sort(comparator);
        return list;
    }

    public void addPublication(Publication publication) {
        if (publications.containsKey(publication.getTitle())) {
            throw new PublicationAlreadyExistsException("Publikacja o takim tytule juz istnieje");
        }
        publications.put(publication.getTitle(), publication);
    }

    public void addUser(LibraryUser libraryUser) {
        if (users.containsKey(libraryUser.getPesel())) {
            throw new UserAlreadyExistsException("Użytkownik o numerze pesel: " + libraryUser.getPesel() + " jest wpisany");
        }
        users.put(libraryUser.getPesel(), libraryUser);
    }

    public boolean removePublication(Publication pub) {
        if (publications.containsValue(pub)) {
            publications.remove(pub.getTitle());
            return true;
        } else {
            return false;
        }
    }

}
