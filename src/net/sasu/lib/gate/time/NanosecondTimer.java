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
public class NanosecondTimer implements Timer {
    
    private long startTime; // in nanoseconds
    private long elapsedTime; // in nanoseconds
    private boolean isRunning = false;

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
        this.isRunning = true;
    }

    /**
     * Stops the timer.
     */
    @Override
    public void stop() {
        this.elapsedTime = System.nanoTime() - this.startTime;
        this.isRunning = false;
    }
    
    /**
     * Returns elapsed time in nanoseconds
     *
     * @return
     */
    public long getElapsedTimeRaw() {
        if(this.isRunning){
            return System.nanoTime() - this.startTime;
        }
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



}
