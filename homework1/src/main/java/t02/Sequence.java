package t02;

public class Sequence {

    private static int elementsUnderEpsilon(double epsilon) {

        int i = 1;
        double currElem = 1. / Math.pow(i + 1, 2);

        while (currElem > epsilon) {
            System.out.println(currElem);
            i++;
            currElem = 1. / Math.pow(i + 1, 2);
        }

        System.out.println(currElem);
        System.out.println("Element number: " + i);

        return i;
    }

    public static void main(String[] args) {
        try {
            elementsUnderEpsilon(Double.valueOf(args[0]));
        } catch (NumberFormatException e) {
            System.out.println("Parameter should be of type 'double'");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Run program with parameter.");
        }
    }
}
