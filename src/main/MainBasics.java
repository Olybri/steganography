
package main;

/**
 * @author
 */
public final class MainBasics {

    public static void main(String[] args) {

        // TESTING Image manipulation tools

        int[][]image = Helper.read("images/mickey-mouse.png");
        testImageConversion(image);
    }

    public static void testImageConversion(int[][] image) {
        Helper.show(image, "RGB image");

        int[][] gray = ImageMessage.toGray(image);

        boolean[][] bw = ImageMessage.toBW(gray, 240);

        Helper.show(ImageMessage.toRGB(bw), "Black and white image");
    }

}