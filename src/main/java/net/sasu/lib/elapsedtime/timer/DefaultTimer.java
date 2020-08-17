package net.sasu.lib.elapsedtime.timer;

import net.sasu.lib.elapsedtime.timer.timesource.SystemMilliSecondTimeSource;
import net.sasu.lib.elapsedtime.timer.timesource.TimeSource;
import net.sasu.lib.elapsedtime.timeteller.VerboseTimePrinter;

/**
 * Measures passage of time using the given time source, or if none
 * given, SystemMilliSecondTimeSource as default. 
 * 
 * Typically used followingly:
 * 
 * 1) Create instance and start timer with DefaultTimer.getInstanceAndStart()
 * 2) Stop timer and get elapsed time as string with
 * timer.getElapsedTimeAndStop()
 *  
 * @author Sasu
 *
 */
public class DefaultTimer implements Timer {

	private long startTime;
	private long elapsedTime;
	private boolean isRunning = false;
	private TimeSource timeSource;

	public DefaultTimer() {
		super();
		this.timeSource = new SystemMilliSecondTimeSource();
	}
	
	public DefaultTimer(TimeSource timeSource) {
		super();
		this.timeSource = timeSource;
	}

	/**
	 * Starts the timer.
	 */
	@Override
	public void start() {
		this.elapsedTime = 0;
		this.startTime = this.timeSource.getCurrentTime();
		this.isRunning = true;
	}

	/**
	 * Stops the timer.
	 */
	@Override
	public void stop() {
		if (!this.isRunning) {
			return;
		}
		this.elapsedTime = this.timeSource.getCurrentTime() - this.startTime;
		this.isRunning = false;
	}

	/**
	 * @return elapsed time in the time unit of the underlying time source
	 */
	public long getElapsedTimeRaw() {
		if (this.isRunning) {
			return this.timeSource.getCurrentTime() - this.startTime;
		}
		return this.elapsedTime;
	}

	/**
	 * Stops timer and returns elapsed time as a human-readable string
	 *
	 * @return elapsed time as a human-readable string, e.g. "10ms".
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
	 * @return elapsed time as a human-readable string, e.g. "10 ms"
	 */
	public String getElapsedTime() {
		long elapsedTimeRaw = this.getElapsedTimeRaw();
		return new VerboseTimePrinter().outputElapsedTime(elapsedTimeRaw, this.timeSource.getDefaultTimeUnit());
	}

}
