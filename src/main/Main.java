package main;

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
    public static final Game stand = new StandardGame(3);

    public static void main(String[] args) {
        int averageWinner = 0;
        for (int i = 0; i < 100; i++){
            System.out.println(stand.play());
            averageWinner += stand.play();
        }
        System.out.println(averageWinner/100);
    }
}