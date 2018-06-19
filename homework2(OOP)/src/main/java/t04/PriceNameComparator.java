package t04;

import t03.Stationery;

import java.util.Comparator;

public class PriceNameComparator implements Comparator<Stationery> {
    @Override
    public int compare(Stationery o1, Stationery o2) {

        int priceCompare = o1.getPrice().compareTo(o2.getPrice());
        if (priceCompare != 0)
            return priceCompare;
        return o1.getName().compareTo(o2.getName());
    }
}
