package com.assignment1files;

import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    public static Scanner scanner = new Scanner(System.in);
    public static Random randomNumber = new Random();

    public static int winCounter;
    public static int loseCounter;
    public static int tieCounter;
    public static int userChoice;
    public static int computerChoice;
    public static String roundsToPlayString;
    public static int whoWins;
    public static int round;
    public static String playAgainString;
    public static boolean playAgainBoolean = true;

    public static void main(String[] args) {
        
        while (playAgainBoolean) {
            System.out.println("");
            System.out.println("Rock Paper Scissors: ");
            System.out.println("");
            System.out.print("How many rounds would you like to play? (Enter a number thats between 1 and 10): ");
            roundsToPlayString = scanner.next();

            if (!validEntry(roundsToPlayString)) {
                System.out.println("The value entered was not a number. The program will now exit.");
                break;
            } else {
                int roundsToPlay = Integer.parseInt(roundsToPlayString);
                if (roundsToPlay < 10 && roundsToPlay > 0) {
                    for (round = 1; round <= roundsToPlay; round++) {
                        userChoice = getUserChoice();
                        computerChoice = generateComputerChoice();
                        whoWins = determineWhoWins(userChoice, computerChoice);
                        printResults(userChoice, computerChoice, whoWins);
                    }

                    System.out.println("Final Results: ");
                    if (winCounter > loseCounter) {
                        System.out.println("You won more than the computer. YOU WIN!");
                    } else if (winCounter < loseCounter) {
                        System.out.println("The computer won more than you. YOU LOSE!");
                    } else if (winCounter == loseCounter) {
                        System.out.println("IT WAS A TIE!");
                    }
                    System.out.println("Total Rounds: " + roundsToPlay + "     Wins: " + winCounter + "        Losses: " + loseCounter + "         Ties: " + tieCounter);
                } else {
                    System.out.println("The number entered was not between 1 and 10. The program will now exit.");
                    break;
                }
            }
            playAgain();
        }
    }

    // ******************************* METHODS ******************************************************
    public static boolean isNumeric(String str) {
        try {
            int i = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean validEntry(String num) {
        return isNumeric(num);
    }

    public static int getUserChoice() {
        boolean choicePicked = false;
        int choice = 0;
        String choiceString;

        while (choicePicked == false) {
            System.out.println("Enter the number for the choice you want:");
            System.out.println("1)Rock      2)Paper     3)Scissors");
            choiceString = scanner.next();

            if (isNumeric(choiceString)) { //if what they entered is a number
                choice = Integer.parseInt(choiceString);
                if (choice > 0 && choice <= 3) {    // and If a valid choice
                    choicePicked = true;
                } else {
                    System.out.println("The number you entered wasnt a valid choice. Try again!");
                    System.out.println("");
                }
            } else {
                System.out.println("What you entered was not a number! Try again.");    //What they entered was not a number
            }
        }
        return choice;
    }

    public static int generateComputerChoice() {
        return randomNumber.nextInt(3) + 1;
    }

    public static int determineWhoWins(int uChoice, int compChoice) {
        int userChoice = uChoice;
        int computerChoice = compChoice;
        int winnerInt; //this is the number thats returned from the method. 1 = user win, 0 = computer win.

        /* The array that determins who wins.
            Rows are the users choices for rock(1), paper(2), scissors(3).
            and the columns are the computer choises for rock(1), paper(2), scissors(3).
            If the value returned from the 2d array is 1 then that means the user has won. If 
            its a 0 then that means the computer wins. */
        int[][] whoWinsArray = {
            {2, 0, 1},
            {1, 2, 0},
            {0, 1, 2}
        };

        winnerInt = whoWinsArray[userChoice - 1][computerChoice - 1];
        return winnerInt;
    }

    public static void printResults(int uChoice, int compChoice, int whoWon) {

        String userChoice = getPlayedAsString(uChoice);
        String computerChoice = getPlayedAsString(compChoice);

        System.out.println("");
        System.out.println("Results:");

        if (whoWins == 0) {     //Computer has won.
            System.out.println("The computer won. You lose! ");
            loseCounter++;
        } else if (whoWins == 1) {
            System.out.println("You win! ");     //User has won.
            winCounter++;
        } else {
            System.out.println("It was a tie! ");
            tieCounter++;
        }
        System.out.println("You had: " + userChoice);
        System.out.println("The computer had: " + computerChoice);
        System.out.println("");
    }

    public static String getPlayedAsString(int choice) {

        String returnString;

        if (choice == 1) {
            returnString = "Rock";
        } else if (choice == 2) {
            returnString = "Paper";
        } else {
            returnString = "Scissors";
        }
        return returnString;
    }

    public static void playAgain() {
        boolean playAgainDetermined = false;
        while (playAgainDetermined == false) {
            System.out.println("");
            System.out.println("Would you like to play again? Enter 1 for yes or 0 for no.");
            playAgainString = scanner.next();
            if (validEntry(playAgainString)) {
                int playAgainInt = Integer.parseInt(playAgainString);
                if (playAgainInt == 0 || playAgainInt == 1) {
                    if (playAgainInt == 1) {
                        playAgainBoolean = true;
                        playAgainDetermined = true;
                    } else {
                        System.out.println("");
                        System.out.println("Thank you for playing!");
                        playAgainBoolean = false;
                        playAgainDetermined = true;
                    }
                } else {
                    System.out.println("The number you entered was not 0 or 1. Try agian.");
                }
            } else {
                System.out.println("What you entered was not valid. Try again.");
            }
        }
    }
}
