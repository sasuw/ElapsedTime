package net.sasu.lib.elapsedtime.timeprinter;

import net.sasu.lib.elapsedtime.time.ElapsedTime;
import net.sasu.lib.elapsedtime.util.StringUtil;

import java.text.DecimalFormatSymbols;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

/**
 * Outputs time for humans in a verbose way.
 *
 * @author Sasu
 */
public class VerboseTimePrinter implements TimePrinter {

    private static final String DEFAULT_SEPARATOR = ", ";

    private static final char DECIMAL_SEPARATOR = new DecimalFormatSymbols(Locale.getDefault()).getDecimalSeparator();
    
    /**
     * Returns elapsed time as a human-readable string, e.g. "10 ms". If the elapsed
     * time is more than one second but less than 60 seconds, the time is returned
     * in seconds with the millisecond part after a dot, e.g. "15.233 seconds".
     * <p>
     * If the returned time is more than 60 seconds, several time units are used,
     * e.g. "1 hour, 2 minutes, 3 seconds".
     * <p>
     * With max value of Long.MAX_VALUE and TimeUnit.NANOSECONDS the method supports elapsed
     * times up to 292.277025 years.
     * <p>
     * All time units used for display (see java.util.concurrent.TimeUnit): day, hour, second, minute, millisecond, microsecond, nanosecond.
     *
     * @return elapsed time as a human-readable string, e.g. "10 ms"
     */
    public String outputElapsedTime(long timeElapsedInTimeUnits, TimeUnit timeUnit) {
        ElapsedTime elapsedTime = new ElapsedTime(timeElapsedInTimeUnits, timeUnit);
        SortedMap<TimeUnit, Long> timeValues = elapsedTime.getTimeValuesForVerboseOutput();
        SortedMap<TimeUnit, Long> nonZeroTimeValues = elapsedTime.getNonZeroTimeValues();

        if (nonZeroTimeValues.size() == 0) {
            return "0 nanoseconds";
        }

        Set<Entry<TimeUnit, Long>> timeValueEntries = ((TreeMap<TimeUnit, Long>) timeValues).descendingMap().entrySet();

        StringBuilder sb = new StringBuilder();

        TimeUnit largestTimeUnit = elapsedTime.getLargestTimeUnit();
        if (largestTimeUnit.equals(TimeUnit.NANOSECONDS) || largestTimeUnit.equals(TimeUnit.MICROSECONDS) || largestTimeUnit.equals(TimeUnit.MILLISECONDS)) {
            final Long tempTimeValue = nonZeroTimeValues.entrySet().iterator().next().getValue();
            String timeString = createTimeString(tempTimeValue, largestTimeUnit);
            sb.append(timeString);
        } else if (largestTimeUnit.equals(TimeUnit.SECONDS)) {
            final Iterator<Entry<TimeUnit, Long>> iterator = timeValueEntries.iterator();
            Entry<TimeUnit, Long> seconds = iterator.next();
            Entry<TimeUnit, Long> milliSeconds = iterator.next();

            sb.append(seconds.getValue());
            sb.append(VerboseTimePrinter.DECIMAL_SEPARATOR);
            sb.append(StringUtil.padLeft(milliSeconds.getValue().toString(), '0', 3));
            sb.append(" seconds");
        } else {
            boolean nonZeroValueFound = false;
            for (Entry<TimeUnit, Long> timeValueEntry : timeValueEntries) {
                final TimeUnit tempTimeUnit = timeValueEntry.getKey();
                final Long tempTimeValue = timeValueEntry.getValue();

                if(tempTimeValue.equals(0L) && !nonZeroValueFound){
                    continue;
                }
                nonZeroValueFound = true;

                String timeString = createTimeString(tempTimeValue, tempTimeUnit);
                sb.append(timeString);
                sb.append(DEFAULT_SEPARATOR);
            }
            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }

    /**
     * When given the number of time units (e.g. seconds) and the name of the time
     * unit (e.g. "second") a human-readable time string is created and returned
     * (e.g. "1 second" or "5 seconds").
     *
     * @param tempTimeValue
     * @param timeUnit
     * @return
     */
    private String createTimeString(Long tempTimeValue, TimeUnit timeUnit) {
        String timeUnitNameLowerCase = timeUnit.toString();
        String timeUnitNameLowerCaseSingular = timeUnitNameLowerCase.toLowerCase().substring(0, timeUnitNameLowerCase.length() - 1);
        return createTimeString(tempTimeValue, timeUnitNameLowerCaseSingular);
    }

    /**
     * When given the number of time units (e.g. seconds) and the name of the time
     * unit (e.g. "second") a human-readable time string is created and returned
     * (e.g. "1 second" or "5 seconds").
     *
     * @param timeUnits
     * @param timeUnitName
     * @return
     */
    private static String createTimeString(long timeUnits, String timeUnitName) {
        String retVal = timeUnits + " " + timeUnitName;
        if (timeUnits != 1 && !timeUnitName.substring(timeUnitName.length() - 1).equals("s")) {
            return retVal + "s";
        }
        return retVal;
    }
}
