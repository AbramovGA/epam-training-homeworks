package t03;

import static org.apache.commons.math3.util.Precision.round;

public class PlotFunction {

    private static double function(double argument) {
        return Math.tan(2 * argument) - 3;
    }

    private static void plot(double lBound, double rBound, double step) {

        double arg = lBound;
        double func = function(arg);

        do {
            System.out.println(round(arg, 3) + "\t\t" + round(func, 3));
            arg += step;
            func = function(arg);
        } while (arg < rBound);

        System.out.println(round(rBound, 3) + "\t\t" + round(function(rBound), 3));

    }

    public static void main(String[] args) {
        try {
            plot(Double.valueOf(args[0]), Double.valueOf(args[1]), Double.valueOf(args[2]));
        } catch (NumberFormatException e) {
            System.out.println("Parameters should be of type 'double'");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Run program with 3 parameters.");
        }
    }

}