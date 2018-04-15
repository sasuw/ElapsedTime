package net.sasu.lib.elapsedtime.estimator;

import java.util.concurrent.TimeUnit;

/**
 * Basic implementation for estimations with low variability.
 * 
 * @author Sasu
 *
 */
public class BasicEstimator extends BaseEstimator {

	public static BasicEstimator createInstanceAndStart(long remainingWorkUnitsArg) {
		return new BasicEstimator().initAndStart(remainingWorkUnitsArg);
	}
	
    @Override
    public BasicEstimator initAndStart(long remainingWorkUnitsArg) {
        this.initializeRemainingWorkUnits(remainingWorkUnitsArg);
        this.start();
        return this;
    }

    @Override
    public long getRemainingTime(TimeUnit timeUnit) {
        final long remainingWorkUnits = this.getRemainingWorkUnits();
        final long totalWorkUnits = this.getTotalWorkUnits();

        final long completedWorkUnits = totalWorkUnits - remainingWorkUnits;
        if (completedWorkUnits == 0) {
            return -1;
        }
        if (completedWorkUnits == totalWorkUnits) {
            return 0;
        }

        final long elapsedTime = this.getElapsedTime(timeUnit);

        return remainingWorkUnits * elapsedTime / completedWorkUnits;
    }

}
