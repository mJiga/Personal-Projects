import java.util.Scanner;

public class favoriteFoodGame {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Welcome to my game. You have three attempts to guess my favorite food. Do you want to play? (Yes or No)");

    boolean start = true;
    while(start){
    String yesOrNo = scanner.nextLine();

      if(yesOrNo.equals("Yes")){
        start = !start;
      } else{
        System.out.println("Are you sure? ...");
      }
    }

    System.out.println("I know you want to play...");
    System.out.println("Let's start!");
    System.out.println("---------------------------------------");

    System.out.println("What is my favorite food?");
    System.out.println("Options: Burger, Chicken Wings, Pizza, Steak");

    System.out.println("Write food: ");
    
    int lifes = 3;
    for(; lifes > 0; lifes--){
      String food = scanner.nextLine();


        if (food.equals("Chicken Wings")) {
          System.out.println("You won! Chicken Wings is my favorite food! :)");
          System.out.println("Closing program...");
          break;

        }else{
          System.out.println("Incorrect!");
          System.out.println("-1 life");
        }

        if (lifes > 1) {
          System.out.println("You have " + (lifes - 1) + " left.");
        }
      }
    scanner.close();
    if (lifes == 0){
      System.out.println("You lost! :(");
    }
  }
}

