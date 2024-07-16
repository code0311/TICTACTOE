package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    boolean playerOneActive;
    private TextView playerOneScore, playerTwoScore, playerStatus;
    private Button[] buttons = new Button[9];
    private Button resetGame, playAgainGame;

    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPosition = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    int rounds;
    private int playerOneScoreCount, playerTwoScoreCount;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerOneScore = findViewById(R.id.score1);
        playerTwoScore = findViewById(R.id.score2);
        playerStatus = findViewById(R.id.textstatus);
        resetGame = findViewById(R.id.reset);
        playAgainGame = findViewById(R.id.playagainbtn);

        buttons[0] = findViewById(R.id.button0);
        buttons[1] = findViewById(R.id.button1);
        buttons[2] = findViewById(R.id.button2);
        buttons[3] = findViewById(R.id.button3);
        buttons[4] = findViewById(R.id.button4);
        buttons[5] = findViewById(R.id.button5);
        buttons[6] = findViewById(R.id.button6);
        buttons[7] = findViewById(R.id.button7);
        buttons[8] = findViewById(R.id.button8);

        for(int i=0; i<buttons.length; i++)
        {
            buttons[i].setOnClickListener(this);

        }

        playerOneScoreCount = 0;
        playerTwoScoreCount = 0;
        playerOneActive = true;
        rounds = 0;


    }

    @Override
    public void onClick(View view) {
        if(!((Button)view).getText().toString().equals(""))
        {
            return;
        }
        else if(checkWinner())
        {
            return;
        }
        String buttonID = view.getResources().getResourceEntryName(view.getId());
        int gameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length()-1,buttonID.length()));

        if(playerOneActive){
            ((Button)view).setText("X");
            ((Button)view).setTextColor(Color.parseColor("#ffc34a"));
            gameState[gameStatePointer] = 0;
        }
        else
        {
            ((Button)view).setText("O");
            ((Button)view).setTextColor(Color.parseColor("#70fc3a"));
            gameState[gameStatePointer] = 1;
        }
    rounds++;

    if(checkWinner())
    {
        if(playerOneActive)
        {
            playerOneScoreCount++;
            updatePlayerScore();
            playerStatus.setText("PLAYER-1 HAS WON");
        }else
        {
            playerTwoScoreCount++;
            updatePlayerScore();
            playerStatus.setText("PLAYER-2 HAS WON");
        }
    }
    else if(rounds==9)
    {
        playerStatus.setText("OOPS! NO WINNER");

    }else
    {
        playerOneActive = !playerOneActive;
    }

    resetGame.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            playAgain();
            playerOneScoreCount = 0;
            playerTwoScoreCount = 0;
            updatePlayerScore();

        }
    });

        playAgainGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 playAgain();
            }
        });



    }

    private boolean checkWinner() {
        boolean winnerResults = false;
        for(int[] winningPosition: winningPosition)
        {
            if(gameState[winningPosition[0]]==gameState[winningPosition[1]]&&
                    gameState[winningPosition[1]]==gameState[winningPosition[2]]&&
                        gameState[winningPosition[0]]!=2)
            {
                winnerResults = true;

            }

        }
        return winnerResults;

    }

    private void playAgain() {
        rounds = 0;
        playerOneActive = true;
        for (int i=0; i<buttons.length; i++)
        {
            gameState[i] = 2;
            buttons[i].setText("");

        }
        playerStatus.setText("status");
    }

    private void updatePlayerScore()
    {
        playerOneScore.setText(Integer.toString(playerOneScoreCount));
        playerTwoScore.setText(Integer.toString(playerTwoScoreCount));
    }
    }