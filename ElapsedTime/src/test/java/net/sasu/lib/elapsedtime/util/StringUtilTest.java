package net.sasu.lib.elapsedtime.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class StringUtilTest {

    @Test
    void padLeftTest(){
        String str = "a";
        String b = StringUtil.padLeft(str, 'x', 5);
        assertEquals("xxxxa", b);
    }

    @Test
    void padLeftTestExceptionIae1() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                ()->{StringUtil.padLeft(null, 'a', 5);} );
        assertNotNull(exception);
    }

    @Test
    void padLeftTestExceptionIae2() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                ()->{StringUtil.padLeft("aa", 'a', 0);} );
        assertNotNull(exception);
    }



}
