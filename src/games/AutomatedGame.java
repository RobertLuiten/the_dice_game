package games;

import materials.Game;

/**
 * Sets up an automated, standard game of dice!
 * Author: Robert Luiten, June 2nd 2024
 */
public class AutomatedGame extends Game {

    /**
     * Creates an automated game of America's favorite game!
     * @param playerCount The amount of computer players in the game
     */
    public AutomatedGame(int playerCount){
        super(3, 1,1,1,playerCount, 3, 3);
    }

    /**
     * Plays the game 1,000,000 times and returns the amount of
     * games each player has won as an array
     * @return The amount of times each player won the game
     */
    public int[] playOneMillionTimesAndReturnTheResult(){
        int[] result = new int[PLAYER.length + 1];
        for (int i = 0; i < 1000000; i++){
            play();
            if (getWin() == -1){
                result[PLAYER.length]++;
            } else {
                result[getWin()]++;
            }
        }
        return result;
    }

}
