package games;

import players.Human;
import materials.Die;
import materials.Game;

/**
 * The classic game of dice with human players that we all know and love!
 * Author: Robert Luiten, June 2nd 2024
 */
public class StandardGame extends Game {

    /**
     * Creates a classic game with a 6 sided die and 3 tokens to start
     * @param playerCount The amount of players
     */
    public StandardGame(int playerCount){
        super(3,1,1,1,playerCount,3, 3);
        this.PLAYER = new Human[playerCount];
        Die die = new Die(6);
        for (int i = 0; i < playerCount; i++){
            PLAYER[i] = new Human(TOKEN_COUNT, null, null, CALC, die, MAXDIES);
        }
        PLAYER[0].setLeft(PLAYER[playerCount-1]);
        PLAYER[0].setRight(PLAYER[1]);
        PLAYER[playerCount-1].setLeft(PLAYER[playerCount-2]);
        PLAYER[playerCount-1].setRight(PLAYER[0]);
        for (int i = 1; i < playerCount - 1; i++){
            PLAYER[i].setLeft(PLAYER[i-1]);
            PLAYER[i].setRight(PLAYER[i+1]);
        }
    }

    /**
     * Plays a standard game of dice, reseting the game if it's already been won
     * and printing the scoreboard between every turn
     * @return The number of the player that won, or 0 if it's a draw
     */
    @Override
    public int play(){
        if (getWin() != -1){
            this.resetGame();
        }
        int cur = 0;
        while (getWin() == -2){
            scoreboard();
            PLAYER[cur % PLAYER.length].turn();
            cur++;
        }
        int result = getWin();
        if (result == -1){
            System.out.println("AND IT'S A DRAW!");
        } else {
            Human winner = (Human) PLAYER[result];
            System.out.println("You win " + winner.getName() + "! Final scoreboard: ");
        }
        scoreboard();
        return result;
    }


    /**
     * Resets the game with human players
     */
    @Override
    public void resetGame(){
        int playerCount = this.PLAYER.length-1;
        Die die = new Die(6);
        for (int i = 0; i < playerCount; i++){
            PLAYER[i] = new Human(TOKEN_COUNT, null, null, CALC, die, MAXDIES);
        }
        PLAYER[0].setLeft(PLAYER[playerCount-1]);
        PLAYER[0].setRight(PLAYER[1]);
        PLAYER[playerCount-1].setLeft(PLAYER[playerCount-2]);
        PLAYER[playerCount-1].setRight(PLAYER[0]);
        for (int i = 1; i < playerCount - 1; i++){
            PLAYER[i].setLeft(PLAYER[i-1]);
            PLAYER[i].setRight(PLAYER[i+1]);
        }
    }

    /**
     * Prints out a scoreboard of the current game
     */
    public void scoreboard(){
        for (int i = 0; i < PLAYER.length; i++){
            //This may be dangerous...
            Human cur = (Human) PLAYER[i];
            System.out.println(cur.getName() + " has a score of " + cur.tokenCount() + " tokens!");
        }
        System.out.println();
    }

}
