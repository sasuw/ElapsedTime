package net.sasu.lib.gate.time;

/**
 * Mock timer for time-independent unit tests.
 */
public class MockTimer implements Timer {

    private long startTime; // in nanoseconds
    private long elapsedTime; // in nanoseconds

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public String getElapsedTime() {
        return null;
    }

    @Override
    public long getElapsedTimeRaw() {
        return 0;
    }
}
