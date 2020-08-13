package net.sasu.lib.elapsedtime.timer;

import net.sasu.lib.elapsedtime.timeteller.VerboseTimeTeller;

import java.util.concurrent.TimeUnit;

/**
 * Measures passage of time using System.nanoTime(). Typically used followingly:
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
        if(!this.isRunning){
            return;
        }
        this.elapsedTime = System.nanoTime() - this.startTime;
        this.isRunning = false;
    }
    
    /**
     * @return elapsed time in nanoseconds
     */
    public long getElapsedTimeRaw() {
        if(this.isRunning){
            return System.nanoTime() - this.startTime;
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
        return new VerboseTimeTeller().outputElapsedTime(this.getElapsedTimeRaw(), TimeUnit.NANOSECONDS);
    }

}
