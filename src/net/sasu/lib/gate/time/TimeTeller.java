package net.sasu.lib.gate.time;

import java.util.concurrent.TimeUnit;

/**
 * Outputs time for humans.
 * 
 * @author Sasu
 *
 */
public class TimeTeller {

	private TimeTeller() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Returns elapsed time as a human-readable string, e.g. "10 ms". If the elapsed
	 * time is at least one second, the time is returned always in seconds with
	 * three decimals, rounded down.
	 *
	 * @return
	 */
	public static String outputElapsedTime(long timeElapsedInTimeUnits, TimeUnit timeUnit) {
		String timeElapsed = "";
		long timeElapsedInNanoSeconds = timeUnit.convert(timeElapsedInTimeUnits, TimeUnit.NANOSECONDS);

		if (timeElapsedInNanoSeconds < 1000) {
			timeElapsed = createTimeString(timeElapsedInNanoSeconds, "nanosecond");
		} else if (timeElapsedInNanoSeconds < 1000000) {
			final long microSeconds = timeElapsedInNanoSeconds / 1000;
			timeElapsed = createTimeString(microSeconds, "microsecond");
		} else if (timeElapsedInNanoSeconds < 1000000000) {
			final long milliSeconds = timeElapsedInNanoSeconds / 1000000;
			timeElapsed = createTimeString(milliSeconds, "ms");
		}

		if (timeElapsedInNanoSeconds > 999999999) {
			long seconds = TimeUnit.SECONDS.convert(timeElapsedInNanoSeconds, TimeUnit.NANOSECONDS);
			long milliSeconds = TimeUnit.MILLISECONDS.convert(timeElapsedInNanoSeconds, TimeUnit.NANOSECONDS);
			String decimalPart = Long.toString(milliSeconds, 10).substring(Long.toString(seconds, 10).length())
					.substring(0, 3);
			timeElapsed = seconds + "." + decimalPart + " seconds";
		}

		return timeElapsed;
	}

	/**
	 * When given the number of time units (e.g. seconds) and the name of the time
	 * unit (e.g. "second") a human-readable time string is created and returned
	 * (e.g. "1 second" or "5 seconds").
	 * 
	 * @param timeUnits
	 * @param timeUnitName
	 * @return
	 */
	private static String createTimeString(long timeUnits, String timeUnitName) {
		String retVal = timeUnits + " " + timeUnitName;
		if (timeUnits != 1 && !timeUnitName.substring(timeUnitName.length() - 1).equals("s")) {
			return retVal + "s";
		}
		return retVal;
	}
}
