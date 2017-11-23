package net.sasu.lib.gate.time;

import java.util.concurrent.TimeUnit;

/**
 * Measures passage of time. Typically used followingly:
 * 
 * 1) Create instance and start timer with Timer.getInstanceAndStart()
 * 2) Stop timer and get elapsed time as string with timer.getElapsedTimeAndStop()
 * 
 * @author Sasu
 *
 */
public class Timer {
	
	private long startTime; // in nanoseconds
	private long elapsedTime; // in nanoseconds

	public Timer() {
		super();
	}

	/**
	 * Creates a new Timer object instance and starts it.
	 *
	 * @return Timer
	 */
	public static Timer getInstanceAndStart() {
		Timer timer = new Timer();
		timer.start();
		return timer;
	}

	/**
	 * Starts the timer.
	 */
	private void start() {
		this.elapsedTime = 0;
		this.startTime = System.nanoTime();
	}

	/**
	 * Stops the timer.
	 */
	public void stop() {
		long endTime = System.nanoTime();
		this.elapsedTime = endTime - this.startTime;
	}
	
	/**
	 * Returns elapsed time in nanoseconds
	 * 
	 * @return
	 */
	public long getElapsedTimeRaw() {
		return this.elapsedTime;
	}

	/**
	 * Stops timer and returns elapsed time as a human-readable string, e.g. "10
	 * ms".
	 *
	 * @return
	 */
	public String getElapsedTimeAndStop() {
		this.stop();
		return this.getElapsedTime();
	}

	/**
	 * Returns elapsed time as a human-readable string, e.g. "10 ms". If the elapsed
	 * time is at least one second, the time is returned always in seconds with
	 * three decimals, rounded down.
	 * 
	 * @return
	 */
	public String getElapsedTime() {
		return getElapsedTime(this.elapsedTime);
	}

	/**
	 * Returns elapsed time as a human-readable string, e.g. "10 ms". If the elapsed
	 * time is at least one second, the time is returned always in seconds with
	 * three decimals, rounded down.
	 *
	 * @return
	 */
	private String getElapsedTime(long timeElapsedInNanoSecondsArg) {
		String timeElapsed = "";
		long timeElapsedInNanoSeconds = timeElapsedInNanoSecondsArg;

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
	private String createTimeString(long timeUnits, String timeUnitName) {
		String retVal = timeUnits + " " + timeUnitName;
		if (timeUnits != 1 && !timeUnitName.substring(timeUnitName.length() - 1).equals("s")) {
			return retVal + "s";
		}
		return retVal;
	}

}
