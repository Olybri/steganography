package main;

public class Steganography
{

    /*
     * ********************************************
     * Part 1b: embed/reveal BW
     * image ********************************************
     */

    /*
     * Methods to deal with the LSB
     */
    /**
     * Inserts a boolean into the Least Significant Bit of an int
     * @param value The int in which to insert a boolean value
     * @param m The boolean value to be inserted into the int
     * @return An int corresponding to {@code value} with {@code m} inserted into its LSB
     */
    public static int embedInLSB(int value, boolean m)
    {
        return (value & 0xFFFF_FFFE) + (m ? 1 : 0);
    }

    /**
     * Extracts the Least Significant Bit of an integer
     * @param value The integer from which to extract the LSB value
     * @return A boolean corresponding to the value of {@code value}'s LSB
     */
    public static boolean getLSB(int value)
    {
        return (value & 1) == 1;
    }

    /*
     * Linear embedding
     */
    /**
     * Embeds a black and white image into a color image's LSB layer using linear embedding
     * @param cover The image in which to embed {@code message}
     * @param message The image to embed into {@code cover}
     * @return A <b>copy</b> of {@code cover} with {@code message}'s pixel values embedded in a linear fashion in the LSB layer
     */
    public static int[][] embedBWImage(int[][] cover, boolean[][] message)
    {
        assert Utils.isImage(cover) : "Not a valid image";
        
        int height = cover.length;
        int width = cover[0].length;

        int[][] embedded = new int[height][width];

        for(int y=0; y<height; ++y)
            for(int x=0; x<width; ++x)
                embedded[y][x] =
                    (x < message[0].length && y < message.length ?
                        embedInLSB(cover[y][x], message[y][x]) :
                        cover[y][x]);

        return embedded;
    }

    /**
     * Reveals a black and white image which was embedded in the LSB layer of another
     * @param cover A color image containing an image embedded in its LSB layer
     * @return The image extracted from the LSB layer of {@code cover}
     */
    public static boolean[][] revealBWImage(int[][] cover)
    {
        assert Utils.isImage(cover) : "Not a valid image";
        
        boolean[][] message = new boolean[cover.length][cover[0].length];

        for(int y=0; y<message.length; ++y)
            for(int x=0; x<message[y].length; ++x)
                message[y][x] = getLSB(cover[y][x]);

        return message;
    }

    /*
     * ********************************************
     * Part 2b: embed/reveal
     * BitArray (Text)
     ********************************************
     */

    /**
     * Embeds a boolean array into the LSB layer of a color image, in a linear fashion
     * @param cover The image in which to embed the bit array
     * @param message The boolean array to be embedded
     * @return A <b>copy</b> of {@code cover} with {@code message}'s values embedded in a linear fashion in the LSB layer
     */
    public static int[][] embedBitArray(int[][] cover, boolean[] message)
    {
        assert Utils.isImage(cover) : "Not a valid image";
        
        int height = cover.length;
        int width = cover[0].length;

        int[][] embedded = new int[height][width];

        for(int y=0; y<height; ++y)
            for(int x=0; x<width; ++x)
                embedded[y][x] =
                    (x + y * width < message.length ?
                        embedInLSB(cover[y][x], message[x + y * width]) :
                        cover[y][x]);

        return embedded;
    }

    /**
     * Reveals a boolean array which was embedded in the LSB layer of an image
     * @param cover A color image containing an bit array embedded in its LSB layer
     * @return The bit array extracted from the LSB layer of {@code cover}
     */
    public static boolean[] revealBitArray(int[][] cover)
    {
        assert Utils.isImage(cover) : "Not a valid image";
        
        int height = cover.length;
        int width = cover[0].length;

        boolean[] message = new boolean[height*width];

        for(int y=0; y<height; ++y)
            for(int x=0; x<width; ++x)
                message[y*width + x] = getLSB(cover[y][x]);

        return message;
    }

    /**
     * Embeds a String into the LSB layer of a color image, in a linear fashion
     * @param cover The image in which to embed the bit array
     * @param message The String to be embedded
     * @return A <b>copy</b> of {@code cover} with {@code message}'s binary representation embedded in a linear fashion in the LSB layer
     * @see TextMessage#stringToBitArray(String)
     * @see Steganography#embedBitArray(int[][], boolean[])
     */
    public static int[][] embedText(int[][] cover, String message)
    {
        return embedBitArray(cover, TextMessage.stringToBitArray(message));
    }

