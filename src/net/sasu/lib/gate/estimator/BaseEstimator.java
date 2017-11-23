/**
 * 
 */
package net.sasu.lib.gate.estimator;

import java.util.concurrent.TimeUnit;

/**
 * Base estimator for handling common tasks
 * 
 * @author Sasu
 *
 */
public abstract class BaseEstimator<T> implements Estimator<T> {

	private long remainingWorkUnits;
	private long elapsedTimeUnits = 0L;

	@Override
	public void completeWorkUnits(long workUnitsCompleted) {
		if (workUnitsCompleted < 0) {
			throw new IllegalArgumentException("workUnitsCompleted may not be negative");
		}
		
		this.remainingWorkUnits = this.remainingWorkUnits - workUnitsCompleted;
		if (this.remainingWorkUnits < 0) {
			throw new IllegalStateException(
					"More work than available completed. Remaining work units: " + this.remainingWorkUnits);
		}
	}

	@Override
	public long getRemainingTime(TimeUnit timeUnit) {
		return 0;
	}

}
