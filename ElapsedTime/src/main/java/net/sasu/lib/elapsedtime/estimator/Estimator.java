package net.sasu.lib.elapsedtime.estimator;

import java.util.concurrent.TimeUnit;

import net.sasu.lib.elapsedtime.timer.Timer;

/**
 * Estimator performing the concrete estimation, either general or optimized
 * for certain types of inputs or desired outputs.
 * 
 * @author Sasu
 *
 * @param <T> Estimator type
 */
public interface Estimator<T extends Estimator<T>> {
    
    /**
     * Initializes Estimator instance and returns a concrete instance.
     * 
     * @param remainingWorkUnits number of total work units
     * @return instance of Estimator
     */
    public T initAndStart(long remainingWorkUnits);

    /**
     * Initializes Estimator instance and returns a concrete instance.
     *
     * @param remainingWorkUnits number of total work units
     * @param timer instance of timer to be used
     * @return instance of Estimator
     */
    public T initAndStart(long remainingWorkUnits, Timer timer);
    
    /**
     * Starts estimator
     */
    public void start();
    
    /**
     * Completes given amount of work units.
     * 
     * @param workUnitsCompleted number of completed work units
     */
    public void completeWorkUnits(long workUnitsCompleted);
    
    /**
     * @param timeUnit TimeUnit of elapsed time to return
     * @return returns elapsed time in given time units
     */
    public long getElapsedTime(TimeUnit timeUnit);
    
    /**
     *
     * 
     * @param timeUnit TimeUnit of elapsed time to return
     * @return remaining time in given time units. If time cannot be calculated -1 is returned.
     */
    public long getRemainingTime(TimeUnit timeUnit);
    
    /**
     * @return remaining time as a human-readable string
     */
    public String getRemainingTimeAsString();
    
    /**
     * @return elapsed time as a human-readable string
     */
    public String getElapsedTimeAsString();
    
    /**
     * Stops estimator
     */
    public void stop();

    /**
     * Sets timer, when the standard timer should be replaced
     *
     * @param timer instance of timer to be used
     */
    public void setTimer(Timer timer);
}
