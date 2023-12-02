import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UtepTube{
    public static void main(String[] args){
        // scanner initialization (only one scanner object)
        Scanner scanner = new Scanner(System.in);

        // file initialization (fileReader)
        String filePath = "/Users/guill/OneDrive/Escritorio/codingprojects/UtepTube/comprehensivelab1/clab1_corpus/corpus.csv"; 
        File myFile = new File(filePath);

        // initialize variables (add song)
        String playlist = "";
        int totalSongs = 0;
        int hours = 0;
        int minutes = 0;
        int seconds = 0;

        // while loop to check for user input
        boolean inputCheck = false;

        while(!inputCheck){ // while loop that is in charge of all the options
            // menu display
            System.out.println("Welcome to UtepTube! Please select an option below to continue:\n\t1. List videos in corpus\n\t2. Add video to playlist\n\t3. View playlist\n\t4. Clear playlist\n\t5. Close UtepTube\n>");
            
            // action variable for option input using scanner
            int action = scanner.nextInt();
            if (action == 5){
                System.out.println("Goodbye!\nSee you soon!");
                inputCheck = !inputCheck;
                scanner.close();
            // display List videos in corpus 
            }else if (action == 1){ 

                try{
                    // fileReader set up
                    Scanner fileReader = new Scanner(myFile);
                    
                    // print header
                    System.out.println("+-----------------------------------------------------------------------------------------------+");
                    System.out.println("|                                        UtepTube corpus                                        |");
                    System.out.println("+-------------+---------------------------------------------------+---------------------+-------+");

                    fileReader.useDelimiter(",|\\n"); // read the comas separating the words
                    while(fileReader.hasNext()){ // run until corpus runs out of words
                        // read and store all data
                        String videoID = fileReader.next(); 
                        String videoTitle = fileReader.next();
                        String creator = fileReader.next();
                        String mm = fileReader.next();
                        String ss = fileReader.next();
                        String preroll = fileReader.next();
                        String midroll = fileReader.next();
                        String postroll = fileReader.next();
                            
                        // print each row with formatting and spacing
                        System.out.print("| " + videoID +  " | " + videoTitle + " | " + creator + " | " + mm + ":" + ss + " |");
                        System.out.println("\n+-------------+---------------------------------------------------+---------------------+-------+");
                    }

                    fileReader.close();
                    
                }catch(FileNotFoundException e){
                    System.out.println("Error - File not found");
                }
            // add song
            }else if (action == 2){ 
                System.out.println("Enter video ID to a add song to playlist...");

                    try{
                        // song variable for song input using scanner
                        String song = scanner.next();

                        // re-initializing fileReader
                        Scanner fileReader = new Scanner(myFile);
                        fileReader.useDelimiter(",|\\n");
                        boolean validID = false; // validID boolean variable to check for correct user input
                        while(fileReader.hasNext()){
                            // read and store all data
                            String videoID = fileReader.next(); 
                            String videoTitle = fileReader.next();
                            String creator = fileReader.next();
                            String mm = fileReader.next();
                            String ss = fileReader.next();
                            String preroll = fileReader.next();
                            String midroll = fileReader.next();
                            String postroll = fileReader.next();

                            if(song.equals(videoID)){ // comparing user input with video ID
                                totalSongs++;
                                validID = true;
                                System.out.println("\n" + videoTitle + "\n" + creator.trim() + "\n" + "Great choice!\n");
                                // conditionals section to determine ads shown
                                    // preroll
                                if (preroll.equals("true")){
                                    preroll = " +30s preroll";
                                    seconds += 30;
                                } else if(preroll.equals("false")){
                                    preroll = "";
                                }
                                    // midroll
                                    if (midroll.equals("true")){
                                        
                                        // promtps user if they want to skip midroll add
                                        System.out.println("Skip the midroll ad? [true/false]");
                                        boolean midrollBoolean = false;
                                        try{
                                            while (!midrollBoolean){
                                                String midrollSkip = scanner.next(); // midrollSkip variable for true/false input using scanner
                                                // skip midroll
                                                if(midrollSkip.equals("true")){
                                                    midroll = " +10s midroll ";
                                                    seconds += 10;
                                                    midrollBoolean = true;
                                                // do not skip midroll
                                                }else if(midrollSkip.equals("false")){
                                                    midroll = " +2m midroll ";
                                                    minutes += 2;
                                                    midrollBoolean = true;
                                                // reprompt user if invalid input
                                                }else{
                                                    System.out.println("Please enter a valid option...");
                                                }
                                            }
                                        }catch(InputMismatchException e){
                                            System.out.println("Invalid Input...");
                                        }
                                    }
                                    else if(midroll.equals("false")){
                                        midroll = " ";
                                    }
                                    //postroll
                                if (postroll.equals("true")){
                                    postroll = "+5s postroll ";
                                    seconds += 5;
                                } else if(postroll.equals("false")){
                                    postroll = "";
                                }

                                // total play time calculation
                                    // convert from String to int with parse method
                                int intMM = Integer.parseInt(mm); 
                                int intSS = Integer.parseInt(ss); 

                                minutes += intMM;
                                seconds += intSS;
                                while (seconds > 59){
                                    minutes++;
                                    seconds -= 60;
                                }
                                while (minutes > 59){
                                    hours++;
                                    minutes -= 60;
                                }

                                // playlist values updates with each iteration
                                if (totalSongs <= 9 && preroll.equals("") && midroll.equals(" ") && postroll.equals("")){
                                    playlist = playlist + " " + totalSongs + ". " + "https://youtu.be/" + videoID + " | " + mm + ":" + ss + " ( no ads )" + "\n";
                                }else if(totalSongs >= 10 && preroll.equals("") && midroll.equals(" ") && postroll.equals("")){
                                    playlist = playlist + totalSongs + ". " + "https://youtu.be/" + videoID + " | " + mm + ":" + ss + " ( no ads )" + "\n"; // fixes double digit spacing
                                }
                                else if(totalSongs <= 9){
                                    playlist = playlist + " " + totalSongs + ". " + "https://youtu.be/" + videoID + " | " + mm + ":" + ss + " (" + preroll + midroll + postroll + ")" + "\n"; 
                                }else if(totalSongs >= 10){
                                    playlist = playlist + totalSongs + ". " + "https://youtu.be/" + videoID + " | " + mm + ":" + ss + " (" + preroll + midroll + postroll + ")" + "\n";
                                }
                            }
                        }
                        // reprompts user if validID is still false after iterating through the previous loops, if true, it does not enter the loop
                        if (!validID){
                            System.out.println("\n" + song + " is not a valid video ID\nReturning to Main menu...");
                        }
                    }catch(FileNotFoundException e){
                    System.out.println("Error - File not found");
                    }
            // view playlist
            }else if (action == 3){ 
                System.out.println("------------- YOUR PLAYLIST ------------");

                // print if playlist is empty
                if (totalSongs <= 0){
                System.out.println("Total play time: 0:00:00");
                }else{ // print if songs added
                System.out.print(playlist);
                // conditionals to fix spacing and added 0 to single digit numbers
                    if(minutes < 10 && seconds < 10){
                        System.out.println("Total play time: " + hours + ":0" + minutes + ":0" + seconds);
                    }else if(minutes > 10 && seconds < 10){
                        System.out.println("Total play time: " + hours + ":" + minutes + ":0" + seconds);   
                    }else if (minutes < 10 && seconds > 10){
                        System.out.println("Total play time: " + hours + ":0" + minutes + ":" + seconds);
                    }else{
                    System.out.println("Total play time: " + hours + ":" + minutes + ":" + seconds);
                    }
                }
                
            // clear playlist
            }else if (action == 4){ 
                boolean clear = false;
                while(!clear){
                    System.out.println("Are you sure you wish to lose your progress?\nEnter [true] to accept\nEnter [false] to return to Main menu\n");
                    // clearScanner variable for true/false input using scanner... String scanner instead of boolean scanner to be able to check for "true" or "false" user input
                    String clearScanner = scanner.next();

                    // clear playlist and return to main menu
                    if(clearScanner.equals("true")){ 
                        playlist = " "; 
                        totalSongs = 0;
                        hours = 0;
                        minutes = 0;
                        seconds = 0;
                        System.out.println("Playlist successfully cleared!");
                        System.out.println("Returning to Main menu...\n");
                        clear = true; 
                    // return to main menu withouth clearing playlist
                    }else if(clearScanner.equals("false")){ 
                        System.out.println("Returning to Main menu...\n");
                        clear = true;

                    //repromt user if invalid input
                    }else{ 
                        System.out.println("Please enter a valid option..."); 
                    }        
                }
            }else{
                System.out.println("Please enter a valid option");
            } 
        }
        scanner.close();
    }
}