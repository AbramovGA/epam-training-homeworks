package t02;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.HashMap;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StationeryManager {

    HashMap<String, ArrayList<Stationery>> stationeryRecords;

    public void addEmployee(String name) {
        if (stationeryRecords.containsKey(name))
            System.out.println("Employee " + name + " already exists");
        else
            stationeryRecords.put(name, new ArrayList<>());
    }

    public void removeEmployee(String name) {
        stationeryRecords.remove(name);
    }

    public void removeStationery(String name, Stationery stationery) {
        if (stationeryRecords.containsKey(name))
            stationeryRecords.get(name).remove(stationery);
    }

    public void addStationery(String name, Stationery stationery) {
        if (!stationeryRecords.containsKey(name))
            addEmployee(name);

        stationeryRecords.get(name).add(stationery);
    }

    public int costByName(String name) {
        if (!stationeryRecords.containsKey(name)) {
            System.out.println("Employee " + name + " doesn't exist");
            return -1;
        }

        int cost = 0;

        for (Stationery stationery : stationeryRecords.get(name)) {
            cost += stationery.getPrice();
        }

        return cost;
    }

}
