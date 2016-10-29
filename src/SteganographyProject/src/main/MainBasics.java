
package SteganographyProject.src.main;

/**
 * @author
 */
public final class MainBasics {

    public static void main(String[] args) {

        // TESTING Image manipulation tools

        int[][]image = Helper.read("images/tiles-large.png");
        testImageConversion(image);
    }

    public static void testImageConversion(int[][] image) {
        Helper.show(image, "RGB image");

        int[][] gray = ImageMessage.toGray(image);

        Helper.show(gray, "Gray image");

        boolean[][] bw = ImageMessage.toBW(gray, 128);

        Helper.show(ImageMessage.toRGB(bw), "Black and white image");
    }

}