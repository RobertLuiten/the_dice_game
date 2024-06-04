package games;

import players.Human;
import materials.Die;
import materials.Game;

/**
 * The year is 2077... computers have come from the future (3077)
 * and now they've taken AHHHHHHHHHHHHHHHHH BEEP BEEP BOOP
 * over the world... and the only way to take them down is by beating them
 * at dice... do you have what it takes mere human?
 * Author: Robert Luiten, June 2nd 2024
 */
public class DICE_2077 extends Game {

    /**
     * Creates a new game of DICE 2077! (Future dice)
     * @param playerCount The amount of NPC for the game
     * @throws IllegalArgumentException If the player count is less than 2 (As that's the
     *                                  minimum you need to play)
     */
    public DICE_2077(int playerCount) {
        super(3, 1, 1, 1, playerCount + 1, 3, 3);
        Die die = new Die(6);
        this.PLAYER[0] = new Human(TOKEN_COUNT, PLAYER[1], PLAYER[playerCount], this.CALC, die, MAXDIES);
        PLAYER[1].setLeft(PLAYER[0]);
        PLAYER[playerCount].setRight(PLAYER[0]);
    }

    /**
     * Plays a standard game of dice, resetting the game if it's already been won
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
            PLAYER[cur % PLAYER.length].turn();
            cur++;
        }
        int result = getWin();
        if (result == -1){
            System.out.println("No one wins I guess.");
        } else {
            if (result == 0){
                System.out.println("You win human! The world is saved!");
            } else {
                System.out.println("BOO!!!");
            }
        }
        return result;
    }

}
