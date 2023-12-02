import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Filters {

  public static void main(String[] args)throws IOException {

    // Read in the image file.
    File f = new File("/Users/guill/OneDrive/Escritorio/UTEP/First Semester (F)/Intro to Computer Science Lab/cs1/comprehensivelab2/clab2_starter/dog.png"); 
    BufferedImage img = ImageIO.read(f);

    // For debugging
    System.out.println("Before:");
    System.out.println(Utilities.getRGBArray(0, 0, img)[0]);
    System.out.println(Utilities.getRGBArray(0, 0, img)[1]);
    System.out.println(Utilities.getRGBArray(0, 0, img)[2]);
    // 92 40 27

    // Apply a filter implementation on img.
    //applyGrayscale(img);
    //applyNorak(img);
    //int[] borderArray = {255, 255, 255};
    //applyBorder(img, 10, borderArray);
    //applyMirror(img);
    //applyBlur(img);
    applyCustom(img);

    // For debugging
    System.out.println("After:");
    System.out.println(Utilities.getRGBArray(0, 0, img)[0]);
    System.out.println(Utilities.getRGBArray(0, 0, img)[1]);
    System.out.println(Utilities.getRGBArray(0, 0, img)[2]);
    // 53 53 53

    // Write the result to a new image file.
    f = new File("/Users/guill/OneDrive/Escritorio/codingprojects/Photo Filter Console App/clab2_starter/dog_filtered.png");
    ImageIO.write(img, "png", f);
  }

  public static void applyGrayscale(BufferedImage img) {
    for(int i = 0; i < img.getHeight(); i++){
      for(int j = 0; j < img.getWidth(); j++){
        int[] RGBArray = Utilities.getRGBArray(i, j, img);
        int average = (RGBArray[0] + RGBArray[1] + RGBArray[2])/3;
        
        RGBArray[0] = average;
        RGBArray[1] = average;
        RGBArray[2] = average; 
          
        Utilities.setRGB(RGBArray, i, j, img);
      }
    }
  }

  public static void applyNorak(BufferedImage img) {
    for(int i = 0; i < img.getHeight(); i++){
      for(int j = 0; j < img.getWidth(); j++){
        int[] RGBArray = Utilities.getRGBArray(i, j, img);
        int average = (RGBArray[0] + RGBArray[1] + RGBArray[2])/3;
        
        RGBArray[0] = average;
        RGBArray[1] = average;
        RGBArray[2] = average; 
        
        if(RGBArray[0] > 153){
          Utilities.setRGB(RGBArray, i, j, img);
        }
      }
    }
  }

  public static void applyBorder(BufferedImage img, int borderThickness, int[] borderColor) {
    for(int i = 0; i < img.getHeight(); i++){
      for(int j = 0; j < img.getWidth(); j++){
        if(borderThickness > i || (img.getHeight() - borderThickness) <= i){
          Utilities.setRGB(borderColor, i, j, img);
        }
        if(borderThickness > j || (img.getWidth() - borderThickness) <= j){
          Utilities.setRGB(borderColor, i, j, img);
        }
      }
    }
  }

  public static void applyMirror(BufferedImage img) {
    for(int i = 0; i < img.getHeight(); i++){
      for(int j = 0; j < img.getWidth()/2; j++){
        int[] first = Utilities.getRGBArray(i, j, img);
        int[] last = Utilities.getRGBArray(i, (img.getWidth() - 1) - j, img);
        Utilities.setRGB(last, i, j, img);
        Utilities.setRGB(first, i, (img.getWidth() - 1) - j, img);
      }
    }
  }

  public static void applyBlur(BufferedImage img) {
    int[][][] blur3D = new int[img.getHeight()][img.getWidth()][3];
    
    for(int i = 1; i < img.getHeight() - 1; i++){
      for(int j = 1; j < img.getWidth() - 1; j++){
        int red = 0;
        int green = 0;
        int blue = 0;

        for(int position1 = -1; position1 <= 1; position1++){
          for(int position2 = -1; position2 <= 1; position2++){
            int[] rgb = Utilities.getRGBArray(i + position1 , j + position2, img);          
            red += rgb[0];
            green += rgb[1];
            blue += rgb[2];
          }
        }

        blur3D[i][j][0] = red / 9;
        blur3D[i][j][1] = green / 9;
        blur3D[i][j][2] = blue / 9;
      }
    }

    for(int a = 1; a < img.getHeight() - 1; a++){
      for(int b = 1; b < img.getWidth() - 1; b++){
        int[] blur1D = blur3D[a][b];
        Utilities.setRGB(blur1D, a, b, img);
      }
    }
  }

  public static void applyCustom(BufferedImage img) {
    int[][][] arr = new int[img.getHeight()][img.getWidth()][3];

    for(int i = img.getHeight() - 2; i >= 0; i--){
      for(int j = img.getWidth() - 2; j >= 0; j--){
        int red = 0;
        int green = 0;
        int blue = 0;

        for(int position1 = 0; position1 <= 1; position1++){
          for(int position2 = 0; position2 <= 1; position2++){
            int[] rgb = Utilities.getRGBArray(i + position1 , j + position2, img);          
            red += rgb[0];
            green += rgb[1];
            blue += rgb[2];
          }
        }
        
      arr[i][j][0] = red;
      arr[i][j][1] = blue;
      arr[i][j][2] = green;
      }
    }
    
    for(int y = 0; y < img.getHeight(); y++){
      for(int x = 0; x < img.getWidth(); x++){
        int[] newArray = arr[y][x];
        Utilities.setRGB(newArray, y, x, img);
      }
    }
  }
}
