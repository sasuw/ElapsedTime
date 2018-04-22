package net.sasu.lib.elapsedtime.timer;

import net.sasu.lib.elapsedtime.util.ReflectionUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NanoSecondTimerTest {

    @Test
    public void stopTest1() throws InterruptedException {
        NanosecondTimer nst = new NanosecondTimer();
        nst.stop();

        assertEquals(0L, nst.getElapsedTimeRaw());
    }

    @Test
    public void stopTest2() throws InterruptedException {
        NanosecondTimer nst = new NanosecondTimer();
        nst.start();

        nst.stop();
        long elapsedTimeRaw1 = nst.getElapsedTimeRaw();

        Thread.sleep(500);
        long elapsedTimeRaw2 = nst.getElapsedTimeRaw();
        assertEquals(elapsedTimeRaw1, elapsedTimeRaw2);

        nst.start();
        Thread.sleep(500);
        long elapsedTimeRaw3 = nst.getElapsedTimeRaw();
        assertNotEquals(elapsedTimeRaw3, elapsedTimeRaw2);
    }

    @Test
    public void getElapsedTimeRawTest() throws InterruptedException {
        NanosecondTimer nst = new NanosecondTimer();
        long elapsedTimeRaw0 = nst.getElapsedTimeRaw();
        assertEquals(0L, elapsedTimeRaw0);

        nst.start();
        Thread.sleep(500);
        long elapsedTimeRaw1 = nst.getElapsedTimeRaw();
        assertTrue(elapsedTimeRaw1 > 450000000 && elapsedTimeRaw1 < 550000000, "elapsedTimeRaw1: " + elapsedTimeRaw1);

        Thread.sleep(500);
        nst.stop();
        long elapsedTimeRaw2 = nst.getElapsedTimeRaw();
        assertTrue(elapsedTimeRaw2 > 900000000 && elapsedTimeRaw2 < 1100000000);

        long elapsedTimeRaw3 = nst.getElapsedTimeRaw();
        assertEquals(elapsedTimeRaw2, elapsedTimeRaw3);
    }

    @Test
    public void getElapsedTimeAndStopTest() throws InterruptedException {
        NanosecondTimer nst = new NanosecondTimer();
        nst.start();
        Thread.sleep(500);
        String elapsedTime = nst.getElapsedTimeAndStop();
        String elapsedTime2 = nst.getElapsedTime();
        assertEquals(elapsedTime2, elapsedTime);

        Thread.sleep(500);
        String elapsedTime3 = nst.getElapsedTimeAndStop();
        assertEquals(elapsedTime3, elapsedTime);
    }
}
