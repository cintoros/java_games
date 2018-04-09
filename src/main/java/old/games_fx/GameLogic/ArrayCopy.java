package old.games_fx.GameLogic;

/**
 * class responsible for copying of simple object Arrays how int String,boolean;
 *
 * @author Elias
 */
public final class ArrayCopy {

    /**
     * copys an 2D array of the type int
     *
     * @param array[][] the array that should be cloned
     * @return returns a copy of the Array.
     * @author Elias Schorr, BBBaden
     * @since 05.10.2014
     * @version 1.0
     */
    public static int[][] clone2DArray(int[][] array) {
        int[][] clone = new int[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            System.arraycopy(array[i], 0, clone[i], 0, array[i].length);
        }
        return clone;
    }

    /**
     * copys an 2D array of the type boolean
     *
     * @param array[][] the array that should be cloned
     * @return returns a copy of the Array.
     */
    public static boolean[][] clone2DArrayBoolean(boolean[][] array) {
        boolean[][] clone = new boolean[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            System.arraycopy(array[i], 0, clone[i], 0, array[i].length);
        }
        return clone;
    }

    /**
     * copys an 2D array of the type String
     *
     * @param array[][] the array that should be cloned
     * @return returns a copy of the Array.
     */
    public static String[][] clone2DArrayBoolean(String[][] array) {
        String[][] clone = new String[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            System.arraycopy(array[i], 0, clone[i], 0, array[i].length);
        }
        return clone;
    }
}
