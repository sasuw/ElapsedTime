package net.sasu.lib.elapsedtime.timeprinter;

import java.util.concurrent.TimeUnit;

/**
 * Outputs time.
 * 
 * @author Sasu
 */
public interface TimePrinter {

	/**
	 * Returns given elapsed time as a string.
	 * 
	 * @param timeElapsedInTimeUnits Elapsed time in given TimeUnit type
	 * @param timeUnit TimeUnit type of elapsed time to output
	 * @return elapsed time as string
	 */
	public String outputElapsedTime(long timeElapsedInTimeUnits, TimeUnit timeUnit);
	
}
