package net.sasu.lib.elapsedtime.estimator;

public class BasicEstimatorExample {

	public static void main(String[] args) throws InterruptedException{
        Long unitsOfWork = 10L;

        BasicEstimator basicEstimator =  BasicEstimator.createInstanceAndStart(unitsOfWork);

        for(int i = 0; i < unitsOfWork; i++){
            System.out.println("Starting to execute one unit of work. Work left: " + (unitsOfWork - i) + " units.");

            final int sleepTime = 1000;
            Thread.sleep(sleepTime);
            
            //here we would call the method executing the actual work
            basicEstimator.completeWorkUnits(1);
            final String remainingTimeAsString = basicEstimator.getRemainingTimeAsString();
            System.out.println("Executed work unit. Remaining time: " + remainingTimeAsString);
        }
    }
}
