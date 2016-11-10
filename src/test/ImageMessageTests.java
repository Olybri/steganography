package test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import main.ImageMessage;

public class ImageMessageTests {

    private static final int COLOUR = 0b11110000_00001111_01010101;
    private static final int GREY = 0b01110001_01110001_01110001;
    private static final int RED_FULL = 0b11110000_11110000_11110000;
    private static final int GREEN_FULL = 0b00001111_00001111_00001111;
    private static final int BLUE_FULL = 0b01010101_01010101_01010101;
    private static final int WHITE = 16777215; // = 0x00FFFFFF
    private static final int RED = 0b11110000;
    private static final int GREEN = 0b00001111;
    private static final int BLUE = 0b01010101;
    private static final int GREY_VALUE = 0b01110001;

    @Test
    public void getColoursTest() {
        assertEquals(ImageMessage.getRed(COLOUR), RED);
        assertEquals(ImageMessage.getGreen(COLOUR), GREEN);
        assertEquals(ImageMessage.getBlue(COLOUR), BLUE);
        assertEquals(ImageMessage.getGray(COLOUR), GREY_VALUE);
    }

    @Test
    public void getBWTest() {
        assertEquals(true, ImageMessage.getBW(GREY_VALUE, GREY_VALUE));
        assertEquals(true, ImageMessage.getBW(GREY_VALUE, GREY_VALUE-1));
        assertEquals(false, ImageMessage.getBW(GREY_VALUE, GREY_VALUE+1));
    }
    
    @Test
    public void getRGBTest() {
        assertEquals(ImageMessage.getRGB(RED, GREEN, BLUE), COLOUR);
        assertEquals(ImageMessage.getRGB(GREY_VALUE), GREY);
        assertEquals(ImageMessage.getRGB(true), WHITE);
        assertEquals(ImageMessage.getRGB(false), 0);
    }
    
    @Test
    public void toRGBTest() {
        assertArrayEquals(ImageMessage.toRGB(new int[][] { { GREY_VALUE, BLUE }, { GREEN, RED } }),
                new int[][] { { GREY, BLUE_FULL }, { GREEN_FULL, RED_FULL } });
        assertArrayEquals(ImageMessage.toRGB(new boolean[][] { { true, false }, { true, false } }),
                new int[][] { { WHITE, 0 }, { WHITE, 0 } });
    }

    @Test
    public void toGrayTest() {
        assertArrayEquals(ImageMessage.toGray(new int[][] { { COLOUR, GREY }, { RED_FULL, GREEN_FULL } }),
                new int[][] { { GREY_VALUE, GREY_VALUE }, { RED, GREEN } });
    }
    
    @Test
    public void toBWTest() {
        int[][] image = {{255, 0}, {GREY_VALUE-2, GREY_VALUE}};
        assertArrayEquals(new boolean[][] {{true, false}, {false, true}}, ImageMessage.toBW(image, GREY_VALUE-1));
        assertArrayEquals(new boolean[][] {{true, false}, {false, true}}, ImageMessage.toBW(image, GREY_VALUE));
        assertArrayEquals(new boolean[][] {{true, false}, {true, true}}, ImageMessage.toBW(image, GREY_VALUE-2));
        assertArrayEquals(new boolean[][] {{true, true}, {true, true}}, ImageMessage.toBW(image, 0));
        assertArrayEquals(new boolean[][] {{true, false}, {false, false}}, ImageMessage.toBW(image, 255));
        assertArrayEquals(new boolean[][] {{false, false}, {false, false}}, ImageMessage.toBW(image, Integer.MAX_VALUE));
    }
    
    @Test
    public void bwImageBitArrayTest() {
        boolean[][] image = {{true, false}, {false, true}};
        boolean[] bitarray = {
                //height
                false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, 
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
                //width
                false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, 
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,
                //data
                true, false, false, true};
        System.out.println(bitarray.length);
        assert Arrays.equals(bitarray, ImageMessage.bwImageToBitArray(image));
        assertArrayEquals(image, ImageMessage.bitArrayToImage(bitarray));
    }
}
