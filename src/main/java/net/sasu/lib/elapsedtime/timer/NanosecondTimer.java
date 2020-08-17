package net.sasu.lib.elapsedtime.timer;

import net.sasu.lib.elapsedtime.timer.timesource.SystemNanoSecondTimeSource;
import net.sasu.lib.elapsedtime.timer.timesource.TimeSource;

/**
 * You should use this class only if you are aware of the advantages and
 * disadvantages of System.nanoTime() vs. System.currentTimeMillis(). For
 * example, System.nanoTime() is not reliable when used between threads,
 * and also in some other cases.
 * 
 * @author Sasu
 *
 */
public class NanosecondTimer extends DefaultTimer {

	public NanosecondTimer() {
		super(new SystemNanoSecondTimeSource());
	}

	@SuppressWarnings("unused")
	private NanosecondTimer(TimeSource timeSource) {
		super(timeSource);
		throw new IllegalStateException("Do not use");
	}
	
}
