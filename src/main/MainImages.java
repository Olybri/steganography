package main;

public class MainImages {

    public static void main(String[] args) {

        //TESTING PART 1
        // first test case
        String coverName = "TheStarryNight-hidden";

        // A cover with a hidden image
        int [][] coverWithSecret =  Helper.read("images/TheStarryNight/" + coverName + ".png");

        testRevealImage(coverWithSecret);

        // testing the complete process
        // prepare cover

        coverName = "TheStarryNight";

        int [][] cover  =  Helper.read("images/TheStarryNight/" + coverName + ".png");

        // Prepare message
        String messageName = "calvin-hobbes";
        int[][] message  = Helper.read("images/TheStarryNight/" + messageName  + ".png");


        testHideImage(cover, message);

        // second test case

        String name = "tiles-large";
        cover = Helper.read("images/" + name + ".png");

        message = Helper.read("images/mickey-mouse.png");

        testHideImage(cover, message);

        // END TEST OF PART 1
    }

    public static void testRevealImage(int[][] coverWithEmbedding) {
        Helper.show(coverWithEmbedding, "Cover With hidden image");
        boolean[][] message2 = Steganography.revealBWImage(coverWithEmbedding);
        Helper.show(ImageMessage.toRGB(message2), "Exposed Message");
    }

    public static void testHideImage(int[][] cover, int[][] message) {
        Helper.show(cover, "Cover");
        Helper.show(message, "Message");

        int[][] gray = ImageMessage.toGray(message);

        boolean[][] bw = ImageMessage.toBW(gray, 240);
        Helper.show(ImageMessage.toRGB(bw), "Black and white message");

        int[][] hidden = Steganography.embedBWImage(cover, bw);
        Helper.show(hidden, "Cover with hidden Message");

        boolean[][] message2 = Steganography.revealBWImage(hidden);
        Helper.show(ImageMessage.toRGB(message2), "Exposed Message");
    }
}