package t03;


abstract class Writable extends Stationery {

    private StringBuffer paper;

    Writable(int price, String name, StringBuffer paper) {
        super(price, name);
        this.paper = paper;
    }

    void read(String text) {
        paper.append(text);
    }
}
