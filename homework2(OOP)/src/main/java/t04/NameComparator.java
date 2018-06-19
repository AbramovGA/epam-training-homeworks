package t04;

import t03.Stationery;

import java.util.Comparator;

public class NameComparator implements Comparator<Stationery> {
    @Override
    public int compare(Stationery o1, Stationery o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
