package main;

public class Utils {

    /*
     * ***********************************************************
     * Helper functions
     * ***********************************************************
     */
	
	 /**
     * Checks if a cover image is large enough to embed a sequence of bits 
     * @param cover A 2D integer array
	 * @param message a sequence of bits to embed into the cover  (using LSB)
     * @return {@code true} if the cover is large enough  {@code false} otherwise
     */
    public static boolean isCoverLargeEnough(int[][] cover, boolean[] message) {
        if (!isImage(cover)) return false;

        int coverHeight = cover.length;
        int coverWidth = cover[0].length;

        return (coverWidth * coverHeight >= message.length);
    }

      /**
     * Checks if a cover image is large enough to embed a black and white  image
     * @param cover A 2D integer array
	 * @param message  A 2D boolean array to embed into the cover (using LSB)
     * @return {@code true} if the cover is large enough  {@code false} otherwise
     */
    public static boolean isCoverLargeEnough(int[][] cover, boolean[][] message) {
        return (
                   isImage(cover)
                   && isImage(message)
                   && cover.length >= message.length
                   && cover[0].length >= message[0].length
               );
    }

	 /**
     * Checks if a cover image is large enough to embed another image
	 * (to be further converted to a black and white one)
     * @param cover A 2D integer array
	 * @param message  A 2D integer array to embed into the cover (using LSB after
	 * conversion to black and white)
     * @return {@code true} if the cover is large enough  {@code false} otherwise
     */
    public static boolean isCoverLargeEnough(int[][] cover, int[][] message) {
        return (
                   isImage(cover)
                   && isImage(message)
                   && cover.length >= message.length
                   && cover[0].length >= message[0].length
               );
    }

    /**
     * Checks if an array is a valid image; i.e. if it is rectangular and non-empty
     * @param cover A 2D integer array to be checked
     * @return {@code true} if the array is an image, {@code false} otherwise
     */
    public static boolean isImage(int[][] cover) {
        if (cover == null)
            return false;
        if (cover.length == 0)
            return false;

        int width = cover[0].length;
        if (width == 0)
            return false;

        for (int[] line : cover) {
            if (line.length != width)
                return false;
        }
        return true;
    }
   /**
     * Checks if an array is a valid image; i.e. if it is rectangular and non-empty
     * @param cover A 2D boolean array to be checked
     * @return {@code true} if the array is an image, {@code false} otherwise
     */
    public static boolean isImage(boolean[][] cover) {
        if (cover == null)
            return false;
        if (cover.length == 0)
            return false;

        int width = cover[0].length;
        if (width == 0)
            return false;

        for (boolean[] line : cover) {
            if (line.length != width)
                return false;
        }
        return true;
    }
}
