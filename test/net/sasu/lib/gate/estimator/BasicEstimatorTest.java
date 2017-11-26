package net.sasu.lib.gate.estimator;

import net.sasu.lib.gate.time.MockTimer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * Tests BasicEstimator
 */
public class BasicEstimatorTest {

    @Test
    public void getRemainingTimeTest(){
        BasicEstimator be = new BasicEstimator();
        final MockTimer mockTimer = new MockTimer();

        be.init(10);
        be.setTimer(mockTimer);

        Assertions.assertEquals(0, be.getElapsedTime(TimeUnit.NANOSECONDS));
        Assertions.assertEquals(-1 , be.getRemainingTime(TimeUnit.NANOSECONDS));

        mockTimer.increment();
        be.completeWorkUnits(1);
        Assertions.assertEquals(1, be.getElapsedTime(TimeUnit.NANOSECONDS));
        Assertions.assertEquals(9 , be.getRemainingTime(TimeUnit.NANOSECONDS));
    }
}
