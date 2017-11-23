package net.sasu.lib.gate.estimator;

import java.util.concurrent.TimeUnit;

/**
 * Basic implementation for estimations with low variability.
 * 
 * @author Sasu
 *
 */
public class BasicEstimator extends BaseEstimator<BasicEstimator> {

	@Override
	public BasicEstimator init(long remainingWorkUnitsArg) {
		this.initializeRemainingWorkUnits(remainingWorkUnitsArg);
		return this;
	}

	@Override
	public long getRemainingTime(TimeUnit timeUnit) {
		return this.getTotalWorkUnits() / this.getRemainingWorkUnits() * this.getElapsedTime(timeUnit);
	}

	@Override
	public String getRemainingTimeAsString() {
		// TODO Auto-generated method stub
		return null;
	}

}
