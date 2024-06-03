package materials;

import java.util.Random;

/**
 * A class representing a random x sided die
 * Author: Robert Luiten, June 2nd 2024
 */
public class Die {

    /**
     * The number of sides that the die has
     */
    protected final int sides;

    /**
     * Random number generator we will use to roll our die
     */
    protected final Random rand;

    /**
     * Creates a die
     * @param sides The number of sides the die has
     */
    public Die (int sides){
        this.sides = sides;
        rand = new Random();
    }

    /**
     * @return The number of sides the die has
     */
    public int sideCount(){
        return sides;
    }

    /**
     * Rolls the die
     * @return A pseudo-random value between 1 and the number of sides
     */
    public int roll(){
        int result = rand.nextInt();
        return ((result*result) % sides) + 1;
    }

}
