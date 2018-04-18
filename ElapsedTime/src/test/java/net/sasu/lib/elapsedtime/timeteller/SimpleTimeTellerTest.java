package net.sasu.lib.elapsedtime.timeteller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimpleTimeTellerTest {

	@Test
	public void outputElapsedTimeTest() {
		Map<Long, String> inputExcpectedOutputMap = new LinkedHashMap<>();

        inputExcpectedOutputMap.put(0L, "00:00:00.000");
        inputExcpectedOutputMap.put(1L, "00:00:00.000");
        inputExcpectedOutputMap.put(999L, "00:00:00.000");
        inputExcpectedOutputMap.put(1000L, "00:00:00.000");

        inputExcpectedOutputMap.put(999999L, "00:00:00.000");
        inputExcpectedOutputMap.put(1000000L, "00:00:00.001");
        inputExcpectedOutputMap.put(999999999L, "00:00:00.999");
        inputExcpectedOutputMap.put(1000000000L, "00:00:01.000");
        inputExcpectedOutputMap.put(1001000000L, "00:00:01.001");
        inputExcpectedOutputMap.put(1500000000L, "00:00:01.500");
        inputExcpectedOutputMap.put(1601000000L, "00:00:01.601");
        inputExcpectedOutputMap.put(15000000000L, "00:00:15.000");
        inputExcpectedOutputMap.put(15030000000L, "00:00:15.030");
        inputExcpectedOutputMap.put(12345678901L, "00:00:12.345");
        inputExcpectedOutputMap.put(12895678901L, "00:00:12.895");
        inputExcpectedOutputMap.put(59000000000L, "00:00:59.000");
        inputExcpectedOutputMap.put(60000000000L, "00:01:00.000");
        inputExcpectedOutputMap.put(61000000000L, "00:01:01.000");
        inputExcpectedOutputMap.put(62000000000L, "00:01:02.000");
        inputExcpectedOutputMap.put(121000000000L, "00:02:01.000");
        inputExcpectedOutputMap.put(122000000000L, "00:02:02.000");
        inputExcpectedOutputMap.put(3540000000000L, "00:59:00.000");
        inputExcpectedOutputMap.put(3599000000000L, "00:59:59.000");
        inputExcpectedOutputMap.put(3600000000000L, "01:00:00.000");
        inputExcpectedOutputMap.put(3601000000000L, "01:00:01.000");
        inputExcpectedOutputMap.put(3661000000000L, "01:01:01.000");
        inputExcpectedOutputMap.put(3662000000000L, "01:01:02.000");
        inputExcpectedOutputMap.put(3722000000000L, "01:02:02.000");
        inputExcpectedOutputMap.put(7200000000000L, "02:00:00.000");
        inputExcpectedOutputMap.put(86399000000000L, "23:59:59.000");
        inputExcpectedOutputMap.put(86400000000000L, "24:00:00.000");
        inputExcpectedOutputMap.put(86401000000000L, "24:00:01.000");
        inputExcpectedOutputMap.put(86402000000000L, "24:00:02.000");
        inputExcpectedOutputMap.put(180000000000000L, "50:00:00.000");

        SimpleTimeTeller vtt = new SimpleTimeTeller();
        
        Set<Entry<Long, String>> entrySet = inputExcpectedOutputMap.entrySet();
        for (Entry<Long, String> entry : entrySet) {
            final String testResult = vtt.outputElapsedTime(entry.getKey(), TimeUnit.NANOSECONDS);
            System.out.println(entry.getKey() + " / " + entry.getValue() + ": " + testResult);
            Assertions.assertEquals(entry.getValue(), testResult, "Test with input value " + entry.getKey() + " failed.");
        }
	}
}
