package materials;

/**
 * A class representing generalized game of dice
 * Author: Robert Luiten, June 2nd 2024
 */
public class Game {

    /**
     * The players in the game
     */
    protected Player[] PLAYER;

    /**
     * The calculator that dictates the results of the game
     */
    protected Calculate CALC;

    /**
     * The maximum dies players can roll, and the max amount of tokens they can have
     */
    protected int MAXDIES;

    /**
     * The amount of tokens that each player starts out with initially
     */
    protected int TOKEN_COUNT;

    /**
     * Creates a new game of dice
     * @param keep The weight for a player keeping their token
     * @param left The weight for a token going left
     * @param right The weight for a token going right
     * @param center The weight for a token going center
     * @param playerCount The amount of players for the game
     * @param tokenCount The initial amount of tokens a player has
     * @param maxDies The maximum amount of dies a player can roll
     * @throws IllegalArgumentException If the player count is less than 2 (As that's the
     * minimum you need to play)
     */
    public Game(int keep, int left, int right, int center, int playerCount, int tokenCount, int maxDies){
        if (playerCount < 2){
            throw new IllegalArgumentException();
        }
        this.TOKEN_COUNT = tokenCount;
        this.MAXDIES = maxDies;
        this.PLAYER = new Player[playerCount];
        this.CALC = new Calculate(keep, left, right, center);
        Die die = new Die(left+right+center+keep);
        for (int i = 0; i < playerCount; i++){
            PLAYER[i] = new Player(tokenCount, null, null, CALC, die, maxDies);
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
     * Plays a game of dice, resetting the game if it's already been won
     * @return The number of the player that won, or 0 if it's a draw
     */
    public int play(){
        if (getWin() != -1){
            this.resetGame();
        }
        int cur = 0;
        while (getWin() == -2){
            PLAYER[cur % PLAYER.length].turn();
            cur++;
        }
        return getWin();
    }

    /**
     * Returns the winner of the game
     * @return The winner of the game, -1 if there's a draw, and -2 if no one wins
     */
    protected int getWin(){
        int total = 0;
        int win = -1;
        for (int i = 0; i < PLAYER.length; i++){
            if (PLAYER[i].hasToken()){
                win = i;
                total++;
            }
        }
        if (total > 1) {
            return -2;
        } else if (total == 0){
            return -1;
        }
        return win;
    }

    /**
     * Resets the game to it's initial state
     */
    public void resetGame(){
        Die die = new Die(this.CALC.maxSize());
        int playerCount = this.PLAYER.length-1;
        for (int i = 0; i < playerCount; i++){
            PLAYER[i] = new Player(this.TOKEN_COUNT, null, null, this.CALC, die, this.MAXDIES);
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

}
