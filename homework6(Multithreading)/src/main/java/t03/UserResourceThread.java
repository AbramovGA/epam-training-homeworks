package t03;

public class UserResourceThread {
    public static void main(String[] args) throws InterruptedException {
        SharedResource res = new SharedResource();
        IntegerSetterGetter t1 = new IntegerSetterGetter("1", res);
        IntegerSetterGetter t2 = new IntegerSetterGetter("2", res);
        IntegerSetterGetter t3 = new IntegerSetterGetter("3", res);
        IntegerSetterGetter t4 = new IntegerSetterGetter("4", res);
        IntegerSetterGetter t5 = new IntegerSetterGetter("5", res);
        IntegerSetterGetter t6 = new IntegerSetterGetter("6", res);
        IntegerSetterGetter t7 = new IntegerSetterGetter("7", res);
        IntegerSetterGetter t8 = new IntegerSetterGetter("8", res);
        IntegerSetterGetter t9 = new IntegerSetterGetter("9", res);
        IntegerSetterGetter t10 = new IntegerSetterGetter("10", res);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
        Thread.sleep(100);
        t1.stopThread();
        t2.stopThread();
        t3.stopThread();
        t4.stopThread();
        t5.stopThread();
        t6.stopThread();
        t7.stopThread();
        t8.stopThread();
        t9.stopThread();
        t10.stopThread();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        t6.join();
        t7.join();
        t8.join();
        t9.join();
        t10.join();
        System.out.println("main");
    }
}