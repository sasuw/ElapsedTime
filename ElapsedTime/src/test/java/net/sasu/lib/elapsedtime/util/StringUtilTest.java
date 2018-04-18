package net.sasu.lib.elapsedtime.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StringUtilTest {

    @Test
    public void padLeftTest(){
        String str = "a";
        String b = StringUtil.padLeft(str, 'x', 5);
        assertEquals("xxxxa", b);
    }
}
