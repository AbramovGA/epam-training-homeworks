package t01.atomicIsolation;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransactionPerformer implements Runnable {

    Account sender;
    Account reciever;
    int summ;


    public void setData(Account sender, Account reciever, int summ) {
        setSender(sender);
        setReciever(reciever);
        setSumm(summ);
    }

    @Override
    public void run() {
        boolean withdrawSuccess = sender.withdraw(summ);
        if (withdrawSuccess) {
            reciever.deposit(summ);
        }
    }
}