    /**
     * Reveals a String which was embedded in the LSB layer of an image
     * @param cover A color image containing a String embedded in its LSB layer
     * @return The String extracted from the LSB layer of {@code cover}
     * @see TextMessage#bitArrayToString(boolean[])
     */
    public static String revealText(int[][] cover)
    {
        return TextMessage.bitArrayToString(revealBitArray(cover));
    }

    /*
     * ********************************************
     * Part 3: embed/reveal bit
     * array with spiral embedding
     ********************************************
     */

    /**
     * Embeds a black and white image into a color image's LSB layer using spiral embedding
     * @param cover The image in which to embed {@code message}
     * @param bwImage The image to embed into {@code cover}
     * @return A <b>copy</b> of {@code cover} with {@code message}'s pixel values embedded in a spiral fashion in the LSB layer
     * @see ImageMessage#bwImageToBitArray(boolean[][])
     * @see Steganography#embedSpiralBitArray(int[][], boolean[])
     */
    public static int[][] embedSpiralImage(int[][] cover, boolean[][] bwImage)
    {
        return embedSpiralBitArray(cover, ImageMessage.bwImageToBitArray(bwImage));
    }

    /**
     * Reveals an image which was embedded in the LSB layer of an image in a spiral fashion
     * @param cover A color image containing an bit array embedded in its LSB layer
     * @return The image extracted from the LSB layer of {@code cover}
     * @see ImageMessage#bitArrayToImage(boolean[])
     * @see Steganography#revealSpiralBitArray(int[][])
     */
    public static boolean[][] revealSpiralImage(int[][] cover)
    {
        return ImageMessage.bitArrayToImage(revealSpiralBitArray(cover));
    }

    /**
     * Embeds a bit array into a color image's LSB layer using linear embedding
     * @param cover The image in which to embed {@code message}
     * @param message The boolean array to embed into {@code cover}
     * @return A <b>copy</b> of {@code cover} with {@code message}'s values embedded in a spiral fashion in the LSB layer
     */
    public static int[][] embedSpiralBitArray(int[][] cover, boolean[] message)
    {
        assert Utils.isCoverLargeEnough(cover, message) : "Message is too big for cover";
        assert Utils.isImage(cover) : "Not a valid image";

        int height = cover.length;
        int width = cover[0].length;

        int[][] embedded = cover.clone();

        int index = 0;

        for(int i=0; index < message.length; ++i)
        {
            for(int x=i; x<width-i; ++x)
                if(index < message.length)
                    embedded[i][x] = embedInLSB(cover[i][x], message[index++]);
    
            for(int y=i+1; y<height-i; ++y)
                if(index < message.length)
                    embedded[y][width-i - 1] = embedInLSB(cover[y][width-i - 1], message[index++]);
    
            for(int x=width-i-2; x>=i; --x)
                if(index < message.length)
                    embedded[height-i-1][x] = embedInLSB(cover[height-i-1][x], message[index++]);
            
            for(int y=height-i-2; y>=i+1; --y)
                if(index < message.length)
                    embedded[y][i] = embedInLSB(cover[y][i], message[index++]);
        }

        return embedded;
    }

    /**
     * Reveals a boolean array which was embedded in the LSB layer of an image in a spiral fashion
     * @param hidden A color image containing an bit array embedded in its LSB layer
     * @return The bit array extracted from the LSB layer of {@code cover}
     */
    public static boolean[] revealSpiralBitArray(int[][] hidden)
    {
        assert Utils.isImage(hidden) : "Not a valid image";
        
        int height = hidden.length;
        int width = hidden[0].length;

        boolean[] array = new boolean[height*width];
        int index = 0;

        for(int i=0; index < array.length; ++i)
        {
            for(int x=i; x<width-i; ++x)
                if(index < array.length)
                    array[index++] = getLSB(hidden[i][x]);

            for(int y=i+1; y<height-i; ++y)
                if(index < array.length)
                    array[index++] = getLSB(hidden[y][width-i-1]);

            for(int x=width-i-2; x>=i; --x)
                if(index < array.length)
                    array[index++] = getLSB(hidden[height-i-1][x]);

            for(int y=height-i-2; y>=i+1; --y)
                if(index < array.length)
                    array[index++] = getLSB(hidden[y][i]);
        }

        return array;
    }

}

