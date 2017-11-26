package net.sasu.lib.gate.time;

import java.util.concurrent.TimeUnit;

/**
 * Mock timer for time-independent unit tests.
 */
public class MockTimer implements Timer {

    private long startTime; // in nanoseconds
    private long elapsedTime; // in nanoseconds

    @Override
    public void start() {
        this.startTime = 0L;
        this.elapsedTime = 0L;
    }

    @Override
    public void stop() {
        //do nothing, it's ok
    }

    @Override
    public String getElapsedTime() {
        return TimeTeller.outputElapsedTime(this.elapsedTime, TimeUnit.NANOSECONDS);
    }

    @Override
    public long getElapsedTimeRaw() {
        return this.elapsedTime;
    }

    public void increment(){
        this.elapsedTime++;
    }
}
