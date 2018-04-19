package net.sasu.lib.elapsedtime.timer;

import java.util.concurrent.TimeUnit;

import net.sasu.lib.elapsedtime.timeteller.VerboseTimeTeller;

/**
 * Timer for measuring elapsed time and outputting it in a human-readable format
 */
public interface Timer {

    /**
     * Starts timer
     */
    public void start();

    /**
     * Stops timer
     */
    public void stop();

    /**
     * @return elapsed time in the base time units defined by this timer implementation
     */
    public long getElapsedTimeRaw();

    /**
     * @return Elapsed time with the default TimeTeller implementation of this Timer
     */
    public String getElapsedTime();
}
