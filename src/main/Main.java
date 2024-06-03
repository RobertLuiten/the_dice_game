package main;

import games.AutomatedGame;
import games.StandardGame;
import materials.Game;

/**
 * Main file: play a game of dice!
 * Author: Robert Luiten, June 2nd 2024
 */
public class Main {

    /**
     * Change this to however many players you want to play
     */
    public static int PLAYER_COUNT = 3;

    public static void main(String[] args) {
        Game game = new StandardGame(PLAYER_COUNT);
        game.play();
    }

    /**
     * Cool method to mess around with! It plays an automated
     * 1,000,000 games and returns the average winner!
     */
    public static void findAverageWinner(){
        AutomatedGame game = new AutomatedGame(PLAYER_COUNT);
        int[] results = game.playOneMillionTimesAndReturnTheResult();
        for (int i = 0; i < results.length; i++){
            System.out.println("Player " + (i+1) + " won " + results[i] + " games!");
        }
        System.out.println(results[PLAYER_COUNT] + " games were a draw!");
    }
}