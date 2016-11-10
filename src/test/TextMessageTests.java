package test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import main.TextMessage;

public class TextMessageTests {

    private static final int INT_MESSAGE = 0x7EDCBA98; //2128394904
    private static final boolean[] INT_BIT_ARRAY = { false, false, false, true, true, false, false, true, false, true,
            false, true, true, true, false, true, false, false, true, true, true, false, true, true, false, true, true,
            true, true, true, true, false };
    private static final String STRING_MESSAGE = "|/&2>";
    private static final boolean[] STRING_BIT_ARRAY = { 
        false, false, true, true, true, true, true, false, false, false, false, false, false, false, false, false, 
        true, true, true, true, false, true, false, false, false, false, false, false, false, false, false, false, 
        false, true, true, false, false, true, false, false, false, false, false, false, false, false, false, false, 
        false, true, false, false, true, true, false, false, false, false, false, false, false, false, false, false, 
        false, true, true, true, true, true, false, false, false, false, false, false, false, false, false, false };

    @Test
    public void intConversionTest() {
        assert Arrays.equals(INT_BIT_ARRAY, TextMessage.intToBitArray(INT_MESSAGE, 32));
        assert Arrays.equals(Arrays.copyOfRange(INT_BIT_ARRAY, 0, 16), TextMessage.intToBitArray(INT_MESSAGE, 16));
        assertEquals(INT_MESSAGE, TextMessage.bitArrayToInt(INT_BIT_ARRAY));
        assertEquals(INT_MESSAGE, TextMessage.bitArrayToInt(TextMessage.intToBitArray(INT_MESSAGE, 32)));
    }

    @Test
    public void stringConversionTest() {
        assert Arrays.equals(STRING_BIT_ARRAY, TextMessage.stringToBitArray(STRING_MESSAGE));
        assertEquals(STRING_MESSAGE, TextMessage.bitArrayToString(STRING_BIT_ARRAY));
    }

}
