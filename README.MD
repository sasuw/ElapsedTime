# ElapsedTime

This Java 8 library is for

* **estimating** remaining time of work for measurable tasks
* **measuring** elapsed time accurately
* **outputting** elapsed time

This library is light-weight: the compiled JAR file is ca. 15 kilobytes, with no external dependencies and production-ready.

## Code quality

I strive to keep all essential parts of the code covered with unit tests to ensure that the code is as bug-free as possible. Details can be seen at Sonarcloud: https://sonarcloud.io/dashboard?id=net.sasu.lib.elapsedtime%3AElapsedTime

# Basic usage

## Estimating time

```java
public static void main(String[] args) throws InterruptedException{
        Long unitsOfWork = 10L;

        BasicEstimator basicEstimator =  BasicEstimator.createInstanceAndStart(unitsOfWork);

        for(int i = 0; i < unitsOfWork; i++){
            System.out.println("Starting to execute one unit of work. Work left: " + (unitsOfWork - i) + " units.");

            final int sleepTime = 1000;
            Thread.sleep(sleepTime);

            basicEstimator.completeWorkUnits(1);
            final String remainingTimeAsString = basicEstimator.getRemainingTimeAsString();
            System.out.println("Executed work unit. Remaining time: " + remainingTimeAsString);
        }
}
```

In this example, we do three things

1. Create a new BasicEstimator object
2. Initialize the BasicEstimator object
3. Start the BasicEstimator object

As constructor argument we give the total number of work units. A work unit could be e.g. byte, loop iteration counter etc.

`BasicEstimator basicEstimator =  BasicEstimator.createInstanceAndStart(unitsOfWork);`

After completing some amount of work, we inform the BasicEstimator how many work units we have completed.

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

This example outputs something like

`1.002 seconds`

```java
public static void  main(String[] args) throws InterruptedException {
    Timer nsTimer = new NanosecondTimer();
    nsTimer.start();

    Thread.sleep(1000);

    String elapsedTime = ((NanosecondTimer) nsTimer).getElapsedTimeAndStop();
    System.out.println(elapsedTime);
}
```

## Outputting time

You can output time in a nice, human-readable format using `SimpleTimeTeller` or `VerboseTimeTeller`.

For example, when you want to output an amount of elapsed time in a consistent format for logging purposes, you could

```java
SimpleTimeTeller stt = new SimpleTimeTeller();
log(stt.outputElapsedTime(15030000000L, TimeUnit.NANOSECONDS));
```

In this case, the output would be `00:00:15.030`

When the output should be more verbose, you can use `VerboseTimeTeller`:

```java
VerboseTimeTeller stt = new VerboseTimeTeller();
log(stt.outputElapsedTime(15030000000L, TimeUnit.NANOSECONDS));
```
In this case, the output would be `15.030 seconds`
