package t01.atomicIsolation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    int id;
    private AtomicInteger balance;

    public boolean withdraw(final int amount) {
        if (amount > 0 && amount < balance.get()) {
            balance.getAndAdd(-amount);
            return true;
        }
        return false;
    }

    public boolean deposit(final int amount) {
        if (amount > 0) {
            balance.getAndAdd(amount);
            return true;
        }
        return false;
    }

}
