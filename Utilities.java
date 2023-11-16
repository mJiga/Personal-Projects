/**
 * Utility methods for Image Filter project.
 *
 * <p>Call these methods in your solution.
 * Do not modify this file.
 *
 * <p>Copyright 2023 Google LLC,
 * licensed to The University of Texas at El Paso.
 */

import java.awt.image.BufferedImage;

public class Utilities {

  /**
   * Gets a copy of the RGB values of the specified image pixel.
   *
   * @param row coordinate, increases from top to bottom of the image.
   * @param col coordinate, increases from left to right of the image.
   * @param img the image
   * @return length 3 int array, holding the red, green, and blue color value (0-255), respectively.
   */
  public static int[] getRGBArray(int row, int col, BufferedImage img) {
    int p = img.getRGB(col, row);
    int a = (p>>24)&0xff;
    int r = (p>>16)&0xff;
    int g = (p>>8)&0xff;
    int b = p&0xff;
    return new int[]{r, g, b};
  }

  /**
   * Sets / updates the specified image pixel RBG values.
   *
   * @param rgb length 3 int array, holding the red, green, and blue color value (0-255), respectively.
   * @param row coordinate, increases from top to bottom of the image.
   * @param col coordinate, increases from left to right of the image.
   * @param img the image to update
   */
  public static void setRGB(int[] rgb, int row, int col, BufferedImage img) {
    int p = img.getRGB(col, row);
    int a = (p>>24)&0xff;
    p = (a<<24) | (rgb[0]<<16) | (rgb[1]<<8) | rgb[2];
    img.setRGB(col, row, p);
  }
}
