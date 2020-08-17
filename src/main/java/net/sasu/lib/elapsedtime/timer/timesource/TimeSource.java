package net.sasu.lib.elapsedtime.timer.timesource;

import java.util.concurrent.TimeUnit;

/**
 * Provides a time source for Timer
 * 
 * @author Sasu
 *
 */
public interface TimeSource {

	/**
	 * Returns the current time according to the 
	 * underlying time source.
	 * @return
	 */
	public long getCurrentTime();
	
	/**
	 * Returns the time unit, on which the underlying
	 * time source is based.
	 * 
	 * @return
	 */
	public TimeUnit getDefaultTimeUnit();
	
}
