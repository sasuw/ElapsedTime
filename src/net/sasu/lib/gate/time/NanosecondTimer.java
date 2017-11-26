package net.sasu.lib.gate.time;

import java.util.concurrent.TimeUnit;

/**
 * Measures passage of time. Typically used followingly:
 * 
 * 1) Create instance and start timer with NanosecondTimer.getInstanceAndStart()
 * 2) Stop timer and get elapsed time as string with timer.getElapsedTimeAndStop()
 * 
 * @author Sasu
 *
 */
public class NanosecondTimer implements Timer<NanosecondTimer> {
	
	private long startTime; // in nanoseconds
	private long elapsedTime; // in nanoseconds

	public NanosecondTimer() {
		super();
	}

	/**
	 * Starts the timer.
	 */
	@Override
	public void start() {
		this.elapsedTime = 0;
		this.startTime = System.nanoTime();
	}

	/**
	 * Stops the timer.
	 */
	@Override
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
	@Override
	public String getElapsedTime() {
		return TimeTeller.outputElapsedTime(this.elapsedTime, TimeUnit.NANOSECONDS);
	}

}
