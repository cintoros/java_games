package old.games_fx.GameLogic;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.fail;

public class TimeCalculationTest {

    TimeCalculation instance = new TimeCalculation();

    @Test
    @Ignore
    public void testStart() {
        instance.start();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        instance.stop();
        if (instance.getTimeSec() == 0) {
            fail("same time");
        }
    }

    @Test
    public void testStop() {
        instance.start();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        instance.stop();
        int a = instance.getTimeSec();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        if (instance.getTimeSec() != a) {
            fail("not the same time");
        }
    }

    @Test
    public void testReset() {
        instance.start();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        instance.stop();
        instance.reset();
        if (instance.getTimeSec() != 0) {
            fail("not 0");
        }
    }

    /**
     * Test of getTimeSec method, of class TimeCalculation.
     */
    @Test
    public void testGetTimeSec() {
        instance.reset();
        if (instance.getTimeSec() != 0) {
            fail("not 0");
        }
    }

    /**
     * Test of setTime method, of class TimeCalculation.
     */
    @Test
    public void testSetTime() {
        int s = 162000;
        instance.setTime(s);
        if (instance.getTimeSec() != s) {
            fail("not 0");
        }
    }

    /**
     * Test of setTime method, of class TimeCalculation.
     */
    @Test
    public void testGetTime() {
        int s = 2;
        instance.setTime(s);
        if (!instance.getTime()
                .equals("Zeit: 0:0:2")) {
            fail("not 2 sec");
        }
    }
}
