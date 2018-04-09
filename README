Library for estimating remaining time of work for measurable tasks.

# Basic usage

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

Finally, we show the user 

