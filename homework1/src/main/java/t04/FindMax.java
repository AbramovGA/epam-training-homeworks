package t04;

public class FindMax {

    private static double getMax(Double[] initValues) {

        var maxValue = initValues[0] + initValues[initValues.length - 1];

        for (int i = 1; i < initValues.length / 2; i++) {
            maxValue = Math.max(maxValue, initValues[i] + initValues[(initValues.length - 1) - i]);
        }

        System.out.println(maxValue);

        return maxValue;
    }

    public static void main(String[] args) {


        try {
            assert (args.length % 2 == 0);
            var initValues = new Double[args.length];
            for (int i = 0; i < args.length; i++) {
                initValues[i] = Double.parseDouble(args[i]);
            }
            getMax(initValues);
        } catch (NumberFormatException e) {
            System.out.println("Parameters should be of type 'double'.");
        } catch (AssertionError e) {
            System.out.println("Parameters number should be even.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Run program with parameters.");
        }
    }

}
