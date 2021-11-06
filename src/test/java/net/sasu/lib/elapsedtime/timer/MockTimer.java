package net.sasu.lib.elapsedtime.timer;

import java.util.concurrent.TimeUnit;

import net.sasu.lib.elapsedtime.timeprinter.VerboseTimePrinter;

/**
 * Mock timer for time-independent unit tests.
 */
public class MockTimer implements Timer {

    private long startTime; // in nanoseconds
    private long elapsedTime; // in nanoseconds
    private Boolean isStarted;

    @Override
    public void start() {
        this.startTime = 0L;
        this.elapsedTime = 0L;
        this.isStarted = true;
    }

    @Override
    public void stop() {
        this.isStarted = false;
    }

    @Override
    public String getElapsedTime() {
        return new VerboseTimePrinter().outputElapsedTime(this.elapsedTime, TimeUnit.NANOSECONDS);
    }

    @Override
    public long getElapsedTimeRaw() {
        return this.elapsedTime;
    }

    public void increment(){
        this.elapsedTime++;
    }

	public long getStartTime() {
		return startTime;
	}

	public Boolean getIsStarted() {
		return isStarted;
	}

}
