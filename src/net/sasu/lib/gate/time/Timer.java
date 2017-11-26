package net.sasu.lib.gate.time;

public interface Timer<T> {

    public void start();

    public void stop();

    public String getElapsedTime();
}
