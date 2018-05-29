package t05;

public class Matrix {

    private static int[][] getMatrix(int size) {

        var matrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            matrix[i][i] = 1;
            matrix[size - 1 - i][i] = 1;
        }

        return matrix;
    }

    public static void main(String[] args) {
        try {
            var matrix = getMatrix(Integer.parseInt(args[0]));

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++)
                    System.out.print(matrix[i][j]);
                System.out.println();
            }

        } catch (NumberFormatException e) {
            System.out.println("Parameter should be of type 'int'");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Run program with parameter.");
        }
    }
}
