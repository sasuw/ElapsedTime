package net.sasu.lib.gate.estimator;

import java.util.concurrent.TimeUnit;

import net.sasu.lib.gate.time.TimeTeller;
import net.sasu.lib.gate.time.Timer;

/**
 * Basic implementation for estimations with low variability.
 * 
 * @author Sasu
 *
 */
public class BasicEstimator extends BaseEstimator {

    @Override
    public BasicEstimator init(long remainingWorkUnitsArg) {
        this.initializeRemainingWorkUnits(remainingWorkUnitsArg);
        return this;
    }

    @Override
    public long getRemainingTime(TimeUnit timeUnit) {
        final long remainingWorkUnits = this.getRemainingWorkUnits();
        final long totalWorkUnits = this.getTotalWorkUnits();

        final long completedWorkUnits = totalWorkUnits - remainingWorkUnits;
        if(completedWorkUnits == 0){
            return -1;
        }
        if(completedWorkUnits == totalWorkUnits){
            return 0;
        }

        final long elapsedTime = this.getElapsedTime(timeUnit);

        return Math.round((float) remainingWorkUnits / completedWorkUnits * elapsedTime);
    }

    @Override
    public String getRemainingTimeAsString() {
        TimeUnit timeUnit = TimeUnit.NANOSECONDS;
        return TimeTeller.outputElapsedTime(this.getRemainingTime(timeUnit), timeUnit);
    }

}
