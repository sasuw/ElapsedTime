# ElapsedTime

This Java 8 library is for 
* **estimating** remaining time of work for measurable tasks.
* **measuring** elapsed time accurately
* **outputting** elapsed time

# Code quality

https://sonarcloud.io/dashboard?id=net.sasu.lib.elapsedtime%3AElapsedTime

# Basic usage

## Estimating time

```java
public static void main(String[] args) throws InterruptedException{
        Long unitsOfWork = 3000L;

        BasicEstimator basicEstimator =  BasicEstimator.createInstanceAndStart(unitsOfWork);

        for(int i = 0; i < unitsOfWork; i++){
            System.out.println("i: " + i);

            Random rnd = new Random();
            final int sleepTime = 500;
            Thread.sleep(sleepTime);

            basicEstimator.completeWorkUnits(1);
            final String remainingTimeAsString = basicEstimator.getRemainingTimeAsString();
            System.out.println("remaining time: " + remainingTimeAsString);
        }
    }
```

In this example, we do three things

Create a new BasicEstimator object, initialize and start it. As constructor argument we give the total number of work units. A work unit could be e.g. byte, loop iteration counter etc. depending on context.

`BasicEstimator basicEstimator =  BasicEstimator.createInstanceAndStart(unitsOfWork);`

After completing some amount of work, we inform the BasicEstimator of this.

`basicEstimator.completeWorkUnits(1);`

Finally, we show the user the estimated amount of remaining time obtained from the call

`basicEstimator.getRemainingTimeAsString()`

, which outputs something like

`15.0 seconds`
or 
`1 minute 2 seconds`
or
`1 hour 30 minutes`
depending on the time left.

### Implementing a better estimator
The BasicEstimator is good for most basic use cases. For some estimations where more intelligence is needed (e.g. network or human-activity based estimations) you may want to implement your own estimator. This can be easily done by extending the class `BaseEstimator.java` (see `BasicEstimator.java` for an example) and implementing your own logic in method `getRemainingTime`.

## Measuring time

TODO

## Outputting time

TODO
