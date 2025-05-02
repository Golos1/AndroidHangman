package com.example.androidhangmanapp.activities;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.os.Bundle;

import com.example.androidhangmanapp.R;
import com.google.android.material.button.MaterialButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.view.View;


import com.example.androidhangmanapp.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import models.GameState;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private CoordinatorLayout activity_main;
    private GameState state;
    private ImageView currentImage;
    private EditText guessBox;
    private TextView previous_guesses;
    private TextView correct_guesses;
    private ConstraintLayout content_main;
    private MaterialButton fab;
    private Button newGame;
    private Random rand = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String word;

        guessBox = (EditText)findViewById(R.id.guess);
        currentImage = (ImageView)findViewById(R.id.currentImage);
        previous_guesses = (TextView)findViewById(R.id.previous_guesses);
        correct_guesses = (TextView)findViewById(R.id.correct_guesses);
        content_main = (ConstraintLayout)findViewById(R.id.content_main);
        activity_main = (CoordinatorLayout)findViewById(R.id.activity_main);

        fab = (MaterialButton)findViewById(R.id.fab);
        newGame = (Button) findViewById(R.id.new_game);
        newGame.setOnClickListener(lambda->{
            startNewGame();
        });
        startNewGame();
    }

    private void startNewGame() {
        String word;
        int wordIndex = rand.nextInt(20) + 1;
        switch (wordIndex){
            case 1:
                word = getResources().getString(R.string.word1);
                break;
            case 2:
                word = getResources().getString(R.string.word2);
                break;
            case 3:
                word = getResources().getString(R.string.word3);
                break;
            case 4:
                word = getResources().getString(R.string.word4);
                break;
            case 5:
                word = getResources().getString(R.string.word5);
                break;
            case 6:
                word = getResources().getString(R.string.word6);
                break;
            case 7:
                word = getResources().getString(R.string.word7);
                break;
            case 8:
                word = getResources().getString(R.string.word8);
                break;
            case 9:
                word = getResources().getString(R.string.word9);
                break;
            case 10:
                word = getResources().getString(R.string.word10);
                break;
            case 11:
                word = getResources().getString(R.string.word11);
                break;
            case 12:
                word = getResources().getString(R.string.word12);
                break;
            case 13:
                word = getResources().getString(R.string.word13);
                break;
            case 14:
                word = getResources().getString(R.string.word14);
                break;
            case 15:
                word = getResources().getString(R.string.word15);
                break;
            case 16:
                word = getResources().getString(R.string.word16);
                break;
            case 17:
                word = getResources().getString(R.string.word17);
                break;
            case 18:
                word = getResources().getString(R.string.word18);
                break;
            case 19:
                word = getResources().getString(R.string.word19);
                break;
            case 20:
                word = getResources().getString(R.string.word20);
                break;
            default:
                word = getResources().getString(R.string.word1);
        }
        state = new GameState(word);
        String underscores = "";
        for(int i = 0; i < word.length(); i++){
            underscores = underscores.concat("_");
        }
        fab.setVisibility(VISIBLE);
        fab.setClickable(true);
        guessBox.setVisibility(VISIBLE);
        newGame.setClickable(false);
        newGame.setVisibility(INVISIBLE);
        guessBox.setText("");
        previous_guesses.setText("");
        correct_guesses.setText(underscores);
        setSupportActionBar(binding.toolbar);
        setOnClickListener();
        activity_main.getRootView().invalidate();
    }

    private void setOnClickListener() {
        fab.setOnClickListener(view -> {
            String guess = guessBox.getText().toString();
            if(guess.contains(" ")){
                guessBox.setHint(R.string.no_spaces);
                return;
            }
            state.guessWord(guess);
            int minusPoints = state.getNegativePoints();
            switch(minusPoints){
                case 0:
                    break;
                case 1:
                    currentImage.setImageResource(R.drawable.word1);
                    break;
                case 2:
                    currentImage.setImageResource(R.drawable.word2);
                    break;
                case 3:
                    currentImage.setImageResource(R.drawable.word3);
                    break;
                case 4:
                    currentImage.setImageResource(R.drawable.word4);
                    break;
                case 5:
                    currentImage.setImageResource(R.drawable.word5);
                    break;
                case 6:
                    currentImage.setImageResource(R.drawable.word6);
                    break;
                case 7:
                    currentImage.setImageResource(R.drawable.word7);
                    break;
                case 8:
                    currentImage.setImageResource(R.drawable.word8);
                    break;
                case 9:
                    currentImage.setImageResource(R.drawable.word9);
                    break;
                case 10:
                    currentImage.setImageResource(R.drawable.word10);
                    fab.setVisibility(INVISIBLE);
                    fab.setClickable(false);
                    guessBox.setVisibility(INVISIBLE);
                    newGame.setVisibility(VISIBLE);
                    newGame.setClickable(true);
                    String restart = "New Game";
                    newGame.setText(restart);
                    currentImage.setImageResource(R.drawable.word0);
                    break;
            }
            String correctGuessString = new String(state.getLetters());
            correct_guesses.setText(correctGuessString);
            if(minusPoints < 10) {
                String newText = previous_guesses.getText() + " " + guess;
                previous_guesses.setText(newText);
            }
            else{
                previous_guesses.setText(R.string.you_lost);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}