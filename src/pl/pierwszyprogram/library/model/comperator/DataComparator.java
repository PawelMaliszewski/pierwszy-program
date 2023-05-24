package pl.pierwszyprogram.library.model.comperator;

import pl.pierwszyprogram.library.model.Publication;

import java.util.Comparator;

public class DataComparator implements Comparator<Publication> {
    @Override
    public int compare(Publication o1, Publication o2) {
        if (o1 == null && o2 == null) {
            return 0;
        } else if (o1 == null) {
            return 1;
        } else if (o2 == null) {
            return -1;
        }
        Integer i1 = o1.getYear();
        Integer i2 = o2.getYear();
        return -i1.compareTo(i2);
    }
}
