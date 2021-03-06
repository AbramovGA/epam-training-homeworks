package t01.synchronizedIsolation;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import t01.Transaction;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class ApplicationTest {

    @Test
    @SneakyThrows
    public void synchornizedIsolationTest() {

        var manager = new Application();
        manager.prepare();
        Account sender;
        Account reciever;

        for (Transaction transaction : manager.transactions) {
            sender = manager.getAccount(transaction.getSenderID());
            reciever = manager.getAccount(transaction.getRecieverID());
            var performer = new TransactionPerformer();
            performer.setData(sender, reciever, transaction.getSumm());
            new Thread(performer).start();

        }
        Thread.sleep(5000);
        System.out.printf("%s | %s%n", manager.accounts.get(0), manager.accounts.get(1));


        assertThat(manager.accounts.get(0).getBalance() +
                manager.accounts.get(1).getBalance(), is(20_000_000));

    }

}