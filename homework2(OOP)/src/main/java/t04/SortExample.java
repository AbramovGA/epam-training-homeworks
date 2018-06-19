package t04;

import t03.*;

import java.util.ArrayList;

public class SortExample {

    public static void main(String[] args) {

        NewbieSet set = new NewbieSet(new ArrayList<>());

        set.add(new Pen(100, "pen"));
        set.add(new TextBook(20, "textbook", new StringBuffer()));
        set.add(new Stepler(150, "stepler"));
        set.add(new Marker(100, "marker"));


        System.out.println(set.getSet());

        set.getSet().sort(new PriceComparator());
        System.out.println("Sorted by price: \n" + set.getSet());

        set.getSet().sort(new NameComparator());
        System.out.println("Sorted by name: \n" + set.getSet());

        set.getSet().sort(new PriceNameComparator());
        System.out.println("Sorted by price and name: \n" + set.getSet());
    }
}
