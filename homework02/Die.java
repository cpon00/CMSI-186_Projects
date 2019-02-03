import java.util.Random;
public class Die {
    sidesCount = (nSides);
    private int sidesCount;
    private Random randomroll;
    public Die(int nSides) {
//Constructor for a single die with sides numbered 1, 2... up to nSides.
    sidesCount = (nSides);
    }
    public int roll() {
//returns a value as a result of randomly rolling this die.
    randomroll = new Random(nSides);
    return (randomroll);


    }
    public int getValue() {
//returns the current value of this die which resulted from the last roll.
    }
    public int setSides(int nSides) {
//change the configuration of this die and return the new number of sides.
    String setSides = console.readLine("Enter new number of sides:");
    }
    public String toString() {
//Instance method that returns a string-y representation of THIS die, e.g., "11".
    }
    public static String toString() {
//Classwide version of the preceding instance method
    }
    public Static void main( String[] args) {
//The built-in test program for this class.
//TRY TO TEST AT LEAST 10 DIFFERENT CONFIGURATIONS.
    }

}
