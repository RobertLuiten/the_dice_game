package materials;

/**
 * A class which can calculate the result of a die roll in
 * a game of dice
 * Author: Robert Luiten, June 2nd 2024
 */
public class Calculate {

    /**
     * The weights for a token to go left, center, right, and to stay
     * respectively, along with the size
     */
    protected final int LEFT, CENTER, RIGHT, KEEP, SIZE;

    /**
     * Creates a new dice result calculator
     * @param keep The weight for a token to
     * @param left The weight for a token to go left
     * @param right The weight for a token to stay in the center
     * @param center The weight for a token to go right
     */
    public Calculate(int keep, int left, int right, int center){
        this.LEFT = left;
        this.RIGHT = right;
        this.CENTER = center;
        this.KEEP = keep;
        this.SIZE = left + right + center + keep;
    }

    /**
     * Calculates what happens to a token after a roll
     * @param roll The roll of the dice
     * @return -1 if the roll size is too big, 0 if it keeps, 1 if it goes left,
     * 2 if it goes right, and 3 if it goes to the center
     */
    public int calculate(int roll){
        if (roll > SIZE){
            return -1;
        }
        if (roll - this.LEFT <= 0){
            return 1;
        } else if (roll - this.LEFT - this.RIGHT <= 0){
            return 2;
        } else if (roll - this.LEFT - this.RIGHT - this.CENTER <= 0){
            return 3;
        }
        return 0;
    }

    /**
     * @return The maximum dice size
     */
    public int maxSize(){
        return this.SIZE;
    }

    /**
     * @return The weights of the dies, in the following order:
     * keep, left, right, center;
     */
    public int[] weight(){
        int[] weight = new int[5];
        weight[0] = this.KEEP;
        weight[1] = this.LEFT;
        weight[2] = this.RIGHT;
        weight[3] = this.CENTER;
        return weight;
    }

}
