package t01.atomicIsolation;

import t01.Transaction;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static t01.JsonInteractions.*;

public class Application {

    ArrayList<Account> accounts = new ArrayList<>();

    ArrayList<Transaction> transactions;


    public void prepare() {

        accounts.add(new Account(1, new AtomicInteger(10_000_000)));
        accounts.add(new Account(2, new AtomicInteger(10_000_000)));
        serialize();

        transactions = deserialize(JSON_PATH);
    }

    public Transaction getTransaction() {

        return transactions.remove(0);
    }

    public Account getAccount(int id) {
        for (Account account : accounts) {
            if (account.getId() == id)
                return account;
        }
        return null;
    }

}
