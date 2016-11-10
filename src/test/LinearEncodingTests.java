package test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import main.*;

public class LinearEncodingTests {

    private static final int[][] IMAGE = { { 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 }, { 0, 0, 1, 1, 1, 1, 1, 1, 0, 0 },
            { 0, 1, 1, 1, 1, 1, 1, 0, 0, 1 }, { 0, 1, 1, 1, 1, 1, 0, 0, 0, 1 }, { 0, 1, 1, 1, 1, 0, 0, 0, 1, 1 },
            { 0, 1, 1, 0, 0, 0, 0, 1, 1, 1 }, { 0, 1, 0, 0, 0, 0, 1, 1, 1, 1 }, { 0, 0, 0, 0, 0, 1, 1, 1, 1, 1 },
            { 0, 0, 0, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

    private static final boolean[][] BIT_IMAGE = { { false, false, true, true, true, true, true, true, true, true },
            { false, false, true, true, true, true, true, true, false, false },
            { false, true, true, true, true, true, true, false, false, true },
            { false, true, true, true, true, true, false, false, false, true },
            { false, true, true, true, true, false, false, false, true, true },
            { false, true, true, false, false, false, false, true, true, true },
            { false, true, false, false, false, false, true, true, true, true },
            { false, false, false, false, false, true, true, true, true, true },
            { false, false, false, true, true, true, true, true, true, true },
            { true, true, true, true, true, true, true, true, true, true } };

    private static final String TEXT = "$\\|";

    private static final int[][] CODED_TEXT = { { 0, 0, 1, 0, 0, 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 1, 1 },
            { 1, 0, 1, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1, 1, 1, 1, 1, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

    @Test
    public void embedBWImageTest() {
        int[][] cover = new int[10][10]; // filled with 0

        int[][] hidden = Steganography.embedBWImage(cover, BIT_IMAGE);

        assertArrayEquals(IMAGE, hidden);
    }

    @Test
    public void revealBWImageTest() {

        boolean[][] message = Steganography.revealBWImage(IMAGE);

        assertArrayEquals(BIT_IMAGE, message);
    }

    /**
     * Transforms a 2D boolean array into a 1D boolean array
     * 
     * @param bitImage
     *            A 2D boolean array
     * @return A 1D boolean array corresponding to the input's subarrays put
     *         next to each other
     */
    private boolean[] flattenArray(boolean[][] bitImage) {
        int width = bitImage[0].length;
        int length = bitImage.length;
        boolean[] bitArray = new boolean[length * width];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                bitArray[i * width + j] = bitImage[i][j];
            }
        }
        return bitArray;
    }

    @Test
    public void testEmbedBitArray() {
        assertArrayEquals(IMAGE, Steganography.embedBitArray(new int[10][10], flattenArray(BIT_IMAGE)));
    }

    @Test
    public void testRevealBitArray() {
        assert Arrays.equals(flattenArray(BIT_IMAGE), Steganography.revealBitArray(IMAGE));
    }

    @Test
    public void testEmbedText() {
        assertArrayEquals(CODED_TEXT, Steganography.embedText(new int[10][10], TEXT));
    }

    @Test
    public void testRevealText() {
        assertEquals(TEXT, Steganography.revealText(CODED_TEXT).substring(0, TEXT.length()));
    }

    @Test
    public void getLSBTest() {
        assertTrue(Steganography.getLSB(1));
        assertFalse(Steganography.getLSB(0));
    }

    @Test
    public void embedInLSBTest() {
        assertEquals(1, Steganography.embedInLSB(0, true));
        assertEquals(1, Steganography.embedInLSB(1, true));
        assertEquals(0, Steganography.embedInLSB(0, false));
        assertEquals(0, Steganography.embedInLSB(1, false));
        int testInt = -1234501181;
        assertEquals(testInt - 1, Steganography.embedInLSB(testInt, false));
        assertEquals(testInt, Steganography.embedInLSB(testInt, true));
    }

}
