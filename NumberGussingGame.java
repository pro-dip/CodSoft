import java.util.Random;
import java.util.Scanner;

public class NumberGussingGame{
    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int minRange = 1;
        int maxRange = 100;
        int attemptsLimit = 5;
        int score = 0;
        boolean playAgain = true;
        System.out.println("**Welcome to Number Guessing Game!**");
        while(playAgain){
            int randomNumber =random.nextInt(maxRange - minRange + 1) + minRange;
            System.out.println("A random number between " + minRange + " and " + maxRange + " has been generated.");
            System.out.println("You have " + attemptsLimit + " attempts to guess the number.");

            for(int attempt=1; attempt<=attemptsLimit; attempt++){
                System.out.println("Attempt " + attempt + " : Enter your guess: ");
                int userGuess = scanner.nextInt();
                
                if(userGuess == randomNumber){
                    System.out.println("Congratulations! You guessed the correct number!");
                    score += attemptsLimit - (attempt-1);
                    break;
                }else if(userGuess < randomNumber){
                    System.out.println("Too low. Try again.");
                }else{
                    System.out.println("Too high. Try again.");
                }

                if(attempt == attemptsLimit){
                    System.out.println("Sorry, you've used all your attempts. The correct number was " + randomNumber);
                }
            }

            System.out.println("Your current score: " + score);
            System.out.println("Do you want to play again? (yes/no): ");
            String playAgainResponse = scanner.next();
            playAgain = playAgainResponse.equalsIgnoreCase("yes");
        }

        System.out.println("Thank you for playing! Your final score: " + score);
        scanner.close();
    }
}