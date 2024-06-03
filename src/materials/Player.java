package materials;

/**
 * A player class for a standard game of dice
 * Author: Robert Luiten, June 2nd 2024
 */
public class Player {

    /**
     * The amount of tokens the player has
     */
    protected int tokens;

    /**
     * The max amount of dice a player can roll
     */
    protected final int dice;

    /**
     * The players to the left & right of the player
     */
    protected Player left, right;
    /**
     * A calculator to determine the results of rolls
     */
    protected final Calculate calc;

    /**
     * Die which the player will use throughout the game
     */
    protected final Die die;

    /**
     * Creates a new player
     * @param tokenCount The initial token count for the player
     * @param left The player to the left
     * @param right The player to the right
     * @param calc A calculator to find the result of a roll
     * @param die The die which the player will use throughout the game
     * @param dice The max amount of dice a player can roll
     */
    public Player(int tokenCount, Player left, Player right, Calculate calc, Die die, int dice){
        this.tokens = tokenCount;
        this.left = left;
        this.right = right;
        this.calc = calc;
        this.die = die;
        this.dice = dice;
    }

    /**
     * Gives the player another token
     */
    public void getToken(){
        tokens++;
    }

    /**
     * Makes the player give a token to the left player
     */
    protected void giveLeft(){
        loseToken();
        left.getToken();
    }

    /**
     * Makes the player give a token to the right player
     */
    protected void giveRight(){
        loseToken();
        right.getToken();
    }

    /**
     * Removes one of the player's tokens from the game
     */
    protected void loseToken(){
        tokens--;
    }

    /**
     * @return The amount of tokens the player has
     */
    public int tokenCount(){
        return tokens;
    }

    /**
     * @return True if the player has tokens, False otherwise
     */
    public boolean hasToken(){
        return (tokens > 0);
    }

    /**
     * Allows the player to play a turn
     */
    public void turn(){
        for (int i = 0; i < dice; i++){
            //Ensures there's enough tokens for all the dies
            if (i + 1 <= tokens){
                int result = calc.calculate(die.roll());
                if (result == 1){
                    //Gives token to left
                    giveLeft();
                } else if (result == 2){
                    //Gives token to right
                    giveRight();
                } else if (result == 3){
                    //Gives token to center
                    loseToken();
                }
                //If none of these are true, however, the player keeps!
            }
        }
    }

    /**
     * Sets a new left player
     * @param left New left player
     */
    public void setLeft(Player left){
        this.left = left;
    }

    /**
     * Sets a new right player
     * @param right New left player
     */
    public void setRight(Player right){
        this.right = right;
    }

}
