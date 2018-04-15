package net.sasu.lib.elapsedtime.timeteller;

import net.sasu.lib.elapsedtime.time.ElapsedTime;

import java.util.SortedMap;
import java.util.concurrent.TimeUnit;

/**
 * Outputs time for humans in a simple, numeric way in
 * format hh:mm:ss.mmm
 * 
 * @author Sasu
 *
 */
public class SimpleTimeTeller implements TimeTeller {

	private static final String DEFAULT_SEPARATOR = ":";

	/* (non-Javadoc)
	 * @see net.sasu.lib.timeteller.TimeTeller#outputElapsedTime(long, java.util.concurrent.TimeUnit)
	 */
	@Override
	public String outputElapsedTime(long timeElapsedInTimeUnits, TimeUnit timeUnit) {
		ElapsedTime elapsedTime = new ElapsedTime(timeElapsedInTimeUnits, timeUnit);
		SortedMap<TimeUnit, Long> nonZeroTimeValues = elapsedTime.getCommonZeroTimeValues();

		return nonZeroTimeValues.get(TimeUnit.HOURS) + DEFAULT_SEPARATOR 
				+ nonZeroTimeValues.get(TimeUnit.MINUTES) + DEFAULT_SEPARATOR 
				+ nonZeroTimeValues.get(TimeUnit.SECONDS) + "."
				+ nonZeroTimeValues.get(TimeUnit.MILLISECONDS) + DEFAULT_SEPARATOR;
	}

}
