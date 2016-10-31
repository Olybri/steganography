package SteganographyProject.src.main;

public class TextMessage
{
    public static final int sizeOfChar = 16;

    /*
     * ********************************************
     * Part 2a: prepare text message (text <-> bit array)
     * ********************************************
     */
    /**
     * Converts an integer to its binary representation
     * @param value The integer to be converted
     * @param bits The number of bits for {@code value}'s binary representation
     * @return A boolean array corresponding to {@code value}'s {@code bits}-bit binary representation
     */
    public static boolean[] intToBitArray(int value, int bits)
    {
        boolean[] bitArray = new boolean[bits];
        for(int i=0; i<bits; ++i)
            bitArray[i] = ((value >> i) & 1) == 1;

        return bitArray;
    }

    /**
     * Converts a bit array to it's integer value
     * @param bitArray A boolean array corresponding to an integer's binary representation
     * @return The integer that the array represented
     */
    public static int bitArrayToInt(boolean[] bitArray)
    {
        int size = bitArray.length;
        int value = 0;

        for(int i=0; i<size; ++i)
            value = (value << 1) + (bitArray[size-1 - i] ? 1 : 0);

        return value;
    }

    /**
     * Converts a String to its binary representation, i.e. the sequence of the 16-bit binary representations of its chars' integer values
     * @param message The String to be converted
     * @return A boolean array corresponding to the String's binary representation
     */
    public static boolean[] stringToBitArray(String message)
    {
        int size = message.length();
        boolean[] bitArray = new boolean[sizeOfChar * size];

        for(int i=0; i<size; ++i)
        {
            boolean[] charBitArray = intToBitArray(message.charAt(i), sizeOfChar);
            System.arraycopy(charBitArray, 0, bitArray, i * sizeOfChar, sizeOfChar);
        }

        return bitArray;
    }

    /**
     * Converts a boolean array to the String of which it is the representation
     * @param bitArray A boolean array representing a String
     * @return The String that the array represented
     * @see TextMessage#stringToBitArray(String)
     */
    public static String bitArrayToString(boolean[] bitArray)
    {
        String message = "";

        for(int i = 0; i < (bitArray.length / sizeOfChar); ++i)
        {
            boolean[] charBitArray = new boolean[sizeOfChar];
            System.arraycopy(bitArray, i * sizeOfChar, charBitArray, 0, sizeOfChar);

            message += (char)bitArrayToInt(charBitArray);
        }

        return message;
    }

}
