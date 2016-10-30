
package SteganographyProject.src.main;

public class MainChocolate {

    public static void main(String[] args) {

        // A  SECRET TEST OF PART 2 :-)

        // first test case
        String coverName = "EasterEggs-hidden";

        // A cover with a hidden image
        int [][] coverWithSecret =  Helper.read("images/EasterEggs/" + coverName + "-2.png");

        testRevealText(coverWithSecret);
    }

    public static String hex(int n) {
        // call toUpperCase() if that's required
        return String.format("0x%4s", Integer.toHexString(n)).replace(' ', '0');
    }

    public static void testRevealText(int[][] coverWithEmbedding) {
        //Helper.show(coverWithEmbedding, "Cover with a secret hidden text");
        String decode = Steganography.revealText(coverWithEmbedding);

        for(int i=0; i<decode.length(); i+=10)
        {
            for(int j = 0; j < Math.min(10, decode.length()-i); ++j)
                System.out.print(hex(decode.charAt(i+j)) + " ");

            System.out.print(" | ");

            for(int j = 0; j < Math.min(10, decode.length()-i); ++j)
                System.out.print(decode.charAt(i+j));

            System.out.println();
        }

        /*for(char c : decode.toCharArray())
            System.out.print(c == '\r' ? "" : c);*/

        //System.out.println("Secret Message:  " + decode);
    }
}