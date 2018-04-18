package net.sasu.lib.elapsedtime.time;

import java.util.Arrays;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/**
 * Represents elapsed time.
 *
 * The time is split in its logical units. For example, 36.5 hours
 * would be split logically to 1 day, 12 hours and 30 minutes.
 *
 * @author Sasu
 */
public class ElapsedTime {

    private long elapsedTimeNs;
    private TimeUnit[] allTimeUnitsDesc = {TimeUnit.DAYS, TimeUnit.HOURS, TimeUnit.MINUTES, TimeUnit.SECONDS, TimeUnit.MILLISECONDS, TimeUnit.MICROSECONDS, TimeUnit.NANOSECONDS};
    private Long[] timeSplitToAllUnits = new Long[7];

    /**
     * Constructs new ElapsedTime object using the given elapsed time in given TimeUnit.
     *
     * @param elapsedTimeArg
     * @param timeUnitArg
     */
    public ElapsedTime(long elapsedTimeArg, TimeUnit timeUnitArg) {
        this.elapsedTimeNs = TimeUnit.NANOSECONDS.convert(elapsedTimeArg, timeUnitArg);

        long remainingTimeToSplit = this.elapsedTimeNs;
        for (int i = 0; i < allTimeUnitsDesc.length; i++) {
            TimeUnit timeUnit = allTimeUnitsDesc[i];
            long timeElapsedInTimeUnit = timeUnit.convert(remainingTimeToSplit, TimeUnit.NANOSECONDS);
            remainingTimeToSplit = remainingTimeToSplit - TimeUnit.NANOSECONDS.convert(timeElapsedInTimeUnit, timeUnit);
            timeSplitToAllUnits[i] = timeElapsedInTimeUnit;
        }

    }

    /**
     * Returns a SortedMap containing the elapsed time split in its time units, containing
     * only non-zero values. For example, if this object is created with 7203 seconds,
     * this method returns a SortedMap in length 2 (omitting TimeUNIT.MINUTES and other non-zero
     * time units).
     * <p>
     * TimeUnit.HOURS: 2
     * TimeUnit.SECONDS: 3
     *
     * @return
     */
    public SortedMap<TimeUnit, Long> getNonZeroTimeValues() {
        SortedMap<TimeUnit, Long> nonZeroTimeMap = new TreeMap<>();

        for (int i = 0; i < timeSplitToAllUnits.length; i++) {
            Long time = timeSplitToAllUnits[i];
            if (time != 0L) {
                nonZeroTimeMap.put(allTimeUnitsDesc[i], time);
            }
        }

        return nonZeroTimeMap;
    }

    /**
     * Returns a SortedMap containing the elapsed time split in all the common time units, i.e.
     * TimeUnit.HOURS, TimeUnit.MINUTES, TimeUnit.SECONDS and TimeUnit.MILLISECONDS.
     * For example, if this object is created with 7203 seconds, this method returns
     * a SortedMap in length 4:
     * <p>
     * TimeUnit.HOURS: 2
     * TimeUnit.MINUTES: 0
     * TimeUnit.SECONDS: 3
     * TimeUnit.MILLISECONDS: 0
     *
     * @return
     */
    public SortedMap<TimeUnit, Long> getCommonZeroTimeValues() {
        SortedMap<TimeUnit, Long> commonTimeUnitMap = new TreeMap<>();

        timeSplitToAllUnits[1] = timeSplitToAllUnits[1] + timeSplitToAllUnits[0] * 24;
        for (int i = 1; i < 5; i++) {
            Long time = timeSplitToAllUnits[i];
            commonTimeUnitMap.put(allTimeUnitsDesc[i], time);
        }

        return commonTimeUnitMap;
    }

    /**
     * Returns a SortedMap containing only the time units and their respective values,
     * which make sense for a verbose output.
     * <p>
     * Rules:
     * <p>
     * - when time is less than one microsecond, only nanoseconds are returned
     * - when time is less than one second, only the largest time unit values are returned, i.e. either microseconds or milliseconds
     * - when time is less than one minute, only the largest and second largest time units are returned
     * - when time is at least one minute, all non-zero time units larger or equal to seconds are returned
     *
     * @return
     */
    public SortedMap<TimeUnit, Long> getTimeValuesForVerboseOutput() {
        if (this.elapsedTimeNs < 1000) {
            return getLessThanOneMicrosecondVerboseMap();
        } else if (this.elapsedTimeNs < 1000000000L) {
            return getLessThanOneSecondVerboseMap();
        } else if (this.elapsedTimeNs < 60000000000L) {
            return getLessThanOneMinuteVerboseMap();
        } else {
            return getAtLeastOneMinuteVerboseMap();
        }
    }

    private SortedMap<TimeUnit, Long> getLessThanOneMicrosecondVerboseMap() {
        SortedMap<TimeUnit, Long> verboseMap = new TreeMap<>();
        verboseMap.put(TimeUnit.NANOSECONDS, this.elapsedTimeNs);
        return verboseMap;
    }

    private SortedMap<TimeUnit, Long> getLessThanOneSecondVerboseMap() {
        SortedMap<TimeUnit, Long> verboseMap = new TreeMap<>();

        for (int i = 4; i < 6; i++) {
            if (timeSplitToAllUnits[i] != 0) {
                verboseMap.put(allTimeUnitsDesc[i], timeSplitToAllUnits[i]);
                return verboseMap;
            }
        }

        return verboseMap;
    }

    private SortedMap<TimeUnit, Long> getLessThanOneMinuteVerboseMap() {
        SortedMap<TimeUnit, Long> verboseMap = new TreeMap<>();

        for (int i = 3; i < 7; i++) {
            if (timeSplitToAllUnits[i] != 0) {
                verboseMap.put(allTimeUnitsDesc[i], timeSplitToAllUnits[i]);
                verboseMap.put(allTimeUnitsDesc[i + 1], timeSplitToAllUnits[i + 1]);
                return verboseMap;
            }
        }

        return verboseMap;
    }

    private SortedMap<TimeUnit, Long> getAtLeastOneMinuteVerboseMap() {
        SortedMap<TimeUnit, Long> verboseMap = new TreeMap<>();

        for (int i = 0; i < 3; i++) {
            verboseMap.put(allTimeUnitsDesc[i], timeSplitToAllUnits[i]);
            verboseMap.put(allTimeUnitsDesc[i + 1], timeSplitToAllUnits[i + 1]);
        }

        return verboseMap;
    }

    /**
     * Return the largest TimeUnit of the represented time.
     *
     * @return
     */
    public TimeUnit getLargestTimeUnit(){
        if(timeSplitToAllUnits == null || timeSplitToAllUnits.length == 0){
            throw new IllegalStateException("Not initialized.");
        }

        for (int i = 0; i < timeSplitToAllUnits.length; i++) {
            Long time = timeSplitToAllUnits[i];
            if (time != 0L) {
                return allTimeUnitsDesc[i];
            }
        }

        //if nothing has been found this far it's 0 nanoseconds
        return TimeUnit.NANOSECONDS;
    }

    @Override
    public String toString() {
        return "ElapsedTime [timeSplitToAllUnits=" + Arrays.toString(timeSplitToAllUnits) + "]";
    }

}