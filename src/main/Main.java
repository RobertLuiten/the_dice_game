package main;

import Games.DICE_2077;
import Games.StandardGame;
import materials.Game;

/**
 * Main file: play a game of dice!
 * Author: Robert Luiten, June 2nd 2024
 */
public class Main {

    /**
     * Change this game to whatever dice game you want to play or test!
     */
    public static final Game game = new DICE_2077(3);

    public static void main(String[] args) {
        int averageWinner = 0;
        for (int i = 0; i < 100; i++){
            System.out.println(game.play());
            averageWinner += game.play();
        }
        System.out.println(averageWinner/100);
    }
}