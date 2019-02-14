/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  File name     :  DiceSet.java
*  Purpose       :  Provides a class describing a set of dice
*  Author        :  B.J. Johnson
*  Date          :  2017-02-09
*  Description   :  This class provides everything needed (pretty much) to describe a set of dice.  The
*                   idea here is to have an implementing class that uses the Die.java class.  Includes
*                   the following:
*                   public DiceSet( int k, int n );                  // Constructor for a set of k dice each with n-sides
*                   public int sum();                                // Returns the present sum of this set of dice
*                   public void roll();                              // Randomly rolls all of the dice in this set
*                   public void rollIndividual( int i );             // Randomly rolls only the ith die in this set
*                   public int getIndividual( int i );               // Gets the value of the ith die in this set
*                   public String toString();                        // Returns a stringy representation of this set of dice
*                   public static String toString( DiceSet ds );     // Classwide version of the preceding instance method
*                   public boolean isIdentical( DiceSet ds );        // Returns true iff this set is identical to the set ds
*                   public static void main( String[] args );        // The built-in test program for this class
*
*  Notes         :  Stolen from Dr. Dorin pretty much verbatim, then modified to show some interesting
*                   things about Java, and to add this header block and some JavaDoc comments.
*  Warnings      :  None
*  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*  Revision Histor
*  ---------------
*            Rev      Date     Modified by:  Reason for change/modification
*           -----  ----------  ------------  -----------------------------------------------------------
*  @version 1.0.0  2017-02-09  B.J. Johnson  Initial writing and release
* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Random;
public class DiceSet {
    private int count;
    private int sides;
    private Die[] ds = null;
    /**
    * constructor
    * @param  count int value containing total dice count
    * @param  sides int value containing the number of pips on each die
    * @throws IllegalArgumentException if one or both arguments don't make sense
    * @note   parameters are checked for validity; invalid values throw "IllegalArgumentException"
    */
    public DiceSet(int count, int sides) {
        if (count > 1 && sides > 3) {
            ds = new Die [count];
            for (int i = 0; i < count; i++) {
                ds[i] = new Die(sides);
            }
        }else{
            throw new IllegalArgumentException("Invalid DiceSet.");
        }
        this.count = count;
        this.sides = sides;
    }
    /**
    * @return the sum of all the dice values in the set
    */
    public int sum() {
        int diceSum = 0;
        for (int i = 0; i < count; i++) {
            diceSum += ds[i].getValue();
        }
        return diceSum;
    }
    /**
    * Randomly rolls all of the dice in this set
    *  NOTE: you will need to use one of the "toString()" methods to obtain
    *  the values of the dice in the set
    */
    public void roll() {
        for (int i = 0; i < count; i++) {
            ds[i].roll();
            ds[i].toString();
        }
    }
    /**
    * Randomly rolls a single die of the dice in this set indexed by 'dieIndex'
    * @param  dieIndex int of which die to roll
    * @return the integer value of the newly rolled die
    * @throws IllegalArgumentException if the index is out of range
    */
    public int rollIndividual( int dieIndex ) {
        if (dieIndex < 0 || dieIndex > ds.length) {
            throw new IllegalArgumentException("Invalid rollIndividual dieIndex.");
        }
        ds[dieIndex].roll();
        return (ds[dieIndex].getValue());
    }
    /**
    * Gets the value of the die in this set indexed by 'dieIndex'
    * @param  dieIndex int of which die to roll
    * @throws IllegalArgumentException if the index is out of range
    */
    public int getIndividual( int dieIndex ) {
        if (dieIndex < 0 || dieIndex > ds.length) {
            throw new IllegalArgumentException("Invalid getIndividual dieIndex.");
        }
        return (ds[dieIndex].getValue());
    }
    /**
    * @return Public Instance method that returns a String representation of the DiceSet instance
    */
    public String toString() {
        String result = "";
        for (int i = 0; i < count; i++) {
            result += ds[i].toString();
        }
        return result;
    }
    /**
    * @return Class-wide version of the preceding instance method
    */
    public static String toString( DiceSet ds ) {
        return (ds.toString());
    }
    /**
    * @return  true if this set is identical to the set passed as an argument
    */
    public boolean isIdentical( DiceSet ds ) {
        return (this.count == ds.count && this.sides == ds.sides);
    }
    /**
    * A little test main to check things out
    */
    public static void main( String[] args ) {
        DiceSet testDiceSet = new DiceSet(12,6);
        System.out.println("testDiceSet.sides");
        System.out.println(testDiceSet.sides);
        System.out.println("testDiceSet.count");
        System.out.println(testDiceSet.count);
        System.out.println("testDiceSet.roll()");
        testDiceSet.roll();
        System.out.println("testDiceSet.toString()");
        System.out.println(testDiceSet.toString());
        System.out.println("testDiceSet.getIndividual(0)");
        System.out.println(testDiceSet.getIndividual(0));
        System.out.println("testDiceSet.rollIndividual(0)");
        System.out.println(testDiceSet.rollIndividual(0));
        System.out.println("testDiceSet.toString()");
        System.out.println(testDiceSet.toString());
    }
}
