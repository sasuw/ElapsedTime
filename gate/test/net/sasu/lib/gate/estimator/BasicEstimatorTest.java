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
        final long totalWorkUnits = 10;

        be.initAndStart(totalWorkUnits);
        be.setTimer(mockTimer);

        final TimeUnit timeUnitNs = TimeUnit.NANOSECONDS;

        Assertions.assertEquals(0, be.getElapsedTime(timeUnitNs));
        Assertions.assertEquals(-1 , be.getRemainingTime(timeUnitNs));

        for(int i = 1; i < totalWorkUnits; i++) {
            mockTimer.increment();
            be.completeWorkUnits(1);
            Assertions.assertEquals(i, be.getElapsedTime(timeUnitNs));
            Assertions.assertEquals(totalWorkUnits - i, be.getRemainingTime(timeUnitNs));
        }

        mockTimer.increment();
        be.completeWorkUnits(1);

        Assertions.assertEquals(totalWorkUnits, be.getElapsedTime(timeUnitNs));
        Assertions.assertEquals(0 , be.getRemainingTime(timeUnitNs));
    }
}
