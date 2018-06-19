package t03;

public abstract class Writing extends Stationery {

    public Writing(int price, String name) {
        super(price, name);
    }

    void write(Writable writable, String text) {
        writable.read(text);
    }
}
