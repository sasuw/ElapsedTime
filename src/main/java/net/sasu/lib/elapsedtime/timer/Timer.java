package net.sasu.lib.elapsedtime.timer;

/**
 * Timer for measuring elapsed time and outputting it in a human-readable format
 */
public interface Timer {

    /**
     * Starts timer
     */
    public abstract void start();

    /**
     * Stops timer
     */
    public abstract void stop();

    /**
     * @return elapsed time in the base time units defined by this timer implementation
     */
    public abstract long getElapsedTimeRaw();

    /**
     * @return Elapsed time with the default TimePrinter implementation of this Timer
     */
    public abstract String getElapsedTime();
    
}
