package SteganographyProject.src.main;

public class MainSpiral {

    public static void main(String[] args)
    {
        //TESTING PART 3

        // first test case

        String coverName = "ThePersistenceOfMemory-hidden";

        // A cover with a spiral-encoded  hidden image
        int [][] coverWithSecret =  Helper.read("images/ThePersistenceOfMemory/" + coverName + ".png");

        testRevealImageInSpiral(coverWithSecret);

        String name = "tiles-large";
        int[][]cover = Helper.read("images/" + name + ".png");

        int[][]message = Helper.read("images/mickey-mouse.png");

        testHideSpiral(cover, message);

        // END OF TEST PART 3

    }

    public static void testRevealImageInSpiral(int[][] coverWithEmbedding) {
        Helper.show(coverWithEmbedding, "Cover with Spiral-encoded hidden Message");
        boolean[][] message = Steganography.revealSpiralImage(coverWithEmbedding);
        Helper.show(ImageMessage.toRGB(message), "Exposed Spiral-encoded Message");
    }


    public static void testHideSpiral(int[][] cover, int[][] message) {

        int[][] gray = ImageMessage.toGray(message);
        boolean[][] bw = ImageMessage.toBW(gray, 240);

        int[][] hidden = Steganography.embedSpiralImage(cover, bw);
        Helper.show(hidden, "Cover with Spiral-encoded hidden Message");

        boolean[][] message2 = Steganography.revealSpiralImage(hidden);
        Helper.show(ImageMessage.toRGB(message2), "Exposed Spiral-encoded Message");
    }
}
