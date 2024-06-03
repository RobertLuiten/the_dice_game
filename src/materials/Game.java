package materials;

/**
 * A class representing generalized game of dice
 * Author: Robert Luiten, June 2nd 2024
 */
public class Game {

    /**
     * The players in the game
     */
    protected Player[] player;

    /**
     * The calculator that dictates the results of the game
     */
    protected Calculate calc;

    /**
     * The maximum dies players can roll, and the max amount of tokens they can have
     */
    protected int maxDies;

    /**
     * The amount of tokens that each player starts out with initially
     */
    protected int tokenCount;

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
        this.tokenCount = tokenCount;
        this.maxDies = maxDies;
        this.player = new Player[playerCount];
        this.calc = new Calculate(keep, left, right, center);
        Die die = new Die(left+right+center+keep);
        for (int i = 0; i < playerCount; i++){
            player[i] = new Player(tokenCount, null, null, calc, die, maxDies);
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
     * Plays a game of dice, reseting the game if it's already been won
     * @return The number of the player that won, or 0 if it's a draw
     */
    public int play(){
        if (getWin() != -1){
            this.resetGame();
        }
        int cur = 0;
        while (getWin() == -1){
            player[cur % player.length].turn();
            cur++;
        }
        return getWin();
    }

    /**
     * Returns the winner of the game
     * @return The winner of the game, 0 if it's a draw, and -1 if there's no winner
     */
    protected int getWin(){
        int total = 0;
        int win = 0;
        for (int i = 0; i < player.length; i++){
            if (player[i].hasToken()){
                win = i + 1;
                total++;
            }
        }
        if (total > 1) {
            return -1;
        }
        return win;
    }

    /**
     * Resets the game to it's initial state
     */
    public void resetGame(){
        Die die = new Die(this.calc.maxSize());
        int playerCount = this.player.length-1;
        for (int i = 0; i < playerCount; i++){
            player[i] = new Player(this.tokenCount, null, null, this.calc, die, this.maxDies);
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

}
