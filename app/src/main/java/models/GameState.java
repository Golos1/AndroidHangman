package models;


import com.google.gson.Gson;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GameState {
    private int totalGuesses;
    private int negativePoints;
    private final String wordToGuess;
    private final char[] letters;
    private List<String> previousGuesses = new LinkedList<>();

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
        wordToGuess = word.toLowerCase().replace(" ","");
        letters = lttrs;
    }

    /**
     * Initializes the beginning of a game.
     * @param word the word that needs to be guessed.
     */
    public GameState(String word){
        negativePoints = 0;
        totalGuesses = 0;
        wordToGuess = word.toLowerCase().replace(" ","");
        letters = new char[ 2 * wordToGuess.length()];
        for(int i = 0;i < letters.length; i++){
            if(i % 2 == 0){
                letters[i] = '_';
            }
            else {
                letters[i] = ' ';
            }
        }
    }
    public int getTotalGuesses(){
        return totalGuesses;
    }
    public List<String> getPreviousGuesses(){
        return previousGuesses;
    }

    /**
     * Indicates whether the game is finished or not.
     * @return true if the game is finished, false otherwise
     */
    public boolean isFinished(){
        String victoryString = new String(letters);
        victoryString = victoryString.replace(" ","");
        return (negativePoints >= 10 || victoryString.equals(wordToGuess));
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
        if(!previousGuesses.contains(guess)){
            previousGuesses.add(guess);
        }
        totalGuesses += 1;
        if(guess.length() > 1){
            if(guess.toLowerCase().equals(wordToGuess)){
                for (int i = 0; i < wordToGuess.length(); i++){
                    letters[2 * i] = wordToGuess.charAt(i);
                }
            }
            else{
                negativePoints += 2;
            }
        }
        else if (guess.length() == 1){
            int index = wordToGuess.indexOf(guess.toLowerCase());
            if( index == -1){
                negativePoints += 1;
            }
            else {
                for (int i = 0; i < wordToGuess.length(); i++) {
                    if (guess.charAt(0) == wordToGuess.charAt(i)) {
                        letters[2 * i] = wordToGuess.charAt(i);
                    }
                }
            }
        }
    }

    /**
     * Gets current game state as json string.
     * @param game the current game state
     * @return a json formatted string.
     */
    public static String getJSONFromGame(GameState game){
        Gson gson = new Gson();
        return gson.toJson (game);
    }
    /**
     * Deserializes the json string containing the game state.
     *
     * @param json The serialized json string of the game object
     * @return The GameState object
     */
    public static GameState getGameFromJSON (String json)
    {
        Gson gson = new Gson ();
        return gson.fromJson (json, GameState.class);
    }}
