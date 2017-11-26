package net.sasu.lib.gate.estimator;

import java.util.concurrent.TimeUnit;

/**
 * Estimator performing the concrete estimation, either general or optimized
 * for certain types of inputs or desired outputs.
 * 
 * @author Sasu
 *
 * @param <T>
 */
public interface Estimator<T> {
    
    /**
     * Initializes Estimator instance and returns a concrete instance.
     * 
     * @param remainingWorkUnits
     * @return
     */
    public T init(long remainingWorkUnits);
    
    /**
     * Starts estimator
     */
    public void start();
    
    /**
     * Completes given amount of work units.
     * 
     * @param workUnitsCompleted
     */
    public void completeWorkUnits(long workUnitsCompleted);
    
    /**
     * Returns elapsed time in given time units
     * 
     * @param timeUnit
     * @return
     */
    public long getElapsedTime(TimeUnit timeUnit);
    
    /**
     * Returns remaining time in given time units
     * 
     * @param timeUnit
     * @return
     */
    public long getRemainingTime(TimeUnit timeUnit);
    
    /**
     * Returns remaining time as a human-readable string
     * 
     * @return
     */
    public String getRemainingTimeAsString();
    
    /**
     * Returns elapsed time as a human-readable string
     * 
     * @return
     */
    public String getElapsedTimeAsString();
    
    /**
     * Stops estimator
     */
    public void stop();
}
