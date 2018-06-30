package t04;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

@NoArgsConstructor
@Data
public class MovieCollection implements SavingLoading, Serializable {

    HashMap<String, ArrayList<String>> collection = new HashMap<>();

    public static void main(String[] args) {
        var movieCollection = new MovieCollection();
        movieCollection.collection.put("OIOINOI", new ArrayList<>());

        movieCollection.save(movieCollection, "/home/germanium/IdeaProjects/epam-training-homeworks/homework4(IO)/src/main/resources/output.txt");

        var newCollection = (MovieCollection) movieCollection.load("/home/germanium/IdeaProjects/epam-training-homeworks/homework4(IO)/src/main/resources/output.txt");

        System.out.println(newCollection);
    }

}
