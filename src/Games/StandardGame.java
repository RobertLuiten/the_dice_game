package Games;

import Players.Human;
import materials.Calculate;
import materials.Die;
import materials.Game;
import materials.Player;

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
        this.player = new Human[playerCount];
        Die die = new Die(6);
        for (int i = 0; i < playerCount; i++){
            player[i] = new Human(tokenCount, null, null, calc, die, maxDies);
        }
        player[0].setLeft(player[playerCount-1]);
        player[0].setRight(player[1]);
        player[playerCount-1].setLeft(player[playerCount-2]);
        player[playerCount-1].setRight(player[0]);
        for (int i = 1; i < playerCount - 1; i++){
            player[i].setLeft(player[i-1]);
            player[i].setRight(player[i+1]);
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
        while (getWin() == -1){
            scoreboard();
            player[cur % player.length].turn();
            cur++;
        }
        int result = getWin();
        Human winner = (Human) player[result - 1];
        System.out.println("You win " + winner.getName() + "! Final scoreboard: ");
        scoreboard();
        return result;
    }


    /**
     * Resets the game with human players
     */
    @Override
    public void resetGame(){
        int playerCount = this.player.length-1;
        Die die = new Die(6);
        for (int i = 0; i < playerCount; i++){
            player[i] = new Human(tokenCount, null, null, calc, die, maxDies);
        }
        player[0].setLeft(player[playerCount-1]);
        player[0].setRight(player[1]);
        player[playerCount-1].setLeft(player[playerCount-2]);
        player[playerCount-1].setRight(player[0]);
        for (int i = 1; i < playerCount - 1; i++){
            player[i].setLeft(player[i-1]);
            player[i].setRight(player[i+1]);
        }
    }

    /**
     * Prints out a scoreboard of the current game
     */
    public void scoreboard(){
        for (int i = 0; i < player.length; i++){
            //This may be dangerous...
            Human cur = (Human) player[i];
            System.out.println(cur.getName() + " has a score of " + cur.tokenCount() + " tokens!");
        }
        System.out.println();
    }

}
