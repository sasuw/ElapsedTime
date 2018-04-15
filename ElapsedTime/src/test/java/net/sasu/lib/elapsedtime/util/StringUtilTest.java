package net.sasu.lib.elapsedtime.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringUtilTest {

    @Test
    public void padLeftTest(){
        String str = "a";
        String b = StringUtil.padLeft(str, 'x', 5);
        assertEquals("xxxxa", b);
    }
}
