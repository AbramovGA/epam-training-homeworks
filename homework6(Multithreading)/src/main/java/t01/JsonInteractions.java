package t01;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public interface JsonInteractions {

    String JSON_PATH = "/home/germanium/IdeaProjects/epam-training-homeworks/homework6(Multithreading)/src/test/resources/transactions.json";

    @SneakyThrows
    static ArrayList<Transaction> deserialize(String filepath) {

        ArrayList<Transaction> transactions;
        var objmapper = new ObjectMapper();

        transactions = objmapper.readValue(new File(filepath),
                new TypeReference<ArrayList<Transaction>>() {
                });


        return transactions;
    }

    @SneakyThrows
    static void serialize() {
        var transactions = new ArrayList<Transaction>();

        Random rand = new Random();
        int amount, sender, reciever;

        for (int i = 0; i < 100000; i++) {

            amount = rand.nextInt(100);
            if (amount % 2 == 0) {
                sender = 1;
                reciever = 2;
            } else {
                sender = 2;
                reciever = 1;
            }

            transactions.add(new Transaction(sender, reciever, amount));
        }

        var objectMapper = new ObjectMapper();

        objectMapper.writeValue(new File(JSON_PATH), transactions);

    }

}
