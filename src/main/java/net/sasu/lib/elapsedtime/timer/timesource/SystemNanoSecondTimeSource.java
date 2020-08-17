package net.sasu.lib.elapsedtime.timer.timesource;

import java.util.concurrent.TimeUnit;

/**
 * You should use this class only if you are aware of the advantages and
 * disadvantages of System.nanoTime() vs. System.currentTimeMillis(). For
 * example, System.nanoTime() is not reliable when used between threads,
 * and also in some other cases.
 * 
 * Furthermore, despite its name, it does not have nanosecond accuracy, only
 * precision.
 * 
 * @author Sasu
 *
 */
public class SystemNanoSecondTimeSource implements TimeSource{

	@Override
	public long getCurrentTime() {
		return System.nanoTime();
	}

	@Override
	public TimeUnit getDefaultTimeUnit() {
		return TimeUnit.NANOSECONDS;
	}
	
}
