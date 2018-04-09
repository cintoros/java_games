package shared;

public final class ArrayUtil {

    private ArrayUtil() {}

    public static boolean[][] deepCopyBooleanMatrix(boolean[][] input) {
        boolean[][] result = new boolean[input.length][];
        for (int r = 0; r < input.length; r++) {
            result[r] = input[r].clone();
        }
        return result;
    }

    public static int[][] deepCopyIntMatrix(int[][] input) {
        int[][] result = new int[input.length][];
        for (int r = 0; r < input.length; r++) {
            result[r] = input[r].clone();
        }
        return result;
    }

    public static <T> T[][] deepCopyMatrix(T[][] input) {
        input = input.clone();
        for (int r = 0; r < input.length; r++) {
            input[r] = input[r].clone();
        }
        return input;
    }

}
