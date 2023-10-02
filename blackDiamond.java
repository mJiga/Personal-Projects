import java.util.Scanner;

public class blackDiamond {
  public static void main(String[] args) {
    System.out.print("Enter a positive integer for pattern size: ");
    Scanner scanner = new Scanner(System.in); // create scanner
    int size = scanner.nextInt(); // input from the console using Scanner

    // Nested For loop to generate upper star pattern

    for (int line = 1; line < size + 1; line++){ // place linebreak
      
      for (int space = size; space > line; space--){ // place space
        System.out.print(" ");
      }

        for (int star = 0; star < line; star++){ // place star
          if (star > 0){
          System.out.print("**");
          }else{
          System.out.print("*");
          }
        }
    System.out.println();
    }

    // Nested For loop to generate lower star pattern

    for (int line = size - 1; line + 1 > 1; line--){ // place linebreak
      
      for (int space = size; space > line; space--){ // place space
        System.out.print(" ");
      }

        for (int star = 0; star < line; star++){ // place star
          if (star > 0){
          System.out.print("**");
          }else{
          System.out.print("*");
          }
        }
    System.out.println();
    }
    scanner.close();
  }
}
