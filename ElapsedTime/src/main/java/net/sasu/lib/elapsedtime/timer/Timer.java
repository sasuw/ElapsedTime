package net.sasu.lib.elapsedtime.timer;

import java.util.concurrent.TimeUnit;

import net.sasu.lib.elapsedtime.timeteller.VerboseTimeTeller;

/**
 * Timer for measuring elapsed time and outputting it in a human-readable format
 */
public interface Timer {

    public void start();

    public void stop();

    public long getElapsedTimeRaw();

    /**
     * Returns elapsed time as a human-readable string, e.g. "10 ms". If the elapsed
     * time is at least one second, the time is returned always in seconds with
     * three decimals, rounded down.
     *
     * @return
     */
    default String getElapsedTime() {
        return new VerboseTimeTeller().outputElapsedTime(this.getElapsedTimeRaw(), TimeUnit.NANOSECONDS);
    }
}
