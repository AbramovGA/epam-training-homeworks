package t01.synchronizedIsolation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    final Object obj = new Object();
    int id;
    private int balance;

    public boolean withdraw(final int amount) {
        if (amount > 0 && amount < balance) {
            synchronized (obj) {
                balance -= amount;
            }
            return true;
        }
        return false;
    }

    public boolean deposit(final int amount) {
        if (amount > 0) {
            synchronized (obj) {
                balance += amount;
            }
            return true;
        }
        return false;
    }

}
