package main;

public class MainStrings {

    public static void main(String[] args) {
        // some basic tests
        /*testStringConversion("\01");
        testStringConversion("bla bla asdf");
        testStringConversion("bla bla asfg 54%%^&&& 33 asdf");
        testStringConversion("bla bla asfg 54%%^&&& 33 asdfasdf 123k");
        testIntToBitArray(10);
        testIntToBitArray(8);*/

        //TESTING PART 2

        // first test case

        /*String coverName = "AdeleBlochBauer-hidden";

        // A cover with a hidden image
        int [][] coverWithSecret =  Helper.read("images/AdeleBlochBauer/" + coverName + ".png");

        testRevealText(coverWithSecret);*/

        // complete process

        String name = "tiles-large";

        int[][] cover = Helper.read("images/" + name + ".png");

        testHideText(cover, "Hello! This is a super secret hidden message!");

        // END TESTING PART2

    }

    public static void testStringConversion(String message) {
        System.out.println("Message: " + message);
        boolean[] encode = TextMessage.stringToBitArray(message);
        //for(boolean b : encode)
          //  System.out.print((b == true ? 1 : 0) + " ");
        //System.out.println();
        String decode = TextMessage.bitArrayToString(encode);
        System.out.println("Decode:  " + decode);
    }

    public static void testIntToBitArray(int value) {
        System.out.println("Value:  " + value);
        boolean[] encode = TextMessage.intToBitArray(value, 32);
        // System.out.println("Encode: " + encode);
        int decode = TextMessage.bitArrayToInt(encode);
        System.out.println("Decode: " + decode);
    }

    public static void testRevealText(int[][] coverWithEmbedding) {
        Helper.show(coverWithEmbedding, "Cover With hidden text");
        String decode = Steganography.revealText(coverWithEmbedding);
        System.out.println("Decode:  " + decode);
    }

    public static void testHideText(int[][] cover, String message) {

        System.out.println("Message: " + message);
        Helper.show(cover, "Cover");
        int[][] encode = Steganography.embedText(cover, message);
        Helper.show(encode, "Cover with hidden message");
        String decode = Steganography.revealText(encode);
        System.out.println("Decode:  " + decode);
    }
}