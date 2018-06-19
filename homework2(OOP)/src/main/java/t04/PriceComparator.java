package t04;

import t03.Stationery;

import java.util.Comparator;

public class PriceComparator implements Comparator<Stationery> {

    @Override
    public int compare(Stationery o1, Stationery o2) {
        return o1.getPrice().compareTo(o2.getPrice());
    }
}
