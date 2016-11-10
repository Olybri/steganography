package test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import main.*;

public class SpiralEncodingTests {

    public static final boolean[] bitArray = {
            // height (2)
            false, true, false, false, false, false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
            false, false,

            // width (3)
            true, true, false, false, false, false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
            false, false,

            // image data
            true, true, true, false, false, false };

    public static final int[][] encodedCover = { { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 1, 1, 1, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

    public static final boolean[][] bwImage = { { true, true, true }, { false, false, false } };

    public static final int[][] cover = new int[10][10];

    @Test
    public void embedImageTest() {
        assertArrayEquals(encodedCover, Steganography.embedSpiralImage(cover, bwImage));
    }

    @Test
    public void revealImageTest() {
        assertArrayEquals(bwImage, Steganography.revealSpiralImage(encodedCover));
    }

    @Test
    public void embedBitArrayTest() {
        assertArrayEquals(encodedCover, Steganography.embedSpiralBitArray(cover, bitArray));
    }

    @Test
    public void revealBitArrayTest() {
        assert Arrays.equals(bitArray, Arrays.copyOfRange(Steganography.revealSpiralBitArray(encodedCover), 0, bitArray.length));
    }
}
