package net.sasu.lib.gate.time;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Tests TimeTeller
 * 
 * @author Sasu
 *
 */
public class TimeTellerTest {

    @Test
    public void getElapsedTimeTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Map<Long, String> inputExcpectedOutputMap = new LinkedHashMap<>();

        inputExcpectedOutputMap.put(0L, "0 nanoseconds");
        inputExcpectedOutputMap.put(1L, "1 nanosecond");
        inputExcpectedOutputMap.put(999L, "999 nanoseconds");
        inputExcpectedOutputMap.put(1000L, "1 microsecond");
        inputExcpectedOutputMap.put(999999L, "999 microseconds");
        inputExcpectedOutputMap.put(1000000L, "1 ms");
        inputExcpectedOutputMap.put(999999999L, "999 ms");
        inputExcpectedOutputMap.put(1000000000L, "1.000 seconds");
        inputExcpectedOutputMap.put(1001000000L, "1.001 seconds");
        inputExcpectedOutputMap.put(1500000000L, "1.500 seconds");
        inputExcpectedOutputMap.put(15000000000L, "15.000 seconds");
        inputExcpectedOutputMap.put(15030000000L, "15.030 seconds");
        inputExcpectedOutputMap.put(12345678901L, "12.345 seconds");

        Set<Entry<Long, String>> entrySet = inputExcpectedOutputMap.entrySet();
        for (Entry<Long, String> entry : entrySet) {
            final String testResult = TimeTeller.outputElapsedTime(entry.getKey(), TimeUnit.NANOSECONDS);
            Assertions.assertEquals(entry.getValue(), testResult);
        }
    }
}
