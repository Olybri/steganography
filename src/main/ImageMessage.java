package main;

public final class ImageMessage
{
    /*
     * ********************************************
     * Part 1a: prepare image message (RGB image <-> BW image)
     * ********************************************
     */
    /**
     * Returns red component from given packed color.
     * @param rgb 32-bits RGB color
     * @return an integer between 0 and 255
     * @see #getGreen
     * @see #getBlue
     * @see #getRGB(int, int, int)
     */
    public static int getRed(int rgb)
    {
        return (rgb & 0xFF0000) >> 16;
    }

    /**
     * Returns green component from given packed color.
     * @param rgb 32-bits RGB color
     * @return an integer between 0 and 255
     * @see #getRed
     * @see #getBlue
     * @see #getRGB(int, int, int)
     */
    public static int getGreen(int rgb)
    {
        return (rgb & 0xFF00) >> 8;
    }

    /**
     * Returns blue component from given packed color.
     * @param rgb 32-bits RGB color
     * @return an integer between 0 and 255
     * @see #getRed
     * @see #getGreen
     * @see #getRGB(int, int, int)
     */
    public static int getBlue(int rgb)
    {
        return rgb & 0xFF;
    }

    /**
     * Returns the average of red, green and blue components from given packed color.
     * @param rgb 32-bits RGB color
     * @return an integer between 0 and 255
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     * @see #getRGB(int)
     */
    public static int getGray(int rgb)
    {
        return
            (clampColor(getRed(rgb)) +
            clampColor(getGreen(rgb)) +
            clampColor(getBlue(rgb))) / 3;
    }

    /**
     * @param gray an integer between 0 and 255
     * @param threshold
     * @return true if gray is greater or equal to threshold, false otherwise
     */
    public static boolean getBW(int gray, int threshold)
    {
        return gray > threshold;
    }

    /**
     * @param value any integer
     * @return the value clamped between 0 and 255
     */
    private static int clampColor(int value)
    {
        value = (value > 0xFF ? 0xFF : value);
        value = (value < 0 ? 0 : value);
        return value;
    }

    /**
     * Returns packed RGB components from given red, green and blue components.
     * @param red an integer between 0 and 255
     * @param green an integer between 0 and 255
     * @param blue an integer between 0 and 255
     * @return 32-bits RGB color
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     */
    public static int getRGB(int red, int green, int blue)
    {
        return (clampColor(red) << 16) + (clampColor(green) << 8) + clampColor(blue);
    }

    /**
     * Returns packed RGB components from given grayscale value.
     * @param gray an integer between 0 and 255
     * @return 32-bits RGB color
     * @see #getGray
     */
    public static int getRGB(int gray)
    {
        int value = clampColor(gray);
        return (value << 16) + (value << 8) + value;
    }


    /**
    * Returns packed RGB components from a boolean value.
    * @param value a boolean
    * @return 32-bits RGB encoding of black if value is false
    * and encoding of white otherwise
    */
    public static int getRGB(boolean value)
    {
        return value ? 0xFFFFFF : 0;
    }


    /**
     * Converts packed RGB image to grayscale image.
     * @param image a HxW int array
     * @return a HxW int array
     * @see #encode
     * @see #getGray
     */
    public static int[][] toGray(int[][] image)
    {
        int height = image.length;
        int width = image[0].length;

        int[][] grayImage = new int[height][width];

        for(int y=0; y<height; ++y)
            for(int x=0; x<width; ++x)
                grayImage[y][x] = getRGB(getGray(image[y][x]));

        return grayImage;
    }

    /**
     * Converts grayscale image to packed RGB image.
     * @param channels a HxW float array
     * @return a HxW int array
     * @see #decode
     * @see #getRGB(float)
     */
    public static int[][] toRGB(int[][] gray)
    {
        int height = gray.length;
        int width = gray[0].length;

        int[][] rgbImage = new int[height][width];

        for(int y=0; y<height; ++y)
            for(int x=0; x<width; ++x)
                rgbImage[y][x] = getRGB(gray[y][x]);

        return rgbImage;
    }

    /**
     * Converts grayscale image to a black and white image using a given threshold
     * @param gray a HxW int array
     * @param threshold an integer threshold
     * @return a HxW int array
     */
    public static boolean[][] toBW(int[][] gray, int threshold)
    {
        int height = gray.length;
        int width = gray[0].length;

        boolean[][] bwImage = new boolean[height][width];

        for(int y=0; y<height; ++y)
            for(int x=0; x<width; ++x)
                bwImage[y][x] = getBW(getGray(gray[y][x]), threshold);

        return bwImage;
    }

    /**
     * Converts a black and white image to packed RGB image
     * @param image a HxW boolean array (false stands for black)
     * @return a HxW int array
     */
    public static int[][] toRGB(boolean[][] image)
    {
        int height = image.length;
        int width = image[0].length;

        int[][] rgbImage = new int[height][width];

        for(int y=0; y<height; ++y)
            for(int x=0; x<width; ++x)
                rgbImage[y][x] = getRGB(image[y][x]);

        return rgbImage;
    }

    /*
     * ********************************************
     * Part 3: prepare image message for spiral encoding (image <-> bit array)
     * ********************************************
     */
    /**
     * Converts a black-and-white image to a bit array
     * @param bwImage A black and white (boolean) image
     * @return A boolean array containing the binary representation of the image's height and width (32 bits each), followed by the image's pixel values
     * @see ImageMessage#bitArrayToImage(boolean[])
     */

    public static boolean[] bwImageToBitArray(boolean[][] bwImage)
    {
        int height = bwImage.length;
        int width = bwImage[0].length;

        boolean[] array = new boolean[width*height + 2*Integer.SIZE];

        for(int i=0; i<Integer.SIZE; ++i)
            array[i] = ((height >> i) & 1) == 1;
        for(int i=0; i<Integer.SIZE; ++i)
            array[i+Integer.SIZE] = ((width >> i) & 1) == 1;

        for(int y=0; y<height; ++y)
            for(int x=0; x<width; ++x)
                array[y*width + x + 2*Integer.SIZE] = bwImage[y][x];

        return array;
    }

    /**
     * Converts a bit array back to a black and white image
     * @param bitArray A boolean array containing the binary representation of the image's height and width (32 bits each), followed by the image's pixel values
     * @return The reconstructed image
     * @see ImageMessage#bwImageToBitArray(boolean[][])
     */
    public static boolean[][] bitArrayToImage(boolean[] bitArray)
    {
        int height = 0;
        for(int i=0; i<Integer.SIZE; ++i)
            height = (height << 1) + (bitArray[Integer.SIZE-1-i] ? 1 : 0);
        int width = 0;
        for(int i=0; i<Integer.SIZE; ++i)
            width = (width << 1) + (bitArray[2*Integer.SIZE-1-i] ? 1 : 0);

        boolean[][] image = new boolean[height][width];

        for(int y=0; y<height; ++y)
            for(int x=0; x<width; ++x)
                image[y][x] = bitArray[y*width + x + 2*Integer.SIZE];

        return image;
    }

}
