package old.maze_fx.def.gameLogic;

import java.util.Arrays;
import org.junit.Test;
import shared.ArrayUtil;

import static org.junit.Assert.assertTrue;

public class ArrayUtilTest {

    @Test
    public void testDeepCopyMatrix() {
        Integer[][] original = {{1, 1}, {2, 2}};
        System.out.println(Arrays.toString(original));
        Integer[][] copy = ArrayUtil.deepCopyMatrix(original);
        copy[1][1] = 500;
        original[0][0] = 515;

        Integer[][] expectedOriginal = {{515, 1}, {2, 2}};
        assertTrue(Arrays.deepEquals(expectedOriginal, original));
        Integer[][] expectedCopy = {{1, 1}, {2, 500}};
        assertTrue(Arrays.deepEquals(expectedCopy, copy));
    }

    @Test
    public void testDeepCopyIntMatrix() {
        int[][] original = {{1, 1}, {2, 2}};
        System.out.println(Arrays.toString(original));
        int[][] copy = ArrayUtil.deepCopyIntMatrix(original);
        copy[1][1] = 500;
        original[0][0] = 515;

        int[][] expectedOriginal = {{515, 1}, {2, 2}};
        assertTrue(Arrays.deepEquals(expectedOriginal, original));
        int[][] expectedCopy = {{1, 1}, {2, 500}};
        assertTrue(Arrays.deepEquals(expectedCopy, copy));
    }
}