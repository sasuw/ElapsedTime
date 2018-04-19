package net.sasu.lib.elapsedtime.estimator;

import net.sasu.lib.elapsedtime.time.MockTimer;
import net.sasu.lib.elapsedtime.timer.Timer;
import net.sasu.lib.elapsedtime.util.ReflectionUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;


public class BaseEstimatorTest {

    MockEstimator mockEstimator;
    MockTimer mockTimer;

    class MockEstimator extends BaseEstimator{

        boolean initAndStartMethodCalled = false;

        @Override
        public BaseEstimator initAndStart(long remainingWorkUnits) {
            initAndStartMethodCalled = true;
            return this;
        }

        @Override
        public BaseEstimator initAndStart(long remainingWorkUnits, Timer timer) {
            fail("Should not be called");
            return null;
        }

        @Override
        public long getRemainingTime(TimeUnit timeUnit) {
            fail("Should not be called");
            return 0;
        }

        public Timer getTimer() throws NoSuchFieldException, IllegalAccessException {
            return (Timer) ReflectionUtil.getPrivateFieldValue(this.getClass().getSuperclass(), this, "timer");
        }
    }


    /**
     * Tests that the init method
     *
     * 1) returns a new instance of the BaseEstimator
     * 2) calls the initAndStart method
     * 3) sets the Timer object
     *
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @BeforeEach
    public void initTest() throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {

        this.mockTimer =  new MockTimer();
        this.mockEstimator = new MockEstimator();
    }

    @Test
    public void setTimerTest() throws NoSuchFieldException, IllegalAccessException {
        this.mockEstimator.setTimer(this.mockTimer);
        assertEquals(this.mockTimer, this.mockEstimator.getTimer());
    }
}
