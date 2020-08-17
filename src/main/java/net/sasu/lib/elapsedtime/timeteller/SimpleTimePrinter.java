package net.sasu.lib.elapsedtime.timeteller;

import net.sasu.lib.elapsedtime.time.ElapsedTime;
import net.sasu.lib.elapsedtime.util.StringUtil;

import java.util.SortedMap;
import java.util.concurrent.TimeUnit;

/**
 * Outputs time for humans in a simple, numeric way in
 * format hh:mm:ss.mmm
 *
 * @author Sasu
 */
public class SimpleTimePrinter implements TimePrinter {

    private static final String DEFAULT_SEPARATOR = ":";

    /* (non-Javadoc)
     * @see net.sasu.lib.timeteller.TimePrinter#outputElapsedTime(long, java.util.concurrent.TimeUnit)
     */
    @Override
    public String outputElapsedTime(long timeElapsedInTimeUnits, TimeUnit timeUnit) {
        ElapsedTime elapsedTime = new ElapsedTime(timeElapsedInTimeUnits, timeUnit);
        SortedMap<TimeUnit, Long> commonZeroTimeValues = elapsedTime.getCommonZeroTimeValues();

        final char padChar = '0';
        final int hourMinSecLen = 2;
        final int msLen = 3;
        return StringUtil.padLeft(commonZeroTimeValues.get(TimeUnit.HOURS).toString(), padChar, hourMinSecLen)
                + DEFAULT_SEPARATOR
                + StringUtil.padLeft(commonZeroTimeValues.get(TimeUnit.MINUTES).toString(), padChar, hourMinSecLen)
                + DEFAULT_SEPARATOR
                + StringUtil.padLeft(commonZeroTimeValues.get(TimeUnit.SECONDS).toString(), padChar, hourMinSecLen)
                + "."
                + StringUtil.padLeft(commonZeroTimeValues.get(TimeUnit.MILLISECONDS).toString(), padChar, msLen);
    }

}
