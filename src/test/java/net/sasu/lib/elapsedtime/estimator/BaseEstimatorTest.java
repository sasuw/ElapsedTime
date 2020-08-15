package net.sasu.lib.elapsedtime.estimator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.sasu.lib.elapsedtime.time.MockTimer;
import net.sasu.lib.elapsedtime.timer.Timer;
import net.sasu.lib.elapsedtime.util.ReflectionUtil;

class BaseEstimatorTest {

	MockEstimator mockEstimator;
	MockTimer mockTimer;

	class MockEstimator extends BaseEstimator {

		boolean initAndStartMethodCalled = false;

		@Override
		public BaseEstimator initAndStart(long remainingWorkUnitsArg) {
			initAndStartMethodCalled = true;
			this.initializeRemainingWorkUnits(remainingWorkUnitsArg);
			this.start();

			return this;
		}

		@Override
		public BaseEstimator initAndStart(long remainingWorkUnitsArg, Timer timer) {
			this.setTimer(timer);
			this.initializeRemainingWorkUnits(remainingWorkUnitsArg);
			return this;
		}

		@Override
		public long getRemainingTime(TimeUnit timeUnit) {
			return this.getRemainingWorkUnits();
		}

		public Timer getTimer() throws NoSuchFieldException, IllegalAccessException {
			return (Timer) ReflectionUtil.getPrivateFieldValue(this.getClass().getSuperclass(), this, "timer");
		}
	}

	/**
	 * Tests that the init method
	 *
	 * 1) returns a new instance of the BaseEstimator 2) calls the initAndStart
	 * method 3) sets the Timer object
	 *
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	@BeforeEach
	public void initTest() throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
		this.mockTimer = new MockTimer();
		this.mockEstimator = new MockEstimator();
	}

	@Test
	void setTimerTest() throws NoSuchFieldException, IllegalAccessException {
		this.mockEstimator.setTimer(this.mockTimer);
		assertEquals(this.mockTimer, this.mockEstimator.getTimer());
	}

	@Test
	void completeWorkUnitsExceptionIae() {
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			this.mockEstimator.completeWorkUnits(-1);
		});
		assertNotNull(exception);
	}

	@Test
	void completeWorkUnitsExceptionIse() {
		this.mockEstimator.initAndStart(10L);
		Throwable exception = assertThrows(IllegalStateException.class, () -> {
			this.mockEstimator.completeWorkUnits(11);
		});
		assertNotNull(exception);
	}

	@Test
	void getElapsedTimeAsStringTest() {
		this.mockTimer.start();
		this.mockTimer.increment();
		this.mockEstimator.setTimer(this.mockTimer);
		
		String elapsedTime = this.mockTimer.getElapsedTime();
		String elapsedTimeAsString = this.mockEstimator.getElapsedTimeAsString();
		
		assertEquals(elapsedTime, elapsedTimeAsString);
	}
	
	@Test
	void stopTest() {
		this.mockTimer.start();
		this.mockEstimator.setTimer(this.mockTimer);
		this.mockEstimator.stop();
		
		assertEquals(false, this.mockTimer.isStarted());
	}
	
	@Test
	void startTest() {
		this.mockEstimator.setTimer(this.mockTimer);
		this.mockEstimator.start();
		
		assertEquals(true, this.mockTimer.isStarted());
	}
	
	@Test
	void getRemainingTimeAsStringTest() {
		this.mockEstimator.initAndStart(5);
		assertEquals("5.000 seconds", this.mockEstimator.getRemainingTimeAsString());
	}
}
