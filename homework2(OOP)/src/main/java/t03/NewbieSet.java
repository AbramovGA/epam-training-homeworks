package t03;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class NewbieSet {

    ArrayList<Stationery> set;

    public boolean add(Stationery stationery) {
        return set.add(stationery);
    }

    public boolean remove(Stationery stationery) {
        return set.remove(stationery);
    }
}
