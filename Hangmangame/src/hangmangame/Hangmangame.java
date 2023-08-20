package hangmangame;

import java.util.Scanner;

public class Hangmangame {

    public static void main(String[] args) {
        String[] words = {"apple", "banana", "orange", "grape", "pear", "watermelon", "strawberry"};
        String word = chooseWord(words);
        int maxAttempts = 6;
        StringBuilder guessedLetters = new StringBuilder();

        System.out.println("Welcome to Hangman!");

        while (maxAttempts > 0) {
            System.out.println("\nWord: " + displayWord(word, guessedLetters));
            char guess = getGuess();
            
            if (guessedLetters.indexOf(String.valueOf(guess)) != -1) {
                System.out.println("You already guessed that letter.");
                continue;
            }

            guessedLetters.append(guess);

            if (word.indexOf(guess) == -1) {
                maxAttempts--;
                System.out.println("Incorrect! Attempts left: " + maxAttempts);
            }

            if (!displayWord(word, guessedLetters).contains("_")) {
                System.out.println("\nCongratulations! You guessed the word: " + word);
                break;
            }
        }

        if (maxAttempts == 0) {
            System.out.println("\nGame Over. The word was: " + word);
        }
    }

    public static String chooseWord(String[] words) {
        int randomIndex = (int) (Math.random() * words.length);
        return words[randomIndex];
    }

    public static String displayWord(String word, StringBuilder guessedLetters) {
        StringBuilder display = new StringBuilder();
        for (char letter : word.toCharArray()) {
            if (guessedLetters.indexOf(String.valueOf(letter)) != -1) {
                display.append(letter);
            } else {
                display.append("_");
            }
        }
        return display.toString();
    }

    public static char getGuess() {
        Scanner scanner = new Scanner(System.in);
        char guess;
        while (true) {
            System.out.print("Guess a letter: ");
            String input = scanner.nextLine().toLowerCase();
            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Please enter a single letter.");
            } else {
                guess = input.charAt(0);
                break;
            }
        }
        return guess;
    }
}

