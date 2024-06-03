package players;

import materials.Calculate;
import materials.Die;
import materials.Player;
import java.util.Scanner;


/**
 * A human player for a game of dice!
 * Author: Robert Luiten, June 2nd 2024
 */
public class Human extends Player {

    /**
     * Takes user input
     */
    private final Scanner SCAN;

    /**
     * The name of the player
     */
    private final String NAME;

    /**
     * Creates a new human player
     * @param tokenCount The amount of tokens the player will start with
     * @param left The player to their left
     * @param right The player to their right
     * @param calc The calculator for the game
     * @param die The die for the game
     * @param dice The max amount of dice the player can have
     */
    public Human (int tokenCount, Player left, Player right, Calculate calc, Die die, int dice){
        super(tokenCount, left, right, calc, die, dice);
        this.SCAN = new Scanner(System.in);
        System.out.println("Hello there player, what's your name?");
        this.NAME = SCAN.next();
        System.out.println("Okay " + NAME + ", good luck!");
        System.out.println();
    }

    /**
     * Lets the human player "roll" the dies for their turn
     */
    @Override
    public void turn(){
        int initialCount = TOKENS;
        System.out.println("You're up, " + NAME + "! You have " + tokenCount() + " tokens!");
        for (int i = 0; i < super.DICE; i++){
            //Ensures there's enough tokens for all the dies
            if (i + 1 <= initialCount){
                System.out.println("Roll " + NAME + "! You have " + TOKENS + " left...");
                SCAN.next();
                int result = CALC.calculate(DIE.roll());
                if (result == 1){
                    System.out.println("Sorry "+ NAME + ", the person to the left of you gets this one!");
                    giveLeft();
                } else if (result == 2){
                    giveRight();
                    System.out.println("Sorry "+ NAME + ", the person to the right of you gets this one!");
                } else if (result == 3){
                    loseToken();
                    System.out.println("Sorry "+ NAME + ", this token's going to the center!");
                } else {
                    System.out.println("Congrats "+ NAME + "! You're safe... this time...");
                }
            }
        }
        System.out.println("You have " + tokenCount() + " tokens left!");
        System.out.println();
    }

    /**
     * @return The name of the player
     */
    public String getName(){
        return NAME;
    }

}
