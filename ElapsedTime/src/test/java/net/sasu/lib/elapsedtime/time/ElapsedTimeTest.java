package net.sasu.lib.elapsedtime.time;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import net.sasu.lib.elapsedtime.time.ElapsedTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for class ElapsedTime
 * 
 * @author Sasu
 */
public class ElapsedTimeTest {

	@Test
	public void constructorTest() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Map<ElapsedTime, Long[]> inputExcpectedOutputMap = new LinkedHashMap<>();

		//define test cases
		inputExcpectedOutputMap.put(new ElapsedTime(0, TimeUnit.SECONDS), new Long[] { 0L, 0L, 0L, 0L, 0L, 0L, 0L });
		
		inputExcpectedOutputMap.put(new ElapsedTime(1, TimeUnit.NANOSECONDS), new Long[] { 0L, 0L, 0L, 0L, 0L, 0L, 1L });
		inputExcpectedOutputMap.put(new ElapsedTime(1000, TimeUnit.NANOSECONDS), new Long[] { 0L, 0L, 0L, 0L, 0L, 1L, 0L });
		inputExcpectedOutputMap.put(new ElapsedTime(1001, TimeUnit.NANOSECONDS), new Long[] { 0L, 0L, 0L, 0L, 0L, 1L, 1L });
		
		inputExcpectedOutputMap.put(new ElapsedTime(1, TimeUnit.MICROSECONDS), new Long[] { 0L, 0L, 0L, 0L, 0L, 1L, 0L });
		inputExcpectedOutputMap.put(new ElapsedTime(1000, TimeUnit.MICROSECONDS), new Long[] { 0L, 0L, 0L, 0L, 1L, 0L, 0L });
		inputExcpectedOutputMap.put(new ElapsedTime(1001, TimeUnit.MICROSECONDS), new Long[] { 0L, 0L, 0L, 0L, 1L, 1L, 0L });
		
		inputExcpectedOutputMap.put(new ElapsedTime(1, TimeUnit.MILLISECONDS), new Long[] { 0L, 0L, 0L, 0L, 1L, 0L, 0L });
		inputExcpectedOutputMap.put(new ElapsedTime(1000, TimeUnit.MILLISECONDS), new Long[] { 0L, 0L, 0L, 1L, 0L, 0L, 0L });
		inputExcpectedOutputMap.put(new ElapsedTime(1001, TimeUnit.MILLISECONDS), new Long[] { 0L, 0L, 0L, 1L, 1L, 0L, 0L });
		
		inputExcpectedOutputMap.put(new ElapsedTime(1, TimeUnit.SECONDS), new Long[] { 0L, 0L, 0L, 1L, 0L, 0L, 0L });
		inputExcpectedOutputMap.put(new ElapsedTime(60, TimeUnit.SECONDS), new Long[] { 0L, 0L, 1L, 0L, 0L, 0L, 0L });
		inputExcpectedOutputMap.put(new ElapsedTime(61, TimeUnit.SECONDS), new Long[] { 0L, 0L, 1L, 1L, 0L, 0L, 0L });
		
		inputExcpectedOutputMap.put(new ElapsedTime(1, TimeUnit.MINUTES), new Long[] { 0L, 0L, 1L, 0L, 0L, 0L, 0L });
		inputExcpectedOutputMap.put(new ElapsedTime(60, TimeUnit.MINUTES), new Long[] { 0L, 1L, 0L, 0L, 0L, 0L, 0L });
		inputExcpectedOutputMap.put(new ElapsedTime(61, TimeUnit.MINUTES), new Long[] { 0L, 1L, 1L, 0L, 0L, 0L, 0L });
		
		inputExcpectedOutputMap.put(new ElapsedTime(1, TimeUnit.HOURS), new Long[] { 0L, 1L, 0L, 0L, 0L, 0L, 0L });
		inputExcpectedOutputMap.put(new ElapsedTime(24, TimeUnit.HOURS), new Long[] { 1L, 0L, 0L, 0L, 0L, 0L, 0L });
		inputExcpectedOutputMap.put(new ElapsedTime(25, TimeUnit.HOURS), new Long[] { 1L, 1L, 0L, 0L, 0L, 0L, 0L });
		
		inputExcpectedOutputMap.put(new ElapsedTime(90061001001001L, TimeUnit.NANOSECONDS), new Long[] { 1L, 1L, 1L, 1L, 1L, 1L, 1L });
		
		//execute test cases
		Set<Entry<ElapsedTime,Long[]>> entrySet = inputExcpectedOutputMap.entrySet();
		int i = 1;
		for (Entry<ElapsedTime, Long[]> entry : entrySet) {
			ElapsedTime elapsedTime = entry.getKey();
			Field f = elapsedTime.getClass().getDeclaredField("timeSplitToAllUnits");
			f.setAccessible(true);
			Long[] timeSplitToAllUnits = (Long[]) f.get(elapsedTime);
			Assertions.assertArrayEquals(timeSplitToAllUnits, entry.getValue(), "Failed at entry " + i);
			i++;
		}
	}

}
