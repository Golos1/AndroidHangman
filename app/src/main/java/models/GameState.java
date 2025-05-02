package models;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidhangmanapp.R;

import java.util.Arrays;
import java.util.Random;

public class GameState {
    private int totalGuesses;
    private int negativePoints;
    private String wordToGuess;
    private Random rand = new Random();
    private char[] letters;
    private String string;
    private String[] guesses = new String[10];

    /**
     *
     * @param guesses The total number of guesses already made.
     * @param negative The number of negative points (wrong characters are minus 1 point,
     *                 and wrong words are minus two.
     * @param word the word that needs to be guessed
     * @param lttrs the number of letters that have already been guessed
     */
    public GameState(int guesses, int negative, String word, char[] lttrs){
        negativePoints = negative;
        totalGuesses = guesses;
        wordToGuess = word;
        letters = lttrs;
    }

    /**
     * Initializes the beginning of a game.
     * @param word the word that needs to be guessed.
     */
    public GameState(String word){
        negativePoints = 0;
        totalGuesses = 0;
        wordToGuess = word;
        letters = new char[wordToGuess.length()];
        Arrays.fill(letters, '_');
    }
    public int getTotalGuesses(){
        return totalGuesses;
    }
    public int getNegativePoints(){
        return negativePoints;
    }

    /**
     * Gets the letters that have been guessed so far.
     * @return a char array consisting of all correctly guessed
     */
    public char[] getLetters(){
        return letters;
    }

    public void guessWord(String guess){
        totalGuesses += 1;
        if(guess.length() > 1){
            if(guess.equals(wordToGuess)){
                for (int i = 0; i < wordToGuess.length(); i++){
                    letters[i] = wordToGuess.charAt(i);
                }
            }
            else{
                negativePoints += 2;
            }
        }
        else if (guess.length() == 1){
            int index = wordToGuess.indexOf(guess);
            if( index == -1){
                negativePoints += 1;
            }
            else{
                letters[index] = wordToGuess.charAt(index);
            }
        }
    }
}
