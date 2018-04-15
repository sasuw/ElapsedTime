package net.sasu.lib.elapsedtime.timeteller;

import java.util.concurrent.TimeUnit;

/**
 * Outputs time.
 * 
 * @author Sasu
 */
public interface TimeTeller {

	/**
	 * Returns given elapsed time as a string.
	 * 
	 * @param timeElapsedInTimeUnits
	 * @param timeUnit
	 * @return
	 */
	public String outputElapsedTime(long timeElapsedInTimeUnits, TimeUnit timeUnit);
	
}
