package main;
public class SignatureChecks {

    // Check if the signatures of all required functions are correct
    public static void main(String[] argv) {
        int[][] intArray = new int[0][0];
        boolean[] boolArray = new boolean[0];
        boolean[][] bool2DArray = new boolean[0][0];

        //ImageMessage.java
        ImageMessage.getBlue(0);
        ImageMessage.getRed(0);
        ImageMessage.getGreen(0);
        ImageMessage.getBW(0, 0);
        ImageMessage.getGray(0);

        ImageMessage.getRGB(0,0,0);
        ImageMessage.getRGB(0);
        ImageMessage.getRGB(true);

        ImageMessage.toBW(intArray, 0);
        ImageMessage.toGray(intArray);
        ImageMessage.toRGB(bool2DArray);
        ImageMessage.toRGB(intArray);

        ImageMessage.bitArrayToImage(boolArray);
        ImageMessage.bwImageToBitArray(bool2DArray);

        //TextMessage.java
        TextMessage.bitArrayToInt(boolArray);
        TextMessage.bitArrayToString(boolArray);
        TextMessage.intToBitArray(0, 0);
        TextMessage.stringToBitArray("");

        //Steganography.java
        Steganography.embedInLSB(0, true);
        Steganography.getLSB(0);

        Steganography.embedBitArray(intArray, boolArray);
        Steganography.embedBWImage(intArray, bool2DArray);
        Steganography.embedSpiralBitArray(intArray, boolArray);
        Steganography.embedSpiralImage(intArray, bool2DArray);
        Steganography.embedText(intArray, "");

        Steganography.revealBitArray(intArray);
        Steganography.revealBWImage(intArray);
        Steganography.revealSpiralBitArray(intArray);
        Steganography.revealSpiralImage(intArray);
        Steganography.revealText(intArray);
    }
}
