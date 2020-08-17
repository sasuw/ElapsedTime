package net.sasu.lib.elapsedtime.timer.timesource;

import java.util.concurrent.TimeUnit;

/**
 * TimeSource using System.currentTimeMillis()
 * This is a reasonable default for measuring time.
 * 
 * @author Sasu
 *
 */
public class SystemMilliSecondTimeSource implements TimeSource{

	@Override
	public long getCurrentTime() {
		return System.currentTimeMillis();
	}

	@Override
	public TimeUnit getDefaultTimeUnit() {
		return TimeUnit.MILLISECONDS;
	}
	
}
