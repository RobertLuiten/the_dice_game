package Players;

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
    private final Scanner sc;

    /**
     * The name of the player
     */
    private final String name;

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
        this.sc = new Scanner(System.in);
        System.out.println("Hello there player, what's your name?");
        this.name = sc.next();
        System.out.println("Okay " + name + ", good luck!");
        System.out.println();
    }

    /**
     * Lets the human player "roll" the dies for their turn
     */
    @Override
    public void turn(){
        int initialCount = tokens;
        System.out.println("You're up, " + name + "! You have " + tokenCount() + " tokens!");
        for (int i = 0; i < super.dice; i++){
            //Ensures there's enough tokens for all the dies
            if (i + 1 <= initialCount){
                System.out.println("Roll " + name + "! You have " + tokens + " left...");
                sc.next();
                int result = calc.calculate(die.roll());
                if (result == 1){
                    System.out.println("Sorry "+ name + ", the person to the left of you gets this one!");
                    giveLeft();
                } else if (result == 2){
                    giveRight();
                    System.out.println("Sorry "+ name + ", the person to the right of you gets this one!");
                } else if (result == 3){
                    loseToken();
                    System.out.println("Sorry "+ name + ", this token's going to the center!");
                } else {
                    System.out.println("Congrats "+ name + "! You're safe... this time...");
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
        return name;
    }

}
